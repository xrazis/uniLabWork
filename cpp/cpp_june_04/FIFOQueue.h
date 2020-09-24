//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_04_FIFOQUEUE_H
#define CPP_JUNE_04_FIFOQUEUE_H

#include <list>
#include "roomAction.h"

using namespace std;

class FIFOQueue {
private:
    list<roomAction *> queue;

public:
    FIFOQueue();

    virtual ~FIFOQueue();

    void insertA(roomAction *);

    void executeA();

    void printOut();

    void printFile();
};


#endif //CPP_JUNE_04_FIFOQUEUE_H
