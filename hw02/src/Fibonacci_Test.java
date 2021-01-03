import java.util.Scanner;

public class Fibonacci_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int menu, num;
		long startTime, endTime;
		double runTime;
		
		System.out.println("n값을 입력하시오.");
		num = scanner.nextInt();
		
		if(num < 0) {
			System.out.println("오류입력입니다. 종료.");
			return ;
		}
		
		System.out.println("방법\n1 : Recursion\n2 : Array\n3 : Recursive squaring");
		menu = scanner.nextInt();

		if(menu < 1 || menu > 3) {
			System.out.println("오류입력입니다. 종료.");
			return ;
		}

	
		System.out.println("---------------------------------------------------------");

		switch(menu) {
		case 1:
			Fibonacci_Recursion fibonacci_Recursion = new Fibonacci_Recursion();
			for(int i=0 ; i <= num ; i++) {
				
				startTime = System.nanoTime();	
				System.out.print("f<"+String.format("%2d", i)+"> = ");
				System.out.printf("%-20d",fibonacci_Recursion.fibonacci_R(i));
				endTime = System.nanoTime();
				runTime = (double)(endTime-startTime)/1000000000;

				System.out.println("\t"+String.format("%.12f", runTime)+" sec");

				if(i%10 == 9) {
					System.out.println("---------------------------------------------------------");
				}

			}
			break;
			
		case 2:
			Fibonacci_Array fibonacci_Array;
			for(int i=0 ; i <= num ; i++) {
				
				fibonacci_Array = new Fibonacci_Array(i);
				startTime = System.nanoTime();
				System.out.print("f<"+String.format("%2d", i)+"> = ");
				System.out.printf("%-20d",fibonacci_Array.fibonacci_A());
				
				endTime = System.nanoTime();
				runTime = (double)(endTime-startTime)/1000000000;

				System.out.println("\t"+String.format("%.12f", runTime)+" sec");

				if(i%10 == 9) {
					System.out.println("---------------------------------------------------------");
				}

			}
			break;
			
		case 3:
			Fibonacci_RecursiveSquaring fibonacci_RecursiveSquaring = new Fibonacci_RecursiveSquaring();
			for(int i=0 ; i <= num ; i++) {
			
				startTime = System.nanoTime();
				System.out.print("f<"+String.format("%2d", i)+"> = ");
				System.out.printf("%-20d",fibonacci_RecursiveSquaring.fibonacci_Re(i));
				
				endTime = System.nanoTime();
				runTime = (double)(endTime-startTime)/1000000000;
				
				System.out.println("\t"+String.format("%.12f", runTime)+" sec");

				if(i%10 == 9) {
					System.out.println("---------------------------------------------------------");
				}

			}
			break;
		default:
			System.out.println("오류입력입니다. 종료.");
			break;
		}

		scanner.close();
	}

}
