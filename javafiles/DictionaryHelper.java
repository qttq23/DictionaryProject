package TryDictionary;

import java.util.*;
import java.io.*;




public class DictionaryHelper implements IDictionaryHelper<String, String>{
	IDictionary<String, String> dic;
	String dataFileName = "";
	String outputFileName = "";

	List<String> sortedList;
	List<String> sortedValueToKeyList;
	IndexTable table;
	IndexTable tableValueToKey;

	List<String> historyList;

	boolean isDicUsed;

	public DictionaryHelper(IDictionary<String, String> iconDic, String data, String output){
		dic = iconDic;
		dataFileName = data;
		outputFileName = output;
		isDicUsed = true;
		loadDataToDictionary();

		sortedList = new ArrayList<String>();
		sortedValueToKeyList = new ArrayList<String>();
		table = new IndexTable();
		tableValueToKey = new IndexTable();
		historyList = new ArrayList<String>();
		update();

	}

	public void setDicUsed(boolean isUsed){
		this.isDicUsed = isUsed;
	}

	public void loadDataToDictionary(){
		dic.loadFrom(dataFileName);
	}

	public void saveDataFromDictionary(){
		//dic.saveTo(dataFileName);

		// save to file
		// String filename = this.dataFileName;

		// try(DataOutputStream dos = new DataOutputStream(
		// 			new FileOutputStream(filename))
		// ){
			
		// 	String result = "";
		// 	byte[] deli = new byte[]{9}; // 'tab' character
		// 	String deliString = new String(deli);

		// 	for(String key: sortedList){
		// 		result += key + deliString + dic.search(key) + "\n";
		// 	}
		// 	byte[] bytes = result.getBytes();


		// 	dos.write(bytes, 0, bytes.length);	
		// }
		// catch(Exception ex){
		// 	ex.printStackTrace();	
		// }
		dic.saveTo(dataFileName);
	}

	public void update(){
		// sort
		sortedList = dic.getList();	// dictionary with treemap sorted automatically
		sortedValueToKeyList = dic.getValueToKeyList();
		// Collections.sort(sortedList, new SortLowerCaseWord());

		// update table
		table.setList(this.sortedList);
		table.update();

		tableValueToKey.setList(this.sortedValueToKeyList);
		tableValueToKey.update();
	}



	// return list of keys in sorted order
	public List<String> getList(){
		List<String> list = new ArrayList<String>();
		List<String> usedList;
		if(isDicUsed){
			usedList = sortedList;
		}
		else{
			usedList = sortedValueToKeyList;
		}

		for (String key : usedList) {
		    // use the key here
		    //System.out.println(key + ": " + dic.get(key)); 
		    list.add(key);
		}


		return list;
	}




	// return key at index
	public String get(int index){
		if(isDicUsed)
			return sortedList.get(index);
		else
			return sortedValueToKeyList.get(index);
	}

	// return value at key
	public String search(String key){
		// search
		String result = null;
		if(isDicUsed)
			result = dic.search(key);
		else
			result = dic.searchValueToKey(key);

		// add to history
		if(result != null && isDicUsed){
			if(historyList.contains(key) == false){
				historyList.add(key);
			}
		}

		return result;
	}

	public int indexOfWordStartWith(String prefix){

		int result = -1;

		IndexTable it;
		List<String> usedList;
		if(isDicUsed){
			it=table;
			usedList = sortedList;
		}
		else{
			it = tableValueToKey;
			usedList = sortedValueToKeyList;
		}

		int firstIndex = it.firstIndexOf(prefix);
		if(firstIndex < 99999){
			int lastIndex = it.lastIndexOf(prefix);

			for(int i = firstIndex; i <= lastIndex; i++){
				System.out.println("check from  - " + firstIndex + " - times: " + (i - firstIndex));
				String key = usedList.get(i).toLowerCase();
				if(key.startsWith(prefix.toLowerCase())
				){
					result = i;
					break;
				}
			}

		}

		

		return result;
	}


	public int add(String key, String value){
		// add to dictionary
		int result = -1;

		// check if key and value empty.
		if(key == null || key.length() == 0){
			return 2;
		}

		result = dic.add(key, value);
		if(result == 0){
			// re-sort List
			// update table
			update();
		
		}
		return result;
	}

	public int remove(String key){
		int result = -1;
		// check if key and value empty.
		if(key == null || key.length() == 0){
			return 2;
		}
		result = dic.remove(key);

		if(result == 0){
			update();
		}

		return result;
	}

	public int edit(String key, String newContent){
		// check if key and value empty.
		if(key == null || key.length() == 0){
			return 2;
		}
		return dic.edit(key, key, newContent);
		// just edit content of key
		// no need to update
	}

	public int rename(String key, String newName){
		int result = -1;

		String oldContent = dic.search(key);
		result = dic.edit(key, newName, oldContent);

		// remove and add new key - value
		// need to update
		update();
		return result;
	}

	public int export(String icon){
		int res = -1;
		boolean isAppend = true;
		String filename = this.outputFileName;

		if(dic.search(icon) != null){

			try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename, isAppend)
															)
			){
				
				String result = "";
				byte[] deli = new byte[]{9}; // 'tab' character
				String deliString = new String(deli);

				result += icon + deliString + dic.search(icon) + "\n";
				byte[] bytes = result.getBytes();
				dos.write(bytes, 0, bytes.length);
				res = 0;
			}
			catch(Exception ex){
				ex.printStackTrace();
				res = 1;
			}
		}

		return res;
	}

	public List<String> history(){
		// sort
		Collections.sort(historyList, new SortLowerCaseWord());

		List<String> result = new ArrayList<String>(historyList);
		return result;
	}

	public String getDataFileName(){
		return dataFileName;
	}

	public String getOutputFileName(){
		return outputFileName;
	}

}

class SortLowerCaseWord implements Comparator<String>{
	public int compare(String s1, String s2){
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	}
}