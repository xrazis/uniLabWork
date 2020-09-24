//
// Created by xrazis on 9/19/20.
//

#ifndef UNTITLED1_MANAGER_H
#define UNTITLED1_MANAGER_H

#include "salaried.h"

class manager : public salaried {
private:
    float bonus;

public:
    manager(const string &name, float dailyPay, float bonus);

    virtual ~manager();

    float getBonus() const;

    void setBonus(float bonus);

    float wageCalculator(int days);
};


#endif //UNTITLED1_MANAGER_H
