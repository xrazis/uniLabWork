//
// Created by xrazis on 9/23/20.
//
#include <iostream>
#include <fstream>
#include "pixel.h"

using namespace std;

pixel::pixel() {}

pixel::pixel(unsigned char red, unsigned char green, unsigned char blue) : red(red), green(green), blue(blue) {}

pixel::pixel(const pixel &cPixel) {
    red = cPixel.red;
    green = cPixel.green;
    blue = cPixel.blue;
}

pixel::~pixel() {

}
