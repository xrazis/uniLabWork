//
// Created by xrazis on 9/20/20.
//

#include <iostream>
#include <fstream>
#include "roomAction.h"

int roomAction::getId() const {
    return id;
}

void roomAction::setId(int id) {
    roomAction::id = id;
}

const string &roomAction::getCustomerName() const {
    return customerName;
}

void roomAction::setCustomerName(const string &customerName) {
    roomAction::customerName = customerName;
}

const string &roomAction::getCustomerSurname() const {
    return customerSurname;
}

void roomAction::setCustomerSurname(const string &customerSurname) {
    roomAction::customerSurname = customerSurname;
}

int roomAction::getDaysOfStay() const {
    return daysOfStay;
}

void roomAction::setDaysOfStay(int daysOfStay) {
    roomAction::daysOfStay = daysOfStay;
}

roomAction::roomAction(int id, const string &customerName, const string &customerSurname, int daysOfStay) : id(id),
                                                                                                            customerName(
                                                                                                                    customerName),
                                                                                                            customerSurname(
                                                                                                                    customerSurname),
                                                                                                            daysOfStay(
                                                                                                                    daysOfStay) {}

roomAction::~roomAction() {

}

void roomAction::printOut() {
    printf("\n ID: %d \t Name: %s \t Surname: %s \t Days of Stay: %d \t",
           id, customerName.c_str(), customerSurname.c_str(), daysOfStay);
}

void roomAction::printFile() {
    ofstream outfile("RoomActions.txt", std::ios_base::app);
    outfile << "\n ID: " << id << " \t Name: " << customerName << "\t Surname: " << customerSurname <<
            "\t Days of Stay: " << daysOfStay << "\t";
    outfile.close();
}
