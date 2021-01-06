import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Matrix_chain_multiplication {

	public static List<Integer> matrixList = new ArrayList<Integer>(); 
	public static int[][] M;


	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String[] st_Arr;
		String filePath = "data11_matrix_chain.txt";

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null; 

			if((st = inFile.readLine()) != null) {
				st_Arr = st.split(",");
				matrixList.add(Integer.parseInt(st_Arr[0]));
				matrixList.add(Integer.parseInt(st_Arr[1]));
			}

			while( (st = inFile.readLine()) != null ) {
				st_Arr = st.split(",");
				matrixList.add(Integer.parseInt(st_Arr[1]));
			}

			inFile.close();


			setTable();
			printTable();
		}

	}

	public static void setTable(){

		int n = matrixList.size()-1;

		M = new int[n+1][n+1];

		for(int idx=1;idx <= n; idx++) {
			M[idx][idx] = 0;
		}

		for(int l=2; l<=n;l++) {

			for(int r=1; r<= (n-l+1);r++) {
				int c = r+l-1;
				M[r][c]= (int)Double.POSITIVE_INFINITY;

				for(int k = r; k < c ; k++) {
					int q = M[r][k] + M[k+1][c] + matrixList.get(r-1) * matrixList.get(k) * matrixList.get(c); 
					if(q < M[r][c]) {
						M[r][c] = q;
					}
				}

			}
		}

	}

	public static void printTable() {

		for(int r=1; r< M.length ;r++) {
			for(int c=1;c < M.length ; c++) {
				if(c < r) System.out.print("	");
				else System.out.print(M[r][c]+ "	");
			}
			System.out.println();
		}
	}



}
