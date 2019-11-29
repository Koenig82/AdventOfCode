package adventOfCode;

import java.util.Objects;

/**
 * Pair of values with any type<br>
 * <br>
 * Access with:<br>
 * {@link #getA()}<br>
 * {@link #getB()}<br>
 * <br>
 * Example:<br>
 * {@code
 * 	Pair<String,Integer> pair = new Pair<>("",0);
 * 	System.out.println(pair);
 * }
 * <br>
 * @author konignik
 *
 * @param <T> any type
 * @param <T2> any type
 */
public class Pair <T,T2>{
	
	private T a;
	private T2 b;
	public Pair(T a, T2 b) {
		super();
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString() {
		return "Pair [a=" + a + ", b=" + b + "]";
	}
	/**
	 * @return first value
	 */
	public T getA() {
		return a;
	}
	
	/**
	 * @param a store first value
	 */
	public void setA(T a) {
		this.a = a;
	}
	
	/**
	 * @return second value
	 */
	public T2 getB() {
		return b;
	}
	
	/**
	 * @param b store second value
	 */
	public void setB(T2 b) {
		this.b = b;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pair))
			return false;
		Pair other = (Pair) obj;
		return Objects.equals(a, other.a) && Objects.equals(b, other.b);
	}
	
}
