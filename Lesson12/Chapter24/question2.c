//In vm.c
case OBJ_NATIVE: {
    ObjNative* native = AS_NATIVE_OBJ(callee);

    if(argCount != native -> arity){
        runtimeError("Expected %d arguments but got %d.", native -> arity, argCount);
        return false
    }
}


//In object.h

typdef struct{
    Obj obj;
    NativeFn function;
    int arity;
    ObjNative;
}
