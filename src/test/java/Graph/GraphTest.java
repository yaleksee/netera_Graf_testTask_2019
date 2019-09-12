package Graph;

import Entity.Vertex;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    /**
     * Метод проверяющий создание графа с поддержкой направленности
     */
    @Test
    public void isDirected() {
        Graph directedGraph = new Graph(true);
        assertTrue(directedGraph.isDirect);

        Graph undirectedGraph = new Graph(false);
        assertFalse(undirectedGraph.isDirect);
    }

    @Test
    public void testGetPath() {
        Graph directedGraph = new Graph(true);
        Vertex<String> V0 = new Vertex<>("0");
        Vertex<String> V1 = new Vertex<>("1");
        Vertex<String> V2 = new Vertex<>("2");
        Vertex<String> V3 = new Vertex<>("3");
        directedGraph.addEdge(V0, V1);
        directedGraph.addEdge(V0, V2);
        directedGraph.addEdge(V0, V3);
        directedGraph.addEdge(V2, V0);
        directedGraph.addEdge(V2, V1);
        directedGraph.addEdge(V1, V3);
        Assert.assertEquals("[2 => 1, 1 => 3]", directedGraph.getPath(V2, V3).toString());
    }

}
