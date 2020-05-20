package org.interview.graphlib.impl;

import org.interview.graphlib.Graph;
import org.interview.graphlib.model.Edge;
import org.interview.graphlib.model.Vertex;

import java.util.Set;
import java.util.TreeSet;

public class DirectedGraph<T extends Comparable<T>> extends BaseGraphImpl<T> {

    @Override
    public Graph<T> addEdge(Edge<T> edge) {
        Set<Vertex<T>> destinations = list.computeIfAbsent(edge.getStart(), k -> new TreeSet<>());
        destinations.add(edge.getEnd());
        return this;
    }
}
