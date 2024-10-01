1. asm中的call,其实只需liveout,不需要livein,因为livein是在call之前的,而call之后的livein是不需要的,因为call之后的livein是call之前的liveout
2. 而且只是一个重拍
3. getele asm 可以优化
4. the inline order first inline small functions 