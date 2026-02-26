//Edit the AST

defineAs(outputDir, "Stmt", Arrays.asList(
    "Block : List<Stmt> statements",
    "Break : ", //<---
    "Expression : Expr expression",
    "If : Expr condition, Stmt thenBranch, Stmt elseBranch",
    "Print : Expr expression",
    "Var : Token name, Expr intializer",
    "While : Expr condition, Stmt body"
));

//Storing paramters in the fields
String[] fields;
if (fieldList.isEmpty()){
    fields = new String[0];
}else{
    fields = fieldList.split(",");
}

//Adding break keyword to parser
AND, BREAK, CLAS, ELSE, FALSE, FUN, FOR, IF, NIL, OR,
keywords.put("break", BREAK);

//Parsing break statement
if(match(BREAK)) return breakStatement();

//The method
private Stmt breakStatement(){
    consume(SEMICOLON, "EXPECT ';' after break.");
    return new Stmt.Break();
}

//Prevent illegal break in parser
private int loopDepth = 0;

//In the forStatement();
try{
    loopDepth++;
    Stmt body = statement();

    if(increment != null){
        body = new Stmt.Block(Arrays.asList(
            body, newStmt.Expression(increment)));
    }

    if(condition == null) conditin = new Expr.Literal(true);
    body = new Stmt.While(condition, body);

    if(intializer != null){
        body = new Stmt.Block(arrays.asList(initializer, body));
    }
    return body;
}finally {
    loopDepth--;
}

//Put in the whileStaement()

try{
    loopDepth++;
    Expr condition = expression();
    Stmt body = statement();

    return new Stmt.While(condition,body);
}finally {
    loopDepth--;
}

//Update breakStatement
private Stmt breakStatement(){
    if(loopDepth == 0){
        error(previous(), "Must be inside a loop to use break");
    }
    consume(SEMICOLON, "Expect ';' after break");
    return new Stmt.Break();
}

//Implement break exception
@Override
public Void visitBreakStmt(Stmt.Break stmt){
    throw new BreakException();
}

//Catch break in a while loop
@Override
public Void visitWhileStmt(Stmt.While stmt){
    try{
        while(isTruthy(evaluate(stmt.condition))){
            execute(stmt.body);
        }
    }catch (Break Exception ex){
        //Does nothing, just catches exception to break out of the loop

    }
    return null;
}