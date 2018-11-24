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
		Map<Double,LocNot> Mini=new BST<Double,LocNot>();
		Map<Double, Map<Double, LocNot>> Max=new BST<Double, Map<Double, LocNot>>();
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
		    	
		    
		    	  System.out.println(tmp);
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
		    String line=null;
		//    while (true) {
		    	
		    	List<Pair<Double, Map<Double, LocNot>>> Max = nots.getAll(); 
		    	Max.findFirst();
	    		double lat =Max.retrieve().first;
	    		System.out.println(lat);
	    		Map<Double,LocNot> Mini= Max.retrieve().second;
	    		//List<Pair<Double, LocNot>> Mini2 =  Mini.getAll();


	
		    		
		    		
//		    		Mini2.findFirst();
//		    		while(!Mini2.last()) {
//			    		double lng = Mini2.retrieve().first;
//			    		br.write(String.valueOf(lat));  br.write(" ");
//			    		br.write(String.valueOf(lng));  br.write(" ");
//			    		LocNot add = Mini2.retrieve().second;
//			    		br.write(add.getMaxNbRepeats());  br.write(" "); br.write(add.getNbRepeats());  br.write(" ");br.write(add.getText());
//			    		br.newLine();
//			    		Mini2.findNext();
//		    		}

		    		
		    	
		    	
		 //   }
    
		}catch(Exception e){
			
		}
		
		
	}

	// Return all notifications sorted first by latitude then by longitude.
	public static List<LocNot> getAllNots(Map<Double, Map<Double, LocNot>> nots) {
		return null;
	}

	// Add a notification. Returns true if insert took place, false otherwise.
	public static boolean addNot(Map<Double, Map<Double, LocNot>> nots, LocNot not) {
		return false;
	}

	// Delete the notification at (lat, lng). Returns true if delete took place, false otherwise.
	public static boolean delNot(Map<Double, Map<Double, LocNot>> nots, double lat, double lng) {
		return false;
	}

	// Return the list of notifications within a square of side dst (in meters) centered at the position (lat, lng) (it does not matter if the notification is active or not). Do not call Map.getAll().
	public static List<LocNot> getNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		return null;
	}

	// Return the list of active notifications within a square of side dst (in meters) centered at the position (lat, lng). Do not call Map.getAll().
	public static List<LocNot> getActiveNotsAt(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
		return null;
	}

	// Perform task of any active notification within a square of side dst (in meters) centered at the position (lat, lng) (call method perform). Do not call Map.getAll().
	public static void perform(Map<Double, Map<Double, LocNot>> nots, double lat, double lng, double dst) {
	}

	// Return a map that maps every word to the list of notifications in which it appears. The list must have no duplicates.
	public static Map<String, List<LocNot>> index(Map<Double, Map<Double, LocNot>> nots) {
		return null;
	}

	// Delete all notifications containing the word w.
	public static void delNots(Map<Double, Map<Double, LocNot>> nots, String w) {
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
