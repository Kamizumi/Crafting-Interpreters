//In scanner.h
typedef enum{
    TOKEN_TRUE, TOKEN_VAR, TOKEN_WHILE,

    TOKEN_COUNTINUE,

    TOkEN_ERROR, TOKEN_EOF
} TokenType;

//In scanner.c 
static TokenType identifierType() {
    switch (scanner start[0]){
        case 'a': return checkKeyword(1, 2, 'nd', TOKEN_AND);

        case 'c': 
            if(scanner.current - scanner.start > 1){
                switch(scanner.start[1]) {
                    case 'l': return checkKeyword(1, 4, "class", TOKEN_CLASS);
                    case 'o': return checkKeyword(1, 6, "continue", TOKEN_CONTINUE);
                }
            }
            break;
        case 'e': return checkKeyword(1, 3, "else", TOKEN_ELSE);
    }
    return TOKEN_IDENFIFIER;
}


//In compiler.c
int innermostLoopStart = -1;
int innermostLoopScopeDepth = 0;

static void continueStatement();

[TOKEN_COUNTINUE] = {NULL, NULL, PREC_NONE};

static void continueStatement() {
    if (innermostLoopStart == -1) {
        error("Cannot use 'continue' outside of a loop");
    }

    consume(TOKEN_SEMICOLON, "Expect ';' after 'continue'.");

   
    for (int i-current->local-1; i >= 0; i--) {
        if (current->locals[i].depth < innermostLoopScopeDepth) {
            break;
        }
        emitByte(OP_POP);
    }

    emitLoop(innermostLoopStart);
}

static void forStatement(){
    beginScope();

    consume(TOKEN_LEFT_PAREN, "Expect '(' after 'for'.");
    if(match(TOKEN_VAR)){
        varDeclaration();
    } else if (!match(TOKEN_SEMICOLON)){

    } else{
        expressionStatement();
    }

    int surroundingLoopStart = innermostLoopStart;
    int surroundingLoopScopeDepth = innermostLoopScopeDepth;

    innermostLoopStart = currentChunk()->count;
    innermostLoopScopeDepth = current->scopeDepth;
    
    int extiJump = -1;
    if (!match(TOKEN_SEMICOLON)){
        expression();
        consumer(TOKEN_SEMICOLON, "Expect ';' after loop condition.");

        exitJump = emitJump(OP_JUMP_IF_FALSE);
        emitByte(OP_POP); 
    }

    if(!match(TOKEN_RIGHT_PAREN)){
        int bodyJUmp = emitJump(OP_JUMP);
        int incrementStart = currentChunk()->count;
        expression();
        emitByte(OP_POP);
        consume(TOKEN_RIGHT_PAREN, "Expect ')' after for clauses.");

        emitLoop(innermostLoopStart);
        innermostLoopStart = incrementStart;

        patchJump(bodyJump);
    }
    statement();

    emitLoop(innermostLoopStart);

    if (exitJump != -1){
        patchJump(exitJump);
        emitByte(OP_POP);
    }

    innermostLoopStart = surroundingLoopStart;
    innermostLoopScopeDepth = surroundingLoopScopeDepth;
    endScope();
}

//while statement
static void whileStatement() {
    int loopStart  currentChunk()->count;

    int surroundingLoopStart = innermostLoopStart;
    int surroundingLoopScopeDepth = innermostLoopScopeDepth;

    innermostLoopStart = loopStart;
    innermostLoopScopeDepth = current->scopeDepth;

    consume(TOKEN_LEFT_PAREN, "Expect '(' after 'while'.");
    expression();
    consume(TOKEN_RIGHT_PAREN, "Expect ')' after condition.");

    int exitJump = emitJump(OP_JUMP_IF_FALSE);
    emitByte(OP_POP);
    statement();
    emitLoop(loopStart);

    patchJump(exitJump);
    emitByte(OP_POP);

    innermostLoopStart = surroundingLoopStart;
    innermostLoopScopeDepth = surroundingLoopScopeDepth;
}