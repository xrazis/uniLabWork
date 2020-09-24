//

#include <fstream>
#include "room.h"

// Created by xrazis on 9/20/20.

//
room::room(int number, int floor, const string &description, const string &amenities, int capacity, bool smoking)
        : number(number), floor(floor), description(description), amenities(amenities), capacity(capacity),
          smoking(smoking) {}

int room::getNumber() const {
    return number;
}

void room::setNumber(int number) {
    room::number = number;
}

int room::getFloor() const {
    return floor;
}

void room::setFloor(int floor) {
    room::floor = floor;
}

const string &room::getDescription() const {
    return description;
}

void room::setDescription(const string &description) {
    room::description = description;
}

const string &room::getAmenities() const {
    return amenities;
}

void room::setAmenities(const string &amenities) {
    room::amenities = amenities;
}

int room::getCapacity() const {
    return capacity;
}

void room::setCapacity(int capacity) {
    room::capacity = capacity;
}

bool room::isSmoking() const {
    return smoking;
}

void room::setSmoking(bool smoking) {
    room::smoking = smoking;
}

room::~room() {

}

void room::printOut() {
    printf("\n Number: %d \t Floor: %d \t Description: %s \t Amenities: %s \t Capacity: %d \t Smoking: %s \t ",
           number, floor, description.c_str(), amenities.c_str(), capacity, smoking ? "true" : "false");
}

void room::printFile() {
    ofstream outfile("RoomActions.txt", std::ios_base::app);
    outfile << "\n Number: " << number << " \t Floor: " << floor << "\t Amenities: " << amenities <<
            "\t Capacity: " << capacity << "\t Smoking: " << smoking ? "true" : "false";
    outfile.close();
}
