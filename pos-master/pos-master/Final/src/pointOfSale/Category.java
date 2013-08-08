package pointOfSale;



////////////////////added By Ting Zheng 7/26/2013
public class Category implements java.io.Serializable{

	private CategoryNode head, current, previous;
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private int size;
	
	public Category () {
		size=0;
	}
	
	public void addCategory( String newName)
	{
		CategoryNode newNode = new CategoryNode ( newName);
		if (isEmpty()) {
			head = newNode;
			current = newNode;
			previous = newNode;
		}else if (previous.getCategoryLink() !=null) {
			current.setCategoryLink(newNode);
			previous = current;
			current = newNode;
		}else {
			previous.setCategoryLink(newNode);
			current = newNode;
		}
		size++;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (head == null) {
			return true;
		}
		return false;
	}
	/**
	 * Changes an existing category object from active to inactive and sets its name to "Empty"
	 */
	public void deleteCategory(String categoryName)
	{
		CategoryNode curr  = head;
		CategoryNode previous = head;
		boolean found = false;
		
		while (!found && curr !=null) {
			if (curr.getCategoryName().equalsIgnoreCase(categoryName)){
				if (curr == head) {
					head = head.getCategoryLink();
					found = true;
					size--;
				}else {
					if (curr == this.previous) {
						this.previous =previous;
					}else if (this.current ==curr) {
						this.current =previous;
					}
					size--;
					previous.setCategoryLink(curr.getCategoryLink());
					found = true;
				}
			}else {
				
				previous = curr;
				curr = curr.getCategoryLink();
			}
		
	}
	
	
	}
	public CategoryNode findCategory (String categoryName) {
		CategoryNode curr  = head;
		boolean found = false;		
		
		while (curr != null && !found){
			if (curr.getCategoryName().equalsIgnoreCase(categoryName)) {
				return curr;
			}else {
				curr = curr.getCategoryLink();
			}
			
		}
		
		return curr;
	}
	public CategoryNode getfirst () {
		return head;
	}
	public int getCatSize() {
		return size;
	}
public String toString() {
	CategoryNode curr = head;
	String list = "";
	while (curr != null) {
		list=list + curr.getCategoryName()+" " +curr.toString();
		curr =curr.getCategoryLink();
	}
	return list;
}
}


