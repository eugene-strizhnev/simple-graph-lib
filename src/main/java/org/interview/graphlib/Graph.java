package org.interview.graphlib;

import org.interview.graphlib.model.Edge;
import org.interview.graphlib.model.Vertex;

import java.util.List;

public interface Graph<T extends Comparable<T>> {

    /**
     * Adds vertex to the graph
     *
     * @param vertex new vertex
     * @return current instance of the graph
     */
    Graph<T> addVertex(Vertex<T> vertex);

    /**
     * Adds edge to the graph
     *
     * @param edge new edge
     * @return current instance of the graph
     */
    Graph<T> addEdge(Edge<T> edge);

    /**
     * Returns a list of edges between 2 vertices (path might not be optimal)
     *
     * @param start start vertex
     * @param end end vertex
     * @return list of edges. Empty list in case of path not found.
     */
    List<Edge<T>> getPath(Vertex<T> start, Vertex<T> end);

}
