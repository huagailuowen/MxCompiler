#!/bin/bash

# Test script that runs make test repeatedly until error or 100 times
# Enhanced to capture segfault and core dump information

max_iterations=10000
current_iteration=0

# Enable core dumps
ulimit -c unlimited
echo "Core dump size limit: $(ulimit -c)"

# Set core dump pattern to current directory
echo "core.%e.%p.%t" | sudo tee /proc/sys/kernel/core_pattern 2>/dev/null || echo "Could not set core pattern (may need sudo)"

echo "Starting continuous test run (max $max_iterations iterations)..."

# Initial build
echo "Building libco and test executable..."
make clean
make libco
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to build libco"
    exit 1
fi

make all
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to build test executable"
    exit 1
fi

while [ $current_iteration -lt $max_iterations ]; do
    current_iteration=$((current_iteration + 1))
    echo "Running iteration $current_iteration/$max_iterations..."
    
    # Run with valgrind for memory error detection
    # if command -v valgrind >/dev/null 2>&1; then
    #     LD_LIBRARY_PATH=../src valgrind \
    #         --tool=memcheck \
    #         --leak-check=no \
    #         --show-reachable=no \
    #         --track-origins=no \
    #         --quiet \
    #         --log-file=valgrind.log \
    #         ./libco-test-64 > test.out 2>&1
    # else
    #     echo "Valgrind not available, running without it..."
    #     LD_LIBRARY_PATH=../src ./libco-test-64 > test.out 2>&1
    # fi
    LD_LIBRARY_PATH=../src ./libco-test-64 > test.out 2>&1
    exit_code=$?
    
    # Check for serious errors (crashes)
    if [ $exit_code -eq 139 ] || [ $exit_code -eq 134 ] || [ $exit_code -eq 11 ] || [ $exit_code -eq 6 ]; then
        echo "ERROR: Test crashed at iteration $current_iteration"
        echo "Exit code: $exit_code"
        
        # Look for core dumps in current directory
        core_files=$(find . -name "core*" -type f -newer test.out 2>/dev/null)
        
        # Also check systemd coredumps
        if command -v coredumpctl >/dev/null 2>&1; then
            echo "Checking systemd coredumps..."
            coredumpctl list libco-test-64 --since="1 minute ago" 2>/dev/null
            
            # Get the latest coredump
            latest_dump=$(coredumpctl list libco-test-64 --since="1 minute ago" --no-legend 2>/dev/null | tail -1 | awk '{print $5}')
            if [ -n "$latest_dump" ]; then
                echo "Found systemd coredump with PID: $latest_dump"
                echo "Extracting coredump..."
                coredumpctl dump $latest_dump > core_systemd_$latest_dump 2>/dev/null
                core_files="$core_files core_systemd_$latest_dump"
            fi
        fi
        
        if [ -n "$core_files" ]; then
            echo "Core dump found!"
            echo "Core dump files: $core_files"
            
            # Analyze with lldb
            if command -v lldb >/dev/null 2>&1; then
                echo "=== LLDB Stack trace ==="
                for core_file in $core_files; do
                    echo "--- Analyzing $core_file with lldb ---"
                    lldb -c "$core_file" ./libco-test-64 -o "bt all" -o "quit" 2>/dev/null
                done
            fi
            
            # Also try with gdb as backup
            if command -v gdb >/dev/null 2>&1; then
                echo "=== GDB Stack trace ==="
                for core_file in $core_files; do
                    echo "--- Analyzing $core_file with gdb ---"
                    gdb -batch -ex "bt" -ex "info registers" -ex "quit" ./libco-test-64 "$core_file" 2>/dev/null
                done
            fi
        else
            echo "No core dump found"
        fi
        
        # Check valgrind for serious errors
        if [ -f "valgrind.log" ]; then
            echo "=== Valgrind serious errors ==="
            grep -E "(SIGSEGV|Segmentation fault|Process terminating|Fatal signal|Invalid)" "valgrind.log"
        fi
        
        # Show test output
        echo "=== Test output ==="
        cat test.out
        
        echo "Check valgrind.log and test.out for details"
        echo "Core dumps preserved for further analysis"
        # Rename core dump files to remove numeric suffixes (e.g., core.XXX.YYY.ZZZ -> core)
        for core_file in *.*.core.*; do
            if [ -f "$core_file" ]; then
                mv "$core_file" core
                echo "Renamed $core_file to core"
                break
            fi
        done
        exit $exit_code
    fi
    
    echo "Iteration $current_iteration completed successfully"
done

echo "All $max_iterations iterations completed successfully!"

exit 0