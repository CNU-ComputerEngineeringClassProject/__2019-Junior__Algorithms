import java.io.FileWriter;
import java.io.IOException;

public class Insert_sort {
	FileWriter fw;
	int[] arr;

	Insert_sort(FileWriter fw, int[] arr){
		this.fw = fw;
		this.arr = arr;
	}

	public void sort_print() {
		this.arr = sortig(arr);
		print_insertArr();
	}

	private int[] sortig(int[] arr_be) {

		int arr_size = arr_be.length;
		int key,temp,i=0;

		if(arr_size < 2 ) return arr_be;

		for(int j = 1; j < arr_size ; j++) {

			key = arr_be[j];

			for(i=j-1 ; 0 <= i ; i--) {

				if(key < arr_be[i]) {

					arr_be[i+1] = arr_be[i];

				}else {
					arr_be[i+1] = key;
					break;
				}
			}

			if(i < 0) {
				arr_be[i+1] = key;
			}
		}

		return arr_be;
	}

	private void print_insertArr() {
		int i=0;

		try {
			fw.write(String.valueOf(arr[i++]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(; i < arr.length ; i++) {
			try {
				fw.write(","+arr[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}
}
