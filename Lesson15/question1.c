#define BINARY_OP(valueType, op)
    do{
        if(!IS_NUMBER(peek(0)) || !IS_NUMBER(peek(1))){
            runtimeError("Operands must be numbers.");
            return INTERPRET_RUNTIME_ERROR;
        }
        double b = AS_NUMBER(pop());
        vm.stackTop[-1] = valueType(AS_NUMBER(vm.stackTop[-1])op b);
    }