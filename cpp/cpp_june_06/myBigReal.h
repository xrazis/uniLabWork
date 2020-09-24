//
// Created by xrazis on 9/21/20.
//

#ifndef CPP_JUNE_06_MYBIGREAL_H
#define CPP_JUNE_06_MYBIGREAL_H


#include "myBigInt.h"

class myBigReal : public myBigInt {
protected:
    string dec;

public:
    myBigReal();

    myBigReal(const string &numb, const string &dec);

    myBigReal(myBigReal &realNum);

    myBigReal operator+(const myBigReal &mReal);

    void print(ostream &out);

    bool operator==(double &mDouble);

    string toCString();
};


#endif //CPP_JUNE_06_MYBIGREAL_H
