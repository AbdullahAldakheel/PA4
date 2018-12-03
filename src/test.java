
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
		
		BST<Integer,Integer> p=new BST<Integer,Integer>();
		p.insert(35, 35);

		p.insert(14, 14);

		p.insert(5, 5);

		p.insert(33, 33);

		p.insert(53, 53);

		p.insert(50, 50);

		p.insert(44, 44);

		p.insert(40, 40);

		p.insert(58, 58);

		p.insert(55, 55);

		p.insert(56, 56);
		p.pqw();
		//System.out.println(p.nbKeyComp(56));
	
		System.out.println(p.nbKeyComp(58, 53));
//
//		p.insert(14, 14);
//		//p.find(0);
//		List<Pair<Integer, Integer>>  T = p.getRange(-1400, 10000);
//		T.findFirst();
//		while(!T.last()) { 
//			System.out.println(T.retrieve().second);
//
//		 	T.findNext();

			
	}

}
