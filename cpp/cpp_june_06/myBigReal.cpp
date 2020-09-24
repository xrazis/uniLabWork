//
// Created by xrazis on 9/21/20.
//

#include <iostream>
#include "myBigReal.h"

using namespace std;

myBigReal::myBigReal() {
    dec = "0";
}

myBigReal::myBigReal(const string &numb, const string &dec) : myBigInt(numb), dec(dec) {}

myBigReal::myBigReal(myBigReal &num) {
    numb = num.numb;
    dec = num.dec;
}

myBigReal myBigReal::operator+(const myBigReal &mInt) {
    myBigReal mBig;
    mBig.numb = this->numb + mInt.numb;
    mBig.dec = this->dec + mInt.dec;
    return mBig;
}

void myBigReal::print(ostream &out) {
    out << numb << "." << dec << "\n";
}

bool myBigReal::operator==(double &mDouble) {
    string compare = numb + "." + dec;
    double converted = atof(compare.c_str());
    return mDouble == converted;
}

string myBigReal::toCString() {
    return numb + "." + dec;
}

