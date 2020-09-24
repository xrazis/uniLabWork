//
// Created by xrazis on 9/23/20.
//

#ifndef CPP_JUNE_07_SPF_H
#define CPP_JUNE_07_SPF_H

#include "picture.h"

class spf : public picture {
public:
    void print(ostream &o) override;

    picture &read(istream &i) override;
};


#endif //CPP_JUNE_07_SPF_H
