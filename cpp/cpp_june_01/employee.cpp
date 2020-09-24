//
// Created by xrazis on 9/19/20.
//

#include "employee.h"

employee::employee(const string &name) : name(name) {}

employee::~employee() {

}

const string &employee::getName() const {
    return name;
}

void employee::setName(const string &name) {
    employee::name = name;
}

float employee::wageCalculator(int days) {}
