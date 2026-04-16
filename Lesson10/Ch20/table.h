#ifndef CLOX_TABLE_H
#define CLOX_TABLE_H

//changes from ObjString* to Value
tyepdef struct {
    Value key;
    Value value;
} Entry;

#endif