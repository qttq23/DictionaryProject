package TryDictionary;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main{

	public static void main(String[] args){


		// create dictionary
		String dataFile = "emotional dictionary.txt";
		String outputFile = "output.txt";
		IDictionary<String, String> iconDic = new IconDictionary();

		// create dictionary bus
		//DictionaryHelper dicHelper = new DictionaryHelper(iconDic, dataFile, outputFile);
		IDictionaryHelper<String, String> dicHelper = new DictionaryHelper(iconDic, dataFile, outputFile);

		// create gui
		//HomeFrame frame = new HomeFrame("Dictionary v1.0");
		DictionaryFrame<String, String> frame = new HomeFrame("Dictionary v2.1", dicHelper);

		//frame.setBus(dicHelper);
		


	}
}