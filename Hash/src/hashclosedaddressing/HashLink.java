package hashclosedaddressing;

import java.util.ArrayList;

import list.ListLinked;
import register.Register;

public class HashLink<T extends Comparable<T>> {

	protected class Element {
		int mark; // 0 is empty and 1 is full.
		Register<T> reg;
		private int dressHash;
		private ListLinked<Register<T>> list;

		public Element(int mark, Register<T> reg, int dressHash) {
			this.mark = mark;
			this.reg = reg;
			this.setDressHash(dressHash);
			this.setList(new ListLinked<Register<T>>());
		}

		public int getDressHash() {
			return dressHash;
		}

		public void setDressHash(int dressHash) {
			this.dressHash = dressHash;
		}

		public ListLinked<Register<T>> getList() {
			return list;
		}

		public void setList(ListLinked<Register<T>> list) {
			this.list = list;
		}

		public String toString() {
			String content = "";
			if (this.mark == 1) {
				content += getDressHash() + "\t" + this.reg.toString();
				if (!(this.list.isEmpty())) {
					content += "\t" + this.list.toString();
				} else {
					content += "\tlist empty";
				}
			} else {
				content += "\tempty";
			}
			return content;
		}
	}

	protected ArrayList<Element> table;
	protected int m; // size

	public HashLink(int size) {
		this.m = size;
		this.table = new ArrayList<Element>(m);
		for (int i = 0; i < m; i++) {
			this.table.add(new Element(0, null, -1));
		}
	}

	private int fuctionHash(int key) {
		return key % m;
	}

	public void insert(int key, T reg) {
		int dressHash = fuctionHash(key);
		Register<T> newReg = new Register<T>(key, reg);
		if (this.table.get(dressHash).mark == 0) {
			this.table.set(dressHash, new Element(1, newReg, dressHash));
		} else {
			if (!(this.table.get(dressHash).reg.equals(newReg))) {
				if (this.table.get(dressHash).list.search(newReg) == false) {
					this.table.get(dressHash).list.insertFirst(newReg);
				} else {
					System.out.println("Key " + key + " is duplicated.");
				}
			} else {
				System.out.println("Key " + key + " is duplicated.");
			}
		}
	}

	public T search(int key) {
		T aux = null;
		int dressHash = fuctionHash(key);
		Register<T> newReg = new Register<T>(key, null);
		if (this.table.get(dressHash).mark == 1) {
			if (this.table.get(dressHash).list.search(newReg)) {
				int position = this.table.get(dressHash).list.getIndex(newReg);
				aux = this.table.get(dressHash).list.getNodeAt(position).getData().getReg();
			} else {
				System.out.println("The key does't exist.");
			}
		} else {
			System.out.println("The key does't exist.");
			aux = null;
		}
		return aux;
	}

	public void remove(int key) {
		int dressHash = fuctionHash(key);
		Register<T> newReg = new Register<T>(key, null);
		if (this.table.get(dressHash).mark == 1) {
			if (this.table.get(dressHash).list.search(newReg)) {
				int position = this.table.get(dressHash).list.getIndex(newReg);
				T aux = this.table.get(dressHash).list.getNodeAt(position).getData().getReg();
				Register<T> toDelete = new Register<T>(key, aux);
				this.table.get(dressHash).list.remove(toDelete);
				System.out.println("The key " + key + " was remove.");
			}
		}
	}

	public String toString() {
		String s = "D.Real\tD.Hash\tRegister\n";
		int i = 0;
		for (Element item : table) {
			s += (i++) + "-->\t" + item.toString() + "\n";
		}
		return s;
	}
}
