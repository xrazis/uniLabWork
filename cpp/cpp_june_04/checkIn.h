//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_04_CHECKIN_H
#define CPP_JUNE_04_CHECKIN_H

#include "roomAction.h"

class checkIn : public roomAction {
private:
    room *mRoom;
    string description;

public:
    checkIn(int id, const string &customerName, const string &customerSurname, int daysOfStay, room *mRoom,
            const string &description);

    virtual ~checkIn();

    const string &getDescription() const;

    void setDescription(const string &description);

    void printOut() override;

    void printFile() override;
};


#endif //CPP_JUNE_04_CHECKIN_H
