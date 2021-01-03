import java.math.BigInteger;

public class Fibonacci_RecursiveSquaring {

	BigInteger[][] A;

	public Fibonacci_RecursiveSquaring() {
		this.A = new BigInteger[2][2];

		this.A[0][0] = new BigInteger("1");
		this.A[0][1] = new BigInteger("1");
		this.A[1][0] = new BigInteger("1");
		this.A[1][1] = new BigInteger("0");
	}

	public BigInteger fibonacci_Re(int n) {

		if(n<2) {
			return new BigInteger(String.valueOf(n));
		}
		return pow(this.A,n-1)[0][0];
	}

	private BigInteger[][] pow(BigInteger[][] A_p, int n) {
		BigInteger[][] A_p_result;
		
		if(n==1) {
			return A_p;
		}else if(n%2 == 0) {
			A_p_result = pow(A_p, (n/2));
			return mul(A_p_result,A_p_result);
		}else {
			A_p_result = pow(A_p, ((n-1)/2));
			return mul(mul(A_p_result,A_p_result),A_p);
		}
	}

	private BigInteger[][] mul(BigInteger[][] A_m, BigInteger[][] B) {
		
		BigInteger[][] re_fi = new BigInteger[2][2];

		re_fi[0][0] = ( A_m[0][0].multiply(B[0][0]) ).add( A_m[0][1].multiply(B[1][0]) );
		re_fi[0][1] = ( A_m[0][0].multiply(B[0][1]) ).add( A_m[0][1].multiply(B[1][1]) ); 
		re_fi[1][0] = ( A_m[1][0].multiply(B[0][0]) ).add( A_m[1][1].multiply(B[1][0]) );
		re_fi[1][1] = ( A_m[1][0].multiply(B[0][1]) ).add( A_m[1][1].multiply(B[1][1]) );
		
		return re_fi;
	}
}
