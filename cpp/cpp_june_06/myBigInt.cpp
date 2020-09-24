//
// Created by xrazis on 9/21/20.
//

#include "myBigInt.h"

myBigInt::myBigInt() {
    numb = "0";
}

myBigInt::myBigInt(const string &numb) : numb(numb) {}

myBigInt::myBigInt(myBigInt &num) {
    numb = num.numb;
}

myBigInt myBigInt::operator+(const myBigInt &mInt) {
    myBigInt mBig;
    mBig.numb = this->numb + mInt.numb;
    return mBig;
}

void myBigInt::print(ostream &out) {
    out << numb;
}
