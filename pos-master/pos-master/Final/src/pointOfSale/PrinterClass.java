package pointOfSale;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
//import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

public class PrinterClass implements Runnable {
		private static final String RECEIPT_PATH = "Files/Receipts/";
		ArrayList<String> strngList=new ArrayList<String>();
		String receipt="";
		String printerName="";
		PrinterBinaryNode printer;
		 int[] pageBreaks;
		 
public PrinterClass (PrinterBinaryNode printer, String receiptName) throws PrinterException{
this.printer=printer;
		receipt=receiptName;	
}
public PrinterClass(){
	printer=null;
			receipt=null;
}

public synchronized void createFile() throws IOException{
PrinterBST printerBST=new PrinterBST();	
PrinterReaderWriter writer=new PrinterReaderWriter();
	PrintService defaultPrinter=PrintServiceLookup.lookupDefaultPrintService();
	PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	PrintService printer=null;
		for (int i=0;i<printServices.length;i++){
			printer=printServices[i];
			printerName=printer.getName();
			if(printer==defaultPrinter){
				printerBST.add(printerName,null,null,true, false);
			}
			else{
				printerBST.add(printerName, null, null, false, false);
			}
			writer.writeObject(printerBST);
		}
}

@Override
public synchronized void run(){
	try {printerName=(String) printer.getPrinterName();
		this.loadReceipt(receipt);
	} catch (PrinterException e) {
		e.printStackTrace();
	}
}
public synchronized void displayPrice(){int a=strngList.size()-1;
for (int i=0; i<4; i++)
{
strngList.remove(a);
a--;	
}
	  for (int i=0;i<strngList.size();i++)
	  {
		  if(strngList.get(i).contains("$")){
			  String list=strngList.get(i);
			 list=list.substring(16);
			  strngList.set(i, list);
		  }
		  else{
			  strngList.set(i, "");
		  }
	  }
	
}
	 
	  public synchronized  void loadReceipt(String receiptFile)throws PrinterException
		{			Scanner inputStream = null;
			try
			{
				inputStream = new Scanner(new File(RECEIPT_PATH + receiptFile));
			}
			catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null,"File Not Found");
			}
			while(inputStream.hasNextLine())
				{strngList.add(inputStream.nextLine());}
			inputStream.close();
			 strngList.trimToSize();
			 if(printer.getPrice()==false){
			 displayPrice();
		 }
		this.printPage();
		}
	  
	  public synchronized PrintService setPrinter(){
		  PrintService[] job = PrinterJob.lookupPrintServices();
			 PrintService myPrinter=null;
	      for (int i = 0; i < job.length; i++){  
	         String svcName = job[i].toString();    
	          if (svcName.contains(printerName)){  
	               myPrinter = job[i];    
	             return myPrinter;  
	          } 
	          }return myPrinter; 
	  }
	  
	  public synchronized void printPage() throws PrinterException{
		  PrinterJob printerJob = PrinterJob.getPrinterJob(); 
		  printerJob.setPrintService(setPrinter());
		  //printerJob.setPrintService(printer.getPrinter());
		  PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		  printerJob.getPageFormat(aset);
		  Book bk = new Book();
		  bk.append(new PaintCover(printer.getFont()), printerJob.defaultPage(), 100);
		 printerJob.setPageable(bk);
		  try {
		  printerJob.print();
		  }catch (Exception exc){
		  System.out.println(exc);
		  }
	  }
		  class PaintCover implements Printable{
		  Font fnt =null;
		  public PaintCover(Font font){
			  if (font!=null){
			  fnt=font;}
			  else{
				  fnt=new Font("Times New Roman", Font.PLAIN, 12);
			  }
		  }
		    
		  public synchronized int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
			  FontMetrics metrics = g.getFontMetrics(fnt);
		        int lineHeight = metrics.getHeight();
		        int linesPerPage = (int)(pf.getImageableHeight()/lineHeight);
	            int numBreaks = (strngList.size())/linesPerPage;
	            pageBreaks = new int[numBreaks];
	            for (int i=0; i<numBreaks; i++) {
	            	pageBreaks[i]= (i+1)*linesPerPage; 
	            }

		  g.setFont(fnt);
		  g.setColor(Color.black);
		  Graphics2D g2d = (Graphics2D)g;
		 g2d.translate(pf.getImageableX(), pf.getImageableY());
		 
//Draws the lines with the height adjusted for font size
		 int y = 0; 
		  int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
		  int end= (pageIndex == pageBreaks.length) ? strngList.size() : pageBreaks[pageIndex];
		  for (int line=start; line<end; line++) {
			  y += lineHeight;
			  g.drawString(strngList.get(line), 0, y);
	
		  }		  return PAGE_EXISTS;
}
		  }
		    		   }