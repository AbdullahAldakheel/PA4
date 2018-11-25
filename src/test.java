
public class test {

	public static void main(String[] args) {
		BST<Integer,Integer> p=new BST<Integer,Integer>();
		p.insert(8, 8);

		p.insert(5, 5);

		p.insert(0, 0);

		p.insert(1, 1);

		p.insert(2, 2);

		p.insert(3, 3);

		p.insert(4, 4);

		p.insert(6, 6);

		p.insert(7, 7);

		p.insert(12, 12);

		p.insert(9, 9);

		p.insert(10, 10);

		p.insert(11, 11);

		p.insert(13, 13);

		p.insert(14, 14);
		//p.find(0);
		List<Pair<Integer, Integer>>  T = p.getRange(6, 14);
		T.findFirst();
		while(!T.last()) { 
			System.out.println(T.retrieve().second);

			T.findNext();
		}
		//T.findNext();
		//System.out.println(T.retrieve().second);

		System.out.println(p.nbKeyComp(6, 14));
		//T.findNext();
		
		//p.print();
//		
//		LocNotManager.load("input.txt");
		
//		LocNotManager D = new LocNotManager();
//
//		Map<Double, Map<Double, LocNot>> A= D.load("input.txt");
//
//		List<Pair<Double, Map<Double, LocNot>>> B = A.getAll();
//
//		List<Pair<Double, LocNot>> C= new LinkedList<Pair<Double, LocNot>>();
//
//		C.findFirst();
//
//		B.findFirst();
//
//		while(!B.last()) {
//
//		C=B.retrieve().second.getAll();
//
//		C.findNext();
//
//		B.findNext();
//
//		}
//
//		C=B.retrieve().second.getAll();
//
//		C.findFirst();
//
//		B.findFirst();
//
//		while(!C.last()) {
//
//		System.out.print(B.retrieve().first+" ");
//
//		System.out.println(C.retrieve().second.getLat()+""+C.retrieve().second.getLng()+""+C.retrieve().second.getMaxNbRepeats()+""+C.retrieve().second.getNbRepeats()+""+C.retrieve().second.getText());
//
//		C.findNext();
//
//		B.findNext();
//
//		}
//
//		System.out.print(B.retrieve().first+" ");
//
//		System.out.println(C.retrieve().second.getLat()+""+C.retrieve().second.getLng()+""+C.retrieve().second.getMaxNbRepeats()+""+C.retrieve().second.getNbRepeats()+" "+C.retrieve().second.getText());
//
		Map<Double, Map<Double, LocNot>> load = LocNotManager.load("input.txt");
			
		
		//LocNotManager.save("tee.txt", load);
		
		
//			List<Pair<Double, Map<Double, LocNot>>> tmp =  load.getAll();
//			tmp.findFirst();
//			while(!tmp.last()) {
//				List<Pair<Double, LocNot>> tmp2 =  tmp.retrieve().second.getAll();
//				tmp2.findFirst();
//				while(!tmp2.last()) {
//					System.out.println(tmp.retrieve().second.retrieve().toString());
//					tmp2.findNext();
//				}
//				//System.out.println(tmp.retrieve().second.retrieve().toString());
//
//				tmp.findNext();
//			}
//			System.out.println(tmp.retrieve().second.retrieve().toString());
//


			
	}

}
