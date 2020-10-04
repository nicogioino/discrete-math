package tp2;

import graph.Graph;
import graph.factory.GraphFactoryImpl;

import java.util.*;

public class Tp2Impl<T> implements Tp2<T> {
    @Override
    public List<T> depth_first_search(Graph<T> graph) {
        if (graph.order() == 0) return new ArrayList<>();
        T firstVertex = graph.getVertexes().get(0);

        return depthRecursiveAux(firstVertex, new ArrayList<>(), graph);
    }

    public List<T> depthRecursiveAux(T vertex, List<T> visited, Graph<T> graph) {
        List<T> adjacencyList = graph.getAdjacencyList(vertex);
        visited.add(vertex);
        for (T adjacentVertex : adjacencyList) {
            if (!visited.contains(adjacentVertex)) {
                List<T> list = depthRecursiveAux(adjacentVertex, visited, graph);
                for (T t : list) {
                    if (!visited.contains(t)) visited.add(t);
                }
            }
        }
        return visited;
    }

    @Override
    public List<T> breadth_first_search(Graph<T> graph) {
        if (graph.order() == 0) return new ArrayList<>();
        List<T> vertexes = new ArrayList<>();
        vertexes.add(graph.getVertexes().get(0));
        return breadthRecursiveAux(vertexes, new ArrayList<>(), graph, 0);
    }

    public List<T> breadthRecursiveAux(List<T> vertexes, List<T> visited, Graph<T> graph, int index) {
        T currentVertex = vertexes.get(index);
        if (visited.contains(currentVertex)) return vertexes;
        if (graph.getAdjacencyList(vertexes.get(index)).isEmpty()) return vertexes;
        else {
            List<T> adjacencyList = graph.getAdjacencyList(currentVertex);

            for (T vertex : adjacencyList) {
                if (!vertexes.contains(vertex)) vertexes.add(vertex);
            }
            visited.add(currentVertex);

            if (index == vertexes.size() - 1) return vertexes;
            return breadthRecursiveAux(vertexes, visited, graph, index + 1);
        }
    }

    @Override
    public boolean exercise_a(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_b(Graph<T> graph, T v) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_c(Graph<T> graph) {
        boolean pathExists = false;

        for (T vertex : graph.getVertexes()) {
            if (existsPath(graph, vertex, vertex)) pathExists = true;
        }

        return pathExists;

    }

    public boolean existsPath(Graph<T> g, T s, T t) {
        List<T> visited = new ArrayList<>();
        return existsPath(g, s, t, visited,s);
    }

    private boolean existsPath(Graph<T> g, T currentNode, T finalNode, List<T> visited,T lastNode) {
        List<T> adjacent = g.getAdjacencyList(currentNode);
        visited.add(currentNode);
        adjacent.remove(lastNode);

        if (adjacent.contains(finalNode)) {
            return true;
        }
        else {
            for (T node : adjacent) {
                if (!visited.contains(node)) {
                   return existsPath(g, node, finalNode, visited, currentNode);
                }

            }
        }
        return false;
    }


    @Override
    public boolean exercise_d(Graph<T> graph) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_e(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_f(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<T> exercise_g(Graph<T> graph, T v, T w) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int exercise_h(Graph<T> graph) {
        int i = 1;
        List<T> list = new ArrayList<>();
        T currentElement;
        List<T> adjacentList;
        List<T> vertexes = graph.getVertexes();
        HashMap<T, Boolean> visitedMap = new HashMap<>();
        Stack<T> stack = new Stack<>();

        for (T vertex : vertexes) {
            visitedMap.put(vertex, false);
        }
        if (vertexes.size() == 0) return 0;
        stack.push(vertexes.get(0));

        while (list.size() != vertexes.size()) {
            if (stack.isEmpty()) {
                i++;
                for (Map.Entry<T, Boolean> v : visitedMap.entrySet()) {
                    if (!v.getValue()) {
                        stack.push(v.getKey());
                        break;
                    }
                }
            } else {
                while (!stack.isEmpty()) {
                    currentElement = stack.pop();
                    exerciseHAux(currentElement, visitedMap, list);
                    adjacentList = graph.getAdjacencyList(currentElement);

                    if (adjacentList.size() != 0) {
                        for (T vertex : adjacentList) {
                            if (!visitedMap.get(vertex)) {
                                stack.push(vertex);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    private void exerciseHAux(T t, HashMap<T, Boolean> visited, List<T> output) {
        if (!visited.get(t)) {
            output.add(t);
            visited.replace(t, false, true);
        }
    }

    @Override
    public boolean exercise_i(Graph<T> g1, Graph<T> g2) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_j(Graph<T> g1, Graph<T> g2) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean exercise_k(Graph<T> g1) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Graph<T> exercise_l(Graph<T> graph) {
        GraphFactoryImpl<T> factory = new GraphFactoryImpl<>();
        Graph<T> complementaryGraph = factory.getGraph();

        List<T> originalVertexes = graph.getVertexes();

        for (T vertex : originalVertexes) {
            complementaryGraph.addVertex(vertex);
        }

        for (T vertex1 : originalVertexes) {
            for (T vertex2 : originalVertexes) {
                if (!graph.hasEdge(vertex1, vertex2)) {
                    if (!vertex1.equals(vertex2)) {
                        complementaryGraph.addEdge(vertex1, vertex2);
                    }
                }
            }
        }

        return complementaryGraph;

    }

    @Override
    public int exercise_m(Graph<T> graph, T v) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Map<T, Integer> exercise_n(Graph<T> graph) {
        List<T> vertexes = graph.getVertexes();
        Map<T, Integer> map = new HashMap<>();

        for (T vertex : vertexes) {
            int adjacencyLength = graph.getAdjacencyList(vertex).size();
            if (graph.hasEdge(vertex, vertex)) {
                adjacencyLength++;
            }
            map.put(vertex, adjacencyLength);
        }
        return map;
    }

}
