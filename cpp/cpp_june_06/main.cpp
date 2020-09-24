#include <iostream>
#include "myBigReal.h"

using namespace std;

int main() {
    std::cout << "Hello, World!" << std::endl;

    double mDouble = 20.23;

    myBigReal mReal01;
    myBigReal mReal02("20", "23");

    mReal01.print(cout);
    mReal02.print(cout);

    bool boolean = mReal02 == mDouble;

    printf("%s", boolean ? "true" : "false");

    return 0;
}
