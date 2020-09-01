//Breadth first search graph traversal from given starting vertex src
//Depth first search graph traversal from given vertex src
import java.util.*;

public class XFSGraph{
/////////////////////////////////////////////////////////////////////////////////////////////////////
//Graph class
	@SuppressWarnings("unchecked")
	static class Graph{
		private int numV; //number of vertices
		private LinkedList<Integer> adjList[]; //Adjacency List

		//constructor
		Graph(int numV)
		{
			this.numV = numV;//set the numv to input of numv
			adjList = new LinkedList[numV];//adjlist of size numv
			//populate adjlist
			for(int i = 0; i < numV; i++)
				adjList[i] = new LinkedList();
		}

		//add edge given location, destination
		public void addE(int loc, int dest){ adjList[loc].add(dest); }

		//BFS
		public void BFS(int src)
		{
			boolean[] isVisited = new boolean[numV]; //visited boolean array (false = not visited)
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
		//////////////////////END BFS
		
		//DFS
		public void DFS(int src)
		{
			//initiate non visited arraylist of visited checks
			ArrayList<Boolean> isVisited = new ArrayList<Boolean>();
			for(int i = 0; i < numV; i++)
				isVisited.add(false);

			//dfs stack
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(src);
			while(!stack.empty())
			{
				//pop node from stack
				src = stack.peek();
				stack.pop();

				//print item if !isVisited
				if(!isVisited.get(src)){
					System.out.print(src+" -> ");
					isVisited.set(src, true);
				}

				//get all adjacencies and iterate into stack
				Iterator<Integer> iter = adjList[src].iterator();
				while(iter.hasNext())
				{
					int currV = iter.next();
					if(!isVisited.get(currV))stack.push(currV);
				}
			}

		}
		
	}
////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[]args)
	{
		startMenu();
	}
	static void startMenu()
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

		Scanner kb = new Scanner(System.in);
		System.out.println("Choose a graph traversal '1' >>> bfs OR '2' >>> dfs : ");
		String entry1 = kb.nextLine();
		//check if entry1 is valid
		if(!(entry1.equalsIgnoreCase("1")) && !(entry1.equalsIgnoreCase("2"))){
			System.out.println("INVALID ENTRY... ");
			startMenu();
			return;
		}
		System.out.print("You selected: "+entry1+" >>> ");
		if(entry1.equals("1"))System.out.println("BFS");
		else System.out.println("DFS");

		System.out.println("Choose a starting vertex (enter a number 0-8): ");
		String entry2 = kb.nextLine();
		//check if entry2 is a number
		int entryVal;
		try{
			entryVal = Integer.parseInt(entry2);
		}catch(NumberFormatException e){
			System.out.println("INVALID ENTRY... ");
			startMenu();
			return;
		}
		//check if entry 2 is in the number range
		if(entryVal < 0 || entryVal > 8){
			System.out.println("INVALID ENTRY... ");
			startMenu();
			return;
		}
		kb.close();
		//if 1 -> bfs(entryVal) else dfs(entryVal) >>> Entry val = starting vertex
		if(entry1.equals("1")){
			System.out.println("BFS Traversal starting at node "+entryVal);
			graph.BFS(entryVal);
		}
		else{ 
			System.out.println("DFS Traversal starting at node "+entryVal);
			graph.DFS(entryVal);
		}
	}
}
