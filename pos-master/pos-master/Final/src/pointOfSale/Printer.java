package pointOfSale;

import java.awt.print.PrinterException;

public class Printer {
PrinterBinaryNode<String> printer;
String receipt;
Runnable r1;
Thread t1;
public Printer(PrinterBinaryNode printer, String receipt) throws PrinterException, InterruptedException{
	this.printer=printer;
	this.receipt=receipt;
	  r1= new PrinterClass(this.printer,receipt);	     
		 t1 = new Thread(r1);
		 t1.start();
		 t1.join();	
}

}
