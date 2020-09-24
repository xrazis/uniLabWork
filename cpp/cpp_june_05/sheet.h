//
// Created by xrazis on 9/21/20.
//

#ifndef CPP_JUNE_05_SHEET_H
#define CPP_JUNE_05_SHEET_H


#include <map>
#include "cell.h"

class sheet {
private:
    map<pair<int, int>, cell *> sheetMap;

public:
    virtual ~sheet();

    sheet();

    void insertSpecific(int row, int col, cell &nCell);

    cell * findSpecific(int row, int col);

    void deleteSpecific(int row, int col);

    void writeCSV();

    void readCSV();
};

#endif //CPP_JUNE_05_SHEET_H
