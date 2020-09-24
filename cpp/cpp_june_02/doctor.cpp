//
// Created by xrazis on 9/19/20.
//

#include "doctor.h"


doctor::~doctor() {}

doctor::doctor(const string &name, int phone, const string &speciality) : name(name), phone(phone),
                                                                          speciality(speciality) {}

const string &doctor::getName() const {
    return name;
}

void doctor::setName(const string &name) {
    doctor::name = name;
}

int doctor::getPhone() const {
    return phone;
}

void doctor::setPhone(int phone) {
    doctor::phone = phone;
}

const string &doctor::getSpeciality() const {
    return speciality;
}

void doctor::setSpeciality(const string &speciality) {
    doctor::speciality = speciality;
}
