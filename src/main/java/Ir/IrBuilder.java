package Ir;


import AST.ASTVisitor;
import Ir.Node.IRNode;
import Scope.Scope;
//implements ASTVisitor<IRNode>
public class IrBuilder {
  Counter counter;
}

class Counter {
  Scope globalScope;
  Scope currentScope;
}
