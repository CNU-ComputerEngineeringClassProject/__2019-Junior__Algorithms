import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


public class Sorting_Test {



	private static int[] num_arrs;

	public static void main(String[] args) throws IOException{

		//�о���� File���
		String filePath = "C:/Users/Una/eclipse-workspace/[AL05]201701977_������_hw01_1/data02.txt";

		String[] st_Arr;
		num_arrs = null;

		File file = new File(filePath); 
		if(file.exists()) { 
			
			BufferedReader inFile = new BufferedReader(new FileReader(file)); 
			
			String st = null; 
			while( (st = inFile.readLine()) != null ) {

				if(st!=null) {
					st_Arr = st.split(",");
					num_arrs = new int[st_Arr.length];
					for(int i=0; i< st_Arr.length ; i++) {
						num_arrs[i] = Integer.parseInt(st_Arr[i]);
					}

					sorting(num_arrs);
				}
			}
			inFile.close();
		}

	}

	public static void sorting(int[] arr) {

		Insert_sort is;
		Merge_sort ms;

		//output File "test.c"�� ������ �����ֵ��� �ϴ� FileWriter fw�� �����ϰ� NooToC�� �����Ͽ� ntc�� �ְ� ntc���� noo�� �°� C�� �ۼ��Ͽ� ���Ͽ� �Է��ϵ����Ѵ�.
		//�� �Է��ߴٸ� fw.close�� �Ѵ�.
		try (FileWriter fw_insert = new FileWriter("hw01_05_201701977_ insertion.txt");){
			is = new Insert_sort(fw_insert,arr);
			is.sort_print();
			fw_insert.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		try (FileWriter fw_merge = new FileWriter("hw01_05_201701977_merge.txt");){
			ms = new Merge_sort(fw_merge,arr);
			ms.sort_print();
			fw_merge.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
