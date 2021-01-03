import java.math.BigInteger;

public class Fibonacci_Array {

	BigInteger[] arr;

	public Fibonacci_Array(int n) {
		this.arr = new BigInteger[(n+1)];
	}

	public BigInteger fibonacci_A() {

		int idx=0;
		
		while(idx < this.arr.length) {
			if(idx < 2) {
				this.arr[idx] = new BigInteger(String.valueOf(idx));
				idx++;
			}else {
				this.arr[idx] = this.arr[idx-1].add(this.arr[idx-2]);
				idx++;
			}
		}
		
		return this.arr[this.arr.length-1];
	}
}
