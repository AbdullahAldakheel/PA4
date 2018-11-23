import java.util.*;
public class BST <K extends Comparable<K>, T> implements Map<K,T> {
	BSTNode<T,K> root, current;
	
	/** Creates a new instance of BST */
	public BST() {
		root = current = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	public boolean full() {
		return false;
	}

	@Override
	public void clear() {
		root = current = null;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean find(K key) {
		BSTNode<T,K> p = root,q = root;
		
		if(empty())
			return false;
		
		while(p != null) {
			q = p;
			if(p.key.compareTo(key) == 0 ) {
				current = p;
				return true;
			}
			else if(key.compareTo(p.key) < 0 )
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;

	}

	@Override
	public int nbKeyComp(K key) {
		BSTNode<T,K> p = root,q = root;
		int tmp = 0;
		if(empty())
			return 0;
		
		while(p != null) {
			q = p;
			if(p.key.compareTo(key) == 0 ) {
				current = p;
			}
			else if(key.compareTo(p.key) < 0 )
				p = p.left;
			else
				p = p.right;
	tmp++;
		}
		
		current = q;
		return tmp;
	}

	@Override
	public boolean insert(K key, T data) {
BSTNode<T,K> p, q = current;
		
		if(find(key)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		
		p = new BSTNode<T,K>(key, data);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (key.compareTo(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	@Override
	public boolean remove(K key) {
		//BooleanWrapper removed = new BooleanWrapper(false);
		K k1 = key;
		BSTNode<T,K> p = root;
		BSTNode<T,K> q = null; 
		// Parent of p 
		while (p != null) {
			if (k1.compareTo(p.key) < 0 ) {
				   q =p;
		            p = p.left;	
			} else if (k1.compareTo(p.key) > 0) {
            
            	q = p;
            	p = p.right;
			}else {
				if ((p.left != null) && (p.right != null)) { 
						BSTNode<T,K> min = p.right;
						q = p;
						while (min.left != null) {
							q = min;
							min = min.left;
							}
						p.key = min.key;
						p.data = min.data;
						k1 = min.key;
						p = min;
				         }
						}
     if (p.left != null) {               p = p.left;
     } else {
   p = p.right;
   }
     if (q == null) {
    	 root = p;
    	 } else {
    		 if (k1 < q.key) {
    			 q.left = p;
    			 } else {
    				 q.right = p;
    				 }
    		 }
     current = root;
     return true;
     } 
		}
	return false; 
		
		
	}

	
	@Override
	public List<Pair<K, T>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<K, T>> getRange(K k1, K k2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nbKeyComp(K k1, K k2) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}