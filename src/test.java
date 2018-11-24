
public class test {

	public static void main(String[] args) {
		
		LocNotManager D = new LocNotManager();

		Map<Double, Map<Double, LocNot>> A= D.load("input.txt");

		List<Pair<Double, Map<Double, LocNot>>> B = A.getAll();

		List<Pair<Double, LocNot>> C= new LinkedList<Pair<Double, LocNot>>();

		C.findFirst();

		B.findFirst();

		while(!B.last()) {

		C=B.retrieve().second.getAll();

		C.findNext();

		B.findNext();

		}

		C=B.retrieve().second.getAll();

		C.findFirst();

		B.findFirst();

		while(!C.last()) {

		System.out.print(B.retrieve().first+" ");

		System.out.println(C.retrieve().second.getLat()+
		"+C.retrieve().second.getLng()+"
		"+C.retrieve().second.getMaxNbRepeats()+"
		"+C.retrieve().second.getNbRepeats()+"
		"+C.retrieve().second.getText());

		C.findNext();

		B.findNext();

		}

		System.out.print(B.retrieve().first+" ");

		System.out.println(C.retrieve().second.getLat()+
		"+C.retrieve().second.getLng()+"
		"+C.retrieve().second.getMaxNbRepeats()+"
		"+C.retrieve().second.getNbRepeats()+"
		"+C.retrieve().second.getText());

	}

}
