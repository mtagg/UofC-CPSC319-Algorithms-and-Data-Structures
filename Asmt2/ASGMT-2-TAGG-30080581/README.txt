ASGMT2-1-TAGG-30080581
BINARY SEARCH TREE
/*
/
/
**YOU SHOULD NOT REQUIRE THIS README TO OPERATE MY PROGRAM
**THERE ARE PLENTY OF INSTRUCTIONS WHEN RUN THROUGH TERMINAL

**TO RUN:
	>> javac megaBinTree.java
	>> java megaBinTree
/
/
*/

IMPORTANT NOTES:
     1. Pressing ENTER without any other terminal input will skip the step of the program you are on.

     2. If at the last step of the program(traversal methods), ENTER with no other terminal input ends the program.

     3. When entering the file name you would like to open, leave out file type, ie. ".txt" is not necessary

     
PROGRAM OVERVIEW:
     megaBinTree.java is the parent class of this program, utilizing the class "binTreeNode" to create objects 
     that keep track of two leaf nodes: left & right, as well as a data: word and frequency. All are updated by a recursive
     function named: makeTree which also counts the number of different nodes in the process.

     part 3 of the program is carried out using the treeOut function, printing results in main, following this point, users
     will be prompted to either search for words until program termination, or start a new binary tree using a different file

     All commands will be prompted in the terminal. Sufficient instructions will be provided in the terminal to navigate
     the program

     After a file is loaded and output info displayed, user will be able to navigate word searches and word printing 
     functions until cancelled, instructions will be provided.


PROGRAM OPERATION:
     This program initially prompts the user to enter a file name
     Program will open file, appending ".txt" to the filename provided in the terminal                                   [1]
     After parsing file, a binary search tree is created using the recursive function, "makeTree"                        [2]
     makeTree will also track the total number of words and return that number to main                                   [3]
     The method: "treeOut" will search for all remaining information pertaining to part 3 of the assignment outline      [3]
     Main then prints contents found in the "mostOften" array populated by the "treeOut" method                          [3]
     Main function will then loop until user cancels asking for inputs 1-3 to print tree using 3 traversal methods       [4]
     End of program, to close program, ENTER key with no other input is necessary to close    


