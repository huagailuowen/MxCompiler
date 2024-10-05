1. asm中的call,其实只需liveout,不需要livein,因为livein是在call之前的,而call之后的livein是不需要的,因为call之后的livein是call之前的liveout
2. 而且只是一个重拍
3. urgent need for the recursive tail Optimize, transform it into a loop is better
4. the inline order first inline small functions. 
5. will it be better if we prior the s0-s11 ?
6. coalition of registers
7. load and store optimization