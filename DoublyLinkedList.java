
public class DoublyLinkedList 
{
	private Node head;
	private Node tail;	    
	    
	public DoublyLinkedList(Professor[] a)
	{
	    head = null;
	    tail = null;
	    for (int i = 0; i < a.length; i++)
	    {
	    	Node newNode = new Node(a[i]);
	    	if (isEmpty())
	    	{
	    		head = newNode;
	    		System.out.println("HEad");
	    	}
	    		
	    	else 
	    	{
	    		tail.next = newNode;
	    		newNode.previous = tail;
	    	}// end else	
	    	tail = newNode;
	    }// end for
	}// end Professor constructor
	
	/*
	public DoublyLinkedList(Course[] a)
	{
		
	}
	*/
	
	public boolean isEmpty()
	{
		return head == null;
	}// end isEmpty()
	    
	/*
	public void insertLast(int number, String lastName, String firstName, String code, int yearsInOffice, String party, String homeState)
	{
		Node newNode = new Node(number, lastName, firstName, code, yearsInOffice, party, homeState);
	        
	    if (isEmpty())
	    	head = newNode;
	    else
	    {
	        last.next = newNode;
	        newNode.previous = last;
	    }
	        last = newNode;
	    }
	  */  
	    public void displayForward()
	    {
	        System.out.println("Doubly-Linked List Displayed Using Forward Pointers");
	        Node current = head;
	        while (current != null)
	        {
	            current.displayNode();
	            current = current.next;
	            System.out.println("______________NEXT OBJECT_____________");
	        }// end while
	       
	    }// end displayForward()
	    
	    public void displayBackward()
	    {
	        System.out.println("Doubly-Linked List Displayed Using Rearward Pointers");
	        Node current = tail;
	        while (current != null)
	        {
	            current.displayNode();
	            current = current.previous;
	            System.out.println("______________NEXT OBJECT_____________");
	        }// end while
	   
	    }// end displayBackward()
	    
	

	public void buildList(Professor[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			if (a[i].isTenureTrack())
				System.out.println("Test for TTF: TTF");
			else 
				System.out.println("Test for TTF: NTTF");
			a[i].toString();
			
		}
		
		
	}
	
}
