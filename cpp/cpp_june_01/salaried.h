//
// Created by xrazis on 9/19/20.
//

#ifndef UNTITLED1_SALARIED_H
#define UNTITLED1_SALARIED_H


#include "employee.h"

class salaried : public employee {
private:
    float dailyPay;

public:
    salaried(const string &name, float dailyPay);

    virtual ~salaried();

    float getDailyPay() const;

    void setDailyPay(float dailyPay);

    float wageCalculator(int days);

    bool operator!=(float);
};


#endif //UNTITLED1_SALARIED_H
