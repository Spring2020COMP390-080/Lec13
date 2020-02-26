package lec13.tree;

public interface BinaryTree<T> {

	T getValue();
	void setValue(T value);
	void insert(T value);
	BinaryTree<T> getLeft();
	BinaryTree<T> getRight();
	void setLeft(BinaryTree<T> left);
	void setRight(BinaryTree<T> right);
	int height();
}
