//
// Created by xrazis on 9/19/20.
//

#ifndef CPP_JUNE_02_DOCTOR_H
#define CPP_JUNE_02_DOCTOR_H

#include <list>
#include <string>
#include "clientList.h"

using namespace std;

class doctor {
private:
    string name;
    int phone;
    string speciality;

public:
    doctor(const string &name, int phone, const string &speciality);

    virtual ~doctor();

    const string &getName() const;

    void setName(const string &name);

    int getPhone() const;

    void setPhone(int phone);

    const string &getSpeciality() const;

    void setSpeciality(const string &speciality);

};

#endif //CPP_JUNE_02_DOCTOR_H
