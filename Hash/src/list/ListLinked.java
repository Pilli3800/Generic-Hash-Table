package list;

import node.Node;

public class ListLinked<T extends Comparable<T>> implements ListTDA<T> {

	protected Node<T> head; 
	protected int size; 

	public ListLinked() {
		this.head = null;
		this.size = 0;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void initializeList() {
		this.destroyList();

	}

	private void destroyList() {
		while (this.head != null) {
			this.head = this.head.getNext();
		}

		this.size = 0;

	}

	public ListLinked<T> reverseList() {
		ListLinked<T> aux = new ListLinked<T>();
		for (Node<T> i = this.head; i != null; i = i.getNext()) {
			insertFirst(i.getData());
		}
		return aux;
	}

	@Override
	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		}

		return false;

	}

	@Override
	public int length() {
		return this.getSize();

	}

	@Override
	public boolean search(T data) {
		Node<T> aux = this.head;

		while (aux != null && !aux.getData().equals(data))
			aux = aux.getNext();
		if (aux == null)
			return false;
		return true;

	}

	@Override
	public int getIndex(T data) {
		Node<T> aux = this.head;
		int iterator = 0;
		try {
			if (this.head.getData().compareTo(data) == 0)
				return iterator;
			else
				while (aux != null) {
					aux = aux.getNext();
					iterator++;
					if (aux.getData().compareTo(data) == 0)
						break;
				}
			return iterator;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public void insertFirst(T data) {
		if (this.head == null) {
			this.head = new Node<T>(data);
		} else {
			this.head = new Node<T>(data, this.head);
		}

		this.size++;

	}

	@Override
	public void insertLast(T data) {
		Node<T> insert = new Node<T>(data);
		Node<T> aux = this.head;

		if (this.isEmpty())
			this.insertFirst(data);
		else {
			while (aux.getNext() != null)
				aux = aux.getNext();
			aux.setNext(insert);
			this.size++;
		}

//		if (aux == null) {
//			// Apunta a nulo.
//			this.head = insert;
//		} else {
//			// Enlaza el último elemento existente con el nuevo.
//			Node<T> last = getLast();
//			last.setNext(insert);
//		}
//
//		this.size++;

	}

	public void insert(Node<T> node, T data) {
		if (this.head == null || node == null) {
			return;
		}

		Node<T> insert = new Node<T>(data);
		Node<T> preview = node;
		Node<T> next = node.getNext();

		preview.setNext(insert);
		insert.setNext(next);

		this.size++;

	}

	public Node<T> getLast() {
		if (this.head == null)
			return null;

		Node<T> last = this.head;
		Node<T> control = this.head;

		while (control != null) {
			last = control;
			control = last.getNext();
		}

		return last;

	}

	public void removeNode(Node<T> node) {
		if (this.head == null || node == null) {
			return;
		}

		Node<T> control = this.head;
		Node<T> temp = null;

		if (node == this.head) {
			temp = this.head;
			this.head = this.head.getNext();
			temp = null;
		} else {
			while (control.getNext() != node) {
				control = control.getNext();
			}
			temp = control.getNext();
			control.setNext(temp.getNext());
			temp = null;
		}

		this.size--;
	}

	@Override
	public void remove(T data) {
		Node<T> aux = this.head;

		if (isEmpty())
			return;

		if (this.head.getData().equals(data)) {
			this.head = this.head.getNext();
			this.size--;

		} else {
			while (aux.getNext() != null && !aux.getNext().getData().equals(data))
				aux = aux.getNext();
			if (aux.getNext() != null) {
				aux.setNext(aux.getNext().getNext());
				this.size--;
			}
		}

	}

	public Node<T> getNodeAt(int position) {
		if (this.head == null || (this.size - 1) < position) {
			return null;
		}

		Node<T> control = this.head;
		int iterator = 0;

		while (control != null) {
			if (iterator == position)
				break;
			iterator++;
			control = control.getNext();
		}

		return control;

	}

	public Node<T> searchK(int position) {
		Node<T> control = this.head;

		if (this.head == null || (this.size - 1) < position || position > this.size) {
			return null;
		}

		int iterator = 0;

		while (control != null) {
			if (iterator == position)
				break;
			iterator++;
			control = control.getNext();
		}

		return control;

	}

	public Node<T> reverseList(Node<T> head) {
		Node<T> preview = null;
		Node<T> current = this.head;
		Node<T> next = null;
		while (current != null) {
			next = current.getNext();
			current.setNext(preview);
			preview = current;
			current = next;
		}
		head = preview;
		return preview;

	}

	@Override
	public String toString() {
		String list = "";
		if (this.head == null) {
			list = "Empty";
			return list;
		} else {
			for (Node<T> aux = this.head; aux != null; aux = aux.getNext()) {
				list = list + aux.getData().toString() + " -> ";
			}
		}
		return "List: " + list;

	}

}
