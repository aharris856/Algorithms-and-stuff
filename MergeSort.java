//merge sort implementation
import java.util.Scanner;
import java.util.Arrays;
public class MergeSort{
	//sort method
	static void sort(int[] arr, int lo, int hi)
	{
		if(lo < hi){
			int mid = lo+(hi-lo)/2; //same as (lo+hi)/ but not adding to avoid going over max int
			//sort from mid point on each side
			sort(arr, lo, mid);
			sort(arr, mid+1, hi);
			//combine the halves
			merge(arr, lo, mid, hi);
		}
	}
	static void merge(int[] arr, int lo, int mid, int hi)
	{
		int sizeL = mid-lo+1;
		int sizeR = hi - mid;

		//arrays to hold vals and fill
		int[] leftArr = new int[sizeL];
		int[] rightArr = new int[sizeR];

		for(int i = 0; i < sizeL; i++)
			leftArr[i] = arr[lo+i];
		
		for(int i = 0; i < sizeR; i++)
			rightArr[i] = arr[i+mid+1];

		//index left and index right to increment and combine
		int iL = 0;
		int iR = 0;
		int index = lo;
		while(iR < sizeR && iL < sizeL)
		{
			if(leftArr[iL] <= rightArr[iR]){
				arr[index] = leftArr[iL];
				iL++;
			}else{
				arr[index] = rightArr[iR];
				iR++;
			}
			index++;
		}

		//copy the rest of left and right arrays if anything is remaining
		while(iL < sizeL)
		{
			arr[index] = leftArr[iL];
			iL++;
			index++;
		}
		while(iR < sizeR)
		{
			arr[index] = rightArr[iR];
			iR++;
			index++;
		}
	}
///// main ////////////////////////////////////////////////////////////////////
	public static void main(String[]args)
	{
		int[] arr;
		//no entry in command prompt, ask for entry
		if(args.length < 1){

			Scanner kb = new Scanner(System.in);
			System.out.println("Enter a list of ints to Merge sort (Seperate each int with a space)");
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
