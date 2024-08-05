// Generated from Mxparser.g4 by ANTLR 4.13.1
package Grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxparserParser}.
 */
public interface MxparserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxparserParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxparserParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxparserParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MxparserParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MxparserParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(MxparserParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(MxparserParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclarationList(MxparserParser.ParameterDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclarationList(MxparserParser.ParameterDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(MxparserParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(MxparserParser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxparserParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxparserParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(MxparserParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(MxparserParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#atomVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAtomVariableDeclaration(MxparserParser.AtomVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#atomVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAtomVariableDeclaration(MxparserParser.AtomVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#classConstructor}.
	 * @param ctx the parse tree
	 */
	void enterClassConstructor(MxparserParser.ClassConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#classConstructor}.
	 * @param ctx the parse tree
	 */
	void exitClassConstructor(MxparserParser.ClassConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxparserParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxparserParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(MxparserParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(MxparserParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxparserParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxparserParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxparserParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxparserParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxparserParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxparserParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxparserParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxparserParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxparserParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxparserParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStmt(MxparserParser.ExpressionStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStmt(MxparserParser.ExpressionStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableDeclarationStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStmt(MxparserParser.VariableDeclarationStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableDeclarationStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStmt(MxparserParser.VariableDeclarationStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxparserParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxparserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxparserParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxparserParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxparserParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MxparserParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MxparserParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MxparserParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MxparserParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MxparserParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MxparserParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MxparserParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MxparserParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MxparserParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MxparserParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MxparserParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MxparserParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MxparserParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MxparserParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#initalstatement}.
	 * @param ctx the parse tree
	 */
	void enterInitalstatement(MxparserParser.InitalstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#initalstatement}.
	 * @param ctx the parse tree
	 */
	void exitInitalstatement(MxparserParser.InitalstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxparserParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxparserParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#arrayLable}.
	 * @param ctx the parse tree
	 */
	void enterArrayLable(MxparserParser.ArrayLableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#arrayLable}.
	 * @param ctx the parse tree
	 */
	void exitArrayLable(MxparserParser.ArrayLableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#formatStringElement}.
	 * @param ctx the parse tree
	 */
	void enterFormatStringElement(MxparserParser.FormatStringElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#formatStringElement}.
	 * @param ctx the parse tree
	 */
	void exitFormatStringElement(MxparserParser.FormatStringElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxparserParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxparserParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfOpExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSelfOpExpr(MxparserParser.SelfOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfOpExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSelfOpExpr(MxparserParser.SelfOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ternaryExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpr(MxparserParser.TernaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ternaryExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpr(MxparserParser.TernaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(MxparserParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(MxparserParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MxparserParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MxparserParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MxparserParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MxparserParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxparserParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxparserParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code formatStringExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFormatStringExpr(MxparserParser.FormatStringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code formatStringExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFormatStringExpr(MxparserParser.FormatStringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code childExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterChildExpr(MxparserParser.ChildExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code childExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitChildExpr(MxparserParser.ChildExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(MxparserParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(MxparserParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxparserParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxparserParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preOpExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreOpExpr(MxparserParser.PreOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preOpExpr}
	 * labeled alternative in {@link MxparserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreOpExpr(MxparserParser.PreOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIdAtom(MxparserParser.IdAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIdAtom(MxparserParser.IdAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIntAtom(MxparserParser.IntAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIntAtom(MxparserParser.IntAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(MxparserParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(MxparserParser.StringAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterTrueAtom(MxparserParser.TrueAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitTrueAtom(MxparserParser.TrueAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterFalseAtom(MxparserParser.FalseAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitFalseAtom(MxparserParser.FalseAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterThisAtom(MxparserParser.ThisAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitThisAtom(MxparserParser.ThisAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNullAtom(MxparserParser.NullAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNullAtom(MxparserParser.NullAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterArrayAtom(MxparserParser.ArrayAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAtom}
	 * labeled alternative in {@link MxparserParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitArrayAtom(MxparserParser.ArrayAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(MxparserParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(MxparserParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxparserParser#constArray}.
	 * @param ctx the parse tree
	 */
	void enterConstArray(MxparserParser.ConstArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxparserParser#constArray}.
	 * @param ctx the parse tree
	 */
	void exitConstArray(MxparserParser.ConstArrayContext ctx);
}