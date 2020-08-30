package graph;

import java.util.ArrayList;
import java.util.List;


public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {
    private List<T> vertexes;
    private List<List<Boolean>> edges;
    private int n;
    private int alpha;

    public AdjacencyMatrixGraphImpl() {
        this.vertexes = new ArrayList<>();
        this.edges= new ArrayList<>();
        this.n = 0;
        this.alpha =0;
    }

    @Override
    public void addVertex(T x) {
        vertexes.add(n,x);
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            list.add(false);
        }
        for (List<Boolean> edgeList: edges) {
            edgeList.add(false);
        }
        edges.add(n,list);
        n++;
    }

    @Override
    public boolean hasVertex(T v) {
        return vertexes.contains(v);
    }

    @Override
    public void removeVertex(T x) {
        if (hasVertex(x)){
            vertexes.remove(x);
            if (n>1) {
                for (List<Boolean> booleans : edges) {
                    booleans.remove(n - 1);
                }
            }
            n--;
        }
        else throw new IllegalArgumentException("The vertex is not present in the graph");
    }

    @Override
    public void addEdge(T v, T w) {
        if (hasVertex(v) && hasVertex(w)){
            int vIndex=0;
            int wIndex=0;
            for (int i = 0; i < vertexes.size(); i++) {
                if (vertexes.get(i).equals(v)){
                    vIndex=i;
                }
                if (vertexes.get(i).equals(w)){
                    wIndex=i;
                }
            }


            edges.get(vIndex).set(wIndex,true);
            edges.get(wIndex).set(vIndex,true);
            alpha++;

        }
    }

    @Override
    public void removeEdge(T v, T w) {
        if (hasVertex(v) && hasVertex(w)){
            int vIndex=0;
            int wIndex=0;
            for (int i = 0; i < vertexes.size(); i++) {
                if (vertexes.get(i).equals(v)){
                    vIndex=i;
                }
                if (vertexes.get(i).equals(w)){
                    wIndex=i;
                }
            }

            edges.get(vIndex).set(wIndex,false);
            edges.get(wIndex).set(vIndex,false);
            alpha--;

        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if (hasVertex(v) && hasVertex(w)){
            int vIndex=0;
            int wIndex=0;
            for (int i = 0; i < vertexes.size(); i++) {
                if (vertexes.get(i).equals(v)){
                    vIndex=i;
                }
                if (vertexes.get(i).equals(w)){
                    wIndex=i;
                }
            }

            return edges.get(vIndex).get(wIndex) && edges.get(wIndex).get(vIndex);

        }
        return false;
    }

    @Override
    public int order() {
        return n;
    }

    @Override
    public int alpha() {
        return alpha;
    }

    @Override
    public List<T> getVertexes() {
        return vertexes;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        if (hasVertex(v)){
            int vIndex = 0;
            for (int i = 0; i <vertexes.size() ; i++) {
                if (vertexes.get(i).equals(v))
                    vIndex=i;
            }
            List<T> returningList = new ArrayList<>();
            List<Boolean> adjacencyMatrixRow = edges.get(vIndex);

            for (int i = 0; i <adjacencyMatrixRow.size() ; i++) {
                if (adjacencyMatrixRow.get(i)){
                    if (!returningList.contains(vertexes.get(i))){
                        returningList.add(vertexes.get(i));
                    }
                }
            }

            return returningList;
        }
        else throw new IllegalArgumentException("The given vertex is not present in the graph");
    }
}
