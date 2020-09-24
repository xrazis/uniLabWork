//
// Created by xrazis on 9/19/20.
//

#ifndef CPP_JUNE_02_CLIENT_H
#define CPP_JUNE_02_CLIENT_H

#include <list>
#include <string>

using namespace std;

class client {
private:
    int taxNumber;
    string name;
    int age;

public:
    client(int taxNumber, const string &name, int age);

    virtual ~client();

    int getTaxNumber() const;

    void setTaxNumber(int taxNumber);

    const string &getName() const;

    void setName(const string &name);

    int getAge() const;

    void setAge(int age);

    bool operator<(const client &rhs) const;

};


#endif //CPP_JUNE_02_CLIENT_H
