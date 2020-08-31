//Breadth first search graph traversal from given starting vertex v
import java.util.*;

public class BFSGraph{
/////////////////////////////////////////////////////////////////////////////////////////////////////
//Graph class
@SuppressWarnings("unchecked")
static class Graph{
	private int numV; //number of vertices
	private LinkedList<Integer> adjList[];

	//constructor
	Graph(int numV)
	{
		this.numV = numV;//set the numv to input of numv
		adjList = new LinkedList[numV];//adjlist of size numv
		//populate adjlist
		for(int i = 0; i < numV; i++)
			adjList[i] = new LinkedList();
	}

	//add edge given location, weight
	public void addE(int loc, int w){ adjList[loc].add(w); }

	public void BFS(int src)
	{
		boolean isVisited[] = new boolean[numV]; //visited boolean array (false = not visisted)
		Queue<Integer> queue = new LinkedList<Integer>(); 

		//set initial source to visited
		//add source to queue
		//iterate through queue with iterator on adjlist array
		isVisited[src] = true; 
		queue.add(src);
		while(queue.size()!=0)//queue isn't empty
		{
			src = queue.poll();
			System.out.print(src+" -> ");
			//iterator to increment through adjlist array at point source
			Iterator<Integer> iter = adjList[src].listIterator();
			while(iter.hasNext())
			{
				int loc = iter.next();// location (loc) = next iterator
				if(!isVisited[loc]){
					//if it has not been visited mark location as visited and add loc to queue
					isVisited[loc] = true;
					queue.add(loc);
				}
			}
		}
	}
}
////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[]args)
	{
		/*graph that has nodes as follows (format of description in this comment start -> (all destination values))
		0 -> (1)
		1 -> (0, 3, 5)
		2 -> (3, 4)
		3 -> (1, 2, 8)
		4 -> (0, 5, 6)
		5 -> (1, 6)
		6 -> (4, 5, 7, 8)
		7 -> (6)
		8 -> (3, 6) */
		//new graph of size 9 (vertices/nodes 0-8)
		Graph graph = new Graph(9){{
			addE(0, 1);
			addE(1, 0); 
			addE(1, 3);
			addE(1, 5);
			addE(2, 3);
			addE(2, 4);
			addE(3, 1);
			addE(3, 2);
			addE(3, 8);
			addE(4, 0);
			addE(4, 5);
			addE(4, 6);
			addE(5, 1);
			addE(5, 6);
			addE(6, 4);
			addE(6, 5);
			addE(6, 7);
			addE(6, 8);
			addE(7, 6);
			addE(8, 3);
			addE(8, 6);
		}};
		System.out.println("BFS Traversal of graph from node 0");
		//traverse Graph from 0 bfs
		graph.BFS(0);
	}
}
