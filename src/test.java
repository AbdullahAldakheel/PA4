
public class test {

	public static void main(String[] args) {
		LocNotManager D = new LocNotManager();
		Map<Double, Map<Double,LocNot>> A = D.load("input.txt");
		
		LocNot loc = new LocNot("Hi",100,1,1,1);


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
