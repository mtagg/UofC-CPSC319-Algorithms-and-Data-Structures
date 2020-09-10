//ArrayList syntax example retrieved from https://java-array-of-arraylist-example.pnnlbmiiipx.pw/img/31ee2dc1b310025fa1c62cb5a9f132ad.png
// import example retrieved from https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
//Try/catch and general java8 functions learned through use of my installed VScode Language support Extension by "Red Hat"
// general JDK error messaging was also used to adjust and elarn Java 8 for this assignment
//output + writing to new file methods retrieved from : https://www.w3schools.com/java/java_files_create.asp


import java.util.ArrayList;
import java.util.List;
import java.io.*;



public class asgmt1Tagg30080581{

     public static void sortAlpha (List<String> arrlist, int lo, int hi){
          int mid;
          if (hi-1 - lo < 1){
               return; // returns when array cannot be recursively broken down further
          }else{
               mid = ((hi-lo)/2) + lo; 
               sortAlpha(arrlist, lo, mid); //left half of array to be recursively sorted
               sortAlpha(arrlist, mid, hi); //right half of array to be recursively sorted
          }

          //initialize arrays/ index variables for sorting
          String [] arrlo = new String[mid - lo];
          String [] arrhi = new String[hi - mid];
          int i=0,j=0,k=lo;

          //populate left half and right half arrays
          while (i < arrlo.length){
               arrlo[i] = arrlist.get(i+lo).toString();
               i++;
          }
          while (j < arrhi.length){
               arrhi[j] = arrlist.get(mid+j).toString();
               j++;              
          }


          //sort arrlist using the sorted left and right halves of the array
          i = 0;
          j = 0;
          while (i < arrlo.length && j < arrhi.length){
               if (arrlo[i].compareToIgnoreCase(arrhi[j]) <= 0){
                    arrlist.remove(k);
                    arrlist.add(k,arrlo[i++]);
               }else if (arrlo[i].compareToIgnoreCase(arrhi[j]) > 0){
                    arrlist.remove(k);
                    arrlist.add(k,arrhi[j++]);
               }k++;
          }
          while (k < hi){
               if (i < arrlo.length){
                    arrlist.remove(k);
                    arrlist.add(k,arrlo[i++]);
               }else if (j < arrhi.length){
                    arrlist.remove(k);
                    arrlist.add(k,arrhi[j++]);
               }k++;
          }
     }
     
     public static boolean checkAnagrams(String main, String check, int n){
          //once lengths are confirmed in text output function,
               // this function will check for anagrams of the two words
                    // of length, n.
          //will return true if all elements of both char arrays match up ONCE for all elements within 
          char[]c1 = new char[n];
          char[]c2 = new char[n];
          int i,j,tot;
          for (i = 0; i < n; i++){
               c1[i] = main.charAt(i);
               c2[i] = check.charAt(i);
          }
          i = 0; tot = 0;
          while (i < n){
               j = 0;
               while (j < n){
                    if (c1[i] == c2[j]){
                         tot++;
                         c2[j] = 0;
                         j = n;
                    }j++;
               }i++;
               if (i != tot) return false;
          }
          return true;
                    
     }

     public static void printFile(List<String> words, String name){
          try {
               File f = new File (name + ".txt");
               f.createNewFile();

               try {
                    FileWriter out = new FileWriter(f);
                    int i;
                    String t1,t2;
                    while (words.size() > 0){
                         t1 = words.get(0).toString();
                         words.remove(0);
                         out.write(t1);
                         i = 0;
                         while (i < words.size()){
                              t2 = words.get(i).toString();
                              if (t1.length() == t2.length() && checkAnagrams(t1,t2, t1.length()) == true){
                                   out.write(" " + t2);
                                   words.remove(i);
                              }else {i++;}     
                    }out.write("\n");

                    
                    }
                        
                    out.close();
               } catch(IOException e){
                    e.printStackTrace();}
               
          } catch(IOException e){ 
               System.out.println("Can not open new file, " + name +  ", name already exists");
               e.printStackTrace();}    
               
          
     }
     public static void main(String[] args) {
          List<String> ex1 = new ArrayList<String>();
          List<String> ex2 = new ArrayList<String>();
          List<String> ex3 = new ArrayList<String>();
          List<String> ex4 = new ArrayList<String>();
  

          try{
               //import/parse/sort example_1--8_words.txt

               File file = new File("example_1--8_words.txt");
               FileReader fr = new FileReader(file);
               BufferedReader br = new BufferedReader(fr);
               String line;
               while ((line = br.readLine()) != null){
                    ex1.add(line);
               }fr.close();
               sortAlpha(ex1,0,8);
               printFile(ex1, "example_1_out.txt");
              

               //import/parse/sort example_2--13_words.txt

               file = new File("example_2--13_words.txt");
               fr = new FileReader(file);
               br = new BufferedReader(fr);
               while ((line = br.readLine()) != null){
                    ex2.add(line);
               }fr.close();
               sortAlpha(ex2,0,13);
               printFile(ex2, "example_2_out.txt");

               //import/parse/sort example_3--19_words.txt

               file = new File("example_3--19_words.txt");
               fr = new FileReader(file);
               br = new BufferedReader(fr);
               while ((line = br.readLine()) != null){
                    ex3.add(line);
               }fr.close();
               sortAlpha(ex3,0,19);
               printFile(ex3, "example_3_out.txt");

               //import/parse/sort example_4--267_words.txt

               file = new File("example_4--267_words.txt");
               fr = new FileReader(file);
               br = new BufferedReader(fr); 
               while ((line = br.readLine()) != null){
                    ex4.add(line);
               }fr.close();
               sortAlpha(ex4,0,267);
               printFile(ex4, "example_4_out.txt");
               
          } catch(IOException e){ e.printStackTrace();}     
               


     }

          //TODO:
          //open new blank text file in write mode
          //using bubble sort style algorithm, 
          //save each set of annograms to seperate text file lines 
          //in alphabetical order(already done)
          //close file and check program
}


