//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_04_ROOM_H
#define CPP_JUNE_04_ROOM_H

#include "string"

using namespace std;

class room {
private:
    int number;
    int floor;
    string description;
    string amenities;
    int capacity;
    bool smoking;

public:
    room(int number, int floor, const string &description, const string &amenities, int capacity, bool smoking);

    virtual ~room();

    int getNumber() const;

    void setNumber(int number);

    int getFloor() const;

    void setFloor(int floor);

    const string &getDescription() const;

    void setDescription(const string &description);

    const string &getAmenities() const;

    void setAmenities(const string &amenities);

    int getCapacity() const;

    void setCapacity(int capacity);

    bool isSmoking() const;

    void setSmoking(bool smoking);

    void printOut();

    void printFile();
};

#endif //CPP_JUNE_04_ROOM_H
