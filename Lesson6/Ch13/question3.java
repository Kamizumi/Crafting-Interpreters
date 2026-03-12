@globals.define("len, new LoxCallable"){
    @Override
    public int arity(){
        return 1;
    }

    @Override 
    public Object call(Interpreter interpreter, List<Object> argument){
        return(double)((String) arguments.get(0).length());
    }
    
    @Override
    public String toString(){
        return "<native fn>";
    }
}