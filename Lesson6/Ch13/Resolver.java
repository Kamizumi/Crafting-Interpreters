
//LoxClass.java 
LoxClass(String name, List<LoxClass> superclasses,
    Map<String, LoxFunction> methods) {
this.name = name;
this.superclasses = superclasses;
this.methods = methods;
}

LoxFunction findMethod(String name) {
if (methods.containsKey(name)) {
    return methods.get(name);
}

for (LoxClass superclass : superclasses) {
    LoxFunction method = superclass.findMethod(name);
    if (method != null) return method;
}

return null;
}

//Parser.java
List<Expr.Variable> superclasses = new ArrayList<>();
if (match(LESS)) {
do {
    consume(IDENTIFIER, "Expect superclass name.");
    superclasses.add(new Expr.Variable(previous()));
} while (match(COMMA));
}

//ASTPrinter.java
@Override 
public String visitBreakStmt(Stmt.break stmt) {
return "break";
} 
//Resolver.java
class Resolver implements Expr.visitor<void>, stmt.visitor<void>{
    for(Expr.Variable superclass : stmt.superclasses){
        if(stmt.name.lexeme.equals(superclass.name.lexeme)){
            Lox.error(superclass.name, "A class can't inherit from itself.");
    }
    }
    if (!stmt.superclasses.isEmpty()){
        currentClass = classType.SUBCLASS;
        for(Expr.Variable superclass : stmt.superclasses){
            resolve(superclass);
        }
    }
    if(!stmt.superclasses.isEmpty()){
        beginScope();
        scopes.peek().put("super", true);
    }

    beginScope();
    scopes.peek().put("this", true)

    for(Stmt.Function method : stmt.methods){
        FunctionType declaration = Function.Type.METHOD;
        
        if(method.name.lexeme equals("init")){
            declaration = FunctionType.INITIALIZER;
        }

        resolveFunction(method,declaration);
    }
    endScope();

}

//Stmt.java 
static class Break Extends Stmt{
    Break(){
    }

    @Override
    <R> R accept (Visitor<R> visitor) {
        return visitor.visitBreakStmt(this);
    }
}

static class Class extends Stmt{
    Class(Token name, List<Expr.Variable> superclasses, List<Stmt.Function> methods){
        this.name = name;
        this.superclasses = superclasses;
        this.methods = methods;
    }

    @Override
    <R> R accept (Visitor<R> visitor) {
        return visitor.visitBreakStmt(this);
    }

    final Token name;
    final List<Expr.Variable> superclasses;
    final List<Stmt.Function> methods;
    
}

//ASTPrinter.java
@Override 
public String visitBreakStmt(Stmt.break stmt) {
    return "break";
} 

@Override 
public String visitClassStmt(Stmt.Continue stmt) {
    StringBuilder builder = new StringBuilder();
    builder.append("(class " + stmt.name.lexeme);

    if (!stmt..superclasses.isEmpty()){
        builder.append("<");
        for (Expr.Variable superclass : stmt.superclasses){
            builder.append(" " + print(superclass));
        }
    }
} 