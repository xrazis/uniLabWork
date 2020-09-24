//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_04_CHECKOUT_H
#define CPP_JUNE_04_CHECKOUT_H

#include "room.h"
#include "roomAction.h"

class checkOut : public roomAction {
private:
    room *mRoom;
    string description;

public:
    checkOut(int id, const string &customerName, const string &customerSurname, int daysOfStay, room *mRoom,
             const string &description);

    virtual ~checkOut();

    room *getMRoom() const;

    void setMRoom(room *mRoom);

    const string &getDescription() const;

    void setDescription(const string &description);

    void printOut() override;

    void printFile() override;

};

#endif //CPP_JUNE_04_CHECKOUT_H
