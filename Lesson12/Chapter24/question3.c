//In Object.h

typedef bool (*NativeFn)(int argCount, Value* args);

typedef struct{
    Obj obj;
    NativeFn function;
    ObjNative;
}


//In vm.c
case OBJ_NATIVE: {
    NativeFn native = AS_NATIVE(callee);
    Value result;
    if (!native(argCount, vm.stackTop - argCount, &result)){
        return false;
    }
    vm.stackTop -= argCount + 1;
    push(result)
    return true;
}