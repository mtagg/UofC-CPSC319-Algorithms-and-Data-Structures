
public class binTreeNode{
     public int freq;
     public String word;
     binTreeNode left;
     binTreeNode right;


     public binTreeNode(String w){
          //main-constructor
          word = w; //tracked word from .txt input
          freq = 1;  //frequency that the tracked word appears in the input
          left = null; //space holder in the case that a sub-node to the left is needed later
          right = null; //space holder in the case a right sub-node is needed later
     }

     public binTreeNode(){
          //default-null constructor
          word = null;
          freq = 0;
          left = null;
          right = null;
     }
}

