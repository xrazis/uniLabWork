//
// Created by xrazis on 9/19/20.
//

#include "bookstoreItem.h"
#include <string>

using namespace std;

bookstoreItem::bookstoreItem(int code, float price) : code(code), price(price) {}

int bookstoreItem::getCode() const {
    return code;
}

void bookstoreItem::setCode(int code) {
    bookstoreItem::code = code;
}

float bookstoreItem::getPrice() const {
    return price;
}

void bookstoreItem::setPrice(float price) {
    bookstoreItem::price = price;
}

void bookstoreItem::print() {
    printf("Code: %d \t Price: %.1f \t", code, price);
}
