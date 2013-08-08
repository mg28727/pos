package pointOfSale;
import java.awt.Font;
import java.io.Serializable;

import javax.print.PrintService;

//import adt.util.BinaryNode;

public class PrinterBST<T extends Comparable<T>> implements Serializable  {
	PrinterBinaryNode<T> root;
	int length;

	public PrinterBST() {
		root = null;
		length=0;
	}

	//@Override
	public synchronized void add(T printerName, Font font, PrintService printer, boolean checked, boolean price) {
		// TODO Auto-generated method stub
		root = recAdd( printerName,  font,  printer,  checked, this.root, price);
		length+=1;
	}

	private synchronized PrinterBinaryNode<T> recAdd(T printerName, Font font, PrintService printer, boolean checked, PrinterBinaryNode<T> tree, boolean price) {
		if (tree == null) {
			tree = new PrinterBinaryNode<T>( printerName,  font,  printer,  checked, price);
		} else if (printerName.compareTo(tree.getPrinterName()) <= 0) {
			tree.setLeft(recAdd(printerName,  font,  printer,  checked, tree.getLeft(), price));
		} else if (printerName.compareTo(tree.getPrinterName()) > 0) {
			tree.setRight(recAdd(printerName,  font,  printer,  checked, tree.getRight(), price));
		}
		return tree;
	}
	public synchronized int size(){
		return recSize(root);
	}
	
	public synchronized int recSize(PrinterBinaryNode<T> branch ) {
		int toReturn = 0;
		if (branch.getLeft() != null) {
			toReturn+=recSize(branch.getLeft());
		}
		
		toReturn ++;
		if (branch.getRight() != null) {
			toReturn+=recSize(branch.getRight());
		}
		return toReturn;
	}
	
	//@Override
	public void remove(T data) {
		// TODO Auto-generated method stub
		length-=1;
	}


	public synchronized boolean getChecked(T data) {
		return recGetChecked(data, this.root);
	}

