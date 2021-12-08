//
// Created by xrazis on 9/24/20.
//

#include <iostream>
#include "medical.h"

medical::medical() {}

medical::~medical() {}

medical::medical(int code, const string &brand, int capacity, float weight, const string &origin,
                 const string &insulation, int moisture) : fridge(code, brand, capacity, weight, origin),
                                                           insulation(insulation), moisture(moisture) {}

medical &medical::operator=(const medical &cFridge) {
    code = cFridge.getCode();
    brand = cFridge.getBrand();
    capacity = cFridge.getCapacity();
    weight = cFridge.getWeight();
    origin = cFridge.getOrigin();
    insulation = cFridge.getInsulation();
    moisture = cFridge.getMoisture();

    return *this;
}

void medical::print() {
    fridge::print();
    cout << insulation << " " << moisture << "\n";
}
