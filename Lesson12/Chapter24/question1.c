//In VM.c
frame -> ip = ip;
if (!callValue(peek(argCount), argCount)){
    return INTERPRET_RUNTIME_ERROR;
}
frame = &vm.frames[vm.frameCount - 1];
ip = frame->ip;


