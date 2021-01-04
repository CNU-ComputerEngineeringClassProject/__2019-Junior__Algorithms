import java.util.ArrayList;
import java.util.List;

public class Heap_make {

	static List<Node> heap;
	
	Heap_make(){
		heap = new ArrayList<Node>();
	}

	private List<Node> max_heapify(List<Node> arr, int idx){

		int l_idx= idx*2+1;
		int r_idx= idx*2+2;
		int largest=idx;

		if(l_idx < arr.size() && arr.get(idx).getkey() < arr.get(l_idx).getkey()) {
			largest = l_idx;
		}
		if(r_idx < arr.size() && arr.get(largest).getkey() < arr.get(r_idx).getkey()) {
			largest = r_idx;
		}

		if(largest!=idx) {
			Node temp = arr.get(idx);
			Node lar_temp = arr.get(largest);

			arr.remove(idx);
			arr.add(idx, lar_temp);

			arr.remove(largest);
			arr.add(largest, temp);

			arr = max_heapify(arr, largest);
		}

		return arr;
	}

	public void build_max_heap(){

		for(int i=(int)Math.floor(heap.size()/2); 0 < i ; i--) {
			heap = max_heapify(heap,i-1);
		}

	}

	public int size() {
		return heap.size();
	}

	public Node get(int i) {
		return heap.get(i);
	}
	
	public void remove(int i) {
		heap.remove(i);
	}
	
	public void add(int i,Node node) {
		heap.add(i,node);
	}
	
	public void add(Node node) {
		heap.add(node);
	}
}
