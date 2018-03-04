package engine;

import interfaces.Edge;
import interfaces.Vertex;
import models.City;
import models.Flight;
import models.Graph;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DijkstraAlgorithmTest {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExecute() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        Vertex locations[] = new Vertex[]{
                new City("A", "A", 0),
                new City("B", "B", 0),
                new City("C", "C", 0),
                new City("D", "D", 0),
                new City("E", "E", 0)};

        nodes.addAll(Arrays.asList(locations));

        addLane("A->D", 0, 3, 1);
        addLane("A->B", 0, 1, 6);
        addLane("D->B", 3, 1, 2);
        addLane("B->E", 1, 4, 2);
        addLane("D->E", 3, 4, 1);
        addLane("E->C", 4, 2, 5);
        addLane("B->C", 1, 2, 5);

        // Lets check from locations Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(2));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        printResult(path);
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Flight(laneId, (City) nodes.get(sourceLocNo), (City) nodes.get(destLocNo), Duration.ofMinutes(duration), duration, "DummyLine");
        edges.add(lane);
    }

    private void printResult(List<Vertex> path) {
        int i = 0;
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder();
        for (Vertex vertex : path) {
            if (i++ == path.size() - 1) {
                stringBuilder.append(vertex.getName());
            } else {
                stringBuilder.append(vertex.getName()).append("->");
            }
        }

        System.out.println("The shortest path: " + stringBuilder);
    }
}