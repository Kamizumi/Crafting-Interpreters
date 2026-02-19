//throws runtime error instead of returning infinity/-infinity/NaN when div by zero

case SLASH:
    checkNumberOperands(expr.operator, left, right);

    double divisor = (double) right;

    if(divisor = 0){
        throw new RuntimeError(expr.operator, "Division by zero");

    }
    return (double) left / divisor;