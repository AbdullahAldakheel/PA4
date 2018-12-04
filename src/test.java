
public class test {

	public static void main(String[] args) throws Exception {
//		LocNotManager D = new LocNotManager();
//		
//		Map<Double, Map<Double,LocNot>> A = LocNotManager.load("input.txt");
//		D.save("dsd.txt" ,A);
//		D.load("dsd.txt");
//		D.print(D.getAllNots(A));
		
//		List<Pair<Double,Map<Double,LocNot>>> B = A.getAll();
//		List<Pair<Double,LocNot>> c = new LinkedList <Pair<Double,LocNot>>();
//		B.findFirst();
//		B.findNext();
//		B.findNext();
//		c = B.retrieve().second.getAll();
//		c.findFirst();
//		
//		while(!c.last()) {
//			System.out.println(c.retrieve().second.getLng());
//			c.findNext();
//		}
//		if(c.last()) {
//			
//			System.out.println(c.retrieve().second.getLng());
//		}
		
		//LocNot loc = new LocNot("Hi",100,1,1,1);


		//System.out.println(loc.toString());
		//System.out.println(f);
		
		BST<Double,Double> p=new BST<Double,Double>();
		p.insert(35.0, 35.0);

		p.insert(14.0, 14.0);

		p.insert(5.0, 5.0);

		p.insert(33.0, 33.0);

		p.insert(53.0, 53.0);

		p.insert(50.0, 50.0);

		p.insert(44.0, 44.0);
  
		p.insert(40.0, 40.0);

		p.insert(58.0, 58.0);

		p.insert(55.0, 55.0);

		p.insert(56.0, 56.0);
		p.pqw();
System.out.println("====");
		System.out.println(p.nbKeyComp(35.0, 53.0));
		System.out.println(p.nbKeyComp(33.0, 53.0));
		System.out.println(p.nbKeyComp(1.0, 60.0));
		System.out.println(p.nbKeyComp(58.0, 53.0));
		System.out.println(p.nbKeyComp(53.0, 56.0));
		System.out.println(p.nbKeyComp(50.0, 56.0));
		System.out.println(p.nbKeyComp(50.0, 55.0));
		System.out.println(p.nbKeyComp(40.0, 56.0));
		System.out.println(p.nbKeyComp(5.0, 35.0));
		System.out.println(p.nbKeyComp(50.0, 53.0));
		System.out.println(p.nbKeyComp(50.0, 56.0));
		System.out.println(p.nbKeyComp(5.0, 33.0));
		System.out.println(p.nbKeyComp(40.0, 44.0));
		System.out.println(p.nbKeyComp(0.0, 0.0));
		System.out.println(p.nbKeyComp(-10.0, 0.0));
		System.out.println(p.nbKeyComp(800.0, 53.0));
		System.out.println(p.nbKeyComp(300.0, 800.0));
		System.out.println(p.nbKeyComp(44.0, 40.0)); // 4
		System.out.println(p.nbKeyComp(14.0, 5.0));//2
		System.out.println(p.nbKeyComp(14.0, 33.0));
		System.out.println(p.nbKeyComp(33.0, 14.0));
		System.out.println(p.nbKeyComp(35.0, 14.0));
		System.out.println(p.nbKeyComp(56.0, 53.0));








//
//		p.insert(14, 14);
//		//p.find(0);
//		List<Pair<Integer, Integer>>  T = p.getRange(-1400, 10000);
//		T.findFirst();
//		while(!T.last()) { 
//			System.out.println(T.retrieve().second);
//
//		 	T.findNext();

			
		
//	     if (p == null) {
//	            return; 
//	        }
//	        if(p.key.compareTo(k1) >= 0)
//	        nbKeyCompRec(p.left, k1, k2);
//	  	        if(p.key.compareTo(k1) >= 0 || p.key.compareTo(k2) < 0) {
//	  	        	count+=1;
//	  	        }
//	  	        if( p.key.compareTo(k2) < 0)
//	  	      nbKeyCompRec(p.right, k1, k2); 
	}

}
