import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test_Quick_Sort{


	public static void main (String[] args)throws IOException  {
		// TODO Auto-generated method stub


		String[] st_Arr;
		String filePath = "data05.txt";
		
		List<Integer> arr =  new ArrayList<Integer>();

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null; 

			if( (st = inFile.readLine()) != null ) {

				st_Arr = st.split(",");

				for(int i=0; i< st_Arr.length ; i++) {
					arr.add(Integer.parseInt(st_Arr[i]));
				}


			}
			inFile.close();


			FileWriter fw_q = new FileWriter("hw05_05_201701977_quick.txt");
			List<Integer> arr_q = quickSort(arr,0,arr.size()-1);


			FileWriter fw_qr = new FileWriter("hw05_05_201701977_quickRandom.txt");
			List<Integer> arr_qr = quickSort_withRandom(arr,0,arr.size()-1);

			int idx=0;

			for(; idx < arr.size()-1 ;idx++) {
				fw_q.write(arr_q.get(idx)+",");
				fw_qr.write(arr_qr.get(idx)+",");
			}

			fw_qr.write(String.valueOf(arr_qr.get(idx)));
			fw_q.write(String.valueOf(arr_q.get(idx)));
			
			fw_q.close();
			fw_qr.close();

		}

	}

	private static int partition (List<Integer> A, int p, int r) {

		Integer pivot = A.get(r);
		int i = p-1;
		int j = p;
		Integer temp;

		for(; j < r; j++) {

			if(A.get(j).compareTo(pivot) <= 0 ) {

				i++;
				temp = A.get(j);
				A.set(j, A.get(i));
				A.set(i, temp);
			}	
		}

		i++;

		temp = A.get(j);
		A.set(j, A.get(i));
		A.set(i, temp);


		return i;

	}

	private static int randomizedPartition (List<Integer> A, int p, int r) {

		int i = random(A,p,r);

		Integer temp = A.get(r);
		A.set(r, A.get(i));
		A.set(i, temp);

		return partition(A,p,r);
	}	

	private static int random(List<Integer> A,int p, int r) {

		Random rand = new Random(); 
		int one, two, three;
		int min, max, avg;
		
		one = rand.nextInt(r-p+1) + p;
		two = rand.nextInt(r-p+1) + p;
		three = rand.nextInt(r-p+1) + p;
		
		while(one==two) {
			two = rand.nextInt(r-p+1) + p;	
		}
		
		while(one==three || two==three) {
			three = rand.nextInt(r-p+1) + p;	
		}
		
		if(A.get(one) < A.get(two)) {
			min = one;
			max = two;
		}else {
			min = two;
			max = one;	
		}
		
		if(A.get(min) < A.get(three)) {
			avg = three;
		}else {
			avg = min;
			min = three;	
		}
		
		if(A.get(avg) > A.get(max)) {
			avg = max;
		}
		
		return avg;
	}

	private static List<Integer> quickSort (List<Integer> A, int p, int r) {

		if(p >= r) return A;

		int q = partition(A,p,r);

		A = quickSort(A, p, q-1);
		A = quickSort(A, q+1, r);


		return A;

	}

	private static List<Integer> quickSort_withRandom (List<Integer> A, int p, int r) {

		if( (r-p+1) < 3 ) return A;

		int q = randomizedPartition(A,p,r);

		A = quickSort_withRandom(A, p, q-1);
		A = quickSort_withRandom(A, q+1, r);

		return A;

	}

}
