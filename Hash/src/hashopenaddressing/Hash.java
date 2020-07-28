package hashopenaddressing;
import java.util.ArrayList;

import register.Register;

public class Hash<T extends Comparable<T>> {

	protected class Element {
		int mark; // 0 is empty and 1 is full.
		Register<T> reg;

		public Element(int mark, Register<T> reg) {
			this.mark = mark;
			this.reg = reg;
		}
	}

	protected ArrayList<Element> table;
	protected int m;

	public Hash(int n) {
		this.m = n;
		this.table = new ArrayList<Element>(m);
		for (int i = 0; i < m; i++) {
			this.table.add(new Element(0, null));
		}
	}

	private int fuctionHash(int key) {
		return key % m;
	}

	private int linearProbing(int dressHash, int key) {
		int posInit = dressHash;
		do {
			if (table.get(dressHash).mark == 0) {
				return dressHash; // Empty space was founded.
			}
			if (table.get(dressHash).mark == 1 && table.get(dressHash).reg.getKey() == key) {
				return -1;
			}
			dressHash = (dressHash + 1) % m;
		} while (dressHash != posInit);

		return -1;
	}

	@SuppressWarnings("unused")
	private int fuctionHashMidSquare(int key) {
		int square = key * key;
		int dressHash = (square % 1000000) / 100; // 4 digits
		return dressHash % m;
//		Source: Data Structures and Algorithms with Object-Oriented Design Patterns in Java by Bruno R. Preiss, P.Eng (1998)
//		int k = 10; // M==1024
//		int w = 32;
//
//		Integer hashed = (number * number) >>> (w - k);
//		return hashed % DATA_LENGTH;

	}

	@SuppressWarnings("unused")
	private Integer fuctionFold(int number) {
		StringBuffer numberText = new StringBuffer(String.valueOf(number));
		Integer num1 = Integer.valueOf(numberText.substring(0, 2));
		Integer num2 = Integer.valueOf(numberText.substring(3, 5));
		Integer num3 = Integer.valueOf(numberText.substring(6));
		// System.out.println(num1 + " " + num2 + " " + num3);
		return (num1 + num2 + num3) % m;
	}

	public void insert(int key, T reg) {
		int dressHash = fuctionHash(key);
		dressHash = linearProbing(dressHash, key);
		if (dressHash == -1) {
			System.out.println("Key table hash is full or key is duplicated...");
			System.out.println(key + " was inserted before.");
			return;
		} else {
			Element aux = new Element(1, new Register<T>(key, reg));
			table.set(dressHash, aux);
		}

	}

	public boolean contains(int key) {
		if (search(key) != null) {
			return true;
		}
		return false;
	}

	public T search(int key) {
		for (int i = fuctionHash(key); table.get(i) != null; i = (i + 1) % m)
			if (table.get(i).reg.getKey() == key)
				return table.get(i).reg.getReg();
		return null;

	}

	public void remove(int key) {
		int dressHash = fuctionHash(key);
		table.set(dressHash, new Element(0, null));
	}

	public String toString() {
		String s = "D.Real\tD.Hash\tRegister\n";
		int i = 0;
		for (Element item : table) {
			s += (i++) + "-->\t";
			if (item.mark == 1) {
				s += fuctionHash(item.reg.getKey()) + "\t" + item.reg.toString() + "\n";
			} else {
				s += "Empty\n";
			}

		}
		return s;
	}
}
