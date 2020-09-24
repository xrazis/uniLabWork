//
// Created by xrazis on 9/20/20.
//

#include "clientList.h"

void clientList::addClient(client *clnt) {
    clients.push_back(clnt);
}

void clientList::listClients() {
    printf("Doctor %s has the following clients \n", doc->getName().c_str());
    for (const auto &item : clients)
        printf("\t~Client: %s \n", item->getName().c_str());
}

clientList::clientList(doctor *newDoc) {
    doc = newDoc;
}
