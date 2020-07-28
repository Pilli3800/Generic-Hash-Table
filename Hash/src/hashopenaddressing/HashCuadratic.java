package hashopenaddressing;

import java.util.ArrayList;

import register.Register;

public class HashCuadratic<T extends Comparable<T>> {

	protected class Element {
		int mark; // 0 is empty and 1 is full.
		Register<T> reg;

		public Element(int mark, Register<T> reg) {
			this.mark = mark;
			this.reg = reg;
		}
	}

	protected ArrayList<Element> table;

	public ArrayList<Element> getTable() {
		return table;
	}

	public void setTable(ArrayList<Element> table) {
		this.table = table;
	}

	protected int m;

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	protected int numEntries; // Numeros que han sido agregados hasta ahora.
	private static final int MAX_FC = 80;

	public HashCuadratic(int size) {
		this.m = size;
		this.table = new ArrayList<Element>(m);
		for (int i = 0; i < m; i++) {
			this.table.add(new Element(0, null));
		}
	}

	private int fuctionHash(int key) {
		return key % m;
	}

	private int cuadraticProbing(int dressHash, int key) {
		int posInit = dressHash;
		int quadratic = 0;
		do {
			if (table.get(dressHash).mark == 0) {
				return dressHash; // Empty space was founded.
			}
			if (table.get(dressHash).mark == 1 && table.get(dressHash).reg.getKey() == key) {
				return -1;
			}
			dressHash = (dressHash + quadratic ^ 2) % m;
			quadratic++;
		} while (dressHash != posInit);

		return -1;
	}

	public void insert(int key, T reg) {
		int dressHash = fuctionHash(key);
		dressHash = cuadraticProbing(dressHash, key);
		if (dressHash == -1) {
			System.out.println("Key table hash is full or key is duplicated...");
			System.out.println(key + " was inserted before.");
			return;
		} else {
			if (CalcularFC(numEntries + 1) >= MAX_FC) {
				ampliarCont(table);

			} else {
				if (dressHash >= m)
					ampliarCont(table);
			}
			Element aux = new Element(1, new Register<T>(key, reg));
			table.set(dressHash, aux);
			numEntries++;
		}
	}

	public ArrayList<Element> ampliarCont(ArrayList<Element> table) {
		while (table.size() < m * 2) {
			this.table.add(new Element(0, null));
		}
		for (int x = m * 2; x <= 0; x--) {
			if (esPrimo(x))
				this.setM(x);
		}
		return table;
	}

	public static boolean esPrimo(int num) {
		int contador = 2;
		boolean primo = true;
		while ((primo) && (contador != num)) {
			if (num % contador == 0)
				primo = false;
			contador++;
		}
		return primo;
	}

	public int CalcularFC(int numEntries) { // Si es mayor o igual a 80% debe agrandar la tabla.
		return (numEntries / m) * 100;
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
