//
// Created by xrazis on 9/19/20.
//

#ifndef UNTITLED1_EMPLOYEE_H
#define UNTITLED1_EMPLOYEE_H

#include <string>

using namespace std;


class employee {
private:
    string name;

public:
    employee(const string &name);

    virtual ~employee();

    const string &getName() const;

    void setName(const string &name);

    virtual float wageCalculator(int days);
};


#endif //UNTITLED1_EMPLOYEE_H
