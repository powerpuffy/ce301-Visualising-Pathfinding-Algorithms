package main.java.com.sam.heuristics;

import main.java.com.sam.util.Node;

public interface Heuristic {
    int calculateHeuristic(Node cur, Node goalNode);

}
