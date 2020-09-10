import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


// Source: https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
// A Java program for Dijkstra's 
// single source shortest path 
// algorithm. The program is for 
// adjacency matrix representation 
// of the graph. 

class DijkstrasAlgorithm { 

	private static final int NO_PARENT = -1; 

	//Task 1:
	public static String getFileName(){
		
		System.out.println("\n\nType a filename containing the matrix you wish to parse.");
		System.out.println("(exclude the '.txt'. when finished, press the ENTER key. ");
		System.out.print(">> ");
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		}catch(IOException e){e.printStackTrace();}
		return null;
	}//EndTask

	//Task 2:
	public static int[][] parseFile(String fileName){
		List <String> lines = FileIO.readFile(fileName); 
		int i,j,k, n = lines.size();
		// clear '#' marks in the .txt files to loop through the matrix exclusively
		for (i=0; i<n; i++){
			if ( (lines.get(i)).charAt(0) == '#'){ lines.remove(i--);n--;}
		}
		n = lines.size(); 
		int[][] mat = new int[n][n]; //return object after parsing
		String temp; //object to extract string digits before parsing to integer matrix
		int len; // object holding length of each line of text whilst parsing
		for (i=0;i<n;i++){
			j = 0;
			k = 0;
			len = lines.get(i).length();
			while (j < len){//break to new line when end of line is reached at any stage
				temp = "";
				while (j < len && lines.get(i).charAt(j) == ' ') j++; //filter empty spaces
				while (j < len && lines.get(i).charAt(j) != ' ') temp += lines.get(i).charAt(j++);//create string rep of next number
				mat[i][k++] = Integer.parseInt(temp);//convert string number into type int	
			}
		}

		//for(i=0;i<n;i++){for(j=0;j<n;j++){System.out.print(mat[i][j] + " ");}System.out.print("\n");}
		return mat;
	}//EndTask





	// Function that implements Dijkstra's 
	// single source shortest path 
	// algorithm for a graph represented 
	// using adjacency matrix 
	// representation 
	//Task 3:
	//Modified function declaration to accept filename for printing to output file
	public static void dijkstra(int[][] adjacencyMatrix,int startVertex, String outputPath) { 
	//EndTask	
	 	int nVertices = adjacencyMatrix[0].length; 

		// shortestDistances[i] will hold the 
		// shortest distance from src to i 
		int[] shortestDistances = new int[nVertices]; 

		// added[i] will true if vertex i is 
		// included / in shortest path tree 
		// or shortest distance from src to 
		// i is finalized 
		boolean[] added = new boolean[nVertices]; 

		// Initialize all distances as 
		// INFINITE and added[] as false 
		for (int vertexIndex = 0; vertexIndex < nVertices; 
											vertexIndex++) 
		{ 
			shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
			added[vertexIndex] = false; 
		} 
		
		// Distance of source vertex from 
		// itself is always 0 
		shortestDistances[startVertex] = 0; 

		// Parent array to store shortest 
		// path tree 
		int[] parents = new int[nVertices]; 

		// The starting vertex does not 
		// have a parent 
		parents[startVertex] = NO_PARENT; 

		// Find shortest path for all 
		// vertices 
		for (int i = 1; i < nVertices; i++) 
		{ 

			// Pick the minimum distance vertex 
			// from the set of vertices not yet 
			// processed. nearestVertex is 
			// always equal to startNode in 
			// first iteration. 
			int nearestVertex = -1; 
			int shortestDistance = Integer.MAX_VALUE; 
			for (int vertexIndex = 0; 
					vertexIndex < nVertices; 
					vertexIndex++) 
			{ 
				if (!added[vertexIndex] && 
					shortestDistances[vertexIndex] < 
					shortestDistance) 
				{ 
					nearestVertex = vertexIndex; 
					shortestDistance = shortestDistances[vertexIndex]; 
				} 
			} 

			// Mark the picked vertex as 
			// processed 
			added[nearestVertex] = true; 

			// Update dist value of the 
			// adjacent vertices of the 
			// picked vertex. 
			for (int vertexIndex = 0; 
					vertexIndex < nVertices; 
					vertexIndex++) 
			{ 
				int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 
				
				if (edgeDistance > 0
					&& ((shortestDistance + edgeDistance) < 
						shortestDistances[vertexIndex])) 
				{ 
					parents[vertexIndex] = nearestVertex; 
					shortestDistances[vertexIndex] = shortestDistance + 
													edgeDistance; 
				} 
			} 
		} 

		printSolution(startVertex, shortestDistances, parents); 
		//Task 3:
		//Call to String creating functions to be passed to FileIO.java for output file writing
		String outputData = dataToString(startVertex, shortestDistances, parents);
		FileIO.writeFile(outputPath, outputData);
		//EndTask
	} 

	// A utility function to print 
	// the constructed distances 
	// array and shortest paths 
	private static void printSolution(int startVertex, 
									int[] distances, 
									int[] parents) 
	{ 
		int nVertices = distances.length; 
		System.out.print("Vertex\t Distance\tPath"); 
		
		for (int vertexIndex = 0; 
				vertexIndex < nVertices; 
				vertexIndex++) 
		{ 
			if (vertexIndex != startVertex) 
			{ 
				System.out.print("\n" + startVertex + " -> "); 
				System.out.print(vertexIndex + " \t\t "); 
				System.out.print(distances[vertexIndex] + "\t\t"); 
				printPath(vertexIndex, parents); 
			} 
		} 
	} 

	// Function to print shortest path 
	// from source to currentVertex 
	// using parents array 
	private static void printPath(int currentVertex, 
								int[] parents) 
	{ 
		
		// Base case : Source node has 
		// been processed 
		if (currentVertex == NO_PARENT) 
		{ 
			return; 
		} 
		printPath(parents[currentVertex], parents); 
		System.out.print(currentVertex + " "); 
	} 
	






	//Task 3:
	// Code copied/modified from the printSolution
	// function above to output a string instead of print
	private static String dataToString(int startVertex, int[] distances, int[] parents) {
		
		String Data = "";
		int nVertices = distances.length; 
		Data += "Vertex\t   Distance\t   Path\n"; 
		
		//replaced all print calls from printSolution with String modifications
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++){ 
			if (vertexIndex != startVertex) { 
				Data += Integer.toString(startVertex) + " -> "; 
				Data += Integer.toString(vertexIndex) + " \t\t "; 
				Data += Integer.toString(distances[vertexIndex]) + "\t\t"; 
				Data += pathToString(vertexIndex, parents); 
				Data += "\n";
			} 
		} return Data;
	} //EndTask

	//Task 3:
	// Code copied/modified from the printPath
	// function above to output a string instead of print
	private static String pathToString(int currentVertex, int[] parents) { 
		
		// Base case : Source node has 
		// been processed, return "".
		if (currentVertex == NO_PARENT) { 
			return ""; 
		} String pathData = "";
		pathData += pathToString(parents[currentVertex], parents); 
		//replaced printPath's print calls with String ammendments
		return pathData += Integer.toString(currentVertex) + " "; 	
	}//EndTask








	// Driver Code 
	public static void main(String[] args) 
	{ 
		int[][] adjacencyMatrix = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
				            { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
					    { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
					    { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
					    { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
					    { 0, 0, 4, 0, 10, 0, 2, 0, 0 }, 
					    { 0, 0, 0, 14, 0, 2, 0, 1, 6 }, 
					    { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
					    { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
//Task 3: 
// Had to change this function call to compensate for FileWriting		
		dijkstra(adjacencyMatrix, 0, "NoFile"); 
//EndTask
	} 
} 

// This code is contributed by Harikrishnan Rajan 
