//
// Created by xrazis on 9/24/20.
//

#include <iostream>
#include "light_duty.h"

light_duty::light_duty() {}

light_duty::~light_duty() {}

light_duty::light_duty(int code, const string &brand, int capacity, float weight, const string &origin,
                       int durationNoPower)
        : fridge(code, brand, capacity, weight, origin), durationNoPower(durationNoPower) {}

light_duty &light_duty::operator=(light_duty &cFridge) {
    code = cFridge.getCode();
    brand = cFridge.getBrand();
    capacity = cFridge.getCapacity();
    weight = cFridge.getWeight();
    origin = cFridge.getOrigin();
    durationNoPower = cFridge.getDurationNoPower();

    return *this;
}

void light_duty::print() {
    fridge::print();
    cout << durationNoPower << "\n";
}
