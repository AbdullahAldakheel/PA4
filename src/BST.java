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
		current.data=e;
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

	private boolean removed = false;
	public boolean remove(K key) {
		removed = false;
		BSTNode<T,K> p;
		p = remove_aux(key, root, removed);
		current = root = p;
		
	return removed;	
	}
	
	private BSTNode<T,K> remove_aux (K key,BSTNode<T,K> p , boolean flag){
		
		BSTNode<T,K> q , child=null;
		if(p==null) {
			return null;
		}
		if(key.compareTo(p.key) < 0) {
			p.left = remove_aux(key, p.left,flag);
		}else if(key.compareTo(p.key) > 0) {
			p.right = remove_aux(key, p.right,flag);
		}else {
			removed = true;
			if(p.left != null && p.right != null) {
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}else {
				if(p.right == null) {
					child = p.left;
				}else if (p.left == null ) {
					child = p.right;
				}
				return child;
			}
		}
		return p;
		
	}
	private BSTNode<T,K> find_min(BSTNode<T,K> p){
		if(p==null) {
			return null;
		}
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}

	
	@Override
	public List<Pair<K, T>> getAll() {
		LinkedList<Pair<K, T>> tmp = new LinkedList<Pair<K, T>>();
		current = root;
	       if (root == null) 
	            return null; 
	       BSTNode<T, K> p = root;
 	        Pair tmp1 = new Pair(p.key,p.data);
 	       // tmp.insert(tmp1);
	       printInorder(p,tmp );
		
		
		return tmp;
	}
	  private void printInorder(BSTNode<T,K> p,List<Pair<K, T>> tmp ) { 
	        if (p == null) {
	            return ; 

	        }

	  	        printInorder(p.left, tmp); 
	  	        Pair tmp1 = new Pair(p.key,p.data);
	  	        tmp.insert(tmp1);
	  	        printInorder(p.right, tmp); 

	    } 
	  

	@Override
	public List<Pair<K, T>> getRange(K k1, K k2) {
		LinkedList<Pair<K, T>> tmp = new LinkedList<Pair<K, T>>();
		if(root==null) {
			return null;
		}
	       BSTNode<T,K> p = root;
		printInorderForRange(p, tmp, k1, k2);
	        if(p.key.compareTo(k1) >= 0 && p.key.compareTo(k2) <= 0) {
	  	        Pair tmp1 = new Pair(p.key,p.data);
	  	        tmp.insert(tmp1);
  	        }
		return tmp;
	}
	  private void printInorderForRange(BSTNode<T,K> p,List<Pair<K, T>> tmp , K k1,K k2) { 
	        if (p == null) {
	            return; 

	        }
	        printInorderForRange(p.left, tmp, k1, k2);
	  	        if(p.key.compareTo(k1) >= 0 && p.key.compareTo(k2) <= 0) {
		  	        Pair tmp1 = new Pair(p.key,p.data);
		  	        tmp.insert(tmp1);
	  	        }
	  	      printInorderForRange(p.right, tmp, k1, k2); 
	    }

	  private int count=0;
	  
	  public int nbKeyComp(K k1, K k2) {
	       //BSTNode<T,K> p = root;
		  count=0;
			BSTNode<T,K> p = root;
	       nbKeyCompRec(p, k1, k2);
		return count;
	}
	  private void nbKeyCompRec(BSTNode<T,K> p, K k1,K k2) { 
	        if (p == null) {
	            return; 

	        }
	        nbKeyCompRec(p.left, k1, k2);
	  	        if(p.key.compareTo(k1) >= 0 && p.key.compareTo(k2) <= 0) {
	  	        	count+=1;
	  	        }
	  	      nbKeyCompRec(p.right, k1, k2); 
	    }
	  public void print() { 
		  print1(root);
	    }
	  public void print1(BSTNode<T, K> t) { 
	        if (t == null) {
	            return ; 

	        }

	        print1(t.left); 
System.out.println(t.data);
	print1(t.right); 
	    }

}