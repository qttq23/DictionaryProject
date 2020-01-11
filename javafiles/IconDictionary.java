package TryDictionary;

import java.util.*;
import java.io.*;




public class IconDictionary implements IDictionary<String, String>{
	Map<String, String> dic; // key to value

	Map<String, String> valueToKeyDic;

	public IconDictionary(){
		
		// dic = new HashMap<String, String>();
		dic = new TreeMap<String, String>(new LowerCaseComparator());
		valueToKeyDic = new TreeMap<String, String>(new LowerCaseComparator());
		
		// System.out.println("treemap");
	}


	public void loadFrom(String filename){
		// read from file
		String result = null;
		byte[] deli = new byte[]{9}; // 'tab' character
		String deliString = new String(deli);

		try(BufferedReader reader = new BufferedReader(
						new FileReader(filename))
		){
			do{
				result = reader.readLine();
				//System.console().printf("%s\n", result);
				if(result != null){
					String[] tokens = result.split(deliString);

					// dictionary
					if(tokens != null && tokens.length >= 2){
						dic.put(tokens[0], tokens[1]);
						valueToKeyDic.put(tokens[1], tokens[0]);
					}
				}

			}
			while(result != null);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void saveTo(String filename){
		// save to file
		String result = "";
		byte[] deli = new byte[]{9}; // 'tab' character
		String deliString = new String(deli);

		for(String key: dic.keySet()){
			result += key + deliString + dic.get(key) + "\n";
		}
		byte[] bytes = result.getBytes();

		try(DataOutputStream dos = new DataOutputStream(
					new FileOutputStream(filename))
		){
			dos.write(bytes, 0, bytes.length);	
		}
		catch(Exception ex){
			ex.printStackTrace();	
		}
	}

	// return list of keys
	public List<String> getList(){
		List<String> list = new ArrayList<String>();
		for (String key : dic.keySet()) {
		    // use the key here
		    //System.out.println(key + ": " + dic.get(key)); 
		    list.add(key);
		}


		return list;
	}

	// return list of value
	public List<String> getValueToKeyList(){
		List<String> list = new ArrayList<String>();
		for (String key : valueToKeyDic.keySet()) {
		    // use the key here
		    //System.out.println(key + ": " + dic.get(key)); 
		    list.add(key);
		}


		return list;
	}

	

	public String search(String icon){
		return dic.get(icon);
	}

	public String searchValueToKey(String value){
		return valueToKeyDic.get(value);
	}

	// add new key
	// result 0 if success, else if false
	public int add(String icon, String meaning){
		int result = -1;

		if(dic.get(icon) == null){
			dic.put(icon, meaning);
			valueToKeyDic.put(meaning, icon);
			result = 0;
		}

		return result;
	}

	public int remove(String icon){
		int result = -1;

		if(dic.get(icon) != null){
			String value = search(icon);
			dic.remove(icon);
			valueToKeyDic.remove(value);
			result = 0;
		}

		return result;
	}

	public int edit(String icon, String newIcon, String newMeaning){
		int result = -1;

		if(dic.get(icon) != null){
			if(icon.equals(newIcon)){
				// this means update the content
				String content = search(icon);
				dic.put(icon, newMeaning);
				valueToKeyDic.remove(content);
				valueToKeyDic.put(newMeaning, icon);
			}
			else{
				// this means change name of icon
				String content = search(icon);
				dic.remove(icon);
				valueToKeyDic.remove(content);
				dic.put(newIcon, newMeaning);
				valueToKeyDic.put(newMeaning, newIcon);
			}

			result = 0;
		}

		return result;
	}

	// append icon to filename
	public void export(String filename, String icon){

	}

}

class LowerCaseComparator implements Comparator<String>{
	public int compare(String s1, String s2){
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	}
}