//
// Created by xrazis on 9/19/20.
//

#include "manager.h"

manager::manager(const string &name, float dailyPay, float bonus) : salaried(name, dailyPay), bonus(bonus) {}

manager::~manager() {}

float manager::getBonus() const {
    return bonus;
}

void manager::setBonus(float bonus) {
    manager::bonus = bonus;
}

float manager::wageCalculator(int days) {
    float wage = salaried::wageCalculator(days) + bonus;

    return wage;
}




