//Instead of using a hash table, cache the initializer directly
typedef struct ObjClass {
    Obj obj;
    ObjString* name;
    Value initializer;
    Table methods;
}ObjClass;

//Make it nil when we create a new class
ObjClass* newClass(ObjString* name) {
    ObjClass* klass = ALLOCATE_OBJ(ObjClass, OBJ_CLASS);
    klass->name = name;
   klass->initializer = NIL_VAL;
    initTable(&klass->methods);
    return klass;
   }

//When defining methods, if its an initializer method, store it in initializer field
static void defineMethod(ObjString* name) {
    Value method = peek(0);
    ObjClass* klass = AS_CLASS(peek(1));
    tableSet(&klass->methods, name, method);
   if (name == vm.initString) klass->initializer = method;
    pop();
   }

case OBJ_CLASS: {
    ObjClass* klass = AS_CLASS(callee);
    vm.stackTop[-argCount - 1] = OBJ_VAL(newInstance(klass));
    if (!IS_NIL(klass->initializer)) {
    return call(AS_CLOSURE(klass->initializer), argCount);
    } else if (argCount != 0) {
    runtimeError("Expected 0 arguments but got %d.", argCount);
    return false;
   }
}
