package org.interview.graphlib.impl;

import org.interview.graphlib.Graph;
import org.interview.graphlib.Graphs;
import org.interview.graphlib.model.Edge;
import org.interview.graphlib.model.Vertex;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphImplTest {
    private record Assertion(int start, int end, List<Edge<Integer>>expectedPath) {
    }

    private record TestData(Graph<Integer>graph, List<Assertion>assertions) {
    }

    private static Stream<TestData> testData() {
        return Stream.of(
                new TestData(
                        Graphs.<Integer>newDirectedGraph()
                                .addEdge(new Edge<>(new Vertex<>(1), new Vertex<>(2)))
                                .addEdge(new Edge<>(new Vertex<>(2), new Vertex<>(3)))
                                .addEdge(new Edge<>(new Vertex<>(3), new Vertex<>(4)))
                                .addEdge(new Edge<>(new Vertex<>(1), new Vertex<>(4)))
                                .addEdge(new Edge<>(new Vertex<>(7), new Vertex<>(8)))
                                .addVertex(new Vertex<>(11)),
                        List.of(
                                new Assertion(
                                        1, 3,
                                        List.of(
                                                new Edge<>(new Vertex<>(1), new Vertex<>(2)),
                                                new Edge<>(new Vertex<>(2), new Vertex<>(3))
                                        )
                                ),
                                new Assertion(
                                        3, 4,
                                        List.of(
                                                new Edge<>(new Vertex<>(3), new Vertex<>(4))
                                        )
                                ),
                                new Assertion(
                                        7, 8,
                                        List.of(
                                                new Edge<>(new Vertex<>(7), new Vertex<>(8))
                                        )
                                ),
                                new Assertion(
                                        7, 9,
                                        List.of()
                                ),
                                new Assertion(
                                        11, 1,
                                        List.of()
                                )

                        )
                ),
                new TestData(
                        Graphs.<Integer>newUndirectedGraph()
                                .addEdge(new Edge<>(new Vertex<>(1), new Vertex<>(2)))
                                .addEdge(new Edge<>(new Vertex<>(2), new Vertex<>(3))),
                        List.of(
                                new Assertion(
                                        1, 3,
                                        List.of(
                                                new Edge<>(new Vertex<>(1), new Vertex<>(2)),
                                                new Edge<>(new Vertex<>(2), new Vertex<>(3))
                                        )
                                ),
                                new Assertion(
                                        3, 1,
                                        List.of(
                                                new Edge<>(new Vertex<>(3), new Vertex<>(2)),
                                                new Edge<>(new Vertex<>(2), new Vertex<>(1))
                                        )
                                ),
                                new Assertion(
                                        1, 2,
                                        List.of(
                                                new Edge<>(new Vertex<>(1), new Vertex<>(2))
                                        )
                                ),
                                new Assertion(
                                        2, 1,
                                        List.of(
                                                new Edge<>(new Vertex<>(2), new Vertex<>(1))
                                        )
                                )

                        )
                ));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testPaths(TestData testData) {
        testData.assertions().forEach((assertion) -> assertEquals(
                assertion.expectedPath(),
                testData.graph().getPath(new Vertex<>(assertion.start()), new Vertex<>(assertion.end())),
                "Paths has to be equal"
        ));
    }
}
