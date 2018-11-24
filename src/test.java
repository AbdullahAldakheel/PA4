
public class test {

	public static void main(String[] args) {
//		Map<Integer,Integer> p=new BST<Integer,Integer>();
//		p.insert(8, 8);
//
//		p.insert(5, 5);
//
//		p.insert(0, 0);
//
//		p.insert(1, 1);
//
//		p.insert(2, 2);
//
//		p.insert(3, 3);
//
//		p.insert(4, 4);
//
//		p.insert(6, 6);
//
//		p.insert(7, 7);
//
//		p.insert(12, 12);
//
//		p.insert(9, 9);
//
//		p.insert(10, 10);
//
//		p.insert(11, 11);
//
//		p.insert(13, 13);
//
//		p.insert(14, 14);
//		
//		System.out.println(p.nbKeyComp(0, 14));
//		
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
		LocNotManager.save("Ah.txt", load);
	}

}
