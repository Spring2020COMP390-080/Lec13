package lec13.sortedlist;

public class ArraySortedList<T extends Comparable<T>> implements SortedList<T> {
	
	private T[] _elements;
	private int _size;
	
	private final int INITIAL_SIZE = 10;
	
	public ArraySortedList() {
		_elements = (T[]) new Object[INITIAL_SIZE];
		_size = 0;
	}
	
	@Override
	public int size() {
		return _size;
	}

	@Override
	public int lookup(T element) {
		if (_size == 0) {
			return 0;
		}

		if (element.compareTo(get(0)) <= 0) {
			return 0;
		}
		
		if (element.compareTo(get(size()-1)) > 0) {
			return size();
		}
		
		int low = 0;
		int high = size()-1;
				
		while (low < high) {
			int mid = (low+high) / 2;
			if (element.compareTo(get(mid)) < 0) {
				high = mid;
			} else if (element.compareTo(get(mid)) > 0) {
				low = mid+1;
			} else {
				// Element at position mid is equal to the element
				// we are looking up. Walk down toward zero to find
				// the first index where that is true.
				
				while ((element.compareTo(get(mid)) == 0) && (mid >= 0)) {
					mid--;
				}
				return mid+1;
			}
		}
		
		// If here, that means low and high have converged on where
		// this element is expected to be. Can return either low or high
		// as they should be the same.

		return low;		
	}

	@Override
	public boolean contains(T element) {
		int i = lookup(element);
		return (get(i).compareTo(element) == 0);
	}

	@Override
	public void add(T element) {
		// Resize if necessary
		
		if (_size == _elements.length) {
			T[] resized_elements = (T[]) new Object[_elements.length*2];
			for (int i=0; i<_size; i++) {
				resized_elements[i] = _elements[i];
			}
			_elements = resized_elements;
		}
		
		// Look up where this elements should go.
		
		int idx = lookup(element);

		// Move everything from end of array
		// down to index for new element over by one.
		
		for (int i=_size+1; i>idx; i--) {
			_elements[i] = _elements[i-1];
		}
		
		// Insert new element and adjust _size
		_elements[idx] = element;		
		_size += 1;
	}

	@Override
	public void remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		
		for (int i=idx; i<size()-1; i++) {
			_elements[i] = _elements[i+1];
		}
		_size -= 1;
	}

	@Override
	public void remove(T element) {
		if (contains(element)) {
			remove(lookup(element));
		}
	}

	@Override
	public T get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		
		return _elements[idx];
	}

}
