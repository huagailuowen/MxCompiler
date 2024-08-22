import os
import re
import subprocess

test_file = []
testcases_folder = r'/mnt/d/周宸源/大学/学习/程序/MxCompiler/testcases/codegen'

def traverse_directory(dir_path):
    for root, dirs, files in os.walk(dir_path):
        for name in files:
            if name.endswith('.mx') or name.endswith('.mt'):
                test_file.append(os.path.join(root, name))

traverse_directory(testcases_folder)

def extract_input_output_exitcode(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()
    input_regex = r'Input:\n=== input ===\n(.*?)\n=== end ==='
    output_regex = r'Output:\n=== output ===\n(.*?)\n=== end ==='
    exitcode_regex = r'ExitCode: (.+)'
    input_match = re.search(input_regex, content, re.DOTALL)
    output_match = re.search(output_regex, content, re.DOTALL)
    exitcode_match = re.search(exitcode_regex, content)
    if input_match:
        input_data = input_match.group(1).strip()
    else:
        input_data = ""
    if output_match:
        output_data = output_match.group(1).strip()
    else:
        output_data = ""
    if exitcode_match:
        exitcode = exitcode_match.group(1).strip()
    else:
        exitcode = ""
    return content, input_data, output_data, exitcode


red_msg = "\033[31m{msg}\033[0m"
green_msg = "\033[32m{msg}\033[0m"

for testcase in test_file:
    try:
        content, input_data, output_data, exitcode = extract_input_output_exitcode(testcase)
        temp = open('test.in', 'w')
        temp.write(input_data)
        temp.flush()
        commands = 'make run'
        process = subprocess.Popen(commands, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, shell=True)
        _, _ = process.communicate(input=content)
        compile_status = process.returncode
        if compile_status != 0:
            raise Exception("Compile Error")
        process.terminate()
        commands = 'clang -m32 src/test/mx/output.ll src/test/mx/builtin.ll -o test'
        process = subprocess.Popen(commands, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, shell=True)
        stdout, _ = process.communicate(input="")
        compile_status = process.returncode
        if compile_status != 0:
            raise Exception("Bin Compile Error")
        process.terminate()
        commands = './test'
        process = subprocess.Popen(commands, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, shell=True)
        stdout, _ = process.communicate(input=input_data)
        print(testcase, green_msg.format(msg="output") if stdout.strip() == output_data else red_msg.format(msg="output"),
                        green_msg.format(msg="retcode") if process.returncode == int(exitcode.strip()) else red_msg.format(msg="retcode"))
        process.terminate()
    except Exception as e:
        print(testcase, red_msg.format(msg=e))