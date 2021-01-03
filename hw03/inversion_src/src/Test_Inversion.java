import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test_Inversion {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String[] st_Arr;
		int[] arr = null;
		int result;
		String filePath = "data03_Inversion.txt";


		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null; 
			try {
				if( (st = inFile.readLine()) != null ) {

					System.out.println("Input Data : "+st);

					st_Arr = st.split(",");
					arr = new int[st_Arr.length];
					for(int i=0; i< st_Arr.length ; i++) {
						arr[i] = Integer.parseInt(st_Arr[i]);
					}

					Inversion inversion = new Inversion(arr);
					result = inversion.sort_and_count(arr,0,arr.length-1);

					System.out.println("Output Data : "+result);

				}
				inFile.close();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}





	}

}
