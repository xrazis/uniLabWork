//
// Created by xrazis on 9/20/20.
//

#include <fstream>
#include "checkOut.h"

checkOut::checkOut(int id, const string &customerName, const string &customerSurname, int daysOfStay, room *mRoom,
                   const string &description) : roomAction(id, customerName, customerSurname, daysOfStay), mRoom(mRoom),
                                                description(description) {}

checkOut::~checkOut() {

}

room *checkOut::getMRoom() const {
    return mRoom;
}

void checkOut::setMRoom(room *mRoom) {
    checkOut::mRoom = mRoom;
}

const string &checkOut::getDescription() const {
    return description;
}

void checkOut::setDescription(const string &description) {
    checkOut::description = description;
}

void checkOut::printOut() {
    roomAction::printOut();
    mRoom->printOut();
    printf("\n Description: %s", description.c_str());
}

void checkOut::printFile() {
    roomAction::printFile();
    mRoom->printFile();
    ofstream outfile("RoomActions.txt", std::ios_base::app);
    outfile << "\n Description: " << description;
    outfile.close();
}