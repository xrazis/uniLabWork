cmake_minimum_required(VERSION 3.17)
project(exams_sem)

set(CMAKE_CXX_STANDARD 14)

add_executable(exams_sem main.cpp fridge.cpp fridge.h light_duty.cpp light_duty.h heavy_duty.cpp heavy_duty.h medical.cpp medical.h warehouse.cpp warehouse.h)