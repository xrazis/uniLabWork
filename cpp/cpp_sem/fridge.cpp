//
// Created by xrazis on 9/24/20.
//

#include <iostream>
#include "fridge.h"


fridge::fridge() {}

fridge::~fridge() {}

fridge::fridge(int code, const string &brand, int capacity, float weight, const string &origin) : code(code),
                                                                                                  brand(brand),
                                                                                                  capacity(capacity),
                                                                                                  weight(weight),
                                                                                                  origin(origin) {}

fridge &fridge::operator=(fridge &cFridge) {
    code = cFridge.getCode();
    brand = cFridge.getBrand();
    capacity = cFridge.getCapacity();
    weight = cFridge.getWeight();
    origin = cFridge.getOrigin();
    return *this;
}

void fridge::print() {
    cout << code << " " << brand << " " << capacity << " " << weight << " " << origin << " ";
}

bool fridge::operator<(fridge &compareF) {
    return capacity < compareF.capacity;
}
