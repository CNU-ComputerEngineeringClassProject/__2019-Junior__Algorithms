import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bellman_ford {

	public static List<int[]> edge;
	public static int[] dist;
	public static int vertexSize=0;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String[] st_Arr;
		String filePath = "data11_bellman_ford_1.txt";

		File file = new File(filePath); 
		if(file.exists()) { 

			BufferedReader inFile = new BufferedReader(new FileReader(file)); 
			String st = null; 

			if((st = inFile.readLine()) != null) {

				st_Arr = st.split(",");
				edge = new ArrayList<int[]>();
				vertexSize = st_Arr.length;


			}

			while( (st = inFile.readLine()) != null ) {
				st_Arr = st.split(",");

				int[] a = {Integer.parseInt(st_Arr[0]),Integer.parseInt(st_Arr[1]),Integer.parseInt(st_Arr[2])};
				edge.add(a);


			}
			inFile.close();


			if(bellman_ford()) {

				int i=0;
				System.out.print("\nFinal distance : [");
				for(i=0;i<vertexSize-1;i++) {
					if(dist[i]==(int)Double.POSITIVE_INFINITY) System.out.print("inf, ");
					else System.out.print(dist[i]+", ");
				}

				if(dist[i]==(int)Double.POSITIVE_INFINITY) System.out.print("inf]");
				else System.out.println(dist[i]+"]");

			}else {
				System.out.println("\nThe edge has nagative cycle");				
			}
		}

	}

	public static boolean bellman_ford() {

		boolean ncycle = false;
		dist = new int[vertexSize];

		dist[0]=0;
		for(int j=1 ; j< vertexSize ; j++) {
			dist[j]= (int)Double.POSITIVE_INFINITY;
		}

		int round=0;
		for(; round < vertexSize-1; round++) {
			System.out.println("\n--------------------------iteration "+round+"------------------------");

			for(int idx=0; idx < edge.size(); idx++) {
				int u = edge.get(idx)[0];
				int v = edge.get(idx)[1];
				int w = edge.get(idx)[2];

				int temp;
				if(dist[u]==(int)Double.POSITIVE_INFINITY) temp= (int)Double.POSITIVE_INFINITY;
				else temp= dist[u] + w;

				if(temp < dist[v]) {
					if(dist[v]==(int)Double.POSITIVE_INFINITY) System.out.println("Update distance of "+v+" from inf to "+temp);					
					else System.out.println("Update distance of "+v+" from "+dist[v]+" to "+temp );	
					dist[v] = temp;
				}

			}

			int i=0;
			System.out.print("iteration "+round+" distance : [");
			for(i=0;i<vertexSize-1;i++) {
				if(dist[i]==(int)Double.POSITIVE_INFINITY) System.out.print("inf, ");
				else System.out.print(dist[i]+", ");
			}

			if(dist[i]==(int)Double.POSITIVE_INFINITY) System.out.print("inf]");
			else System.out.println(dist[i]+"]");
		}

		
		System.out.println("\n--------------------------iteration "+round+"------------------------");

		for(int idx=0; idx < edge.size(); idx++) {
			int u = edge.get(idx)[0];
			int v = edge.get(idx)[1];
			int w = edge.get(idx)[2];

			int temp;
			if(dist[u]==(int)Double.POSITIVE_INFINITY) temp= (int)Double.POSITIVE_INFINITY;
			else temp= dist[u] + w;

			if(temp < dist[v]) {
				ncycle = true;
				if(dist[v]==(int)Double.POSITIVE_INFINITY) System.out.println("Update distance of "+v+" from inf to "+temp);					
				else System.out.println("Update distance of "+v+" from "+dist[v]+" to "+temp );	
				dist[v] = temp;
			}

		}

		int i=0;
		System.out.print("iteration "+round+" distance : [");
		for(i=0;i<vertexSize-1;i++) {
			if(dist[i]==(int)Double.POSITIVE_INFINITY) System.out.print("inf, ");
			else System.out.print(dist[i]+", ");
		}

		if(dist[i]==(int)Double.POSITIVE_INFINITY) System.out.print("inf]");
		else System.out.println(dist[i]+"]");
		
//		for(int idx=0; idx < edge.size(); idx++) {
//			int u = edge.get(idx)[0];
//			int v = edge.get(idx)[1];
//			int w = edge.get(idx)[2];
//
//			if(dist[v] > dist[u] + w) return false;
//
//		}


		return !ncycle;
	}

}
