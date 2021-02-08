//
// Created by xrazis on 9/24/20.
//

#include "warehouse.h"

warehouse::warehouse() {}

warehouse::~warehouse() {}

void warehouse::addFridge(fridge &nFridge) {
    stock.push_back(&nFridge);
}

void warehouse::printAll() {
    for (const auto &item:stock)
        item->print();
}

void warehouse::printSameOrigin(const string &sOrigin) {
    for (const auto &item:stock) {
        if (item->getOrigin() == sOrigin)
            item->print();
    }
}
