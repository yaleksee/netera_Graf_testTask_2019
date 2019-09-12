package Entity;


import Service.Edge;

public class DirectEdge implements Edge {

    private Vertex firstVertex;
    private Vertex secondVertex;

    public DirectEdge(Vertex firstVertex, Vertex secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    @Override
    public Vertex getSecondVertex(Vertex leadingVertex) {
        return secondVertex;
    }

    @Override
    public Vertex getHeadVertex(Vertex slaveVertex) {
        return firstVertex;
    }

    @Override
    public boolean isHeadVertex(Vertex vertex) {

        return vertex.equals(firstVertex);
    }

    @Override
    public String toString() {
        return firstVertex.toString() + " => " + secondVertex.toString();
    }
}
