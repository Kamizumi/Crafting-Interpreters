//Scanner
class Scanner{
    private static final Map<String, TokenType> keywords;
    static{
        keywords = new HashMap<>();
        keywords.put("and", AND);
        keywords.put("break", BREAK);
        keywords.put("class", CLASS);
        keywords.put("else", ELSE);
        keywords.put("false", FALSE);
        keywords.put("for", FOR);
        keywords.put("fun", FUN);
        keywords.put("if", IF);
        keywords.put("nil", NIL);
        keywords.put("or", OR);
        keywords.put("print", PRINT);
        keywords.put("return", RETURN);
        keywords.put("super", SUPER);
        keywords.put("this", THIS);
        keywords.put("true", TRUE);
        keywords.put("var", VAR);
        keywords.put("while, WHILE)";
        keywords.put("inner", INNER);
    }
}
//GenerateAst.java

"This : Token keyword",
"Inner : Token keyword",

//Parser.java
if (match(THIS)) return new Expr.This(previous());

if(match(INNER)) return new Expr.Inner(previous());

//Interpreter.java
private final java.util.Deque<LoxCallable> innerStack = new java.util.ArrayDeque<>();

void pushInner(LoxCallable iner) {innerStack.push(inner);}
void popInner(){innerStack.pop();}

@Override
public Object visitInnerExpr(Expr.Inner expr){
    if(innerStack.isEmpty()){
        return newLoxCallabe(){
            @Override public int arity() {return 0;}
            @Override public Object call (Interpreter interpreter, java.util.List<Object> arguments){
                return null;
            }
            @Override public String toString() {return "<inner-loop>";}
        };
    }
    return innerStack.peek();
}
//Create new file LoxMethodChain.java
Class LoxMethodChain implements LoxCallable{
    private final List<LoxFunction> chain;
    private final LoxInstance receiver;
    private final int index;

    LoxMethodChain(ListLoxFunction> chain, LoxInstance receiver, int index){
        this.chain = chain;
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public int arity(){
        return chain.get(index).arity();
    }
    @Override
    public Object call(Interpreter interpreter, List<Object> arguments){
        LoxCallable nextInner;

        if(index + 1 < chain.size()){
            nextInner = newLoxMethodChain(chain, receiver, index + 1);
        }else {
            nextInner = new LoxCallable(){
                @Override public int arity() {return 0;}
                @Override public Object call(Interpreter i, List<Object> args) (return null;)
                @Override public String toString() {return "i<inner-loop>";}
            };
        }
        interpreter.pushInner(nextInner);
        try{
            reutrn chain.get(index).bind(receiver).call(interpreter, arguments);
        }finally{
            interpreter.popInner();
        }
    }

    @Override
    public String toString(){
        return "<method-chain #" + index + ">";
    }

}
//LoxInstance.java
java.util.List<LoxFunction> chain = klass.findMethodChain(name.lexeme);
if(!chain.isEmpty()){
    return new LoxMethod(chain, this, 0);
}

throw new RunTimeError(name, "Undefined property " + name.lexeme + ".");

void set(Token name, Object value){
    fields.put(name.lexeme, value);
}
@Override
public String toString(){
    return klass.name + " instance";
}
//Resolver.java
@Override
public Void visitInnerExpr(Expr.Inner expr){
    return null;
}

//AstPrinter.java
@Override
public String visitInnerExpr(Expr.Inner expr){
    return paranthesize("inner");
}
