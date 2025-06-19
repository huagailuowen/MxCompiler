## Complement 

1. library function:
- `co_start` : start a coroutine
- `co_yield` : yield control to another coroutine
- `co_resume` : resume a coroutine, detailedly adding the coroutine from global queue to the working queue
- `co_wait` : block until a coroutine finishes

2. schedule feature:
- every 64 local scheduling cycles or have no coroutine to run, the scheduler will try to fetch tasks in the global queue or steal at most half of the task from another thread, if still no coroutine to run, it will sleep, wait to be woken up by other threads.

- every coroutine will have a priority, which will increase after each working cycle, when it is too low, it will be moved to the end of the global queue, so that it will not block other coroutines.



## Debug

```
ulimit -c unlimited
cd test
zsh test.sh
make libco all
LD_LIBRARY_PATH=../src lldb libco-test-64


LD_LIBRARY_PATH=../src ./libco-test-64  



dot -Tpng graph.out -o graph.png
```
