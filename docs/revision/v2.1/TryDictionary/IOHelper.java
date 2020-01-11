package TryDictionary;

import java.util.*;
import java.io.*;

public class IOHelper{

// write an 'integer (4 bytes)' to specific file.
public static void writeIntFile(String filename, int i){
	try(DataOutputStream dos = new DataOutputStream(
					new FileOutputStream(filename))
	){
		dos.writeInt(i);	
	}
	catch(Exception ex){
		ex.printStackTrace();	
	}
	
}

// write an array of 'characters (2bytes)' to specific file.
public static void writeStringFile(String filename, String s){
	try(DataOutputStream dos = new DataOutputStream(
					new FileOutputStream(filename))
	){
		dos.writeChars(s);	
	}
	catch(Exception ex){
		ex.printStackTrace();	
	}
	
}

// write a number of 'bytes (1byte)' to specific file.
public static void writeBytesFile(String filename, byte[] bytes){
	/*try(FileInputStream fis = new FileInputStream(filename)
	){
		fis.write(bytes);
	}
	catch(Exception ex){
		ex.printStackTrace();
	}*/


	try(DataOutputStream dos = new DataOutputStream(
					new FileOutputStream(filename))
	){
		dos.write(bytes, 0, bytes.length);	
	}
	catch(Exception ex){
		ex.printStackTrace();	
	}
}


// read an 'integer (4bytes)' from a specific file.
public static int readIntFile(String filename){

	int result = -999;
	try(DataInputStream dis = new DataInputStream(
					new FileInputStream(filename))
	){
		
		result = dis.readInt();
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	return result;
}

// read a line from file
public static String readLineFile(String filename){
	String result = "";
	try(BufferedReader b = new BufferedReader(
					new FileReader(filename))
	){
		
		result = b.readLine();
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	return result;
}

// read whole file in bytes.
public static byte[] readBytesFile(String filename){
		
	byte[] result = null;
	try(FileInputStream fis = new FileInputStream(filename)
	){
		int size = fis.available();
		//System.out.println(size);

		byte[] bytes = new byte[size];
		fis.read(bytes);
		result = bytes;

		//System.out.println(bytes);
	}
	catch(Exception ex){
		ex.printStackTrace();
	}	

	return result;
}

// read whole file in bytes and convert to string.
public static String readStringFile(String filename){
	
	byte[] bytes = readBytesFile(filename);
	return new String(bytes);
}


public static void writeStringConsole(String s){
	System.out.print(s);
}

public static int readIntConsole(){
	int result = -999;
	try{
		BufferedReader b = new BufferedReader(
					new InputStreamReader(System.in));
		result = Integer.parseInt(b.readLine());
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	return result;
}


public static double readDoubleConsole(){
	double result = -999;
	try{
		BufferedReader b = new BufferedReader(
					new InputStreamReader(System.in));
		result = Double.parseDouble(b.readLine());
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	return result;
}

public static String readLineConsole(){
	String result = "";
	try{
		BufferedReader b = new BufferedReader(
					new InputStreamReader(System.in));
		result = b.readLine();
		
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	return result;
}

// end of class
}