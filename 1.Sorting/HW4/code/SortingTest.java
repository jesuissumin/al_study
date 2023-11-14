import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						ConsoleWriter.println(String.valueOf(newvalue[i]));
					}
					ConsoleWriter.flush();
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		// TODO : Bubble Sort 를 구현하라.
		// value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
		// 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
		// 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
		// 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.
		int numsize = value.length;
		for (int i = numsize; i > 1; i--){
			for (int j=0; j<i-1; j++){
				if (value[j]>value[j+1]){
					int tmp = value[j];
					value[j] = value[j+1];
					value[j+1] = tmp;
				}
			}
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
	{
		// TODO : Insertion Sort 를 구현하라.
		int numsize = value.length;
		for (int i = 1; i<numsize; i++){
			int newItem = value[i];
			int j = i-1;
			while (value[j]>newItem && j>= 0){
				value[j + 1] = value[j];
				j--;
			}
			value[j + 1] = newItem;
		}
		return (value);
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value) {
		// TODO: Heap Sort를 구현하라.
		int numsize = value.length;
		buildMaxHeap(value, numsize);
	
		int[] tmp = new int[numsize];
		for (int i = numsize - 1; i >= 0; i--) {
			tmp[i] = value[0];
			value[0] = value[i];
			heapify(value, 0, i - 1);
		}
		value = tmp;
		return (value);
	}
	private static void heapify(int[] value, int k, int n) {
		int left = 2 * k + 1;
		int right = 2 * k + 2;
		int larger = k;
	
		if (left <= n && value[left] > value[larger]) {
			larger = left;
		}
	
		if (right <= n && value[right] > value[larger]) {
			larger = right;
		}
	
		if (larger != k) {
			int tmp = value[k];
			value[k] = value[larger];
			value[larger] = tmp;
			heapify(value, larger, n);
		}
	}
	
	private static void buildMaxHeap(int[] value, int num) {
		for (int i = (int) Math.floor(num / 2) - 1; i >= 0; i--) {
			heapify(value, i, num - 1);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value) {
		// TODO: Merge Sort를 구현하라.
		int p = 0;
		int r = value.length - 1; // 배열의 마지막 인덱스를 사용해야 합니다.
		mergeSort(value, p, r);
		return value;
	}
	
	private static void mergeSort(int[] value, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(value, p, q);
			mergeSort(value, q + 1, r);
			merge(value, p, q, r);
		}
	}
	
	private static void merge(int[] value, int p, int q, int r) {
		int i = p;
		int j = q + 1;
		int t = 0;
		int[] tmp = new int[r - p + 1];
		while (i <= q && j <= r) {
			if (value[i] <= value[j]) {
				tmp[t++] = value[i++];
			} else {
				tmp[t++] = value[j++];
			}
		}
		while (i <= q) {
			tmp[t++] = value[i++];
		}
		while (j <= r) {
			tmp[t++] = value[j++];
		}
		i = p;t = 0;
		while (i <= r) {
			value[i++] = tmp[t++];
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value)
	{
		// TODO : Quick Sort 를 구현하라.
		int p = 0; int r = value.length-1;
		quickSort(value, p, r);
		return (value);
	}

	private static void quickSort(int[] value, int p, int r){
		if (p<r) {
			int q = partition(value, p, r);
			quickSort(value, p, q-1);
			quickSort(value, q+1, r);
		}
	}

	private static int partition(int[] value, int p, int r){
		int pivotValue = value[r];
		int i = p-1;
		int tmp = 0;
		for (int j=p; j<r; j++){
			if (value[j]<=pivotValue){
				i++;
				tmp = value[i];
				value[i] = value[j];
				value[j] = tmp;	
			}
		}
		if (i+1!=r) {
			tmp = value[i+1];
			value[i+1] = pivotValue;
			value[r] = tmp;
		}

		return (i+1);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{
		// TODO : Radix Sort 를 구현하라.
		// find max
		int max = value[0];
		int min = value[0];
		int numsize = value.length;
		for (int i=1; i<numsize; i++){
			if (value[i]>max) max = value[i];
			if (value[i]<min) min = value[i];
		}
		int exp = 1;
		while ((max-min)/exp>0){
			DoCountingSort(value, exp, min);
			exp *= 10;
		}
		return (value);
	}

	private static void DoCountingSort(int[] value, int exp, int min)
	{
		int numsize = value.length;
		int[] C = new int[10];
		int[] B = new int[numsize];
		for (int i=1; i<10; i++){
			C[i] += C[i-1];
		}
		for (int i=numsize-1; i>=0; i--){
			B[C[((value[i]-min)/exp)%10]-1] = value[i];
			C[((value[i]-min)/exp)%10]--;
		}
		for (int i=0; i<numsize; i++){
			value[i] = B[i];
		}
	}
}

class ConsoleWriter {
	private static BufferedWriter writer = new BufferedWriter(
			new OutputStreamWriter(System.out));

	static void println(String s) throws IOException {
		writer.write(s);
		writer.newLine();
	}

	static void flush() throws IOException {
		writer.flush();
	}
}
