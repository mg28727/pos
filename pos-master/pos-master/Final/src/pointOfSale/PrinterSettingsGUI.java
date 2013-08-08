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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class PrinterSettingsGUI extends JPanel implements ActionListener,ItemListener {
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel transactionPanel = new JPanel(new GridLayout(3,1));
	private JPanel receiptButtonPanel = new JPanel(new GridLayout(4,3));
	private MenuButton systemButton = new MenuButton("Back","Printer",this);
	String printerName=null;
	JCheckBox priceBox;
PrinterReaderWriter writer=new PrinterReaderWriter();
PrinterBST<String> printBST=new PrinterBST<String>();
Font font;
String fontStyle;
String fontSize;


PrinterSettingsGUI(String printerName) throws IOException{
	Container c = new Container();
	c.setBackground(DARK_CHAMPAGNE);
	c.setLayout(new GridLayout(1, 1));
	this.printerName=printerName;
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		halfPanel.add(transactionPanel);
		halfPanel.setBackground(DARK_CHAMPAGNE);
		receiptButtonPanel.add(systemButton);
		systemButton.setFont(new Font(Font.SERIF, Font.PLAIN,36));
printBST=writer.readObject();
if(printBST.getFont(printerName)!=null){
font=printBST.getFont(printerName);
fontStyle=font.getFontName();
fontSize=Integer.toString(font.getSize());
}
else{
fontStyle="Times New Roman";
fontSize="12";
font=new Font(fontStyle,Font.PLAIN, Integer.parseInt(fontSize));
}

 priceBox=new JCheckBox("Display Price?");
 priceBox.setBackground(DARK_CHAMPAGNE);
if(printBST.isEmpty())
{priceBox.setSelected(false);}
else
{ priceBox.setSelected(printBST.getPrice(printerName));
}
priceBox.addItemListener(this);
c.add(priceBox);
add(c);
		String[] font= new String[13];
		font[0]="0";
		for (int i=1; i<13; i++){
			int fontSize=(i*2);
			font[i]=(Integer.toString(fontSize));	
	
		}
		Container s = new Container();
		JComboBox fontsizeBox=new JComboBox (font);
		fontsizeBox.setBackground(DARK_CHAMPAGNE);
		fontsizeBox.addActionListener(this);
		s.setLayout(new GridLayout(2, 5));
		s.add(fontsizeBox);
		s.setBackground(DARK_CHAMPAGNE);
GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
String []fontFamilies = ge.getAvailableFontFamilyNames();
JComboBox fontStyle=new JComboBox(fontFamilies);
fontStyle.setBackground(DARK_CHAMPAGNE);
fontStyle.addActionListener(this);
s.setLayout(new GridLayout(4,6));
s.add(fontStyle);


		transactionPanel.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
		transactionPanel.setBackground(DARK_CHAMPAGNE);
		transactionPanel.add(receiptButtonPanel);
		transactionPanel.add(c);
		transactionPanel.add(s);
		add(halfPanel);
	}
	
	@Override
	public synchronized void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Printer"))
		 {	try {		font= new Font(fontStyle, Font.PLAIN,Integer.parseInt(fontSize));
			 printBST.setFont(printerName, font);
		 writer.writeObject(printBST);
			SystemInit.setPrinterScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 }
		else {
		 JComboBox cb = (JComboBox)event.getSource();
		 		
		 String action = (String)cb.getSelectedItem();
		 if(action.length()<3){
				fontSize=action;	
			}		
		 else{GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 		String []fontFamilies = ge.getAvailableFontFamilyNames();
		 for (int i=0; i<fontFamilies.length; i++){
			 if(action==fontFamilies[i]){
				 fontStyle=action;
			 }
		 }
		 }
		}	
}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JCheckBox source = (JCheckBox) e.getItemSelectable();
if(source==priceBox)
{
	printBST.setPrice(printerName, true);
}
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			printBST.setPrice(printerName, false);
		
	}
	}		
	}