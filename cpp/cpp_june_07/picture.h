//
// Created by xrazis on 9/23/20.
//

#ifndef CPP_JUNE_07_PICTURE_H
#define CPP_JUNE_07_PICTURE_H

#include <list>
#include "pixel.h"

using namespace std;

class picture {
public:
    int height;
    int width;
    list<pixel *> pxList;

    int mRed;
    int mGreen;
    int mBlue;

    picture();

    picture(int width, int height);

    picture(const picture &cPic);

    char getMaxRed();

    char getMaxGreen();

    char getMaxBlue();

    virtual void print(ostream &o);

    virtual picture & read(istream &i);

    virtual ~picture();
};


#endif //CPP_JUNE_07_PICTURE_H
