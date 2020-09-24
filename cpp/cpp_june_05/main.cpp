#include <iostream>
#include "cell.h"
#include "sheet.h"

int main() {
    std::cout << "Hello, World!" << std::endl;

    auto *cell01 = new cell("int", "1");
    auto *cell02 = new cell("int", "2");
    auto *cell03 = new cell("int", "3");

    sheet sheet01;

    sheet01.insertSpecific(2, 3, *cell01);
    sheet01.insertSpecific(1, 1, *cell02);
    sheet01.insertSpecific(2, 5, *cell03);

    cell *rtnValue = sheet01.findSpecific(2, 3);

    cout << rtnValue->getPrice();

    sheet01.writeCSV();

    return 0;
}
