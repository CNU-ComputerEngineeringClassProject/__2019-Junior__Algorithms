
public class Inversion {
	int[] arr_inversion;
	
	public Inversion(int[] arr) {
		this.arr_inversion = arr;
	}
	
	public int sort_and_count(int[] arr, int stidx, int edidx) {
		
		
		if(edidx <= stidx) {
			return 0;
		}
		

		int m = (int) Math.floor((stidx+edidx)/2);
		int fi_count = sort_and_count(arr,stidx,m);
		int se_count = sort_and_count(arr,m+1,edidx);
		int m_count = merge_and_count(arr,stidx,m,edidx);
		
		return fi_count+se_count+m_count;
		
	}
	
	private int merge_and_count(int[] arr, int stidx, int midx ,int edidx) {
		
		int in_count=0;
		int fi_idx = stidx, se_idx = midx+1;
		int[] L = new int[edidx - stidx +1];
		int idx=0;
		
		while(fi_idx <= midx && se_idx <= edidx) {
			
			if(arr[fi_idx] <= arr[se_idx]) {
				L[idx++] = arr[fi_idx++];
			}else{
				in_count = in_count+(midx+1-fi_idx);
				L[idx++] = arr[se_idx++];
			}
		}
		
		if(fi_idx > midx) {
			System.arraycopy(L, 0, arr, stidx, idx);
		}else if(se_idx > edidx) {
			System.arraycopy(arr, fi_idx, arr, stidx+idx, (midx+1)-fi_idx);
			System.arraycopy(L, 0, arr, stidx, idx);
		}
		
		this.arr_inversion = arr;
		return in_count;
	}

}
