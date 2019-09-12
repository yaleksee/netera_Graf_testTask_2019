package v2OptimalPath;

public class VertexOptimalPath<T> {

    private T vertex;
    private int index;

    public VertexOptimalPath(int index, T vertex) {
        this.index = index;
        this.vertex = vertex;
    }

    public T getVertex() {
        return vertex;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VertexOptimalPath))
            return false;
        if (obj == this)
            return true;

        VertexOptimalPath vertexCompare = (VertexOptimalPath) obj;
        return (this.vertex != null && this.vertex.equals(vertexCompare.vertex));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return vertex.toString();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
