package interfaces;

public interface Edge {

    String getId();

    Vertex getDestination();

    Vertex getSource();

    int getWeight();

    String toString();
}
