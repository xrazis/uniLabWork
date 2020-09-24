//
// Created by xrazis on 9/21/20.
//
#include <map>
#include <iostream>
#include <fstream>
#include "sheet.h"

using namespace std;

sheet::sheet() {}

sheet::~sheet() {

}

void sheet::insertSpecific(int row, int col, cell &nCell) {
    sheetMap.insert(make_pair(make_pair(row, col), &nCell));
}

cell *sheet::findSpecific(int row, int col) {
    auto itr = sheetMap.find(make_pair(row, col));
    if (itr == sheetMap.end()) {
        cerr << "Not found!";
        return nullptr;
    } else {
        return itr->second;
    }
}

void sheet::deleteSpecific(int row, int col) {
    sheetMap.erase(make_pair(row, col));
}

void sheet::writeCSV() {
    ofstream csvFile("foo.csv");
    for (auto item : sheetMap) {
        for (int i = 0; i < item.first.first; ++i) {
            csvFile << "\n";
        }
        for (int i = 0; i < item.first.second; ++i) {
            csvFile << ",";
        }
        csvFile << item.second->getPrice();
    }
    csvFile.close();
}

void sheet::readCSV() {

}


