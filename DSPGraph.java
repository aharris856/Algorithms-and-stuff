//Dijstra shortest path on a graph (DSPGraph)
//graph is represented by adjacency matrix (adjM)
public class DSPGraph{
	private static final int MAX_D = Integer.MAX_VALUE; //max distance for unchecked location
	//shortest path from src to all other values printed out
	private static void	dijkstra(int[][] adjM, int src)
	{	
		// number of vertecies
		int numV = adjM[0].length;
		
		//make sure it is not a disconnected vertex
		boolean disconnected_node = true;
		for(int i = 0; i < adjM[src].length; i++)
			if(adjM[src][i] > 0)disconnected_node = false;
		if(disconnected_node){
			System.out.println("\n"+src+" is a disconnected vertex, returning... \n");
			return;
		}

		//boolean array that contains if a vertex is incldued 
		//in the shortest path search
		boolean[] included = new boolean[numV];
		//array of shortest distances
		int[] shortestD = new int[numV];

		//populate shortest distance array
		//and included array with unchecked values
		for(int i = 0; i < numV; i++)
		{
			shortestD[i] = MAX_D;
			included[i] = false;
		}

		//array of parents to hold shortest path
		int[] parents = new int[numV];
		//distance from src to itself = 0
		shortestD[src] = 0;
		//head vertex has no parent => -1
		parents[src] = -1;

		//shortest path to each vertex
		for(int i = 1; i < numV; i++)
		{
			//not found initialized min distance and closest vertex
			int closestV = -1;
			int minD = MAX_D;
			//loop through distance values of adj vertices
			//get nearest values per
			for(int index = 0; index < numV; index++)
				if( !included[index] && (shortestD[index] < minD) )
				{
					closestV = index;
					minD = shortestD[index];
				}
			included[closestV] = true;
			//populate parent array of closest vertices
			//and correct for possible closer distances in shortestD arr
			// by finding min distance + edge distance and comparing the sum
			//to shortestD[at given index]
			for(int index = 0; index < numV; index++)
			{
				int edgeD = adjM[closestV][index];
				if( (edgeD > 0) && (shortestD[index] > (minD+edgeD)) ){
					parents[index] = closestV;
					shortestD[index] = minD+edgeD;
				}		
			}
					
		}
		System.out.print("FORMAT:\nv0 -> vd,\tclosest distance,\tRoute / Path");
		//should have poulated solution array
		printSol(src, shortestD, parents);
		System.out.println();
	}
	//print solution method
	private static void printSol(int src, int[] dist, int[] parents)
	{
		int numV = dist.length;
		//state format for user
		for(int i = 0; i < numV; i++)
		{
			if(i != src)
			{
				System.out.println();
				System.out.print(src+" -> "+i+",\t\t");
				System.out.print(dist[i]+",\t\t");
				path(i, parents);				
			}
		}	
			
	}

	//print closest path from src > i (currVertex) using recursion
	private static void path(int currV, int[] parents)
	{
		if(currV == -1)return;//return if at src node 
		path(parents[currV], parents);
		System.out.print(currV+" -> ");
	}

	//main
	public static void main(String[]args)
	{

        int[][] adjM = {
        		{0, 1, 0, 0, 0, 0, 2, 0, 0},
        		{0, 0, 2, 1, 7, 0, 0, 0, 2},
        		{2, 0, 0, 4, 0, 0, 0, 0, 0},
        		{3, 0, 0, 0, 6, 2, 0, 0, 7},
        		{0, 4, 2, 0, 0, 6, 3, 0, 2},
        		{1, 0, 4, 8, 5, 0, 7, 0, 0},
        		{7, 0, 0, 0, 8, 0, 0, 4, 3},
        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
        		{0, 0, 0, 3, 2, 0, 0, 0, 0}};
		//print off every shortest path for every vertex to every vertex
		//note vertex 7 is disconnected (all 0s) 
 		for(int i = 0; i < adjM.length; i++)
 			dijkstra(adjM, i);
	}
		
}
