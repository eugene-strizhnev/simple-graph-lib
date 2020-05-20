package org.interview.graphlib.impl;

import org.interview.graphlib.Graph;
import org.interview.graphlib.model.Edge;
import org.interview.graphlib.model.Vertex;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UndirectedGraph<T extends Comparable<T>> extends BaseGraphImpl<T> {
    @Override
    public Graph<T> addEdge(Edge<T> edge) {
        List
                .of(edge, new Edge<>(edge.getEnd(), edge.getStart()))
                .forEach(e -> {
                    Set<Vertex<T>> destinations = list.computeIfAbsent(e.getStart(), k -> new TreeSet<>());
                    destinations.add(e.getEnd());
                });
        return this;
    }
}
