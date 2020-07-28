package hashclosedaddressing;

import register.Register;

public class Element<T> {
	public int mark; // 0 is empty and 1 is full.
	Register<T> reg;

	public Element(int mark, Register<T> reg) {
		this.mark = mark;
		this.reg = reg;
	}
}
