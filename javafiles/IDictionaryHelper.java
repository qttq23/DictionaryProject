package TryDictionary;

import java.util.*;
import java.io.*;




public interface IDictionaryHelper<KeyT, ValT>{


	public void setDicUsed(boolean b);
	public void loadDataToDictionary();
	public void saveDataFromDictionary();
	public void update();

	// return list of keys in sorted order
	public List<KeyT> getList();

	// return key at index
	public KeyT get(int index);
	// return value at key
	public ValT search(KeyT key);


	public int indexOfWordStartWith(KeyT keyPrefix);

	public int add(KeyT key, ValT value);

	public int remove(KeyT key);

	public int edit(KeyT key, ValT newContent);

	public int rename(KeyT key, KeyT newKey);

	public int export(KeyT key);

	public List<KeyT> history();

	public String getDataFileName();

	public String getOutputFileName();
}

