//
// Created by xrazis on 9/19/20.
//

#include "warehouse.h"
#include "scItem.h"

void warehouse::addItem(bookstoreItem *item) {
    items.push_back(item);
}

void warehouse::printWarehouse() {
    for (auto &item : items) {
        item->print();
    }
}

warehouse::warehouse() {}
