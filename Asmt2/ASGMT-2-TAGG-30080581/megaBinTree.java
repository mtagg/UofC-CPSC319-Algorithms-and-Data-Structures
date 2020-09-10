import java.util.*;
import java.io.*;


//https://www.geeksforgeeks.org/binary-tree-set-1-introduction/
//2020-07-17
//GEEKSFORGEEKS
//https://www.programiz.com/java-programming/basic-input-output
//Parewa Labs Pvt. Ltd.
//https://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java#:~:text=replaceAll(%22%5C%5CW%22,remove%20all%20non%2Dword%20characters.


public class megaBinTree {

     public static int makeTree(List<String> str_arr, int i, binTreeNode node, binTreeNode root, int tot){
          int n = str_arr.size();      
          
          if (i>=n){
               return tot;                                                // return once end of wordslist has been reached

          //node word == current array word 
          }else if(str_arr.get(i).compareTo(node.word) == 0){
               node.freq++;                                           //increment frequency of root word if a match is found
               tot = makeTree(str_arr, ++i, root, root, tot);                    //recursively calls itself with the next word
               
          //node word == current array word           
          }else if(str_arr.get(i).compareTo(node.word) < 0){
               if (node.left == null){ 
                    node.left = new binTreeNode(str_arr.get(i));      //current word < root, left branch vacant, set new node
                    tot++;
                    tot = makeTree(str_arr, ++i, root, root, tot);               //call self with new index, starting at root once again
               }              
               else{ tot = makeTree(str_arr, i, node.left, root, tot);}          // recursively call itself using left branch as new root to compare
 
          //node word == current array word 
          }else if (str_arr.get(i).compareTo(node.word) > 0){
               if (node.right == null){ 
                    node.right = new binTreeNode(str_arr.get(i));     //current word > root, right branch vacant, set new node
                    tot++;
                    tot = makeTree(str_arr, ++i, root, root, tot);               //call self with new index, starting at root once again
               }
               else{ tot = makeTree(str_arr, i, node.right, root, tot);}         //recursively call itself using right branch as new root to compare
          }
          return tot;
     }

     public static int treeOut(binTreeNode node, List<binTreeNode> mostOften, int unique){
          if (node == null) return unique; //return if either leaf is found null after calling
          if (node.left == null & node.right == null){ // leaf node found
               if (node.freq == 1) return  ++unique;
               else return unique;
          }        
          else{     // Post-Traversial Recursion method to search tree
               unique = treeOut(node.left, mostOften, unique);                //Left
               unique = treeOut(node.right, mostOften, unique);               //Right  
               if (mostOften.size() == 0) mostOften.add(node);                //Set a value in case of empty array  
               if (node.freq >= mostOften.get(0).freq){                       //Node 
                    if (node.freq > mostOften.get(0).freq) mostOften.clear(); //replace the "most frequent" list with the higher frequency node
                    mostOften.add(node);                                      //add current node with same (highest) frequency rate  
               }                                 
               if (node.freq == 1) return  ++unique;                     
               else return unique;
          }     
     }
          
     public static int wordSearch(String input, binTreeNode node){
          if (node == null) return 0;
          else if (input.compareToIgnoreCase(node.word) == 0) {return node.freq;}// no need to keep searching if word is found
          else if (node.left == null && node.right == null) {return 0;} //return if at a leaf without a matching word
          else {return wordSearch(input, node.left) + wordSearch (input, node.right);}
     }

     public static void inOrder(binTreeNode node){
          if (node == null) return;
          else{
               inOrder(node.left);
               System.out.print(" "+node.word);
               inOrder(node.right);
               return;
          }
     }

     public static void preOrder(binTreeNode node){
          if (node == null) return;
          else{
               System.out.print(" "+node.word);
               preOrder(node.left);
               preOrder(node.right);
               return;
          }
     }

     public static void postOrder(binTreeNode node){
          if (node == null) return;
          else{
               postOrder(node.left);
               postOrder(node.right);
               System.out.print(" "+node.word);
               return;
          }
     }
     public static void main(String[] args) {

          List <String> wordslist = new ArrayList<>(); // parsed words will be added to this dynamic array
          String filename;
          BufferedReader userin = new BufferedReader(new InputStreamReader(System.in));
          binTreeNode root = null;




          try{
               //part 1 of assignment - open file, initialize temporaries
               System.out.print("\n\nEnter in the first file name you would like to open. Do not include \".txt\" >> ");
               filename = userin.readLine();
               filename += ".txt";
               File f = new File(filename);
               FileReader file = new FileReader(f);
               BufferedReader r = new BufferedReader(file);
               String line;
               String[] splitwords;

               //part 1 of assignment - filter lines of text, remove punctuation/whitespace/uppercase, append to wordslist
               while ((line = r.readLine()) != null){
                    splitwords = line.split(" ");
                    for (int i = 0; i < splitwords.length; i++){
                         wordslist.add((splitwords[i].replaceAll("[^a-zA-Z]", "")).toLowerCase());                   
                    }              
               }       
               //part 2 of assignment - set root node and create binary tree
               root = new binTreeNode(wordslist.get(0));
               int totalWords = makeTree(wordslist, 1, root, root, 1); //one stop shop for creating the binary tree and finding total words
               System.out.println("\nTotal # of different words in "+ filename +" = "+totalWords);

               //part 2 of assignment - find unique word count and highest frequency word(s)
               List<binTreeNode> mostOften = new ArrayList<>(0);
               int uniqueWords = treeOut(root, mostOften, 0); // find unique words and most occuring words before displaying
               System.out.println("Total # of unique words in "+ filename +" = "+uniqueWords);
               System.out.println("The word(s) which occur(s) most often and at what rate: ");
               for (int i = 0; i < mostOften.size(); i++){
                    System.out.println("     "+ mostOften.get(i).word + " = " + mostOften.get(i).freq + " times ");//Display part 3
               }

               //part 3 of assignment - user input word search
               String search = "";
               int freq;
               System.out.println("\n*** To search for a word within "+filename+", enter a word and press enter. ***");
               System.out.println("*** To cancel the word search, press enter. ***");
               while (true){
                    System.out.print("\nUser Input >> ");
                    search = userin.readLine();
                    if (search.length() == 0) break;
                    else {
                         freq = wordSearch(search.replaceAll("[^a-zA-Z]", ""), root);
                         if (freq > 0) System.out.println("Found! \""+search+"\" appears "+freq+" times in the input text file");
                         else System.out.println("Word not found!");
                    }
               }System.out.println("\nWord search cancelled\n");

               //part 4 of assigment - traversal method word printing
               search = "";
               while (true){
                    System.out.print("\n\nEnter a BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER, just ENTER = end) for "+filename+" >> ");
                    search = userin.readLine();
                    if (search.length() == 0) break;
                    else {
                         if (search.compareToIgnoreCase("1") == 0){
                              System.out.print("IN-ORDER output: ");
                              inOrder(root);}
                         else if (search.compareToIgnoreCase("2") == 0){
                              System.out.print("PRE-ORDER output: ");
                              preOrder(root);}
                         else if (search.compareToIgnoreCase("3") == 0){
                              System.out.print("POST-ORDER output: ");
                              postOrder(root);}
                    }
               }System.out.println("\nEnd of Program!");
               file.close();
          }catch(IOException e){
               e.getStackTrace();
               System.out.println("\n*****Failed to open file, check file name input*****\n");
          }
          
     }
}