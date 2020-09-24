ofstream myfile;
myfile.open ("example.txt");
myfile << "Writing this to a file.\n";
myfile.close();




fstream file;
file.open ("example.txt", ios::out | ios::in );
cout << "Write text to be written on file." << endl;
cin.getline(text, sizeof(text));
// Writing on file
file << text << endl;
// Reding from file
file >> text;
cout << text << endl;
//closing the file
file.close();