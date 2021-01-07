import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra_AL {

	public static int[][] w;
	public static int[] d;
	public static List<Integer> S;
	public static List<Integer> Q;
	public static List<String> vertex;

	public static void main(String[] args) {

		init_Graph();
		dijstra_algorithm();

	}


	public static void init_Graph() {
		
		w = new int[5][5];
		d = new int[5];
		vertex = new ArrayList<String>();

		/* (정점기호 = w 인덱스번호) (A=0) (B=1) (C=2) (D=3) (E=4) */
		vertex.add("A");
		vertex.add("B");
		vertex.add("C");
		vertex.add("D");
		vertex.add("E");
		
		for(int i = 0; i < 5 ; i++) {
			for(int j =0;j <5; j++) {
				w[i][j] = Integer.MAX_VALUE;
			}
			w[i][i]=0;
		}
	
		/*
		 * A => B(10), C(3)
		 * B => C(1), D(2)
		 * C => B(4), D(8), E(2)
		 * D => E(7)
		 * E => D(9)
		 */

		w[0][1] = 10;
		w[0][2] = 3;
		w[1][2] = 1;
		w[1][3] = 2;
		w[2][1] = 4;
		w[2][3] = 8;
		w[2][4] = 2;
		w[3][4] = 7;
		w[4][3] = 9;

		d[0] = 0;
		for(int i=1;i<5;i++) {
			d[i] = Integer.MAX_VALUE;
		}
		
	}

	public static void dijstra_algorithm() {
		
		S = new ArrayList<Integer>();
		Q = new ArrayList<Integer>();

		for(int i=0;i<5;i++) {
			Q.add(i);
		}
		
		Q = buildMinHeap(Q,0);
		
		System.out.println(" dijkstra's algorithm으로 계산한 결과는 다음과 같습니다.");
		
		while(Q.size() != 0) {
			System.out.println("\n──────────────────────────────────────────────────────────");

			Collections.swap(Q, 0, Q.size()-1);
			int u = Q.remove(Q.size()-1);
			S.add(u);

			Q = buildMinHeap(Q,0);	

			System.out.println("   S["+(S.size()-1)+"] : d["+vertex.get(u)+"] = "+d[u]);
			System.out.println("----------------------------------------------------------");
			for(int i=0; i<Q.size(); i++) {
				int v = Q.get(i);
				System.out.print("   Q["+i+"] : d["+vertex.get(v)+"] = "+d[v]);
				if(w[u][v] != Integer.MAX_VALUE) {
					if((d[u]+w[u][v]) < d[v]) {
						System.out.print("  ->  d["+vertex.get(v)+"] = "+(d[u]+w[u][v]));
						d[v] = d[u]+w[u][v];
					}
				}
				System.out.println();
			}
			Q = buildMinHeap(Q,0);
			System.out.println();
		}
	}
	
	public static List<Integer> buildMinHeap(List<Integer> arr, int s) {

		if((arr.size()-2 / 2) < s) return arr;
		arr = buildMinHeap(arr,2*s+1);
		arr = buildMinHeap(arr,2*s+2);
		arr = min_heapify(arr,s);

		return arr;
	}

	public static List<Integer> min_heapify(List<Integer> arr, int s) {

		int left = 2*s+1;
		int right = 2*s+2;
		int shortest = s;

		if(left < arr.size() && d[arr.get(left)] < d[arr.get(shortest)]) {
			shortest = left;
		}

		if(right < arr.size() && d[arr.get(right)] < d[arr.get(shortest)]) {
			shortest = right;
		}

		if(shortest != s) {
			Collections.swap(arr, s, shortest);
			arr = min_heapify(arr,shortest);
		}

		return arr;
	}


}
