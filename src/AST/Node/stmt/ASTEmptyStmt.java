package AST.Node.stmt;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTEmptyStmt extends ASTStmt {
    @Override
    public String toString() {
        return super.toString() + ";";
    }
}
