package list;

public interface ListTDA<T> {
	
	void initializeList(); 

	boolean isEmpty();

	int length(); 

	boolean search(T data); 

	void insertFirst(T data); 

	void insertLast(T data); 
	
	void remove(T data); 

	int getIndex(T data);

}
