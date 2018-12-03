import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class LocNotManager {
	// Load notifications from file. Assume format is correct. The notifications are
	// indexed by latitude then by longitude.
	public static Map<Double, Map<Double, LocNot>> load(String fileName) throws Exception,FileNotFoundException{
		boolean check = false;
		// BST<Double,LocNot> Mini=new BST<Double,LocNot>();
		Map<Double, Map<Double, LocNot>> Max = new BST<Double, Map<Double, LocNot>>();
		Map<Double, LocNot> Mini = new BST<Double, LocNot>();
		double zg = -345435435;
		double zg2 = zg;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			while ((line = br.readLine()) != null) {

				String[] words = line.split("\\s+"); // splits by whitespace
				String tmp = "";

				for (int i = 0; i < words.length; i++) {
					if (i == 4) {
						for (int j = 4; i < words.length; i++) {
							tmp += words[i];
							tmp += " ";
						}
						break;
					}
				}

				LocNot add = new LocNot(tmp, Double.parseDouble(words[0]), Double.parseDouble(words[1]),
						Integer.parseInt(words[2]), Integer.parseInt(words[3]));
				Mini = new BST<Double, LocNot>();

				Mini.insert(Double.parseDouble(words[1]), add);

				boolean t = Max.insert(Double.parseDouble(words[0]), Mini);
				if (!t) {
					Max.find(Double.parseDouble(words[0]));
					Max.retrieve().insert((Double.parseDouble(words[1])), add);
				}
			}

		} catch (Exception e) {
			return Max;
		}

		return Max;
	}

	// Save notifications to file.
	public static void save(String fileName, Map<Double, Map<Double, LocNot>> nots) {
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {


			List<LocNot> l =getAllNots(nots);
			if (!l.empty()) {
				l.findFirst();
				while (!l.last()) {
					System.out.println();
					br.write(l.retrieve().toString());
					br.newLine();
					l.findNext();
				}
				br.write(l.retrieve().toString());

				br.newLine();
			} else {
				System.out.println("Empty to save");
			}
		}catch(Exception e){
			
		}
		
		
	}

	// Return all notifications sorted first by latitude then by longitude.
	public static List<LocNot> getAllNots(Map<Double, Map<Double, LocNot>> nots) {
		List<LocNot> notf = new LinkedList<LocNot>();
if(nots.empty()) {
	return notf;
}
		//Map<Double, Map<Double, LocNot>> Max=nots;
		List<Pair<Double, Map<Double, LocNot>>> in = nots.getAll();
		
		if(!in.empty()) {
			in.findFirst();
			while(!in.last()) {
				List<Pair<Double, LocNot>> inIn = in.retrieve().second.getAll();
				 
				if(!inIn.empty()) {
					inIn.findFirst();
					while(!inIn.last()) {
						notf.insert(inIn.retrieve().second);
						inIn.findNext();
					}
					notf.insert(inIn.retrieve().second);
				}
				
				in.findNext();
			}
			List<Pair<Double, LocNot>> inIn = in.retrieve().second.getAll();
			
			if(!inIn.empty()) {
				inIn.findFirst();
				while(!inIn.last()) {
					notf.insert(inIn.retrieve().second);
					inIn.findNext();
				}
				notf.insert(inIn.retrieve().second);
			}
			
		}
		
		return notf;
	}

	// Add a notification. Returns true if insert took place, false otherwise.
	public static boolean addNot(Map<Double, Map<Double, LocNot>> nots, LocNot not) {
		Map<Double,LocNot> Mini=new BST<Double,LocNot>();
		Map<Double, Map<Double, LocNot>> Max=new BST<Double, Map<Double, LocNot>>();
		
		
		double y=not.getLng();
		double x = not.getLat();

		Mini.insert(y, not);
			boolean G1 = nots.insert(x, Mini);
			if(G1) {
				return true;
			}else {
				nots.find(x);
				nots.retrieve().insert(y, not);
				return true;
			}
 

		
		
		

	}

	// Delete the notification at (lat, lng). Returns true if delete took place, false otherwise.
	public static boolean delNot(Map<Double, Map<Double, LocNot>> nots, double lat, double lng) {
		boolean first = nots.find(lat);
				if(first) { 
					boolean second = nots.retrieve().find(lng);
			if(second) {
				List<Pair<Double, LocNot>> Tmp = nots.retrieve().getAll();
				Tmp.findFirst();
				int count=1; 
				while(!Tmp.last()) {
					Tmp.findNext();
					count++;
				}
				
				
				if(count>1) {
					nots.retrieve().find(lng);
					return nots.retrieve().remove(lng);
				}
				return nots.remove(lat);

			
				
		}
	} 
		return false;

	}
	

	// Return the list of notifications within a square of side dst (in meters) centered at the position (lat, lng) (it does not matter if the notification is active or not). Do not call Map.getAll().
	public static List<LocNot> getNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		
		List<LocNot> notfg = new LinkedList<LocNot>();
		double newDst = GPS.angle(dst/2);
		Map<Double, Map<Double, LocNot>> Max=nots;
		
		
		List<Pair<Double, Map<Double, LocNot>>> in = Max.getRange(lat-newDst, lat+newDst);
		
		if(nots.empty()) {
			return notfg;
		}
		
		
		if(!in.empty()) {
			in.findFirst();
			while(!in.last()) {
				List<Pair<Double, LocNot>> inIn = in.retrieve().second.getRange(lng-newDst, lng+newDst);
				
				if(!inIn.empty()) {
					inIn.findFirst();
					while(!inIn.last()) {
						notfg.insert(inIn.retrieve().second);
						inIn.findNext();
					}
					
					notfg.insert(inIn.retrieve().second);
				}
				
				in.findNext();
			}
			List<Pair<Double, LocNot>> inIn = in.retrieve().second.getRange(lng-newDst, lng+newDst);
			
			if(!inIn.empty()) {
				inIn.findFirst();
				while(!inIn.last()) {
					notfg.insert(inIn.retrieve().second);
					inIn.findNext();
				}
				notfg.insert(inIn.retrieve().second);
			}
			
		}
		
		return notfg;
	}

	// Return the list of active notifications within a square of side dst (in meters) centered at the position (lat, lng). Do not call Map.getAll().
	public static List<LocNot> getActiveNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		List<LocNot> hashm = getNotsAt(nots, lat, lng, dst);
		LinkedList<LocNot> hashmAct = new LinkedList<LocNot>();
			if(!hashm.empty()) {
				hashm.findFirst();
				while(!hashm.last()) {
					boolean tmp = hashm.retrieve().isActive();
					if(tmp) {
						hashmAct.insert(hashm.retrieve());
					}
					hashm.findNext();
				}
				boolean tmp = hashm.retrieve().isActive();
				if(tmp) {
					hashmAct.insert(hashm.retrieve());
				}
			}

		
		
		
		return hashmAct;
	}

	// Perform task of any active notification within a square of side dst (in meters) centered at the position (lat, lng) (call method perform). Do not call Map.getAll().
	public static void perform(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		List<LocNot> aT = getNotsAt(nots, lat, lng, dst);
	if(!aT.empty()) {
		aT.findFirst();
		while(!aT.last()) {
			if(aT.retrieve().isActive()) {
				aT.retrieve().perform();
			}
			aT.findNext();
		}
		if(aT.retrieve().isActive()) {
			aT.retrieve().perform();
		}
		
	}
		

	}

	// Return a map that maps every word to the list of notifications in which it appears. The list must have no duplicates.
	public static Map<String, List<LocNot>> index(Map<Double, Map<Double, LocNot>> nots) {
		List<LocNot> newL = getAllNots(nots);
		//LinkedList<LocNot> tmp = new LinkedList<LocNot>();
		Map<String, List<LocNot>> Max=new BST<String, List<LocNot>>();
		if(nots.empty()) {
			
			return Max;
		}
		
		if(!newL.empty()) {
			newL.findFirst();
			while(!newL.last()) {
				String tmp = newL.retrieve().getText();
				String[] words = tmp.split("\\s+"); 
				
				int t = words.length;
				for(int i = 0 ; i < t ; i++) {
					LinkedList<LocNot> tmp1 = new LinkedList<LocNot>();
					tmp1.insert(newL.retrieve());
					boolean go = Max.insert(words[i], tmp1);
					if(!go) {
						Max.find(words[i]);
					Max.retrieve().insert(newL.retrieve());			
					}
					
				}
				
				
				newL.findNext();
			}
			String tmp = newL.retrieve().getText();
			String[] words = tmp.split("\\s+"); 
			
			int t = words.length;
			for(int i = 0 ; i < t ; i++) {
				LinkedList<LocNot> tmp1 = new LinkedList<LocNot>();
				tmp1.insert(newL.retrieve());
				boolean go = Max.insert(words[i], tmp1);
				if(!go) {
					Max.find(words[i]);
				Max.retrieve().insert(newL.retrieve());			
				}
				
			}
			
			newL.findNext();
			
		}
	
		

//		if(!newL.empty()) {
//			newL.findFirst();
//			
//			String A = newL.retrieve().getText();
//			String[] words = A.split("\\s+"); 
//			System.out.println(words[0]);
//			
//				while(!newL.last()) {
//
//					tmp.insert(newL.retrieve());
//					for(int i=0 ; i<A.length() ; i++) {
//						
//						
//						boolean Nope = Max.insert(words[i], tmp);
//						if(!Nope) {
//							Max.find(words[i]);
//							Max.retrieve().insert(newL.retrieve());
//						}
//						tmp = new LinkedList<LocNot>();
//					}
//					
//					
//					
//					newL.findNext();
//				} 
//			
//		}
		
		return Max;
	
	}

	// Delete all notifications containing the word w.
	public static void delNots(Map<Double, Map<Double, LocNot>> nots, String w) {

		if (nots.empty()) {
			return;
		}
		List<LocNot> test = getAllNots(nots);
		if (!test.empty()) {
			test.findFirst();
			while (!test.last()) {
				if (test.retrieve().getText().contains(w)) {
					delNot(nots, test.retrieve().getLat(), test.retrieve().getLng());
				}

				test.findNext();

			}

			if (test.retrieve().getText().contains(w)) {

				delNot(nots, test.retrieve().getLat(), test.retrieve().getLng());
			}
			test.findNext();
		}

	}

	// Print a list of notifications in the same format used in file.
	public static void print(List<LocNot> l) {
		System.out.println("-------------------------------------------------------------------------------------");
		if (!l.empty()) {
			l.findFirst();
			while (!l.last()) {
				System.out.println(l.retrieve());
				l.findNext();
			}
			System.out.println(l.retrieve());
		} else {
			System.out.println("Empty");
		}
		System.out.println("------------------");
	}

	// Print an index.
	public static void print(Map<String, List<LocNot>> ind) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		List<Pair<String, List<LocNot>>> l = ind.getAll();
		if (!l.empty()) {
			l.findFirst();
			while (!l.last()) {
				System.out.println(l.retrieve().first);
				print(l.retrieve().second);
				l.findNext();
			}
			System.out.println(l.retrieve().first);
			print(l.retrieve().second);
		} else {
			System.out.println("Empty");
		}
		System.out.println("++++++++++++++++++");
	}

}
