package Entity;

import Service.Edge;

public class UnDirectedEdge implements Edge {

    private Vertex firstVertex;
    private Vertex secondVertex;

    public UnDirectedEdge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    @Override
    public Vertex getSecondVertex(Vertex leadingVertex) {
        return leadingVertex.equals(firstVertex) ? secondVertex : firstVertex;
    }

    @Override
    public Vertex getHeadVertex(Vertex slaveVertex) {
        return slaveVertex.equals(firstVertex) ? secondVertex : firstVertex;
    }

    @Override
    public boolean isHeadVertex(Vertex vertex) {
        return vertex.equals(firstVertex) || vertex.equals(secondVertex);
    }

    @Override
    public String toString() {
        return firstVertex.toString() + " - " +
                secondVertex.toString();
    }
}

