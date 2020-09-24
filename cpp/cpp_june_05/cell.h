//
// Created by xrazis on 9/21/20.
//

#ifndef CPP_JUNE_05_CELL_H
#define CPP_JUNE_05_CELL_H

#include <string>

using namespace std;

class cell {
private:
    string type;
    string price;

public:
    const string &getType() const;

    void setType(const string &type);

    const string &getPrice() const;

    void setPrice(const string &price);

    virtual ~cell();

    cell(const string &type, const string &price);
};


#endif //CPP_JUNE_05_CELL_H
