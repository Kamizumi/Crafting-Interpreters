//In vm.c
case OP_NEGATE: 
        vm.stackTop[-1] = -vm.stackTop[-1];
        break;

static InterpretResult run(void) {
#define READ_BYTE() (*vm.ip++)
#define READ_CONSTANT() (vm.chunk->constants.values[READ_BYTE()])
#define BINARY_OP(op) \
    do { \
      vm.stackTop[-2] = vm.stackTop[-2] op vm.stackTop[-1]; \
      vm.stackTop--; \
    } while (false)
