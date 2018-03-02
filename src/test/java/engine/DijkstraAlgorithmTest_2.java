package engine;

import models.*;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DijkstraAlgorithmTest_2 {
    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExecute() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        Vertex location = new City("A", "A", 0);
        nodes.add(location);
        location = new City("B", "B", 0);
        nodes.add(location);
        location = new City("C", "C", 0);
        nodes.add(location);
        location = new City("D", "D", 0);
        nodes.add(location);
        location = new City("E", "E", 0);
        nodes.add(location);


        addLane("ad", 0, 3, 1);
        addLane("ab", 0, 1, 6);
        addLane("db", 3, 1, 2);
        addLane("be", 1, 4, 2);
        addLane("de", 3, 4, 1);
        addLane("ec", 4, 2, 5);
        addLane("bc", 1, 2, 5);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(2));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Flight(laneId, (City) nodes.get(sourceLocNo), (City) nodes.get(destLocNo), Duration.ofMinutes(duration), duration, "DummyLine");
        edges.add(lane);
    }
}