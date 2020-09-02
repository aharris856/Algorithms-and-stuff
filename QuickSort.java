//quick sort implementation
import java.util.Scanner;
import java.util.Arrays;
public class QuickSort{

	//recurseive sort method
	static void sort(int[] arr, int lo, int hi)
	{
		//if low < hi, continue recursion
		if(lo < hi){
			//get pivot index
			//sort both sides of pivot using recursion
			int index = pivot(arr, lo, hi);
			sort(arr, lo, index-1);
			sort(arr, index+1, hi);
			return;
		}
	}
	//pivot sort
	static int pivot(int[] arr, int lo, int hi)
	{
		int val = arr[hi];
		//tracker int tr for lower index 
		int tr = lo-1; 
		for(int i = lo; i < hi; i++)
			if(arr[i] <= val)
			{
				//lower array point is larger than higher val, swap and increment
				tr++;
				int hold = arr[tr];
				arr[tr] = arr[i];
				arr[i] = hold;
			}
		//swap 1+last tracked value in array with val
		int hold = arr[tr+1];
		arr[tr+1] = val;
		arr[hi] = hold;
		return tr+1;
	}


///////////// main method ///////////////////////////////////////////////////////////////////////////
	public static void main(String[]args)
	{
		int[] arr;
		//no entry in command prompt, ask for entry
		if(args.length < 1){

			Scanner kb = new Scanner(System.in);
			System.out.println("Enter a list of ints to Quick sort (Seperate each int with a space)");
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

		}else{//command promt had entry
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
		sort(arr, 0, arr.length-1);
		System.out.println("Input Sorted: \n"+Arrays.toString(arr));
	}
}
