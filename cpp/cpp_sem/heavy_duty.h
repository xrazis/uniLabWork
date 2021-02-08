//
// Created by xrazis on 9/24/20.
//

#ifndef EXAMS_SEM_HEAVY_DUTY_H
#define EXAMS_SEM_HEAVY_DUTY_H


#include "fridge.h"

class heavy_duty : public fridge {
private:
    int minTemp;
    int maxTemp;

public:
    heavy_duty();

    heavy_duty(int code, const string &brand, int capacity, float weight, const string &origin, int minTemp,
               int maxTemp);

    int getMinTemp() const {
        return minTemp;
    }

    void setMinTemp(int minTemp) {
        heavy_duty::minTemp = minTemp;
    }

    int getMaxTemp() const {
        return maxTemp;
    }

    void setMaxTemp(int maxTemp) {
        heavy_duty::maxTemp = maxTemp;
    }

    heavy_duty &operator=(heavy_duty &cFridge);

    void print() override;

    virtual ~heavy_duty();
};


#endif //EXAMS_SEM_HEAVY_DUTY_H
