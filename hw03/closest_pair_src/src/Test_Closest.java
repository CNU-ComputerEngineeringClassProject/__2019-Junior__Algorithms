import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test_Closest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String[] st_Arr;
		double result;
		double x,y;
		String filePath = "data03_closest.txt";

		int count=0;

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 
			BufferedReader in = new BufferedReader(new FileReader(file)); 

			String input_st = null;
			while( (input_st=in.readLine()) != null ) {
				if(count == 0) System.out.println("Input Data : "+input_st);
				else System.out.println("\t     "+input_st);
				count++;
			}
			in.close();

			if(count < 2) {
				System.out.println("Output Data : 오류 - 좌표데이터가 1개 이하입니다.");
			}else {
				String st = null;
				Closest closest = new Closest(count);
				try {
					while( (st = inFile.readLine()) != null ) {
						
						st_Arr = st.split(",");
						x = Double.parseDouble(st_Arr[0]);
						y = Double.parseDouble(st_Arr[1]);
						closest.add(x,y);
					}

					result = closest.closest_start();
					System.out.println(String.format("Output Data : %.3f",result));

					inFile.close();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

	}

}
