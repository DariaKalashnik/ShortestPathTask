package models;

import interfaces.Edge;
import interfaces.Vertex;

import java.util.List;

/* used source for Dijkstra’s shortest path algorithm:
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html */

public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
