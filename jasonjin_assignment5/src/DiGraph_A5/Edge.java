package DiGraph_A5;
import java.util.HashSet;



public class Edge {
	public long idNum;
	public String label;
	public Vertex begin;
	public Vertex end;
	public long weight;
	
	public Edge( long idNum, Vertex start, Vertex end, String label, long weight){
		this.idNum = idNum;
		this.label = label;
		this.begin = start;
		this.end = end;
		this.weight = weight;
	}
	

	
	public long getID() {
		return idNum;
	}
	

	public void setID(long idNum) {
		this.idNum = idNum;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}
}
