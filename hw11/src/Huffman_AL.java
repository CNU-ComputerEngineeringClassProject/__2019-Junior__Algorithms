import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Huffman_AL {

	public static Map<Character,Integer> countTable;
	public static Map<Character,String> encodeTable;
	public static List<Node> Q;

	public static Map<String,Character> decodeTable;
	public static Node tree;
	public static String[] stCode;

	public static class Node{

		char ch;
		String code;
		int count;
		Node left,right;

		Node(char ch, int count){
			this.ch = ch;
			this.count = count;
			this.left = null;
			this.right = null;
		}

		Node(int count, Node left, Node right){
			this.count = count;
			this.left = left;
			this.right = right;
		}

		Node(char ch, String code){
			this.ch = ch;
			this.code = code;
			this.left = null;
			this.right = null;
		}

		Node(Node left, Node right){
			this.ch = (char)0;
			this.code = null;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
		String[] st_Arr;
		String filePath = "data12.txt";

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null; 

			if((st = inFile.readLine()) != null) {
				st_Arr = st.split("");
				char_count(st_Arr);
				Node codeTree = huffman();

				codeTableSetting(codeTree,"");


				outputTable();

				try (FileWriter fw = new FileWriter("hw12_05_201701977_encoded.txt");){
					fw.write(encoding(st_Arr));
					fw.close();
				}catch(IOException e){
					e.printStackTrace();
				}

			}
			inFile.close();

		}

		String[] st_Arr_de;
		String filePath_de = "data12_table.txt";
		String filePath_de2 = "data12_encoded.txt";

		File file_de = new File(filePath_de); 
		if(file_de.exists()) { 

			decodeTable = new HashMap<String, Character>();
			BufferedReader inFile = new BufferedReader(new FileReader(file_de)); 

			String st = null; 

			while((st = inFile.readLine()) != null) {
				st_Arr_de = st.split(",");
				decodeTable.put(st_Arr_de[1],st_Arr_de[0].charAt(0));
			}

			if(0 < decodeTable.size()) {				
				tree = makeTree("");


				File file2 = new File(filePath_de2); 
				if(file2.exists()) { 

					BufferedReader inFile2 = new BufferedReader(new FileReader(file2)); 

					String st2 = null; 

					if((st2 = inFile2.readLine()) != null) {
						stCode = st2.split("");
						String buf = "";
						while(0<stCode.length) {
							buf = buf+decoding();
						}
						try (FileWriter fw = new FileWriter("hw12_05_201701977_decoded.txt");){
							fw.write(buf);
							fw.close();
						}catch(IOException e){
							e.printStackTrace();
						}

					}
					inFile2.close();
				}
			}

			inFile.close();
		}

	}


	public static String encoding (String[] arr) {
		String buf="";
		for(int idx=0; idx<arr.length; idx++) {
			buf = buf+encodeTable.get(arr[idx].charAt(0));
		}
		return buf;
	}

	public static void outputTable() {

		try (FileWriter fw = new FileWriter("hw12_05_201701977_table.txt");){
			Iterator<Character> it = encodeTable.keySet().iterator();

			while(it.hasNext()) {
				char key = (char)it.next();
				if(it.hasNext()) {
					fw.write(key+","+encodeTable.get(key)+"\n");
				}else {
					fw.write(key+","+encodeTable.get(key));
				}
			}

			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void char_count (String[] arr) {
		countTable = new HashMap<Character,Integer>();
		encodeTable = new HashMap<Character,String>();
		Q = new ArrayList<Node>();

		for(int idx=0;idx<arr.length;idx++) {
			char ch = arr[idx].charAt(0);
			if(countTable.containsKey(ch)) {
				int ex_count = countTable.get(arr[idx].charAt(0));
				countTable.replace(arr[idx].charAt(0), ex_count+1);
			}else {
				countTable.put(ch,1);	
			}
		}


		for(Character key : countTable.keySet()) {
			if(0 < countTable.get(key)) {
				Node temp = new Node(key,countTable.get(key));
				Q.add(temp);		
			}
		}

		buildMinHeap(Q,0);
	}

	public static Node huffman() {

		if(Q.size() < 1)return null;

		while(1 < Q.size()) {

			Node x = Extract_Min(Q);
			Node y = Extract_Min(Q);
			Node z = new Node((x.count+y.count),x,y);
			insert(Q,z);	
		}

		return Extract_Min(Q);
	}

	public static void codeTableSetting(Node tree, String buf) {

		if(tree.left == null) {
			encodeTable.put(tree.ch,buf);
		}else {
			codeTableSetting(tree.left, buf+"0");
			if(tree.right != null )codeTableSetting(tree.right, buf+"1");
		}
	}

	public static Node Extract_Min (List<Node> arr) {

		Node min;

		Collections.swap(arr, 0, arr.size()-1);
		min = arr.remove(arr.size()-1);
		buildMinHeap(arr,0);

		return min;
	}

	public static void insert (List<Node> arr, Node input) {

		arr.add(input);
		buildMinHeap(arr,0);

	}

	public static void buildMinHeap(List<Node> arr, int s) {

		if((arr.size()-2 / 2) < s) return ;
		buildMinHeap(arr,2*s+1);
		buildMinHeap(arr,2*s+2);
		min_heapify(arr,s);

	}

	public static void min_heapify(List<Node> arr, int s) {

		int left = 2*s+1;
		int right = 2*s+2;
		int shortest = s;

		if(left < arr.size() && arr.get(left).count < arr.get(shortest).count) {
			shortest = left;
		}

		if(right < arr.size() && arr.get(right).count < arr.get(shortest).count) {
			shortest = right;
		}

		if(shortest != s) {
			Collections.swap(arr, s, shortest);
			min_heapify(arr,shortest);
		}
	}

	public static String decoding() {

		Node point = tree;
		String[] subarr;
		int idx=0;

		if(stCode.length < 1) return "";

		while(point.left != null ) {
			if(stCode[idx].equals("1")) {
				point = point.right;
			}else {
				point = point.left;

			}
			idx++;
		}

		if(idx < stCode.length) {
			subarr = new String[stCode.length-idx];
			System.arraycopy(stCode, idx, subarr, 0, stCode.length-idx);
			stCode = subarr;
		}else if(idx == stCode.length){
			subarr = new String[0];
			stCode = subarr;
		}


		return Character.toString(point.ch);
	}

	public static Node makeTree(String buf){

		if(decodeTable.containsKey(buf)) {
			return new Node(decodeTable.get(buf),buf);
		}else {
			Node left = makeTree(buf+"0");
			Node right = makeTree(buf+"1");
			return new Node(left,right);
		}

	}
}
