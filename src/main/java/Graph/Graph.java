package Graph;

import Entity.DirectEdge;
import Entity.UnDirectedEdge;
import Entity.Vertex;
import Service.*;

import java.util.*;

public class Graph implements IGraph {

    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Edge>> path;
    boolean isDirect;

    public Graph(boolean isDirect) {
        init();
        this.isDirect = isDirect;
    }

    private void init() {
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        path = new HashMap<>();
    }

    /**
     * Добавляет вершину в граф
     *
     * @param newVertex - добавляемвая вершина
     */
    @Override
    public void addVertex(Vertex newVertex) {
        if (vertices.contains(newVertex)) {
            throw new IllegalArgumentException(String.format("Vertex %s exists in the Graph", newVertex));
        }
        vertices.add(newVertex);
    }

    /**
     * Добавляет ребро между 2 вершинами.
     * И если вершины новые то добавляем их в наш граф
     *
     * @param fromVertex - входная вершина
     * @param toVertex   - конечная вершина
     */
    @Override
    public void addEdge(Vertex fromVertex, Vertex toVertex) {
        if (!vertices.contains(fromVertex)) {
            addVertex(fromVertex);
        }
        if (!vertices.contains(toVertex)) {
            addVertex(toVertex);
        }
        if (isDirect = true) {
            Edge edge = createDirectEdge(fromVertex, toVertex);
            edges.add(edge);
        } else {
            Edge edge = createUnDirectEdge(fromVertex, toVertex);
            edges.add(edge);
        }
    }

    private Edge createDirectEdge(Vertex fromVertex, Vertex toVertex) {
        return new DirectEdge(fromVertex, toVertex);
    }

    private Edge createUnDirectEdge(Vertex fromVertex, Vertex toVertex) {
        return new UnDirectedEdge(fromVertex, toVertex);
    }

    /**
     * Метод поиска между 2 вершинами в графе
     *
     * @param fromVertex - стартовая вершина
     * @param toVertex   - конечная вершина
     * @return - список вершин между стартовой и конечной вершиной
     */
    @Override
    public List<Edge> getPath(Vertex fromVertex, Vertex toVertex) {
        if (vertices == null) {
            throw new IllegalArgumentException(String.format("Cannot search in empty graph"));
        }
        if (!vertices.contains(fromVertex) || !vertices.contains(toVertex))
            return null;
        if (fromVertex.equals(toVertex))
            return new LinkedList<Edge>();
        /**
         * Определяем вспомогательные конструкции
         * currentVertex - для фиксации текущей вершины
         *
         */
        Vertex currentVertex;
        Set<Vertex> vertexSet;
        Map<Vertex, Edge> vertexSetWithEdges;
        Deque<Vertex> vertexArrayDeque = new ArrayDeque<>();
        Set<Vertex> visitedVertices = new HashSet<>();
        vertexArrayDeque.push(fromVertex);

        do {
            currentVertex = vertexArrayDeque.pop();
            visitedVertices.add(currentVertex);

            vertexSetWithEdges = getVertexSetWithEdges(currentVertex);
            vertexSet = getVertexSet(vertexSetWithEdges, visitedVertices);
            path.putAll(
                    getPathsForVertexSet(
                            vertexSet, vertexSetWithEdges, path)
            );

            vertexArrayDeque.addAll(vertexSet);

        } while (!vertexArrayDeque.isEmpty() && !path.containsKey(toVertex));

        if (path.containsKey(toVertex))
            return path.get(toVertex);
        return null;
    }

    private Map<Vertex, List<Edge>> getPathsForVertexSet(
            Set<Vertex> vertexSet,
            Map<Vertex, Edge> vertexSetWithEdges,
            Map<Vertex, List<Edge>> currentPaths
    ) {
        Map<Vertex, List<Edge>> result = new HashMap<>();
        for (Vertex vertex : vertexSet) {
            List<Edge> pathToVertex = new LinkedList<>();
            Edge edgeToAddIntoAPath = vertexSetWithEdges.get(vertex);

            if (currentPaths.containsKey(
                    edgeToAddIntoAPath.getHeadVertex(vertex)))
                pathToVertex = new LinkedList<>(
                        currentPaths.get(
                                edgeToAddIntoAPath.getHeadVertex(vertex)
                        ));
            pathToVertex.add(edgeToAddIntoAPath);
            result.put(vertex, pathToVertex);
        }
        return result;
    }

    private Set<Vertex> getVertexSet(Map<Vertex, Edge> vertexSetWithEdges,
                                     Set<Vertex> visitedVertices) {
        Set<Vertex> vertexSet = vertexSetWithEdges.keySet();
        vertexSet.removeAll(visitedVertices);
        return vertexSet;
    }

    private Map<Vertex, Edge> getVertexSetWithEdges(Vertex currentVertex) {
        Map<Vertex, Edge> result = new HashMap<>();
        edges.stream().
                forEach(
                        e -> {
                            if (e.isHeadVertex(currentVertex)) {
                                result.put(e.getSecondVertex(currentVertex), e);
                            }
                        }
                );

        return result;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
