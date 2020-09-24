//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_04_TRANSFER_H
#define CPP_JUNE_04_TRANSFER_H


#include "roomAction.h"

class transfer : public roomAction {
private:
    room *source, *destination;
    string explanation;

public:
    transfer(int id, const string &customerName, const string &customerSurname, int daysOfStay, room *source,
             room *destination, const string &explanation);

    virtual ~transfer();

    room *getSource() const;

    void setSource(room *source);

    room *getDestination() const;

    void setDestination(room *destination);

    const string &getExplanation() const;

    void setExplanation(const string &explanation);

    void printOut() override;

    void printFile() override;

};


#endif //CPP_JUNE_04_TRANSFER_H
