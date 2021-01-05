import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class segmented_Least_squares {

	public static double[][] arr;
	public static int num;
	public static double cost;
	
	public static double[] OPT;
	public static double[][] error;

	public static double[] arr_sxy;
	public static double[] arr_sx;
	public static double[] arr_sy;
	public static double[] arr_sx2;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String[] st_Arr;
		String filePath = "data07.txt";

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null; 

			if( (st = inFile.readLine()) != null ) {

				st_Arr = st.split(",");
				num = (int)Double.parseDouble(st_Arr[0]);
				arr = new double[2][num+1];

				for(int i=1; i <= num; i++) {
					arr[0][i] = Double.parseDouble(st_Arr[2*i-1]);
					arr[1][i] = Double.parseDouble(st_Arr[2*i]);
				}

				cost = Double.parseDouble(st_Arr[st_Arr.length-1]);
			}

			inFile.close();

			SLS();
		}
	}


	public static double SLS() {

		double min, temp;
		double sxy, sx, sy, sx2;
		double a,b;

		OPT = new double[num+1];
		error = new double[num+1][num+1];

		arr_sxy = new double[num+1];
		arr_sx = new double[num+1];
		arr_sy = new double[num+1];
		arr_sx2 = new double[num+1];

		OPT[0]=0;
		arr_sxy[0] = 0; 
		arr_sx[0] = 0;
		arr_sy[0] = 0;
		arr_sxy[0] = 0;

		for(int j=1 ; j<= num ; j++) {

			arr_sxy[j] = arr_sxy[j-1] + (arr[0][j] * arr[1][j]);
			arr_sx[j] = arr_sx[j-1] + (arr[0][j]);
			arr_sy[j] = arr_sy[j-1] + (arr[1][j]);
			arr_sx2[j] = arr_sx2[j-1] + (arr[0][j] * arr[0][j]);

			for(int i=1; i<=j; i++) {

				sxy = arr_sxy[j]-arr_sxy[i-1];
				sx = arr_sx[j]-arr_sx[i-1];
				sy = arr_sy[j]-arr_sy[i-1];
				sx2 = arr_sx2[j]-arr_sx2[i-1];

				a = (double) (((j-i+1)*sxy - sx*sy) / ((j-i+1)*sx2 - sx*sx));
				b = (double) ((sy - a*sx) / (j-i+1));

				error[i][j] = 0;

				for(int idx = i; idx<=j;idx++) {
					error[i][j] += (double) Math.pow((arr[1][idx] - a*arr[0][idx] -b), 2);				
				}
			}
		}

		int idx_m=1;

		for(int j=1;j<= num;j++) {
			min = error[1][j] + cost + OPT[0];
			idx_m = 1;

			for(int i=2 ; i<=j ; i++) {

				temp = error[i][j] + cost + OPT[i-1];

				if(temp < min) {
					min = temp;
					idx_m = i;
				}
			}
			OPT[j] = min;
		}

		System.out.print("Cost of the optimal solution : ");
		System.out.println(String.format("%.6f", OPT[num]));
		System.out.println("\nAn optimal solution : ");

		print(1,idx_m-1);
		print(idx_m,num);
		
		return OPT[num];
	}
	
	public static void print(int q, int w) {
		
		double sxy = arr_sxy[w]-arr_sxy[q-1];
		double sx = arr_sx[w]-arr_sx[q-1];
		double sy = arr_sy[w]-arr_sy[q-1];
		double sx2 = arr_sx2[w]-arr_sx2[q-1];

		double a = (double) (((w-q+1)*sxy - sx*sy) / ((w-q+1)*sx2 - sx*sx));
		double b = (double) ((sy - a*sx) / (w-q+1));
		
		System.out.print("[Segment "+q+" - "+w);
		System.out.print("] : y = "+String.format("%.6f", a));
		System.out.print(" * x + "+String.format("%.6f", b));
		System.out.println(" // square error : "+String.format("%.6f", error[q][w]));
	}

}
