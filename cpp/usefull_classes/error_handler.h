#include <exception>
using namespace std;


class error_handler {
protected:
    int code;
public:
    error_handler(int _code){
        code = _code;
        switch(code) {
            case 1: cout << "Could not open file. Fatal error." << endl; exit(1);
                /* NOTE: After each case...		break;(program continues) OR exit(1); (program ends)
                */
            default: cout << "\n Unknown exception. Program terminating" << endl; exit(1);
        }
    }
    ~error_handler(){
        cout << "An exception object was destroyed, but the program continues to run!" << endl;
    }
};





try {
/*
**	Here is the code to try to run
** Erase this and enter your code here
*/
throw 1;
}
catch (error_handler ex(_code)) {
exit(1);
}
delete ex;