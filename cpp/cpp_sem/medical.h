//
// Created by xrazis on 9/24/20.
//

#ifndef EXAMS_SEM_MEDICAL_H
#define EXAMS_SEM_MEDICAL_H

#include <string>
#include "fridge.h"

using namespace std;

class medical : public fridge {
private:
    string insulation;
    int moisture;

public:
    medical();

    medical(int code, const string &brand, int capacity, float weight, const string &origin, const string &insulation,
            int moisture);

    const string &getInsulation() const {
        return insulation;
    }

    void setInsulation(const string &insulation) {
        medical::insulation = insulation;
    }

    int getMoisture() const {
        return moisture;
    }

    void setMoisture(int moisture) {
        medical::moisture = moisture;
    }

    medical& operator=(const medical &cFridge);

    void print() override;

    virtual ~medical();
};


#endif //EXAMS_SEM_MEDICAL_H
