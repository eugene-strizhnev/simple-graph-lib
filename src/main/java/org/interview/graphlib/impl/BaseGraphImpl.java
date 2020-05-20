package org.interview.graphlib.impl;

import org.interview.graphlib.Graph;
import org.interview.graphlib.model.Edge;
import org.interview.graphlib.model.Vertex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseGraphImpl<T extends Comparable<T>> implements Graph<T> {

    protected Map<Vertex<T>, Set<Vertex<T>>> list = new TreeMap<>();

    @Override
    public Graph<T> addVertex(Vertex<T> vertex) {
        list.putIfAbsent(vertex, new TreeSet<>());
        return this;
    }

    @Override
    public List<Edge<T>> getPath(Vertex<T> start, Vertex<T> end) {
        Set<Vertex<T>> destinations = list.getOrDefault(start, new TreeSet<>());
        if (destinations.contains(end)) {
            return Collections.singletonList(new Edge<>(start, end));
        } else {
            for (Vertex<T> dest : destinations) {
                List<Edge<T>> path = getPath(dest, end);
                if (!path.isEmpty()) {
                    return Stream.concat(Stream.of(new Edge<>(start, dest)), path.stream())
                            .collect(Collectors.toList());
                }
            }
            return List.of();
        }
    }
}
