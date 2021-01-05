import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invariant_hw {

	public static void main (String[] args)throws IOException  {
		// TODO Auto-generated method stub

		String[] st_ArrA;
		String[] st_ArrB;
		String filePathA = "data06_a.txt";
		String filePathB = "data06_b.txt";

		List<Integer> arrA =  new ArrayList<Integer>();
		List<Integer> arrB =  new ArrayList<Integer>();

		File file = new File(filePathA); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 
			String st = null; 

			if( (st = inFile.readLine()) != null ) {

				st_ArrA = st.split(", ");

				for(int i=0; i< st_ArrA.length ; i++) {
					arrA.add(Integer.parseInt(st_ArrA[i]));
				}


			}
			inFile.close();

			File file1 = new File(filePathB); 
			if(file1.exists()) { 

				BufferedReader inFile1 = new BufferedReader(new FileReader(file1)); 
				String st1 = null; 

				if( (st1 = inFile1.readLine()) != null ) {

					st_ArrB = st1.split(", ");

					for(int i=0; i< st_ArrB.length ; i++) {
						arrB.add(Integer.parseInt(st_ArrB[i]));
					}


				}
				inFile1.close();

				invariant_m(arrA, arrB);
			}
		}

	}

	public static void invariant_m (List<Integer> A, List<Integer> B) {
		int a_s=0, a_e=A.size()-1, a_m;
		int b_m;
		int n = A.size();
	

		while( a_s < a_e ) {	

			a_m = (a_e+a_s)/2;
			b_m = n - (a_m+1);

			if(A.get(a_m) < B.get(b_m)) {
				if(a_s != a_m) a_s = a_m;
				else {
					if(A.get(a_m) < B.get(b_m-1))a_s = a_e;
					else a_e = a_s;
				}
			}else {	
				a_e = a_m;
			}

		}
		
		a_m = (a_e+a_s)/2;
		b_m = n - (a_m+1);
		
		if(A.get(a_m) < B.get(b_m)) {
				System.out.println(A.get(a_m));
		}else {
				System.out.println(B.get(b_m));
		}			

	}

}