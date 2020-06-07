package DiGraph_A5;
import java.util.HashMap;
import java.util.*;




public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	
	HashMap<String, Vertex> nodes;
	HashMap<Long, Edge> edges;
	HashMap<Long, Vertex> nodeIDs; 

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  this.nodes = new HashMap<String, Vertex>();
	  this.edges = new HashMap<Long, Edge>();
	  this.nodeIDs = new HashMap<Long, Vertex>();
  }

@Override
public boolean addNode(long idNum, String label) {
	// TODO Auto-generated method stub
	if (label == null) {
		return false;
	}
	if (nodes.containsKey(label)) {
		return false;
	}
	if (idNum < 0 || nodeIDs.containsKey(idNum))
		return false;

	Vertex v = new Vertex(idNum, label);
	this.nodes.put(label, v);
	this.nodeIDs.put(idNum, v);
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if (nodes.get(sLabel) == null || nodes.get(dLabel) == null || idNum < 0 || edges.containsKey(idNum)) {
		return false;
	}
	Vertex start = nodes.get(sLabel);
	Vertex end = nodes.get(dLabel);

	Edge e = new Edge(idNum, start, end, eLabel, weight);

	if (start.out.containsKey(end.label) || end.in.containsKey(start.label)) {
		return false;
	}

	edges.put(idNum, e);
	start.out.put(dLabel, e);
	end.in.put(sLabel, e);
	//modify destination vertex's indegree
	//dVertex.indegree++;
	return true;
}

@Override
public boolean delNode(String label) {
	if (nodes.containsKey(label) == false) {
		return false;
	}
	Vertex found = nodes.get(label);
	for (Vertex temp : this.nodes.values()) {
		if (temp.in.containsKey(label))
			temp.in.remove(label);

		if (temp.out.containsKey(label)) {
			temp.out.remove(label);
		}
	}
	found.in.clear();
	found.out.clear();
	this.nodes.remove(label);
	nodeIDs.remove(found.getID());
	return true;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	// TODO Auto-generated method stub
	if (this.nodes.containsKey(sLabel) == false) {
		return false;
	}
	if (this.nodes.containsKey(dLabel) == false ) {
		return false;
	}

	Vertex start = nodes.get(sLabel);
	Vertex end = nodes.get(dLabel);

	if (start.out.containsKey(dLabel) == false) {
		return false;
	}
	if (end.in.containsKey(sLabel) == false) {
		return false;
	}

	Edge temp = start.out.get(dLabel);
	long id = temp.getID();
	edges.remove(id);
	start.out.remove(dLabel);
	end.in.remove(sLabel);
	return true;
}

@Override
public long numNodes() {
	// TODO Auto-generated method stub
	return this.nodes.size();}

@Override
public long numEdges() {
	// TODO Auto-generated method stub
	return this.edges.size();
}

@Override
public ShortestPathInfo[] shortestPath(String label) {

	Vertex shortest = this.nodes.get(label);
	shortest.distance = 0;
	shortest.prev = null;
	

	int size = (int) (this.numNodes());
	ShortestPathInfo[] paths = new ShortestPathInfo[size];
	

	MinBinHeap priorityQ = new MinBinHeap();
	//PriorityQueue<EntryPair> pq = new PriorityQueue<EntryPair>();
	
	//create new entry at start
	EntryPair ep = new EntryPair(shortest,0);
	priorityQ.insert(ep);
	//pq.add(ep);

	int index = 0;

	while(priorityQ.size()!=0)
	{		
		long d = priorityQ.getMin().priority; //distance
		Vertex n = priorityQ.getMin().value; //node

		if(!n.visited) {
			n.visited = true;
			priorityQ.delMin();
			//EntryPair temp = pq.poll();
			paths[index] = new ShortestPathInfo(n.label, n.distance);
			index += 1;
		} else {
			priorityQ.delMin();
			//pq.poll();
		}


		for (Edge edge : n.out.values()) { 

			if(edge.end.prev == null)
			{
				edge.end.prev = n;
				edge.end.distance = edge.weight + d;
				ep = new EntryPair(edge.end, edge.end.distance);
				priorityQ.insert(ep);
				//pq.add(ep);
			}

			if(edge.end.prev!=null){
				if (edge.end.distance > edge.weight + d) {
					edge.end.distance = edge.weight + d;
					edge.end.prev= n;
					ep = new EntryPair(edge.end, edge.end.distance);
					priorityQ.insert(ep);
					//pq.add(ep);
				}
			}
		}

	}
	for (Vertex vert : this.nodes.values()){
		if(!vert.visited) {
			vert.distance = -1;
			paths[index] = new ShortestPathInfo(vert.label, vert.distance);
			index += 1;
		}
	}	
	return paths;	
}
  
  // rest of your code to implement the various operations
}