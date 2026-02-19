@Override
public Object visitBinaryExpr(Expr.Binary expr) {
    Object left = evlauate(expr.left);
    Object right = evaluate(expr.right);

    switch(expr.operator.type){
        case PLUS:
            //Adding numbers
            if(left instanceof Double && right instanceof Double){
                return(Double) left + (Double) right;
            }

            //If one is a string then we concatenate
            if(left instanceof String || right instanceof String){
                return stringify(left + stringify(right));
            }

            throw new RuntimeError(expr.operator,
                "Operands must be two numbers or at least one string.");
    }

    return null;
}