//Parser.java changes
List<Stmt.Function> classMethods = new ArrayList<>();
while(!check(RIGHT_BRACE) && !isAtEnd()){
    if(match(CLASS)){
        classMethods.add(function("method"));
    }else{
        methods.add(function("method"));
    }
}
return new Stmt.Class(name, superclass, methods, classMethods);

//Stmt.java -> AST node carries both lists

static class Class extends Stmt{
    Class(Token name, expr.Variable superclass,
        List<Stmt.Function> methods,
        List<Stmt.Function> classMethods) {...}
    final List<Stmt.Function> methods;
    final List<Stmt.Functino> classMethods;
}

//Lox.java -> AST node carries both lists
class LoxClass extends LoxInstance implements LoxCallable {...}

private LoxClass(String name, LoxClass superclass, Map<String, LoxFunction> methods){
    super(null);
}

LoxClass(String name, Lox Class supercass, Map<String, LoxFunction> methods, Map<String, LoxFunction> classMethods){
    super(new LoxClass(name +" metaclass", null, classMethods));
}

//Interpreter.java 

Map<String, LoxFunction> classMethods = new HashMap<>();
for(Stmt.Function method: stmt.classMethods){
    LoxFunction function = new LoxFunction(method, environment, false);
    classMethods.put(method.name.lexeme, function);
}

LoxClass klass = newLoxClass(stmt.name.lexeme, (LoxClass)superclass, methods, classMethods);


//no changes made to visitGetExpr

public Object visitGetExpr(Expr.Get expr){
    Object object = evaluate(expr.object);
    if(object instanceof LoxInstance){
        return ((LoxInstance) object.get(expname));
    }
    throw new RuntimeError(...);
}

//Resolver.java resolve static methods
beginScope();
scopes.peek().put("this", true);

for(Stmt.Function method : stmt.methods){... resolveFunction(...)}

endScope();

for(Stmt.Function method : stmt.classMethods){
    resolveFunction(method, FunctionType.METHOD);
}