	private synchronized boolean recGetChecked(T data, PrinterBinaryNode<T> root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return false;// root.getChecked();
		}
		if(data.compareTo(root.getPrinterName())==0) {
			return root.getChecked();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recGetChecked(data,root.getLeft());
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recGetChecked(data,root.getRight());
		} 
		return root.getChecked();

	}


	public synchronized boolean setChecked(T data, boolean newCheck) {
		return recSetChecked(data, this.root,newCheck);
	}

	private synchronized boolean recSetChecked(T data, PrinterBinaryNode<T> root, boolean newCheck) {
		// TODO Auto-generated method stub
		if (root == null) {
			return false;// root.getChecked();
		}
		if(data.compareTo(root.getPrinterName())==0) {
			root.setChecked(newCheck);
			return root.getChecked();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recSetChecked(data,root.getLeft(), newCheck);
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recSetChecked(data,root.getRight(), newCheck);
		} 
		return root.getChecked();

	}
	
	public synchronized boolean getPrice(T data) {
		return recGetPrice(data, this.root);
	}

	private synchronized boolean recGetPrice(T data, PrinterBinaryNode<T> root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return false;// root.getChecked();
		}
		if(data.compareTo(root.getPrinterName())==0) {
			return root.getPrice();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recGetPrice(data,root.getLeft());
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recGetPrice(data,root.getRight());
		} 
		return root.getPrice();

	}


	public synchronized boolean setPrice(T data, boolean newPrice) {
		return recSetPrice(data, this.root,newPrice);
	}

	private synchronized boolean recSetPrice(T data, PrinterBinaryNode<T> root, boolean newPrice) {
		// TODO Auto-generated method stub
		if (root == null) {
			return false;
		}
		if(data.compareTo(root.getPrinterName())==0) {
			root.setPrice(newPrice);
			return root.getPrice();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recSetPrice(data,root.getLeft(), newPrice);
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recSetPrice(data,root.getRight(), newPrice);
		} 
		return root.getPrice();

	}

	public synchronized PrinterBinaryNode getNode(T data) {
		return recGetNode(data, this.root);
	}

	private synchronized PrinterBinaryNode recGetNode(T data, PrinterBinaryNode<T> root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return null;
		}
		if(data.compareTo(root.getPrinterName())==0) {
			
			return root;
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recGetNode(data,root.getLeft());
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recGetNode(data,root.getRight());
		} 
		return root;

	}

	
	public synchronized PrintService getPrinter(T data) {
		return recGetPrinter(data, this.root);
	}

	private synchronized PrintService recGetPrinter(T data, PrinterBinaryNode<T> root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return null; 
		}
		if(data.compareTo(root.getPrinterName())==0) {
			return root.getPrinter();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recGetPrinter(data,root.getLeft());
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recGetPrinter(data,root.getRight());
		} 
		return root.getPrinter();

	}
	
	public synchronized PrintService setPrinter(T data, PrintService printer) {
		return recSetPrinter(data, this.root, printer);
	}

	private synchronized PrintService recSetPrinter(T data, PrinterBinaryNode<T> root, PrintService printer) {
		// TODO Auto-generated method stub
		if (root == null) {
			return null;
		}
		if(data.compareTo(root.getPrinterName())==0) {
			root.setPrinter(printer);
			return root.getPrinter();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recSetPrinter(data,root.getLeft(), printer);
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recSetPrinter(data,root.getRight(), printer);
		} 
		return root.getPrinter();

	}
	
	public synchronized Font getFont(T data) {
		return recGetFont(data, this.root);
	}

	private synchronized Font recGetFont(T data, PrinterBinaryNode<T> root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return null;//return root.getFont();
		}
		if(data.compareTo(root.getPrinterName())==0) {
			return root.getFont();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recGetFont(data,root.getLeft());
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recGetFont(data,root.getRight());
		} 
		return root.getFont();

	}
	
	public synchronized Font setFont(T data, Font font) {
		return recSetFont(data, this.root, font);
	}

	private synchronized Font recSetFont(T data, PrinterBinaryNode<T> root, Font font) {
		if (root == null) {
			
			return null;
		}
		if(data.compareTo(root.getPrinterName())==0) {
			root.setFont(font);
						return root.getFont();
		} else if (data.compareTo(root.getPrinterName())<0) {
			return recSetFont(data,root.getLeft(), font);
		} else if (data.compareTo(root.getPrinterName())>0) {
			return recSetFont(data,root.getRight(), font);
		} 
		return root.getFont();

	}
	


	//@Override
	public synchronized boolean isEmpty() {
		return(root == null);	
	}

	//@Override
	public String inOrder() {
		return inOrder(this.root);
	}

	public String inOrder(PrinterBinaryNode<T> branch) {
		String toReturn = "";
		// Left
		if (branch.getLeft() != null) {
			toReturn +=inOrder(branch.getLeft());
		}
		// Root
		toReturn += branch.getPrinterName().toString()+"\n";
		if (branch.getPrinterName()!=""){
		toReturn+=branch.getPrinterName().toString()+"\n";}
		// Right
		if (branch.getRight() != null) {
			toReturn+= inOrder(branch.getRight());
		}

		return toReturn;
	}

	public String preOrder() {
		return preOrder(this.root);
	}

	public String preOrder(PrinterBinaryNode<T> branch) {

		String toReturn = "";
		// Root
		toReturn += branch.getPrinterName().toString();

		// Left
		if (branch.getLeft() != null) {
			toReturn += preOrder(branch.getLeft());
		}

		// Right
		if (branch.getRight() != null) {
			toReturn += preOrder(branch.getRight());
		}

		return toReturn;

	}

	public String postOrder() {
		return postOrder(this.root);
	}

	public String postOrder(PrinterBinaryNode<T> branch) {

		String toReturn = "";
		// Left
		if (branch.getLeft() != null) {
			toReturn += postOrder(branch.getLeft());
		}

		// Right
		if (branch.getRight() != null) {
			toReturn += postOrder(branch.getRight());
		}

		// Root
		toReturn += branch.getPrinterName().toString();

		return toReturn;

	}

}