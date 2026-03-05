//Modify AST, parser, and interpreter

//AST, add this in expr.java
static class Function extends Expr{

    final List<Token> parameters;
    final List<Stmt> body;

    Function(List<Token> parameters, List<Stmt> body){
        this.parameters = parameters;
        this.body = body;
    }

    <R> R accept(Visitor<R> visitor){
        return visitor.visitFunctionExpr(this);
    }
}

//Parser, modify primary()
if (match(FUN)) return functionExpression();

//implement 
private Expr functionExpression(){
    consume(LEFT_PAREN, "Expect '(' after 'fun'.");

    List<Token>param = new ArrayList<>();
    if (!check(RIGHT_PAREN)){
        do{
            if(param.size() >= 255){
                error(peek(), "Can't have more than 255 parameters");
            }

            param.add(consume(IDENTIFIER, "Expect parameter name."));
        } while (match(COMMA));
    }
    consume(RIGHT_PAREN, "Expect ')' after parameters.");
    consume(LEFT_BRACE, "Expect '{' before function body.");

    List<Stmt> body = block();
    return new Expr.Function(param,body);   
}

//In Interpreter.java implement
@Override
public Object visit FunctionExpr(Expr.Function expr){
    return new LoxFunction(expr, environment);
}
