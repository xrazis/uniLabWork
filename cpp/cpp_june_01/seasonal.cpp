//
// Created by xrazis on 9/19/20.
//

#include "seasonal.h"

seasonal::seasonal(const string &name, float dailyPay) : employee(name), dailyPay(dailyPay) {}

seasonal::~seasonal() {}

float seasonal::wageCalculator(int days) {
    float wage = (days * dailyPay) - 2 * dailyPay;
    return wage;
}

float seasonal::getDailyPay() const {
    return dailyPay;
}

void seasonal::setDailyPay(float dailyPay) {
    seasonal::dailyPay = dailyPay;
}
