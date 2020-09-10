
class Asgmt3 {
	public static void main(String[] args) {
		//Task 1:
		//Fetch+Parse input .txt file
		String inputFileName = DijkstrasAlgorithm.getFileName();
		
		if (inputFileName == null){
			System.out.println("Invalid input file name request");
			System.exit(0);
		}
		int[][] matrix = DijkstrasAlgorithm.parseFile(inputFileName + ".txt"); 

		//Modified dijkstra function to take inputFileName for writing to output file later
		DijkstrasAlgorithm.dijkstra(matrix, 0, inputFileName +  "_dijkstra_output.txt");
		//EndTask
	}

}