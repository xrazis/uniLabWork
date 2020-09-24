#include <iostream>
#include "doctor.h"
#include "client.h"
#include "clientList.h"
#include "doctorList.h"

using namespace std;

int main() {
    cout << "Welcome to the doc app!" << endl;

    auto *doc00 = new doctor("Doctor00", 12346789, "Heart Doc");
    auto *doc01 = new doctor("Doctor01", 12346789, "Head Doc");
    auto *doc02 = new doctor("Doctor02", 12346789, "Lungs Doc");

    auto *client00 = new client(123456789, "Client00", -1);
    auto *client01 = new client(123456789, "Client01", 15);
    auto *client02 = new client(123456789, "Client02", 45);
    auto *client03 = new client(123456789, "Client03", 55);

    printf("Comparison: %s\n", client00 < client01 ? client00->getName().c_str() : client01->getName().c_str());

    clientList list01(doc00);
    list01.addClient(client01);
    list01.addClient(client02);
    list01.addClient(client03);

    list01.listClients();

    return 0;
}