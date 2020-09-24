//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_04_ROOMACTION_H
#define CPP_JUNE_04_ROOMACTION_H

#include "room.h"

class roomAction {
private:
    int id;
    string customerName;
    string customerSurname;
    int daysOfStay;

public:
    roomAction(int id, const string &customerName, const string &customerSurname, int daysOfStay);

    virtual ~roomAction();

    int getId() const;

    void setId(int id);

    const string &getCustomerName() const;

    void setCustomerName(const string &customerName);

    const string &getCustomerSurname() const;

    void setCustomerSurname(const string &customerSurname);

    int getDaysOfStay() const;

    void setDaysOfStay(int daysOfStay);

    virtual void printOut();

    virtual void printFile();
};


#endif //CPP_JUNE_04_ROOMACTION_H
