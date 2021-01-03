import java.io.FileWriter;
import java.io.IOException;

public class Merge_sort {
	FileWriter fw;
	int[] arr;
	int mergenum;
	
	Merge_sort(FileWriter fw, int[] arr){
		this.fw = fw;
		this.arr= arr;
		this.mergenum = 0;
	}
	
	public void sort_print() {
		this.arr = mergeSort(arr, 0, arr.length-1);
		print_insertArr();
	}


	
private int[] mergeSort(int[] arr_be, int st, int ed) {
		
		if(st<ed) {
			int m = (int) Math.floor((st+ed)/2);
			mergeSort(arr_be, st,m );
			mergeSort(arr_be, m+1 , ed );
			return merge(arr_be, st, m ,ed);
			
		}
		return arr_be;
	}
	
	private int[] merge(int[]arr_be, int st_idx,int m_idx,int ed_idx) {
		
		int[] mix = new int[ed_idx - st_idx +1];
		int f=st_idx, s=m_idx+1;
		int idx=0;
		this.mergenum++;
		
		while(f!=m_idx && s!=ed_idx) {
			if(arr_be[f] <= arr_be[s]) {
				mix[idx++] = arr_be[f++];
			}else{
				mix[idx++] = arr_be[s++];
			}
		}
		
		if(f==m_idx) {
			System.arraycopy(mix, 0, arr_be, st_idx, idx);
		}else if(s==ed_idx) {
			System.arraycopy(arr_be, f, arr_be, st_idx+idx, m_idx-f);
			System.arraycopy(mix, 0, arr_be, st_idx, idx);
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
		
		try {
			fw.write(","+this.mergenum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
