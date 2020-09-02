//radix sort
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
public class RadixSort{
	//sort method
	static void sort(int[] arr)
	{ 
		int radix = 10;

		//Bucket list
		ArrayList<Integer>[] bucket = new ArrayList[radix];
		//initialize arraylists of the array
		for(int i = 0; i < bucket.length; i++) 
			bucket[i] = new ArrayList<Integer>();

		boolean maxSize = false;
		int loc = 1; //location tracker
		int tmp = -1; //temporary value
		while(!maxSize)
		{
			maxSize = true;

			//put arr into lists 
			for(int i = 0; i < arr.length; i++)
			{
				tmp = arr[i]/loc;
				bucket[tmp%radix].add(arr[i]);
				if(maxSize && tmp > 0) maxSize = false; //hit size limit and temp value > 0 => continue loop
			}

			//put lists into arr
			int counter = 0;
			for(int i = 0; i < radix; i++){
				for(Integer j : bucket[i])
					arr[counter++] = j;
				bucket[i].clear();

			}
			//move location to next digit
			loc = loc*radix;
		}

		
	}


///// main /////////////////////////////////////////////////////////////
	public static void main(String[]args)
	{
		int[] arr;
		//no entry in command prompt, ask for entry
		if(args.length < 1){

			Scanner kb = new Scanner(System.in);
			System.out.println("Enter a list of POSITIVE ints to Radix sort (Seperate each int with a space)");
			String input = kb.nextLine();
			kb.close();
			String[] arrStr = input.split("\\s+");
			arr = new int[arrStr.length];
			//parse string input array into integer input array
			try{
				for(int i = 0; i < arrStr.length; i++)
					arr[i] = Integer.parseInt(arrStr[i]);
			}catch(NumberFormatException e){
				System.out.println("INVALID INPUT...\nYou can also enter your input in the command prompt before executing\nExitting...");
				System.exit(0);
			}

		}else{//command prompt had entry
			arr = new int[args.length];
			//parse args array into integer input
			try{
				for(int i = 0; i < args.length; i++)
					arr[i] = Integer.parseInt(args[i]);
			}catch(NumberFormatException e){
				System.out.println("INVALID INPUT... Exitting...");
				System.exit(0);
			}
		}
		//should have our input setup to sort by this point
		System.out.println("\nYour input: \n"+Arrays.toString(arr));
		sort(arr);
		System.out.println("\nSorted input: \n"+Arrays.toString(arr));

	}
}