package register;

public class Register<T> implements Comparable<Register<T>> {

	protected int key;
	protected T reg;

	public Register(int key, T reg) {
		this.key = key;
		this.reg = reg;
	}

	@Override
	public int compareTo(Register<T> o) {
		int comparation = 0;
		if (this.key == o.key) {
			comparation = 0;
		}
		if (this.key < o.key) {
			comparation = 1;
		}
		if (this.key > o.key) {
			comparation = 1;
		}
		return comparation;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (o instanceof Register) {
			Register<T> r = (Register<T>) o;
			return r.key == this.key;
		}
		return false;
	}

	public int getKey() {
		return this.key;
	}

	public T getReg() {
		return this.reg;
	}

	public String toString() {
		return this.key + " : " + this.reg.toString();
	}
}
