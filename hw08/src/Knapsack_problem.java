import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack_problem {

	public static List<Integer> ItemNum;
	public static List<Integer> Value;
	public static List<Integer> Weight;
	public static int[][] OPT;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String[] st_Arr;
		String filePath = "data09_knapsack.txt";

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null; 
			ItemNum = new ArrayList<Integer>();
			Value = new ArrayList<Integer>();
			Weight = new ArrayList<Integer>();
			
			ItemNum.add(0);
			Value.add(0);
			Weight.add(0);

			while( (st = inFile.readLine()) != null ) {
				st_Arr = st.split(",");
				ItemNum.add(Integer.parseInt(st_Arr[0]));
				Value.add(Integer.parseInt(st_Arr[1]));
				Weight.add(Integer.parseInt(st_Arr[2]));
			}

			inFile.close();
		}

		Scanner scanner = new Scanner(System.in);
		int bagsize = -1;

		while(bagsize<0 || 50<bagsize) {			
			System.out.print("배낭의 사이즈를 입력하세요(0~50) : ");
			bagsize = scanner.nextInt();
		}

		scanner.close();

		tableOPT(bagsize);
		analyzeTable(bagsize);
	}

	public static void tableOPT(int bag_size) {
		
		OPT = new int[ItemNum.size()][bag_size+1];

		for(int i=0; i < ItemNum.size(); i++) {
			for(int w=0; w <= bag_size ; w++) {
				if(i==0) {
					OPT[0][w] = 0;
				}else if(Weight.get(i) > w) {
					OPT[i][w] = OPT[i-1][w];
				}else {
					int temp1 = OPT[i-1][w];
					int temp2 = Value.get(i)+OPT[i-1][w-Weight.get(i)];
					OPT[i][w] = temp1 > temp2? temp1 : temp2;
				}
			}
		}

		for(int i=0; i < ItemNum.size(); i++) {
			for(int w=0; w <= bag_size ; w++) {
				System.out.print(String.format("%3d", OPT[i][w])+"  ");
			}
			System.out.println();
		}

	}

	public static void analyzeTable(int bag_size) {

		int item = ItemNum.size()-1; 
		int w = bag_size;

		List<Integer> itemSel=new ArrayList<Integer>();
		int itemValue= OPT[item][w];
		int itemWeight= w;


		while( 0 < item && 0 < itemWeight ) {
			if(OPT[item][itemWeight] == OPT[item-1][itemWeight]) {
				item--;	
			}else {
				if(itemValue == OPT[item][itemWeight]) {

					itemSel.add(ItemNum.get(item));

					itemValue -= Value.get(item);
					itemWeight -= Weight.get(item);
				}
				item--;	
			}
		}


		System.out.println("max: " + OPT[ItemNum.size()-1][bag_size]);
		System.out.print("item: ");
		for(int j=itemSel.size()-1; 0 <=j ;j--) {
			System.out.print(itemSel.get(j)+" ");
		}

	}

}
