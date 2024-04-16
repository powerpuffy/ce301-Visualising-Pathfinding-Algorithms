package main.java.com.sam.heuristics;

import main.java.com.sam.heuristics.Heuristic;
import main.java.com.sam.util.Node;

public class Euclidean implements Heuristic {
    @Override
    public int calculateHeuristic(Node cur, Node goalNode) {
        return (int) Math.sqrt((Math.pow(cur.col - goalNode.col,2) + Math.pow(cur.row - goalNode.row,2)));
    }

    @Override
    public String toString() {
        return "Euclidean";
    }
}
