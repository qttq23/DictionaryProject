package TryDictionary;

import java.util.*;
import javax.swing.*;

public class Main{

	public static void main(String[] args){


		// HomeFrame homeFrame = new HomeFrame("Dictionary");
		// homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// homeFrame.pack();
		// //homeFrame.setSize(800,500);
		// homeFrame.setVisible(true);

		
		// create dictionary
		String dataFile = "emotional dictionary.txt";
		String outputFile = "output.txt";
		IconDictionary iconDic = new IconDictionary();

		// create dictionary bus
		DictionaryHelper dicHelper = new DictionaryHelper(iconDic, dataFile, outputFile);

		// create gui
		HomeFrame frame = new HomeFrame("Dictionary v1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBus(dicHelper);

		frame.pack();
		frame.setVisible(true);
		

		// for(String s: list){
		// 	System.out.println(s + "-" + dicHelper.search(s));
		// }
		
		//System.out.println(list);

		// search
		//	find a word
		// String buffer = "";
		// Scanner scanner = new Scanner(System.in);
		// while(true){

		// 	//Scanner scanner = new Scanner(System.in);
		// 	char c = scanner.next().charAt(0);
		// 	scanner.reset();
		// 	buffer += (new String(new char[]{c}
		// 	)).toLowerCase();
		// 	System.out.println(buffer);

		// 	int index = dicHelper.indexOfWordStartWith(buffer);

		// 	if(index >= 0){
		// 		String key = dicHelper.get(index);
		// 		System.out.println(key + "-" + dic.search(key));
		// 	}
		// 	//System.out.println(index);
		// 	else{
		// 		break;
		// 	}
		// }

		// add new word
		//String word = scanner.nextLine();
		//String meaning = scanner.nextLine();
		// dicHelper.add(word, meaning);

		// list = dicHelper.getList();

		// for(String s: list){
		// 	System.out.println(s + "-" + dic.search(s));
		// }

		// // remove a word
		// word = scanner.nextLine();
		// dicHelper.remove(word);
		// list = dicHelper.getList();

		// for(String s: list){
		// 	System.out.println(s + "-" + dic.search(s));
		// }

		//// edit
		// String word = scanner.nextLine();
		// String meaning = scanner.nextLine();
		// dicHelper.edit(word, meaning);

		// // rename
		// String word = scanner.nextLine();
		// String newWord = scanner.nextLine();
		// dicHelper.rename(word, newWord);

		// // save
		// dicHelper.saveDataFromDictionary();

		//// export icon
		// String word = scanner.nextLine();
		// dicHelper.export(word);


		// history
		// String word = scanner.nextLine();
		// System.out.println(word + "-" + dicHelper.search(word));


		// word = scanner.nextLine();
		// System.out.println(word + "-" + dicHelper.search(word));


		// word = scanner.nextLine();
		// System.out.println(word + "-" + dicHelper.search(word));


		// word = scanner.nextLine();
		// System.out.println(word + "-" + dicHelper.search(word));


		// List<String> historyList = dicHelper.history();
		// System.out.println(historyList);

		// list = dicHelper.getList();
		// for(String s: list){
		// 	System.out.println(s + "-" + dicHelper.search(s));
		// }


	}
}