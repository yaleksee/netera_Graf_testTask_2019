package Service;


import Entity.Vertex;

import java.util.List;

public interface IGraph {

    void addVertex(Vertex vertex);

    void addEdge(Vertex fromVertex, Vertex toVertex);

    List<Edge> getPath(Vertex fromVertex, Vertex toVertex);//
}
