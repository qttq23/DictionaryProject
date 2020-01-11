package TryDictionary;

import java.util.*;
import java.io.*;




public class IndexTable{

	int[] table;
	int MAX_CHAR = 200;
	List<String> list;

	public IndexTable(){
		table = new int[MAX_CHAR];
	}

	public void setList(List<String> list){
		this.list = list;
	}

	public void update(){

		// update table
		for(int i = 0; i < MAX_CHAR; i++){
			table[i] = 99999;
			String alpha = getCharacterOfAscii(i);
			alpha = alpha.toLowerCase();

			for(int j = 0; j < list.size(); j++){
				
				String word = list.get(j).toLowerCase();

				if(word.startsWith(alpha)){
					table[i] = j;
					break;
				}
			}

			//System.out.println(alpha + " - " + table[i] + " - " + ((table[i]<99999)?list.get(table[i]):""));
		}
	}

	public int getAsciiOfFirstCharacter(String haystack){
		int result = (int)(haystack.charAt(0));
		//System.out.println(result);
		return result;
	}

	public String getCharacterOfAscii(int ascii){
		String result = new String(new char[]{(char)ascii});
		//System.out.println(result);
		return result;
	}


	public int get(int index){
		return table[index];
	}

	public int firstIndexOf(String prefix){

		prefix = prefix.toLowerCase();
		int pos = (int)(prefix.charAt(0));
		int index = table[pos];
		System.out.println("lookup table first - " + index);

		int firstIndex = index;
		return firstIndex;
	}

	public int lastIndexOf(String prefix){

		prefix = prefix.toLowerCase();
		int pos = (int)(prefix.charAt(0));
		int nextIndex = list.size();
		int count = pos + 1;
		do{
		
			nextIndex = table[count];
			count++;
		}
		while(nextIndex >= 99999 && count < MAX_CHAR);
		
		int lastIndex = nextIndex - 1;
		System.out.println("lookup table last - " + lastIndex);
		return lastIndex;
	}


}

