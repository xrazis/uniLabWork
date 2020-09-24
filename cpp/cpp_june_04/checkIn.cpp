//
// Created by xrazis on 9/20/20.
//

#include <fstream>
#include "checkIn.h"

checkIn::checkIn(int id, const string &customerName, const string &customerSurname, int daysOfStay, room *mRoom,
                 const string &description) : roomAction(id, customerName, customerSurname, daysOfStay), mRoom(mRoom),
                                              description(description) {}

checkIn::~checkIn() {

}

const string &checkIn::getDescription() const {
    return description;
}

void checkIn::setDescription(const string &description) {
    checkIn::description = description;
}

void checkIn::printOut() {
    roomAction::printOut();
    mRoom->printOut();
    printf("\n Description: %s", description.c_str());
}

void checkIn::printFile() {
    roomAction::printFile();
    mRoom->printFile();
    ofstream outfile("RoomActions.txt", std::ios_base::app);
    outfile << "\n Description: " << description;
    outfile.close();
}



