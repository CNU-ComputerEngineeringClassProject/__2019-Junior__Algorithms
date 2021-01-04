import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Testheap {

	static Heap_make heap_T;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		heap_T = new Heap_make();
		start_setting();
		print_List();

		Scanner choice_menu = new Scanner(System.in);
		Scanner sc_name = new Scanner(System.in); 
		Scanner sc_key = new Scanner(System.in); 

		int menu = 0;

		String st_name;
		int key;

		while(menu != 6) {
			System.out.println("\n----------------------------------------------");
			System.out.println(" 1.�۾��߰�             2.�ִ밪               3.�ִ� �켱���� �۾� ó��");
			System.out.println(" 4.���� Ű�� ����     5.�۾� ����           6.����");
			System.out.println("----------------------------------------------");

			menu = Integer.parseInt(choice_menu.nextLine());

			switch(menu) {
			case 1:

				System.out.print("�۾��� �߰��� ������� �Է��ϼ��� : ");
				st_name = sc_name.nextLine();
				System.out.print(st_name+"�� �켱����(key)�� �Է��ϼ��� : ");
				key = Integer.parseInt(sc_key.nextLine());

				insert(new Node(key,st_name));

				print_List();
				break;
			case 2:
				Node max_value = max();
				System.out.println("key���� �ִ��� ���� : "+max_value.getkey()+", "+max_value.getName());
				break;
			case 3:
				extract_max();
				print_List();
				break;
			case 4:

				System.out.print("���� Ű���� ������ų ������� �Է��ϼ��� : ");
				st_name = sc_name.nextLine();
				System.out.print(st_name+"�� ������ Ű���� �Է��ϼ��� : ");
				key = Integer.parseInt(sc_key.nextLine());

				increase_key(st_name,key);

				print_List();
				break;
			case 5:
				System.out.print("�۾����� ������ ������� �Է��ϼ��� : ");
				st_name = sc_name.nextLine();

				delete(st_name);
				print_List();
				break;
			case 6:
				System.out.println("����");
				break;
			default:
				menu=6; //�����Է� ���� ����
				System.out.println("�����Է����� ����");
				break;
			}
		}
		sc_name.close();
		sc_key.close();
		choice_menu.close();

		return ;
	}

	public static void start_setting() throws IOException{

		String[] st_Arr;
		int key;

		String filePath = "data04.txt";

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 

			String st = null;

			while( (st = inFile.readLine()) != null ) {

				st_Arr = st.split(", ");
				key = Integer.parseInt(st_Arr[0]);
				heap_T.add(new Node(key,st_Arr[1]));
			}

			inFile.close();
			heap_T.build_max_heap();
		}
	}


	private static void insert( Node node_value){

		heap_T.add(node_value);
		heap_T.build_max_heap();
	}

	private static Node max() {
		return heap_T.get(0);
	}

	private static void extract_max(){

		Node remove_value = max();

		heap_T.remove(0);
		heap_T.add(0, heap_T.get(heap_T.size()-1));
		heap_T.remove(heap_T.size()-1);


		heap_T.build_max_heap();
		System.out.println("key���� �ִ��� ���� ���� : "+remove_value.getkey()+", "+remove_value.getName());
		
	}

	private static void increase_key(String target, int increase_key){

		int idx = 0;

		while(idx < heap_T.size()) {
			if(heap_T.get(idx).equal_name(target)) break;
			idx++;
		}

		if(idx!=heap_T.size() && heap_T.get(idx).equal_name(target)) {
			heap_T.remove(idx);

			Node new_value = new Node(increase_key,target);
			heap_T.add(idx,new_value);
			heap_T.build_max_heap();
		}

	}

	private static void delete(String node_name){

		int idx=0;

		while(idx < heap_T.size()) {
			if(heap_T.get(idx).equal_name(node_name)) break;
			idx++;
		}

		if(idx!=heap_T.size() && heap_T.get(idx).equal_name(node_name)) {
			
			heap_T.remove(idx);
			heap_T.add(idx, heap_T.get(heap_T.size()-1));
			heap_T.remove(heap_T.size()-1);
			
			heap_T.build_max_heap();
		}
	}

	private static void print_List() {
		System.out.println("\n**** ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ� ****\n");

		for(int i=0; i< heap_T.size();i++) {
			System.out.println(String.format("%3d", heap_T.get(i).getkey())+", "+heap_T.get(i).getName());
		}

	}


}
