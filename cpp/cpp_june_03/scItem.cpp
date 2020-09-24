//
// Created by xrazis on 9/19/20.
//

#include "scItem.h"

scItem::scItem(int code, float price, const string &name) : bookstoreItem(code, price), name(name) {}

const string &scItem::getName() {
    return name;
}

void scItem::setName(const string &name) {
    scItem::name = name;
}

void scItem::print() {
    bookstoreItem::print();
    printf("Name %s\n", name.c_str());
}

float &scItem::operator--() {
    price -= price * 0.05;
    return price;
}
