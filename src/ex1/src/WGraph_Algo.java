package ex1.src;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WGraph_Algo implements weighted_graph_algorithms {
    private weighted_graph graph ;

    public WGraph_Algo(weighted_graph graph) {
        this.graph = graph;
    }

    public WGraph_Algo() {

    }


    @Override
    public void init(weighted_graph g) {
        this.graph = g;
    }

    @Override
    public weighted_graph getGraph() {
        return graph ;
    }

    @Override
    public weighted_graph copy() {
        weighted_graph copy;
        copy = this.graph;
        return copy;
    }

    @Override
    public boolean isConnected() {

        return isConnected(this.graph , this.graph.getV());
    }

    private boolean isConnected(weighted_graph graph, Collection<node_info> v) {
        Iterator it = graph.getV().iterator(); 
        }
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_info> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
