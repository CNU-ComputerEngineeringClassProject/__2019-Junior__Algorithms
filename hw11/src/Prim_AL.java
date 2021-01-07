import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prim_AL {

	public static int[][] w;
	public static int[] key;
	public static int[] parent;
	public static List<Integer> S;
	public static List<Integer> Q;
	public static List<Node> vertex;

	public static class Node{

		String v;
		int s_idx;
		Node next;

		public Node(String v,Node next) {
			this.v = v;
			this.next = next;
		}
		
		public Node(int idx,Node next) {
			this.s_idx = idx;
			this.next = next;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init_Graph();
		prim_Algorithm();
		print_Graph();

	}

	public static void init_Graph() {

		w = new int[9][9];
		vertex = new ArrayList<Node>();

		/* (정점기호 = w 인덱스번호) (a=0) (b=1) (c=2) (d=3) (e=4) (f=5) (g=6) (h=7) (i=8) */
		vertex.add(new Node("a",null));
		vertex.add(new Node("b",null));
		vertex.add(new Node("c",null));
		vertex.add(new Node("d",null));
		vertex.add(new Node("e",null));
		vertex.add(new Node("f",null));
		vertex.add(new Node("g",null));
		vertex.add(new Node("h",null));
		vertex.add(new Node("i",null));

		for(int i = 0; i < vertex.size() ; i++) {
			for(int j =0;j <vertex.size(); j++) {
				w[i][j] = Integer.MAX_VALUE;
			}
			w[i][i]=0;
		}

		w[0][1] = w[1][0] = 4;	// a <-> b = 4
		vertex.get(0).next = new Node(1,vertex.get(0).next);
		vertex.get(1).next = new Node(0,vertex.get(1).next);

		w[0][7] = w[7][0] = 8; // a <-> h = 8
		vertex.get(0).next = new Node(7,vertex.get(0).next);
		vertex.get(7).next = new Node(0,vertex.get(7).next);

		w[1][7] = w[7][1] = 11; // b <-> h = 11
		vertex.get(1).next = new Node(7,vertex.get(1).next);
		vertex.get(7).next = new Node(1,vertex.get(7).next);

		w[1][2] = w[2][1] = 8; // b <-> c = 8
		vertex.get(1).next = new Node(2,vertex.get(1).next);
		vertex.get(2).next = new Node(1,vertex.get(2).next);

		w[2][8] = w[8][2] = 2; // c <-> i = 2
		vertex.get(2).next = new Node(8,vertex.get(2).next);
		vertex.get(8).next = new Node(2,vertex.get(8).next);

		w[2][3] = w[3][2] = 7; // c <-> d = 7
		vertex.get(2).next = new Node(3,vertex.get(2).next);
		vertex.get(3).next = new Node(2,vertex.get(3).next);

		w[2][5] = w[5][2] = 4; // c <-> f = 4
		vertex.get(2).next = new Node(5,vertex.get(2).next);
		vertex.get(5).next = new Node(2,vertex.get(5).next);

		w[3][5] = w[5][3] = 14; // d <-> f = 14
		vertex.get(3).next = new Node(5,vertex.get(3).next);
		vertex.get(5).next = new Node(3,vertex.get(5).next);

		w[3][4] = w[4][3] = 9; // d <-> e = 9
		vertex.get(3).next = new Node(4,vertex.get(3).next);
		vertex.get(4).next = new Node(3,vertex.get(4).next);

		w[4][5] = w[5][4] = 10; // e <-> f = 10
		vertex.get(4).next = new Node(5,vertex.get(4).next);
		vertex.get(5).next = new Node(4,vertex.get(5).next);

		w[5][6] = w[6][5] = 2; // f <-> g = 2
		vertex.get(5).next = new Node(6,vertex.get(5).next);
		vertex.get(6).next = new Node(5,vertex.get(6).next);

		w[6][8] = w[8][6] = 6; // g <-> i = 6
		vertex.get(6).next = new Node(8,vertex.get(6).next);
		vertex.get(8).next = new Node(6,vertex.get(8).next);

		w[6][7] = w[7][6] = 1; //g <-> h = 1 
		vertex.get(6).next = new Node(7,vertex.get(6).next);
		vertex.get(7).next = new Node(6,vertex.get(7).next);

		w[7][8] = w[8][7] = 7; // h <-> i = 7 
		vertex.get(7).next = new Node(8,vertex.get(7).next);
		vertex.get(8).next = new Node(7,vertex.get(8).next);

	}

	public static void prim_Algorithm() {

		S = new ArrayList<Integer>();
		Q = new ArrayList<Integer>();
		key = new int[9];
		parent = new int[9];

		for(int i=0;i < vertex.size() ; i++) {
			Q.add(i);
		}

		key[0] = 0;
		for(int i=1;i<vertex.size();i++) {
			key[i] = Integer.MAX_VALUE;
		}

		Q = buildMinHeap(Q,0);

		while(0 < Q.size()) {
			
			Collections.swap(Q, 0, Q.size()-1);
			int u = Q.remove(Q.size()-1);
			S.add(u);
			
			Node p = vertex.get(u).next;

			while(p != null) {

				int v = p.s_idx;
				if(Q.contains(v) && w[u][v] < key[v]) {
					key[v] = w[u][v];
					parent[v] = u;
					}
				p=p.next;
			}
			
			Q = buildMinHeap(Q,0);
		}


	}

	public static void print_Graph() {
		
		int sum = 0;
		
		//start vertex
		System.out.println("  v( ,"+ vertex.get(S.get(0)).v +") = "+ key[0]);
		
		for(int i=1;i<vertex.size();i++) {			
			int idx = S.get(i);
			System.out.println("  v("+vertex.get(parent[idx]).v+","+vertex.get(idx).v+") = "+key[idx]);
			sum += key[idx];
		}
		System.out.println("\n  v(MST) = "+sum);
		
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

		if(left < arr.size() && key[arr.get(left)] < key[arr.get(shortest)]) {
			shortest = left;
		}

		if(right < arr.size() && key[arr.get(right)] < key[arr.get(shortest)]) {
			shortest = right;
		}

		if(shortest != s) {
			Collections.swap(arr, s, shortest);
			arr = min_heapify(arr,shortest);
		}

		return arr;
	}
}
