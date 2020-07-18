cmake_minimum_required(VERSION 2.8)

project(test)

set(CMAKE_BUILD_TYPE "Debug")
include_directories(./include)

set(src main.cpp)
set(libs curl event)

add_executable(test ${src})
target_link_libraries(test ${libs})
