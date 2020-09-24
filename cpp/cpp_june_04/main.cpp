#include <iostream>
#include "checkIn.h"
#include "FIFOQueue.h"

using namespace std;

int main() {
    std::cout << "Hello, World!" << std::endl;

    FIFOQueue list;

    auto *room01 = new room(11, 0, "Spacious Apartment", "Has toilet", 5, true);
    auto *room02 = new room(12, 1, "Spacious Apartment on the first floor", "Has toilet and sea view", 5, false);

    auto *checkIn01 = new checkIn(123, "Razis", "Haris", 10, room01, "Old client");
    auto *checkIn02 = new checkIn(123, "Razis", "Haris", 10, room02, "Old client's brother");

    list.insertA(checkIn01);
    list.insertA(checkIn02);
    list.executeA();
    list.executeA();

    return 0;
}
