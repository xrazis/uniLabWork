//
// Created by xrazis on 9/19/20.
//

#include <iostream>
#include "client.h"

client::~client() {}

client::client(int taxNumber, const string &name, int age) : taxNumber(taxNumber), name(name), age(age) {}

int client::getTaxNumber() const {
    return taxNumber;
}

void client::setTaxNumber(int taxNumber) {
    client::taxNumber = taxNumber;
}

const string &client::getName() const {
    return name;
}

void client::setName(const string &name) {
    client::name = name;
}

int client::getAge() const {
    return age;
}

void client::setAge(int age) {
    try {
        if (age < 0) {
            throw "Age is negative!";
        } else if (age < 18) {
            throw "Age is bellow 18";
        } else {
            throw "Age is above 18";
        }
    } catch (const char *e) {
        cerr << e << endl;
    }
    client::age = age;
}

bool client::operator<(const client &rhs) const {
    return age < rhs.getAge();
};

