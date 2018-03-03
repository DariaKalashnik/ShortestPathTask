package interfaces;

/**
 * Created by Daria Kalashnikova on 3/3/2018.
 */

public interface Edge {

    String getId();

    Vertex getDestination();

    Vertex getSource();

    int getWeight();

    String toString();
}
