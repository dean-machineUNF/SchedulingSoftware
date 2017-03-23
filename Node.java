
public class Node 
{

	Professor dData;
    public Node next;
    public Node previous;
    
    public Node(Professor a)
    {
        dData = a;
        
    }
    
    public void displayNode()
    {
        System.out.print(dData.toString());
    }
    
}// end class Node

	
	
	
