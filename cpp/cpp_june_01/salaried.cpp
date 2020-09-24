//
// Created by xrazis on 9/19/20.
//

#include "salaried.h"

salaried::salaried(const string &name, float dailyPay) : employee(name), dailyPay(dailyPay) {}

salaried::~salaried() {

}

float salaried::wageCalculator(int days) {
    float wage = (days * dailyPay);
    return wage;
}

float salaried::getDailyPay() const {
    return dailyPay;
}

void salaried::setDailyPay(float dailyPay) {
    salaried::dailyPay = dailyPay;
}

bool salaried::operator!=(float salary) {
    return dailyPay < salary;
}
