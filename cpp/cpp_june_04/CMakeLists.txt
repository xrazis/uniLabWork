cmake_minimum_required(VERSION 3.17)
project(cpp_june_04)

set(CMAKE_CXX_STANDARD 20)

add_executable(cpp_june_04 main.cpp room.cpp room.h roomAction.cpp roomAction.h checkIn.cpp checkIn.h checkOut.cpp checkOut.h transfer.cpp transfer.h FIFOQueue.cpp FIFOQueue.h)