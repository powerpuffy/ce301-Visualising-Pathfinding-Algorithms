package main.java.com.sam.heuristics;

import main.java.com.sam.heuristics.Heuristic;
import main.java.com.sam.util.Node;

public class Manhattan implements Heuristic {
    @Override
    public int calculateHeuristic(Node cur, Node goalNode) {
        return Math.abs(cur.col - goalNode.col) + Math.abs(cur.row - goalNode.row);
    }

    @Override
    public String toString() {
        return "Manhattan";
    }
}
