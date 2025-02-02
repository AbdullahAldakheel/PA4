import java.util.*;
class BSTNode <T,K> {
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


	int nbKey = 0;
	
	public int nbKeyComp(K key) {
		 nbKey=0;
			BSTNode<T,K> p = root;
			
			
			while(p != null) {
				
				
				if(p.key.compareTo(key) == 0 ) {
					nbKey++;
					break;
				}
				else if(key.compareTo(p.key) < 0 )
					p = p.left;
				else
					p = p.right;
				nbKey++;
			}

			return nbKey;
	}
	  private void nbKeyCompRec(BSTNode<T,K> p, K key) { 
	        if (p == null) {
	            return; 
	        }
	    	
	    	
	        if(p.key.compareTo(key) >= 0 ) 
	        nbKeyCompRec(p.left, key);
	       
	        nbKey+=1;	  	   
	         
	        if(p.key.compareTo(key) < 0 ) 
	  	    nbKeyCompRec(p.right, key); 
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

	
	private boolean removed ;
	public boolean remove(K key) {
		removed = false;
		BSTNode<T,K> p;
		p = remove_aux(key, root, removed);
		if(!empty()) {
			current = root = p;

		}else {
			current = null;
		}
		
		
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
	       if (root == null) 
	            return tmp; 
		current = root;

	       BSTNode<T, K> p = root;
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
			return tmp;
		}
	       BSTNode<T,K> p = root;
		printInorderForRange(p, tmp, k1, k2);

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
		   int countRest=nbKeyComp(k1);
			BSTNode<T,K> p = root;
	       nbKeyCompRec(p, k1, k2);

		return count;
	}
	  
	  private void nbKeyCompRec(BSTNode<T,K> p, K k1,K k2) {  

		if (p == null) {
			return;
		}
		if (p.key.compareTo(k1) > 0)
			nbKeyCompRec(p.left, k1, k2);
		count += 1;

		if (p.key.compareTo(k2) < 0)
			nbKeyCompRec(p.right, k1, k2);
	}
	

	  
	  
	  public void pqw() {
		  print(root); 
	  }

public void print(BSTNode<T,K> p){
    if (p == null) {
        return ; 

    }
      print(p.left); 
      System.out.println(p.data);
      print(p.right); 
      
}
}

	  
