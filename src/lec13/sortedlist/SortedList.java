package lec13.sortedlist;

public interface SortedList<T extends Comparable<T>> {
	
	int size();
	int lookup(T element);
	boolean contains(T element);
	void add(T element);
	T get(int idx);
	void remove(int idx);
	void remove(T element);
	
}
