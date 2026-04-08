
//Changes made to answer Question 1:
typedef struct ObjString {
    Obj obj;
    int length;
    char chars[];
   }; ObjString;
   
   ObjString* string = (ObjString*)reallocate(NULL, 0, sizeof(ObjString) + length + 1);


//Changes made to answer Question 2:
typedef struct ObjString {
Obj obj;
int length;
bool ownsChars;
char chars[];
}; ObjString;
