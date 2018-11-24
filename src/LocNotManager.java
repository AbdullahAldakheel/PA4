import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class LocNotManager {
	// Load notifications from file. Assume format is correct. The notifications are
	// indexed by latitude then by longitude.
	public static Map<Double, Map<Double, LocNot>> load(String fileName) {
		double check = 0;
		BST<Double,LocNot> Mini=new BST<Double,LocNot>();
		BST<Double, Map<Double, LocNot>> Max=new BST<Double, Map<Double, LocNot>>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line=null;
		    while ((line = br.readLine()) != null) {
		    	String[] words = line.split("\\s+"); // splits by whitespace
		    	String tmp= "";
		    	for(int i=0 ; i<words.length ; i++) {
		    		if(i==4) {	
		    			for(int j=4 ; i<words.length ; i++) {
		    				 tmp += words[i];
		    				 tmp += " ";
		    			}
		    			break;
		    		}
		    	}
		    	
		    
					LocNot add = new LocNot(tmp, Double.parseDouble(words[0]), Double.parseDouble(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]));
				
					if( Double.parseDouble(words[0]) ==  check) {
						Mini.insert(Double.parseDouble(words[1]), add);
					}else {
						if(!Mini.empty()) {
							Max.insert(Double.parseDouble(words[0]), Mini);
						}
						
						
						Mini=new BST<Double,LocNot>();
						Mini.insert(Double.parseDouble(words[1]), add);
					}
					check = Double.parseDouble(words[0]);
		    	
		    }
    
		}catch(Exception e){
			
		}
		
		
		return Max;
	}

	// Save notifications to file.
	public static void save(String fileName, Map<Double, Map<Double, LocNot>> nots) {
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {

//			List<Pair<Double, Map<Double, LocNot>>> tmp =  nots.getAll();
//			tmp.findFirst();
//			while(!tmp.last()) {
//				List<Pair<Double, LocNot>> tmp2 =  tmp.retrieve().second.getAll();
//				tmp2.findFirst();
//				while(!tmp2.last()) {
//					br.write(tmp.retrieve().second.retrieve().toString());
//					br.newLine();
//					tmp2.findNext();
//				}
//
//				tmp.findNext();
//			}
//			br.write(tmp.retrieve().second.retrieve().toString());
//			br.newLine();
			//Map<Double, Map<Double, LocNot>>

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
				System.out.println("Empty");
			}
			System.out.println("++++++++++++++++++");
		}catch(Exception e){
			
		}
		
		
	}

	// Return all notifications sorted first by latitude then by longitude.
	public static List<LocNot> getAllNots(Map<Double, Map<Double, LocNot>> nots) {
		LinkedList<LocNot> notf = new LinkedList<LocNot>();
		List<Pair<Double, Map<Double, LocNot>>> tmp2 = nots.getAll();

		if(!tmp2.empty()) {
			tmp2.findFirst();
			while(!tmp2.last()) {
				if(tmp2.retrieve().second.retrieve().perform()) {
					notf.insert(tmp2.retrieve().second.retrieve());
				}
				tmp2.findNext();
			}
			if(tmp2.retrieve().second.retrieve().perform()) {
				notf.insert(tmp2.retrieve().second.retrieve());
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
		if(Mini.insert(y, not)) {
			Mini.insert(y, not);
			if(nots.insert(x, Mini)) {
				return true;
			}
			return false;

		}else{
			return false;

		}

	}

	// Delete the notification at (lat, lng). Returns true if delete took place, false otherwise.
	public static boolean delNot(Map<Double, Map<Double, LocNot>> nots, double lat, double lng) {
				if(nots.find(lat)) {
			if(nots.retrieve().find(lng)) {
				nots.retrieve().clear();
				return true;
		}
	}
		return false;

	}
	

	// Return the list of notifications within a square of side dst (in meters) centered at the position (lat, lng) (it does not matter if the notification is active or not). Do not call Map.getAll().
	public static List<LocNot> getNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		LinkedList<LocNot> aT = new LinkedList<LocNot>();
		double d = GPS.dist(dst, dst, dst, dst);
		if(nots.find(lat)) {
			List<Pair<Double, Map<Double, LocNot>>> tmp = nots.getRange(lat+d, lat-d);
			if(!tmp.empty()) {
				if(tmp.retrieve().second.find(lng)) {
					List<Pair<Double, LocNot>> tmp2 = tmp.retrieve().second.getRange(lng+dst, lng-dst);
						if(!tmp2.empty()) {
							while(!tmp2.last()) {
								aT.insert(tmp2.retrieve().second);
								tmp2.findNext();
							}
							aT.insert(tmp2.retrieve().second);
						}
					return aT;

				}
			}
			

			
			
		}
		return aT;
	}

	// Return the list of active notifications within a square of side dst (in meters) centered at the position (lat, lng). Do not call Map.getAll().
	public static List<LocNot> getActiveNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		List<LocNot> hashm = getNotsAt(nots, lat, lng, dst);
		LinkedList<LocNot> hashmAct = new LinkedList<LocNot>();
		if(!hashm.empty()) {
			hashm.findFirst();
			while(!hashm.last()) {
				if(hashm.retrieve().isActive()) {
					hashmAct.insert(hashm.retrieve());
				}
				hashm.findNext();
			}
			if(hashm.retrieve().isActive()) {
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
		return null;
	}

	// Delete all notifications containing the word w.
	public static void delNots(Map<Double, Map<Double, LocNot>> nots, String w) {
		List<Pair<Double, Map<Double, LocNot>>> tmp2 = nots.getAll();
		tmp2.findFirst();
		while(!tmp2.last()) {
			if(tmp2.retrieve().second.retrieve().getText().contains("w")) {
				tmp2.retrieve().second.clear();
			}
			tmp2.findNext();
		}
		if(tmp2.retrieve().second.retrieve().getText().contains("w")) {
			tmp2.retrieve().second.clear();
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
