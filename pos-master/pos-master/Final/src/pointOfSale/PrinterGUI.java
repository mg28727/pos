package pointOfSale;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.print.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class PrinterGUI extends JPanel implements ActionListener, ItemListener {
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);


	private static final long serialVersionUID = 1L;
	
	PrinterBST<String> printerBST=new PrinterBST<String>();
	ArrayList<JCheckBox> theArray=new ArrayList<JCheckBox>();
	private MenuButton systemButton = new MenuButton("Back","System",this);
	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel transactionPanel = new JPanel(new GridLayout(2,2));
	private JPanel receiptButtonPanel = new JPanel(new GridLayout(4,3));
PrinterReaderWriter writer=new PrinterReaderWriter();
	
	PrinterGUI(
			) throws IOException
	{printerBST=writer.readObject();
		setLayout(new GridLayout(1,1));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();

		halfPanel.add(transactionPanel);		
		systemButton.setFont(new Font(Font.SERIF, Font.PLAIN,36));
		receiptButtonPanel.add(systemButton);
		
		Container c = new Container();
		Container s = new Container();
		c.setBackground(DARK_CHAMPAGNE);
	s.setBackground(DARK_CHAMPAGNE);
	receiptButtonPanel.setBackground(DARK_CHAMPAGNE);
		Container pane = new Container();
		pane.setLayout(new BorderLayout());
		c.setLayout(new GridLayout(2, 5));
		s.setLayout(new FlowLayout());
		String printerName;
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		PrintService printer=null;
			for (int i=0;i<printServices.length;i++){
				printer=printServices[i];
				printerName=printer.getName();
				JCheckBox arrayItem=new JCheckBox(printerName);
				arrayItem.setBackground(DARK_CHAMPAGNE);
				if(printerBST.isEmpty())
				{
					arrayItem.setSelected(false);
					}
				else
				{ 
					arrayItem.setSelected(printerBST.getChecked(printerName));
				}
				arrayItem.addItemListener(this);
				theArray.add(arrayItem);
				c.add(theArray.get(i));
				receiptButtonPanel.add(new MenuButton(printer.getName(),printer.getName(),this));
			}
			theArray.trimToSize();
			transactionPanel.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
			transactionPanel.setBackground(DARK_CHAMPAGNE);
			transactionPanel.add(receiptButtonPanel);
	transactionPanel.add(c);
	add(halfPanel);
	writer.writeObject(printerBST);
	}

public void itemStateChanged(ItemEvent e) {
		
		JCheckBox source = (JCheckBox) e.getItemSelectable();
		
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		
		for (int i=0; i<theArray.size(); i++ )
		{		
			 if(source==theArray.get(i))
			{
				 printerBST.setChecked(theArray.get(i).getText(), true);			
			}

		if (e.getStateChange() == ItemEvent.DESELECTED) {
			printerBST.setChecked(source.getText(), false);
		}
		}
		}

	public void actionPerformed(ActionEvent event) {
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		
		for (int i=0; i<printServices.length; i++ )
		{	
			 if(event.getActionCommand()==printServices[i].getName())
			{
				 try {					writer.writeObject(printerBST);
					SystemInit.setPrinterSettings(printServices[i].getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				 }
			}
		 if(event.getActionCommand().equals("System"))
		 {PrinterReaderWriter writer=new PrinterReaderWriter();
			try {
				writer.writeObject(printerBST);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 SystemInit.setAdminScreen();}
	
		}
	}

	  
