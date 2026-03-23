//In vm.h

typedef struct {
    Chunk* chunk;
    uint8_t* ip;
  
    Value* stack;
    Value* stackTop;
    int stackCapacity;
  } VM;


//In vm.c
#include <stdlib.h>

void initVM() {
  vm.stackCapacity = 256;
  vm.stack = malloc(sizeof(Value) * vm.stackCapacity);
  resetStack();
}

void freeVM() {
  free(vm.stack);
}

void push(Value value) {
  if (vm.stackTop - vm.stack >= vm.stackCapacity) {
    vm.stackCapacity *= 2;
    vm.stack = realloc(vm.stack, sizeof(Value) * vm.stackCapacity);
    vm.stackTop = vm.stack + vm.stackCapacity / 2;
  }

  *vm.stackTop = value;
  vm.stackTop++;
}