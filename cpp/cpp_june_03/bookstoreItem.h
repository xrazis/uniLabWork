//
// Created by xrazis on 9/19/20.
//

#ifndef CPP_JUNE_03_BOOKSTOREITEM_H
#define CPP_JUNE_03_BOOKSTOREITEM_H

#include <string>

using namespace std;

class bookstoreItem {
protected:
    float price;
    int code;

public:
    bookstoreItem(int code, float price);

    int getCode() const;

    void setCode(int code);

    float getPrice() const;

    void setPrice(float price);

    virtual void print();
};


#endif //CPP_JUNE_03_BOOKSTOREITEM_H
