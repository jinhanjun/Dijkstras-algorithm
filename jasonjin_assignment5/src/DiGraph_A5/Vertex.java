package DiGraph_A5;
import java.util.HashMap;

public class Vertex {
	long idNum;
	String label;
	HashMap<String, Edge> in; 
	HashMap<String, Edge> out;
	boolean visited;
	long distance;
	Vertex prev;
	
	public Vertex(long idNum, String label)
	{
		 	this.idNum = idNum;
		    this.label = label;
		    this.in = new HashMap<String, Edge>();
		    this.out = new HashMap<String, Edge>();
		    this.visited = false;
		    this.distance = 0;
		    this.prev = null;

	}

	public void delIn(Edge e)
	{
		this.in.remove(e);
	}
	
	public void delOut(Edge e)
	{
		this.out.remove(e);
	}
	
	public long getID()
	{
		return idNum;
	}
}
