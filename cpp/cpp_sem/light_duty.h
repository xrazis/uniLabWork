//
// Created by xrazis on 9/24/20.
//

#ifndef EXAMS_SEM_LIGHT_DUTY_H
#define EXAMS_SEM_LIGHT_DUTY_H

#include "fridge.h"

using namespace std;

class light_duty : public fridge {
private:
    int durationNoPower;

public:
    light_duty();

    light_duty(int code, const string &brand, int capacity, float weight, const string &origin, int durationNoPower);

    int getDurationNoPower() const {
        return durationNoPower;
    }

    void setDurationNoPower(int durationNoPower) {
        light_duty::durationNoPower = durationNoPower;
    }

    light_duty& operator=(light_duty &cFridge);

    void print() override;

    virtual ~light_duty();

};


#endif //EXAMS_SEM_LIGHT_DUTY_H
