#include <iostream>
#include <list>
#include "scItem.h"
#include "book.h"
#include "warehouse.h"

using namespace std;


int main() {
    std::cout << "Welcome to the bookstore app!" << std::endl;

    auto *scItem00 = new scItem(10, 10, "School item 00");
    auto *scItem01 = new scItem(20, 20.2, "School item 01");
    auto *scItem02 = new scItem(30, 30.3, "School item 02");
    auto *scItem03 = new scItem(40, 40.4, "School item 03");
    auto *scItem04 = new scItem(50, 50.5, "School item 04");

    auto *book00 = new book(60, 10.1, "Book 00");
    auto *book01 = new book(70, 20.2, "Book 01");
    auto *book02 = new book(80, 30.3, "Book 02");

    warehouse bookWarehouse;

    bookWarehouse.addItem(scItem00);
    bookWarehouse.addItem(book00);
    bookWarehouse.addItem(scItem01);
    bookWarehouse.addItem(book01);
    bookWarehouse.addItem(scItem02);

    bookWarehouse.printWarehouse();

    book00->operator++();
    scItem00->operator--();

    printf("\n\n");

    bookWarehouse.printWarehouse();

    return 0;
}
