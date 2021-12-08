#include <iostream>
#include "warehouse.h"
#include "heavy_duty.h"
#include "light_duty.h"
#include "medical.h"

using namespace std;

int main() {
    cout << "Hello, World!" << endl;

    warehouse mWarehouse;

    auto *lightFridge = new light_duty(123, "brand", 200, 100.2, "origin01", 1000);
    auto *heavyFridge = new heavy_duty(123, "brand", 200, 100.2, "origin", -10, 10);
    auto *medicalFridge = new medical(123, "brand", 200, 100.2, "origin01", "insulation", 25);

    auto *newLightFridge = lightFridge;

    mWarehouse.addFridge(*lightFridge);
    mWarehouse.addFridge(*heavyFridge);
    mWarehouse.addFridge(*medicalFridge);
    mWarehouse.addFridge(*newLightFridge);

    mWarehouse.printAll();
    mWarehouse.printSameOrigin("origin01");

    bool val = lightFridge < newLightFridge;

    printf("fridge1 is %s smaller than 2", val ? "" : "not");

    return 0;
}
