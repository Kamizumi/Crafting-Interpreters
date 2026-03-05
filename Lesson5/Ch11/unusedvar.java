//Chapter 11 Challenge Question 3

private final Stack<Map<String, Variable>> scopes = new Stack<>();
private enum VariableState {
    DELCARED, 
    DEFINE, 
    USED
}

private static class Variable {
    final Token name;
    VariableState state;

    Variable(Token name, VariableState state){
        this.name = name;
        this.state = state;
    }
}

private void beginScope() {
    scopes.push(new HashMap<String, Variable>());
}

private void endScope() {
    Map<String, Variable> scope = scopes.peek();

    for (Map.Entry<String, Variable> entry : scope.entrySet()) {
        if (entry.getValue().state != VariableState.USED) {
            Lox.error(entry.getValue().name, "Local variable is never used");
        }
    }

    scopes.pop();
}

private void declare(Token name) {
    if (scopes.isEmpty()) return;

    Map<String, Variable> scope = scopes.peek();
    if (scope.containsKey(name.lexme)) {
        Lox.error(name, "Already a variable with this name in this scope.");
    }
    scope.put(name.lexme, new Variable(name, VariableState.DECLARED));
}

private void define(Token name) {
    if (scope.isEmpty()) return;
    scopes.peek().get(name.lexme).state = VariableState.DEFINED;
}

private void resolveLocal(Expr expr, Token name) {
    for (int i = scopes.size() - 1; i >= 0; i--) {
        if (scopes.get(i).containsKey(name.lexme)){
            scopes.get(i).get(name.lexme).state = VariableState.USED;
            interpreter.resolve(expr, scopes.size() -1 - i);
            return; 
        }
    }
}