package pointOfSale;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrinterReaderWriter implements Serializable {
	PrinterBST tester2=new PrinterBST();
	private final String FILENAME="Printers.dat";
	public synchronized void writeObject(PrinterBST object) throws IOException{
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(FILENAME));
			out.writeObject(object);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized PrinterBST readObject()throws IOException{
		try{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(FILENAME));
			tester2=(PrinterBST) in.readObject();
			in.close();
			return tester2;
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tester2;	
	}
}
