cmake_minimum_required(VERSION 4.1)

project(Lesson1Hw)

# 3. Specify the C standard (optional but recommended)
set(CMAKE_C_STANDARD 11)

# 4. Create the executable
# Syntax: add_executable(Desired_Executable_Name source_file.c)
add_executable(CHello HelloWorld.c)

add_executable(CLinkedList DoublyLinkedList.c)
