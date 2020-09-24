//
// Created by xrazis on 9/20/20.
//

#include <fstream>
#include "transfer.h"

transfer::transfer(int id, const string &customerName, const string &customerSurname, int daysOfStay, room *source,
                   room *destination, const string &explanation) : roomAction(id, customerName, customerSurname,
                                                                              daysOfStay), source(source),
                                                                   destination(destination), explanation(explanation) {}

transfer::~transfer() {}

room *transfer::getSource() const {
    return source;
}

void transfer::setSource(room *source) {
    transfer::source = source;
}

room *transfer::getDestination() const {
    return destination;
}

void transfer::setDestination(room *destination) {
    transfer::destination = destination;
}

const string &transfer::getExplanation() const {
    return explanation;
}

void transfer::setExplanation(const string &explanation) {
    transfer::explanation = explanation;
}

void transfer::printOut() {
    roomAction::printOut();
    printf("Source: \n");
    source->printOut();
    printf("Destination: \n");
    destination->printOut();
    printf("\n Explanation: %s", explanation.c_str());
}

void transfer::printFile() {
    roomAction::printFile();
    ofstream outfile("RoomActions.txt", std::ios_base::app);
    outfile << "Source: \n";
    source->printFile();
    outfile << "Destination: \n";
    source->printFile();
    outfile << "\n Explanation: " << explanation;
    outfile.close();
}
