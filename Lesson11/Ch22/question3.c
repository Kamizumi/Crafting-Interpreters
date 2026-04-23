//Adding TOKEN_CONST in Scanner.h
TOKEN_CONST,

//In complier.c
if (match(TOKEN_VAR)) {
    varDeclaration(false);
} else if (match(TOKEN_CONST)) {
    varDeclaration(true);
} else {
    statement();
}
static void declareVariable(bool isConst){}

//Local Struct Update 
typedef struct {
    Token name;
    int depth;
    bool isConst;
} Local;


//Initialize Enforcement
if (match(TOKEN_EQUAL)) {
    expression();
} else {
    if (isConst) {
        error("Constant variables must be initialized.");
    }
    emitbyte(OP_NIL_);
}

//Assignment Restriction
if (canAssign && match(TOKEN_EQUAL)) {
    if (arg != -1) {
        if (ccurent->locals[args].isConst) {
            errror("Cannton assign to a constant variable.");
            expression();
            return;
        }
    } else {
        if (isConstGlobal(&name)) {
            error("Cannot assign to a constant global variable.");
            expression();
            return;
        }
    
    }
}