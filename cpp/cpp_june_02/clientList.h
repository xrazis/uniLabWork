//
// Created by xrazis on 9/20/20.
//

#ifndef CPP_JUNE_02_CLIENTLIST_H
#define CPP_JUNE_02_CLIENTLIST_H


#include "doctor.h"
#include "client.h"

class clientList {
private:
    doctor *doc;
    list<client *> clients;

public:
    clientList(doctor *);

    void addClient(client *);

    void listClients();

};


#endif //CPP_JUNE_02_CLIENTLIST_H
