//
// Created by xrazis on 9/19/20.
//

#ifndef CPP_JUNE_03_WAREHOUSE_H
#define CPP_JUNE_03_WAREHOUSE_H

#include <list>
#include "bookstoreItem.h"

using namespace std;

class warehouse {
protected:
    list<bookstoreItem *> items;

public:
    warehouse();

    void addItem(bookstoreItem *item);

    void printWarehouse();
};


#endif //CPP_JUNE_03_WAREHOUSE_H
