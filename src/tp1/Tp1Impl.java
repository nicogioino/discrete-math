package tp1;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;

// TODO: implement
public class Tp1Impl<T> implements Tp1<T> {
    @Override
    public void exercise_a(Graph<T> graph) {
        System.out.println("g=(V,A)");
        System.out.println("V= " + graph.getVertexes().toString());
        for (T vertex: graph.getVertexes()) {
            System.out.println("Adjacency list for " + vertex + " is: " + graph.getAdjacencyList(vertex).toString());
        }
    }

    @Override
    public int exercise_b(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_c(Graph<T> graph){
        List<T> vertexWithEdges = new ArrayList<>();

        for (T vertex: graph.getVertexes()) {
            if(graph.getAdjacencyList(vertex).contains(vertex))
                vertexWithEdges.add(vertex);
        }
        return vertexWithEdges;
    }

    @Override
    public boolean exercise_d(Graph<T> graph, T vertex) {
        return !(graph.getAdjacencyList(vertex).size() > 0);
    }

    @Override
    public int exercise_e(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_f(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Graph<T> exercise_g(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int[][] exercise_h(Graph<T> graph) {
        int[][] matrix = new int[graph.order()][graph.order()];
        List<T> vertexes = graph.getVertexes();
        for (int i = 0; i <vertexes.size() ; i++) {
            T currentVertex  = vertexes.get(i);
            List<T> adjacencyList = graph.getAdjacencyList(currentVertex);
            for (int j = 0; j <vertexes.size(); j++) {
                if (adjacencyList.contains(vertexes.get(j))) matrix[i][j] = 1;
                else matrix[i][j] = 0;
            }
        }

        return matrix;
    }

    @Override
    public int[][] exercise_i(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }
}
