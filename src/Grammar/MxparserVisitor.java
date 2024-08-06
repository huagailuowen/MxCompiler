// Generated from Mxparser.g4 by ANTLR 4.13.1
package Grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxparserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxparserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxparserParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxparserParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MxparserParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MxparserParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclarationList(MxparserParser.ParameterDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclaration(MxparserParser.ParameterDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxparserParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(MxparserParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#atomVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomVariableDeclaration(MxparserParser.AtomVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#classConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassConstructor(MxparserParser.ClassConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MxparserParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(MxparserParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxparserParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxparserParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxparserParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxparserParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxparserParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStmt(MxparserParser.ExpressionStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableDeclarationStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStmt(MxparserParser.VariableDeclarationStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStmt(MxparserParser.EmptyStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxparserParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MxparserParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MxparserParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MxparserParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MxparserParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(MxparserParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(MxparserParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(MxparserParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MxparserParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#arrayLable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLable(MxparserParser.ArrayLableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#formatStringElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatStringElement(MxparserParser.FormatStringElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MxparserParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selfOpExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfOpExpr(MxparserParser.SelfOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ternaryExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpr(MxparserParser.TernaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(MxparserParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(MxparserParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(MxparserParser.AtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MxparserParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code formatStringExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatStringExpr(MxparserParser.FormatStringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code childExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildExpr(MxparserParser.ChildExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(MxparserParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(MxparserParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preOpExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreOpExpr(MxparserParser.PreOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAtom(MxparserParser.IdAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntAtom(MxparserParser.IntAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtom(MxparserParser.StringAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueAtom(MxparserParser.TrueAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseAtom(MxparserParser.FalseAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisAtom(MxparserParser.ThisAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullAtom(MxparserParser.NullAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAtom(MxparserParser.ArrayAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(MxparserParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxparserParser#constArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstArray(MxparserParser.ConstArrayContext ctx);
}