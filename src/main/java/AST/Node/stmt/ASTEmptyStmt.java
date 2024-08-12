package AST.Node.stmt;

import AST.ASTVisitor;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTEmptyStmt extends ASTStmt {
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return super.toString() + ";";
    }
}
