//
// Created by xrazis on 9/19/20.
//

#ifndef CPP_JUNE_03_SCITEM_H
#define CPP_JUNE_03_SCITEM_H

#include <string>
#include "bookstoreItem.h"

using namespace std;

class scItem : public bookstoreItem {
private:
    string name;

public:
    scItem(int code, float price, const string &name);

    const string &getName();

    void setName(const string &name);

    void print() override;

    float &operator--();
};

#endif //CPP_JUNE_03_SCITEM_H
