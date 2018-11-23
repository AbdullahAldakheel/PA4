public class BSTNode <T,K> {
	public K key;
	public T data;
	public BSTNode<T,K> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(K k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public BSTNode(K k, T val, BSTNode<T, K> l, BSTNode<T, K> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}
}
