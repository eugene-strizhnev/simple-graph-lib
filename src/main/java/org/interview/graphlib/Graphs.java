package org.interview.graphlib;

import org.interview.graphlib.impl.GraphImpl;
import org.interview.graphlib.model.Edge;
import org.interview.graphlib.model.Vertex;

import java.util.List;

public class Graphs {

    /**
     * Creates a new directed graph
     *
     * @param <T> type of the vertex
     * @return the newly created thread pool
     */
    public static <T extends Comparable<T>> Graph<T> newDirectedGraph() {
        return new GraphImpl<>(Graphs::directedEdgeEnhancer);
    }

    /**
     * Creates a new un-directed graph
     *
     * @param <T> type of the vertex
     * @return the newly created graph
     */
    public static <T extends Comparable<T>> Graph<T> newUndirectedGraph() {
        return new GraphImpl<>(Graphs::unDirectedEdgeEnhancer);
    }

    /**
     * Creates a new directed graph, wrapped into the thread safe wrapper
     *
     * @param <T> type of the vertex
     * @return the newly created graph
     */
    public static <T extends Comparable<T>> Graph<T> newThreadSafeDirectedGraph() {
        return new ThreadSafeGraph<>(Graphs.<T>newDirectedGraph());
    }

    /**
     * Creates a new un-directed graph, wrapped into the thread safe wrapper
     *
     * @param <T> type of the vertex
     * @return the newly created graph
     */
    public static <T extends Comparable<T>> Graph<T> newThreadSafeUndirectedGraph() {
        return new ThreadSafeGraph<>(Graphs.<T>newUndirectedGraph());
    }

    private static <T extends Comparable<T>> List<Edge<T>> directedEdgeEnhancer(Edge<T> edge) {
        return List.of(edge);
    }

    private static <T extends Comparable<T>> List<Edge<T>> unDirectedEdgeEnhancer(Edge<T> edge) {
        return List.of(
                edge,
                new Edge<>(edge.getEnd(), edge.getStart())
        );
    }

    private static class ThreadSafeGraph<T extends Comparable<T>> implements Graph<T> {
        private final Graph<T> graph;

        public ThreadSafeGraph(Graph<T> graph) {
            this.graph = graph;
        }

        @Override
        public synchronized Graph<T> addVertex(Vertex<T> vertex) {
            graph.addVertex(vertex);
            return this;
        }

        @Override
        public synchronized Graph<T> addEdge(Edge<T> edge) {
            graph.addEdge(edge);
            return this;
        }

        @Override
        public List<Edge<T>> getPath(Vertex<T> start, Vertex<T> end) {
            return graph.getPath(start, end);
        }
    }
}
