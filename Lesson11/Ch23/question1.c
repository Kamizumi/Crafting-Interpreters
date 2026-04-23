//Add tokens to scanner.h
TOKEN_CASE, TOKEN_DEFAULT, TOKEN_SWITCH, TOKEN_WHILE,

//Single-character tokens 
typedef enum {
    TOKEN_LEFT_PAREN, TOKEN_RIGHT_PAREN,
    TOKEN_LEFT_BRACE, TOKEN_RIGHT_BRACE,
    TOKEN_COMMA, TOKEN_DOT, TOKEN_MINUS, TOKEN_PLUS,
    TOKEN_SEMICOLON, TOKEN_SLASH, TOKEN_STAR,
}

//In compiler.c
static void statement(){
    if (match(TOKEN_PRINT)){
        printStatement();
    } else if (match(TOKEN_SWITCH)){
        switchStatement();
    } else if (match(TOKEN_LEFT_BRACE)){
        beginScope();
    }
}

static TokenType identifierType() {
    switch (scanner start[0]){
        case 'a': return checkKeyword(1, 2, "nd", TOKEN_AND);
        case 'c': 
            if(scanner.current - scanner.start > 1){
                if (scanner.start[1]){
                    case 'l': return checkKeyword(1, 4, "lass", TOKEN_CLASS);
                    case 'a': return checkKeyword(1, 3, "ase", TOKEN_CASE);
                }
        }
       break;
    case 'd': return checkKeyword(1, 6, "default", TOKEN_DEFAULT);
    }
}

//in scanner.c
switch(c){
    case '(': return makeToken(TOKEN_LEFT_PAREN);
    case ')': return makeToken(TOKEN_RIGHT_PAREN);
    case '{': return makeToken(TOKEN_LEFT_BRACE);
    case '}': return makeToken(TOKEN_RIGHT_BRACE);
    case ',': return makeToken(TOKEN_COMMA);
    case ';': return makeToken(TOKEN_SEMICOLON);
    case ':': return makeToken(TOKEN_COLON);

}

//switchStatement() implementation in compiler.c
static void switchStatement() {
    consume(TOKEN_LEFT_PAREN, "Expect '(' after 'switch'.");
    expression();
    consume(TOKEN_RIGHT_PAREN, "Expect ')' after value.");
    consume(TOKEN_LEFT_BRACE, "Expect '{' before switch cases.");

    int state = 0; 
    int caseEnds[MAX_CASES];
    int caseCount = 0;
    int previousCaseSkip = -1;

    while (!match(TOKEN_RIGHT_BRACE) && !check(TOKEN_EOF)){
        if (match(TOKEN_CASE) || match(TOKEN_DEFAULT)){
            TokenType = caseType = parser.previous.type;

            if(state == 2){
                error("Can't have case after default.");
            }

            if (state == 1){
                caseEnds[caseCount++] = emitJump(OP_JUMP);

                patchJump(previousCaseSkip);
                emitByte(OP_POP);
            }

            if (caseType = TOKEN_CASE){
                state = 1;

                emitByte(OP_POP);
                expression();

                consume(TOKEN_COLON, "Expect ':' after case value.");
                previousCaseSkip = emitJump(OP_JUMP_IF_FALSE);

                emitByte(OP_POP);
            } else{
                state = 2;
                consume(TOKEN_COLON, "Expect ':' after default.");
                previousCaseSkip = -1;
            } 
        } else{
            if (state == 0){
                error("Expect case or default.");
            }
            statement();
        } 
    }
}