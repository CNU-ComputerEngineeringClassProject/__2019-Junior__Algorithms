import java.math.BigInteger;

public class Fibonacci_Recursion {


	public BigInteger fibonacci_R(int n) {
	
		if(n<2) {
			return new BigInteger(String.valueOf(n));
		}

		return fibonacci_R(n-1).add(fibonacci_R(n-2));
	}
}
