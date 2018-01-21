
public class Node 
{

	Professor dData;
    public Node next;
    public Node previous;
    
    /*Constructor for Course Node?*/
    public Node(Professor a)
    {
        dData = a;
    }// end constructor
    
    public void displayNode()
    {
        System.out.print(dData.toString());
    }// end displayNode()
    
}// end class Node

	
	
	
