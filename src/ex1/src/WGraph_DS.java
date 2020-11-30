package ex1.src;



import java.util.*;


public class WGraph_DS implements weighted_graph {
    private Map<Integer, ArrayList> graph;

    private HashMap<Integer, node_info> nodes;
    private int MC;
    private HashMap<Integer, Double> weight;
    private int nodeSize;
    private int edgeSize;
    private HashMap<Integer, LinkedList<Integer>> ni;

    public NodeInfo nodeInfo() {
        return new NodeInfo();
    }

    public WGraph_DS() {
        this.graph = new HashMap<Integer, ArrayList>();
        this.nodes = new HashMap<Integer, node_info>();
        nodeSize = 0;
        edgeSize = 0;
        this.weight = new HashMap<Integer, Double>();
        this.ni = new HashMap<Integer, LinkedList<Integer>>();

    }


    public WGraph_DS(Map<Integer, ArrayList> graph) {
        this.graph = graph;

    }

    public WGraph_DS(HashMap<Integer, node_info> nodes, HashMap<Integer, Double> weight,
                     HashMap<Integer, LinkedList<Integer>> ni) {
        this.nodes = nodes;
        this.weight = weight;
        this.ni = ni;

    }

    //return node id
    @Override
    public node_info getNode(int key) {
        return nodes.get(key);

    }
    //check if the graph contains both key if not return
    //than check if one node contains other, if yes return yes
    @Override
    public boolean hasEdge(int node1, int node2) {

        if (!(graph.containsKey(node1)) || !(graph.containsKey(node2))) {
            return false;
        }
        boolean ans = false;
        if (graph.containsKey(node1)) {

            ArrayList m = new ArrayList();
            ArrayList m2 = new ArrayList();
            Iterator<ArrayList> it = graph.get(node1).iterator();
            while (it.hasNext()) {
                m.add(it.next());

            }
            if (m.contains(node2)) {
                ans = true;
            }
            if (ans == true) {
                Iterator<ArrayList> other = graph.get(node2).iterator();
                while (other.hasNext()) {
                    m2.add(other.next());
                    if (m2.contains(node1)) {
                        ans = true;
                    }
                }
            }
        }


        return ans;
        }

//return the weight of the edge
    @Override
    public double getEdge(int node1, int node2) {
        // TODO Auto-generated method stub
        int m = node1+ node2;
        return weight.get(m);
    }
//adds a node to the graph , if the node exist or invalid do nothing
//else add to the graph and sum nodeSize
    @Override
    public void addNode(int key) {
        if ((this.graph.containsKey(key)) || key<0) {
            return;
        }
            this.graph.put(key,new ArrayList<>());
            nodes.put(key,nodeInfo());
            nodeSize++;
            MC++;
        }




//check if the graph doesn't contain node1 or node2 - return false
    //else check if they have edge between them - if yes change they weight to the given
    //else add the edge
    @Override
    public void connect(int node1, int node2, double w) {
        // TODO Auto-generated method stub
        if(!(graph.containsKey(node1)) || !(graph.containsKey(node2))) {
            return;
        }
        if(hasEdge(node1,node2)){
            int m = node1+node2;
            if(weight.get(m).equals(w)){
                return;
            }
            weight.replace(m,w) ;


        }else{
            graph.get(node1).add(graph.get(node2));
            graph.get(node2).add(graph.get(node1));
            LinkedList<Integer> m = new LinkedList<>();
            m.add(node1);
            LinkedList<Integer> m2 = new LinkedList<>();
            m2.add(node2);
            ni.put(node1 ,m2);
            ni.put(node2,m);

                weight.put((node1+node2),w);
            edgeSize++;
        }

    }
    //give pointer to all nodes ni in  the graph
    @Override
    public Collection<node_info> getV() {
        // TODO Auto-generated method stub
        return nodes.values();
    }
    //give collection to all ni of this node
    @Override
    public Collection<node_info> getV(int node_id) {
        Iterator<ArrayList> it = graph.get(node_id).iterator();
        Collection m = new ArrayList();
        while(it.hasNext()) {
            m.add(it.next());
        }
        return m;
    }
    // remove node , if not exist return null
    // else remove all ni of this node and than remove nodre
    @Override
    public node_info removeNode(int key) {
        // TODO Auto-generated method stub
        if (!(graph.containsKey(key))) {
            return null;
        }

        if (!graph.get(key).isEmpty()) {

            while (ni.get(key).size() != 0) {

                int m = ni.get(key).removeLast();



                removeEdge(key, m);
            }

    }

        node_info n = getNode(key);
        graph.remove(key);
        nodeSize--;
        return n;
    }
    //remove edge from graph , if the edge not exist return
    //else remove it
    @Override
    public void removeEdge(int node1, int node2) {
        if(!(graph.containsKey(node1)) || ! (graph.containsKey(node2))){
            return;
        }
        if(!hasEdge(node1, node2)){
            return;
        }

        graph.get(node1).remove(graph.get(node2));
        graph.get(node2).remove(graph.get(node1));
        edgeSize -- ;



    }
    //return num of nodes in the graph
    @Override
    public int nodeSize() {
        // TODO Auto-generated method stub
        return nodeSize;
    }
    //return num of edge in the graph
    @Override
    public int edgeSize() {
        // TODO Auto-generated method stub
        return edgeSize;
    }
    //return how much changes were made
    @Override
    public int getMC() {
        // TODO Auto-generated method stub
        return  MC;
    }
    //function that check equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return MC == wGraph_ds.MC &&
                nodeSize == wGraph_ds.nodeSize &&
                edgeSize == wGraph_ds.edgeSize &&
                Objects.equals(graph, wGraph_ds.graph) &&
                Objects.equals(nodes, wGraph_ds.nodes) &&
                Objects.equals(weight, wGraph_ds.weight);
    }
    //function that make hashcode
    @Override
    public int hashCode() {
        return Objects.hash(graph, nodes, MC, weight, nodeSize, edgeSize);
    }

    private class NodeInfo implements node_info {

        private String info ;
        private double tag;
        private int key;

        public NodeInfo() {
            this.key = -1;
        }
        //return key of node
        @Override
        public int getKey() {
            // TODO Auto-generated method stub
            return this.key;
        }
        ///return info of node
        @Override
        public String getInfo() {
            // TODO Auto-generated method stub
            return info;
        }
        //set info of node
        @Override
        public void setInfo(String s) {
            // TODO Auto-generated method stub
            this.info = s;
        }
        //return tag of info
        @Override
        public double getTag() {
            // TODO Auto-generated method stub
            return tag;
        }


        //set tag
        @Override
        public void setTag(double t) {
            // TODO Auto-generated method stub
            this.tag = t;


        }


    }


}
