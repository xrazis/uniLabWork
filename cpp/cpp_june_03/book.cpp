//
// Created by xrazis on 9/19/20.
//

#include "book.h"

book::book(int code, float price, const string &title) : bookstoreItem(code, price), title(title) {}

const string &book::getName() {
    return title;
}

void book::setName(const string &title) {
    book::title = title;
}

void book::print() {
    bookstoreItem::print();
    printf("Title %s\n", title.c_str());
}

float &book::operator++() {
    price += price * 0.1;
    return price;
}
