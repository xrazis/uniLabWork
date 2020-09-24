//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_02_DOCTORLIST_H
#define CPP_JUNE_02_DOCTORLIST_H


#include "client.h"
#include "doctor.h"

class doctorList {
private:
    client *clnt;
    list<doctor *> doctors;

public:
    doctorList(client *);

    void addDoctor(doctor *);

    void listDoctors();
};


#endif //CPP_JUNE_02_DOCTORLIST_H
