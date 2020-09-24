//
// Created by xrazis on 9/20/20.
//

#include "doctorList.h"

void doctorList::addDoctor(doctor *doc) {
    doctors.push_back(doc);
}

void doctorList::listDoctors() {
    printf("Doctor %s has the following clients \n", clnt->getName().c_str());
    for (const auto &item : doctors)
        printf("\t~Client: %s \n", item->getName().c_str());
}

doctorList::doctorList(client *newClnt) {
    clnt = newClnt;
}
