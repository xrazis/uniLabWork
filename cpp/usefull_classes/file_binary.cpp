//Creating an output stream
ofstream ofstream_ob;
//Calling the open function to write an object to a file
ofstream_ob.open("File9.txt", ios::out);
//Creating an object of A class
A ob1;
//Calling the putdata() function
ob1.putdata();
//Calling the write() function to write an object to a file.
ofstream_ob.write( (char *) & ob1, sizeof(ob1));
cout<<"Congrats! Your object is successfully written to the file \n";
//Closing the output stream
ofstream_ob.close();
//Creating an input stream
ifstream ifstream_ob;
//Calling the open function to read an object from a file
ifstream_ob.open("File11.txt", ios::in);
//Creating an empty object of A class
A ob2;
cout<<"\nReading the object from a file : \n";
//Calling the read() function to read an object from a file and transfer its content to an empty object
ifstream_ob.read( (char *) & ob2, sizeof(ob2));
//Calling the getdata() function
ob2.getdata();
//Closing the input stream
ifstream_ob.close();
