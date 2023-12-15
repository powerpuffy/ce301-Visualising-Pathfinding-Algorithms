# Visualising Search Algorithms

## Summary

This is a Java based tool that can be used by students/teachers to help understand the proccesses of various pathfinding/search algortihms. It is a GUI application that features an interactive 2D grid where the user can place nodes and run different search algorithms on it. It will be made into an exectuable file to be run from any desktop with Java installed.

## Features


- Interactive 2D grid
- Ability to place start nodes, goal nodes, wall nodes, swamp nodes
- Ability to perform a variety of algorithms: BFS, DFS, Dijkstra's, A*, Random Walk
- Ability to save and retrieve pre-made maps
- Ability to control the speed the algorithms operate at
- Dynamic colour alterations of nodes

# Algorithms

To implement the different algorithms, I created an individual class for each one. When the program needs to use a specific algorithm, it will create one of the algorithm classes. Each class hasa set of standard functions (In the fututre I will create a parent Algorithm class which contains the methods each sub-class should use). Each algorithm class currently contains a constructor that sets the start node, goal node, and a complete array of nodes in the graph. Each algorithm class also has a startSearch() method which will commence the search process. This is where the implementation differs for each different algorithm.

A common method needed for each algorithm is a method to find a node's neighbours. I created this function which gets the node above the current node, the node right of the current node, the node below the current node, and the node to the left of the current node, then returns these in an ArrayList of Nodes.

``` Java
public ArrayList<Node> getNeighbours(Node n){

        ArrayList<Node> neighbourList = new ArrayList<>();
        
        if (n.row - 1 >= 0){
            Node uppernode = nodeArray[n.col][n.row-1];
            if (!uppernode.wall){
                neighbourList.add(uppernode);
            }
        }

        if (n.col + 1 < maxCol){
            Node rightnode = nodeArray[n.col+1][n.row];
            if (!rightnode.wall){
                neighbourList.add(rightnode);
            }
        }

        if (n.row + 1 < maxRow){
            Node downnode = nodeArray[n.col][n.row+1];
            if (!downnode.wall){
                neighbourList.add(downnode);
            }
        }

        if (n.col - 1 >= 0){
            Node leftnode = nodeArray[n.col-1][n.row];
            if (!leftnode.wall){
                neighbourList.add(leftnode);
            }
        }

        return neighbourList;
}
```

The common algorithms such as DFS, BFS, Dijkstra, and A* all find a path to the goal. To actually find the path taken when it hits the goal, the algorithm will need to keep track of the parent of each node visited. Now that the parent is known for each node, we can call a backTrackPath() method once the algorithm hits the goal. This function will loop continously until the current node is set to the start node. It will intitially be performed on the goal node, then continue to find the parent of each node from there.

``` Java
public void backTrackPath() throws InterruptedException {
        Node cur = goalNode;

        while (cur != startNode){
            Thread.sleep(10);
            cur = cur.parent;
            if (cur != startNode){
                cur.setAsPath();
            }
        }
}
```


