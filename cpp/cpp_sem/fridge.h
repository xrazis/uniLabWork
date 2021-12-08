//
// Created by xrazis on 9/24/20.
//

#ifndef EXAMS_SEM_FRIDGE_H
#define EXAMS_SEM_FRIDGE_H

#include <string>

using namespace std;


class fridge {
protected:
    int code;
    string brand;
    int capacity;
    float weight;
    string origin;

public:
    fridge();

    fridge(int code, const string &brand, int capacity, float weight, const string &origin);

    int getCode() const {
        return code;
    }

    void setCode(int code) {
        fridge::code = code;
    }

    const string &getBrand() const {
        return brand;
    }

    void setBrand(const string &brand) {
        fridge::brand = brand;
    }

    int getCapacity() const {
        return capacity;
    }

    void setCapacity(int capacity) {
        fridge::capacity = capacity;
    }

    float getWeight() const {
        return weight;
    }

    void setWeight(float weight) {
        fridge::weight = weight;
    }

    const string &getOrigin() const {
        return origin;
    }

    void setOrigin(const string &origin) {
        fridge::origin = origin;
    }

    fridge &operator=(fridge &cFridge);

    virtual bool operator<(fridge &compareF);

    virtual void print();

    virtual ~fridge();
};


#endif //EXAMS_SEM_FRIDGE_H
