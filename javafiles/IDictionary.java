package TryDictionary;

import java.util.*;
import java.io.*;




public interface IDictionary<KeyT, ValT>{



	public void loadFrom(String filename);

	public void saveTo(String filename);

	// return list of keys
	public List<KeyT> getList();

	public List<ValT> getValueToKeyList();


	// input: key, return: content
	public ValT search(KeyT key);

	public KeyT searchValueToKey(ValT value);

	// add new key
	// result 0 if success, else if false
	public int add(KeyT key, ValT meaning);

	public int remove(KeyT icon);

	public int edit(KeyT key, KeyT newKey, ValT newMeaning);

	// append icon to filename
	public void export(String filename, KeyT key);
}