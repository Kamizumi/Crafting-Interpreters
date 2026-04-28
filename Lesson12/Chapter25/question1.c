//Changes in vm.c


ObjFunction* function = AS_FUNCTION(READ_COSTANT());

if(function -> upvalueCount == 0){
    push(OBJ_VAL(function));
    break;
}

ObjClosure* closure = newClosure(function);
push(OBJ_VAL(closure));

//Update call values
case OBJ_CLOSURE:

    return call(AS_CLOSURE(callee), argCount);

case OBJ_FUNCTION:
    ObjFunction* function = AS_FUNCTION(callee);

    ObjClosure* temp = newClosure(function);

    return call(temp, argCount);


//Update interpret()

if(function -> upvalueCount > 0){
    ObjClosure* closure = newClosure(function);

    pop();
    push(OBJ_VAL(closure));
    call(closure,0);
}else{
    call(newClosure(function), 0);
}