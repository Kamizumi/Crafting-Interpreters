//Add this in the intrepreter
private static Object uninitialized = new Object();

//Change the first line of VisitVarStmt() to this statement
Object value = uninitialized;

//Change the visitVaraibleExpr() method to this:

public Object visitVariableExpr(Expr.Variable expr){
    Object value = environment.get(expr.name);
    if(value == uninitialized){
        throw new RuntimeError(expr.name,
            "Variable must be initialized before use.");
    }
    return value;
}

