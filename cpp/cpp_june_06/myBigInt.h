//
// Created by xrazis on 9/21/20.
//

#ifndef CPP_JUNE_06_MYBIGINT_H
#define CPP_JUNE_06_MYBIGINT_H

#include <string>

using namespace std;

class myBigInt {

protected:
    string numb;
public:
    myBigInt();

    myBigInt(const string &numb);

    myBigInt(myBigInt &num);

    myBigInt operator+(const myBigInt &mInt);

    void print(ostream &out);
};


#endif //CPP_JUNE_06_MYBIGINT_H
