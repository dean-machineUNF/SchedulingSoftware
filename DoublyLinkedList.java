
public class DoublyLinkedList 
{
	private Node head;
	private Node tail;	    
	    
	public DoublyLinkedList(Professor[] a)
	{
	    head = null;
	    tail = null;
	    //System.out.println("a Length: " + a.length);
	    
	    for (int i = 0; i < a.length; i++)
	    {
	    	//System.out.println("i: " + i);
	    	Node newNode = new Node(a[i]);
	    	//System.out.println(a[i].isTenureTrack());
	    	//System.out.println(newNode.dData.toString());
	    	System.out.println(newNode.dData.isTenureTrack());
	    	if (isEmpty())
	    	{
	    		head = newNode;
	    		tail = newNode;
	    		System.out.println("Head and Tail");
	    	}// end if
	    	else if (newNode.dData.isTenureTrack())// insert at head of Professor DLL if Professor is TenureTrackFaculty (because TTF are to be matched before NTTF) 
	    	{
	    		head.previous = newNode;
	    		newNode.next = head;
	    		head = newNode;
	    		System.out.println("Head");
	    	}// end else if
	    	else if (!newNode.dData.isTenureTrack())// insert at tail of Professor DLL if Professor is NonTenureTrackFaculty
	    	{
	    		tail.next = newNode;
	    		newNode.previous = tail;
	    		tail = newNode;
	    		System.out.println("Tail");
	    	}// end else if	
	    	System.out.println(newNode.dData.toString());
	    }// end for
	}// end Professor constructor
	
	
	public DoublyLinkedList(Course[] a)
	{
		
	}

	
	public boolean isEmpty()
	{
		return head == null;
	}// end isEmpty()
	
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
	    
	

	
	
}
