//
// Created by xrazis on 9/20/20.
//

#include <fstream>
#include "FIFOQueue.h"

using namespace std;

FIFOQueue::FIFOQueue() {}

FIFOQueue::~FIFOQueue() {

}

void FIFOQueue::insertA(roomAction *action) {
    queue.push_front(action);
}

void FIFOQueue::executeA() {
    roomAction *room = queue.back();

    room->printFile();
    room->printOut();

    queue.pop_back();
}

void FIFOQueue::printOut() {
    for (const auto &item : queue) {
        item->printOut();
    }
}

void FIFOQueue::printFile() {
    for (const auto &item : queue) {
        item->printFile();
    }
}
