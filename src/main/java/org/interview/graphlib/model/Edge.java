package org.interview.graphlib.model;

import java.util.Objects;

public class Edge<T extends Comparable<T>> {

    private final Vertex<T> start;
    private final Vertex<T> end;

    public Edge(Vertex<T> start, Vertex<T> end) {
        this.start = start;
        this.end = end;
    }

    public Vertex<T> getStart() {
        return start;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(start, edge.start) &&
                Objects.equals(end, edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
