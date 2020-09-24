//
// Created by xrazis on 9/23/20.
//

#include "spf.h"

void spf::print(ostream &o) {
    picture::print(o);
    o << "SPF\n";
    o << width;
    o << height;
    o << getMaxRed();
    o << getMaxGreen();
    o << getMaxBlue();

    for (auto &item : pxList) {
        o << item->red << "\n" << item->green << "\n" << item->blue << "\n";
    }

}

picture &spf::read(istream &o) {
    auto *npic = new picture();

    string spf;
    int mRed, mGreen, mBlue;

    o >> spf;
    o >> npic->width;
    o >> npic->height;
    o >> npic->mRed;
    o >> npic->mGreen;
    o >> npic->mBlue;

    for (int i = 0; i < o.eof(); ++i) {
        o >> mRed;
        o >> mGreen;
        o >> mBlue;

        npic->pxList.push_back(new pixel(mRed, mGreen, mBlue));
    }

    return *npic;

}
