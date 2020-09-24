#include <iostream>
#include <string>
#include <vector>
#include "employee.h"
#include "seasonal.h"
#include "salaried.h"
#include "manager.h"

using namespace std;

int main() {
    cout << "Welcome to the employee wage calculator" << endl;

    int days;
    float wage, totalWage = 0;
    seasonal seasonalEmp00("Employee00", 25.0), seasonalEmp01("Employee01", 25.0);
    salaried salariedEmp00("Employee02", 30.0), salariedEmp01("Employee03", 30.0), salariedEmp02("Employee04", 30.0);
    manager manager00("Manager01", 35.0, 5.0);

    vector<employee *> employees = {&seasonalEmp00, &seasonalEmp01, &salariedEmp00, &seasonalEmp01, &salariedEmp02,
                                    &manager00};

    cout << "Please give days" << endl;
    cin >> days;

    for (const auto &item : employees) {
        wage = item->wageCalculator(days);
        totalWage += wage;
        printf("Name: %s \t Wage: %.1f \n", item->getName().c_str(), wage);
    }

    printf("Total wages: %.1f \n", totalWage);

    printf("Overloading: %s", salariedEmp00 != 100 ? "true" : "false");

    return 0;
}
