//
// Created by xrazis on 9/19/20.
//

#ifndef CPP_JUNE_03_BOOK_H
#define CPP_JUNE_03_BOOK_H

#include <string>
#include "bookstoreItem.h"

using namespace std;

class book : public bookstoreItem {
private:
    string title;

public:
    book(int code, float price, const string &title);

    const string &getName();

    void setName(const string &title);

    void print() override;

    float &operator++();
};

#endif //CPP_JUNE_03_BOOK_H
