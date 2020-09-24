//
// Created by xrazis on 9/23/20.
//

#ifndef CPP_JUNE_07_PIXEL_H
#define CPP_JUNE_07_PIXEL_H

#include <iostream>
#include <fstream>

using namespace std;

class pixel {
public:
    unsigned char red;
    unsigned char green;
    unsigned char blue;
    
    pixel();

    pixel(unsigned char red, unsigned char green, unsigned char blue);

    pixel(const pixel &cPixel);

    virtual ~pixel();
};


#endif //CPP_JUNE_07_PIXEL_H
