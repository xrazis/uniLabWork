//
// Created by xrazis on 9/24/20.
//

#ifndef EXAMS_SEM_WAREHOUSE_H
#define EXAMS_SEM_WAREHOUSE_H

#include <vector>
#include "fridge.h"

using namespace std;

class warehouse {
private:
    vector<fridge *> stock;

public:
    warehouse();

    void addFridge(fridge &nFridge);

    void printSameOrigin(const string& sOrigin);

    void printAll();

    virtual ~warehouse();
};


#endif //EXAMS_SEM_WAREHOUSE_H
