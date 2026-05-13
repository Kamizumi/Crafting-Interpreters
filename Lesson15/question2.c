//Add short string

typedef enum{
    OBJ_BOUND_METHOD,
    OBJ_CLASS,
    OBJ_CLOSURE,
    OBJ_FUNCTION,
    OBJ_INSTANCE,
    OBJ_NATIVE,
    OBJ_STRING,
    OBJ_SHORT_STRING,
    OBJ_VALUE,
}ObjTyp;

struct ObjShortString{
    Obj obj;
    char chars[8];
    uint32_t hash;
}

Value takeStringValue(char* chars, int length){
    if(length <= SHORT_STRING_MAX){
        Value value = SHORT_STRING_VAL(chars, length);
        FREE_ARRAY(char, chars, length + 1);
        return value;
    }
    return OBJ_VAL(takeString(chars, length))
}