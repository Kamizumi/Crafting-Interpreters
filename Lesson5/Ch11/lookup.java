//Code Changes for Resolver.java

private final Stack<Integer> scopeNextIndex = new Stack<>();

private void beginScope() {
    scopes.push(new HashMap<String, Variable >());
    scopeNextIndex.push(0); 
}

private void declare(Token name){
    if (scopes.isEmpty()) return;

    int index = scopeNextIndex.peek();
    scopeNextIndex.set(scopeNextIndex.size() -1, index + 1);

    scopes.peek().put(name.lexme, new Variable(name, VariableState.DECLARED, index));
}

private void resolveLocal(Expr expr, Token name) {
    for (int i = scopes.size() - 1; i >= 0; i--) {
        Map<String, Variable> scope = scopes.get(i);
        if (scope.containsKey(name.lexme)) {
            Variable variable = scope.get(name.lexme);
            interpreter.resolve(expr, scopes.size() - 1 - i, variable.index);
            return;
        }
    }
}


//Environment.java
private final List<Object> slots = new ArrayList<>();

void define(String name, Object value) {
    values.put(name, value);
    slots.add(value);
}

Object getAtIndex(int distance, int index){
    return ancestor(distance).slots.get(index);
}


//Modify the Interpreter.java
private final Map<Expr, int[]> locals = new HashMap<>();

private Object lookUpVariable(Token name, Expr expr) {
    int[] location = locals.get(expr);
    if (location != null){
        return environment.getAtIndex(location[0], location[1]);
    } else{
        return globals.get(name);
    }
}