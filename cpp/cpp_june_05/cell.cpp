//
// Created by xrazis on 9/21/20.
//

#include "cell.h"

cell::cell(const string &type, const string &price) : type(type), price(price) {}

cell::~cell() {

}

const string &cell::getType() const {
    return type;
}

void cell::setType(const string &type) {
    cell::type = type;
}

const string &cell::getPrice() const {
    return price;
}

void cell::setPrice(const string &price) {
    cell::price = price;
}
