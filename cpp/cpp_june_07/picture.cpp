//
// Created by xrazis on 9/23/20.


//

#include "picture.h"

picture::picture() {
    width = 0;
    height = 0;
}

picture::picture(int width, int height) : width(width), height(height) {
    for (int i = 0; i < width * height; ++i) {
        pxList.push_back(new pixel(1, 1, 1));
    }
}

picture::picture(const picture &cPic) {
    height = cPic.height;
    width = cPic.width;
    pxList = cPic.pxList;
}

void picture::print(ostream &o) {
}

picture::~picture() {

}

char picture::getMaxRed() {
    char red = -1;
    for (auto item : pxList) {
        if (item->red > red)
            red = item->red;
    }
    return red;
}

char picture::getMaxGreen() {
    char green = -1;
    for (auto item : pxList) {
        if (item->green > green)
            green = item->green;
    }
    return green;
}

char picture::getMaxBlue() {
    char blue = -1;
    for (auto item : pxList) {
        if (item->blue > blue)
            blue = item->blue;
    }
    return blue;
}

picture & picture::read(istream &i) {

}
