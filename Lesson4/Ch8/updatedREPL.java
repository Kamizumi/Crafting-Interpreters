
//Add this to Parse.java
Expr parseExpression(){
    try{
        Expr expr = expression();
        consume(TokenType.EOF, "Expect end of expression.");
        return expr;
    }catch(ParseError error){
        return null;
    }
}

//Add this part to the Intepreter.java
Object evaluateExpressions(Expr expr){
    return evaluate(expr);
}


//This part we'll  use to modify REPL in Lox.java
Scanner scanner = new Scanner(line);
List<Token> tokens = scanner.scanTokens();

Parser parser = new Parser(tokens);

Expr expr = parser.parseExpressions();

if(expr != null && !hadError){
    Object value = interpreter.evaluateExpression(expr);
    System.out.println(interpreter.stringify(value));
}else{
    hadError = false;

    parser = new Parser(tokens);
    List<Stmt> statements = parser.parse();

    if(!hadError){
        interpreter.interpret(statements);
    }

}

hadError = false;