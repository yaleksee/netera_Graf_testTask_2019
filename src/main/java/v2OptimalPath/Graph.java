package v2OptimalPath;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class GraphOptimalPath {
    private ArrayList<LinkedList<VertexOptimalPath>> adj;
    private LinkedList<VertexOptimalPath> graphPath[];
    private ArrayList<ArrayList<Integer>> allPath;
    private ArrayList<Integer> integers = new ArrayList<>();

    GraphOptimalPath() {
        adj = new ArrayList<>();
        allPath = new ArrayList<>();
    }

    void addEdge(VertexOptimalPath v, VertexOptimalPath w) {
        LinkedList<VertexOptimalPath> vertexLinkedList = new LinkedList<>();
        vertexLinkedList.add(v);
        vertexLinkedList.add(w);
        adj.add(vertexLinkedList);
    }

    ArrayList<ArrayList<Integer>> countPathsUtil(int indexF, VertexOptimalPath u, VertexOptimalPath d,
                                                 boolean visited[],
                                                 int pathCount
    ) {
        integers.add(indexF);
        visited[indexF] = true;

        if (u == d) {
            allPath.add(integers);
            integers = new ArrayList<>();
            pathCount++;
        } else {
            Iterator<VertexOptimalPath> i = graphPath[indexF].listIterator();
            while (i.hasNext()) {
                VertexOptimalPath n = i.next();
                int indexN = n.getIndex();
                if (!visited[indexN]) {
                    allPath = countPathsUtil(indexN, n, d,
                            visited,
                            pathCount);
                }
            }
        }
        visited[indexF] = false;

        return allPath;
    }

    ArrayList<ArrayList<Integer>> countPaths(VertexOptimalPath s, VertexOptimalPath d) {
        int indexF = s.getIndex();
        int indexL = d.getIndex();
        int size = adj.size();
        graphPath = new LinkedList[size];
        for (int i = 0; i < size; ++i) {
            graphPath[i] = new LinkedList<>();
        }

        ArrayList<VertexOptimalPath> vertices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            VertexOptimalPath v = adj.get(i).get(0);
            VertexOptimalPath w = adj.get(i).get(1);
            int index = v.getIndex();
            graphPath[index].add(w);
        }

        boolean visited[] = new boolean[size];
        Arrays.fill(visited, false);

        int pathCount = 0;
        allPath = countPathsUtil(indexF, s, d,
                visited,
                pathCount);
        return allPath;
    }

    public static void main(String args[]) {
        GraphOptimalPath g = new GraphOptimalPath();
        VertexOptimalPath<Integer> v0 = new VertexOptimalPath<Integer>(0, 0);
        VertexOptimalPath<Integer> v1 = new VertexOptimalPath<Integer>(1, 1);
        VertexOptimalPath<Integer> v2 = new VertexOptimalPath<Integer>(2, 2);
        VertexOptimalPath<Integer> v3 = new VertexOptimalPath<Integer>(3, 3);

        g.addEdge(v0, v1);
        g.addEdge(v0, v2);
        g.addEdge(v2, v0);
        g.addEdge(v2, v1);
        g.addEdge(v1, v3);


        ArrayList<ArrayList<Integer>> path = g.countPaths(v2, v3);
        System.out.println(path);
    }
}