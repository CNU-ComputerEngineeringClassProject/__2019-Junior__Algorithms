
public class Node {
	int key;
	String name;

	Node(int key, String name){
		this.key= key;
		this.name = name;
	}	

	public String getName() {
		return this.name;
	}

	public int getkey() {
		return this.key;
	}

	public boolean equal_name(String a_name) {
		if(this.name.equals(a_name) ) return true;
		return false;
	}
}
