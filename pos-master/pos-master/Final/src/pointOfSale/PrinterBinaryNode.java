package pointOfSale;
import java.awt.Font;
import java.io.Serializable;
import javax.print.PrintService;

public class PrinterBinaryNode <T extends Comparable> implements Serializable{
	T printerName;
	Font font;
	PrintService printer;
	boolean checked;
	boolean price;
	
	PrinterBinaryNode <T> left;
	PrinterBinaryNode <T> right;

	public PrinterBinaryNode(T printerName, Font font, PrintService printer, boolean checked, boolean price) {
		this.printerName = printerName;
		this.font=font;
		this.printer=printer;
		this.checked=checked;
		this.price=price;
		left = null;
		right = null;
	}
	
	public boolean getPrice(){
		return price;
	}
	public void setPrice(boolean price){
		this.price=price;
	}
	
	public T getPrinterName() {
		return printerName;
	}

	public void setPrinterName(T data) {
		this.printerName = data;
	}
	public Font getFont(){
		return font;
	}
	public void setFont(Font font){
		this.font=font;
	}
	
	public PrintService getPrinter(){
return printer;	
}
	public void setPrinter(PrintService printer){
		this.printer=printer;
	}
public void setChecked(boolean checked){
	this.checked=checked;
}

public boolean getChecked(){
	return checked;
}
	
	public PrinterBinaryNode<T> getLeft() {
		return left;
	}

	public void setLeft(PrinterBinaryNode<T> left) {
		this.left = left;
	}

	public PrinterBinaryNode<T> getRight() {
		return right;
	}

	public void setRight(PrinterBinaryNode<T> right) {
		this.right = right;
	}
}
