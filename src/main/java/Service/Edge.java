package Service;


import Entity.Vertex;

public interface Edge {
    Vertex getSecondVertex(Vertex vertex);

    Vertex getHeadVertex(Vertex vertex);

    boolean isHeadVertex(Vertex vertex);
}
