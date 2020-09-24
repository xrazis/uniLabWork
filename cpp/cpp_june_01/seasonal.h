//
// Created by xrazis on 9/19/20.
//

#ifndef UNTITLED1_SEASONAL_H
#define UNTITLED1_SEASONAL_H


#include "employee.h"

class seasonal : public employee {
private:
    float dailyPay;

public:
    seasonal(const string &name, float dailyPay);

    virtual ~seasonal();

    float getDailyPay() const;

    void setDailyPay(float dailyPay);

    float wageCalculator(int days);
};


#endif //UNTITLED1_SEASONAL_H
