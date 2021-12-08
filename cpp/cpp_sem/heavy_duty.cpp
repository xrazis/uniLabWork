//
// Created by xrazis on 9/24/20.
//

#include <iostream>
#include "heavy_duty.h"

heavy_duty::heavy_duty() {}

heavy_duty::~heavy_duty() {}


heavy_duty::heavy_duty(int code, const string &brand, int capacity, float weight, const string &origin, int minTemp,
                       int maxTemp) : fridge(code, brand, capacity, weight, origin), minTemp(minTemp),
                                      maxTemp(maxTemp) {}

heavy_duty &heavy_duty::operator=(heavy_duty &cFridge) {
    code = cFridge.getCode();
    brand = cFridge.getBrand();
    capacity = cFridge.getCapacity();
    weight = cFridge.getWeight();
    origin = cFridge.getOrigin();
    minTemp = cFridge.getMinTemp();
    maxTemp = cFridge.getMaxTemp();

    return *this;
}

void heavy_duty::print() {
    fridge::print();
    cout << minTemp << " " << maxTemp << "\n";
}
