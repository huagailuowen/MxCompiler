1. asm中的call,其实只需liveout,不需要livein,因为livein是在call之前的,而call之后的livein是不需要的,因为call之后的livein是call之前的liveout 
而且只是一个重拍
3. f urgent need for the recursive tail Optimize, transform it into a loop is better
4. the inline order first inline small functions. 
5. f will it be better if we prior the s0-s11 ?
6. f coalition of registers
7. load and store optimization
8. f the global variable is not always the best choice, we can use the register to store the value, and only store it when it is necessary
   without the call, only use store to replace
9. close the loop's 2 j in small program
10. bne and beq use to make faster not use beqz(IRBuilder)