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

``` java
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

The common algorithms such as DFS, BFS, Dijkstra, and A* all find a path to the goal. To actually find the path taken when it hits the goal, the algorithm will need to keep track of the parent of each node visited. Now that the parent is known for each node, we can call a backTrackPath() method once the algorithm hits the goal. This function will loop continously until the current node is set to the start node. It will intitially be performed on the goal node, then continue to find the parent of each node from there. I have added a Thread.Sleep() so that the user can see the process of the back-tracking.

``` java
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

# Heuristics

Mainly A* will need to use a heursitics to calculate an estimated distance to the goal node. I have currently implemented a function to calculate the Manhattan distance and the Euclidean distance.

``` java
public int calculateHManhattan(Node cur, Node goalNode){
        return Math.abs(cur.col - goalNode.col) + Math.abs(cur.row - goalNode.row);
}
```
``` java
public int calculateHEuclidean(Node cur, Node goalNode){
        return (int) Math.sqrt((Math.pow(cur.col - goalNode.col,2) + Math.pow(cur.row - goalNode.row,2)));
}
```


# Graphical User Interface

## GridPanel

My main frame currently features 2 JPanels, one for the GridPanel and one for the ControlPanel. To create the grid panel I set the maximum number of rows and columns, then set the panel size accordingly. Inside the GridPanel constructor, a Node array will be created with each node representing a column number and row number. Then it will go on to set any change any node to a start node or goal node according to what the user selects. If the user presses the "Go" button, the GridPanel will perform the samSearch() function. This will reset the grid incase there were any previous searches, then create an algorithm based on the algorithms I have implemented above. This algorithm will then run on the GridPanel until it finds the goal or searches every possible node.

``` java
public void samSearch() throws InterruptedException {

        resetSearch();
        resetButtonText();

        samSetStartNode();
        samSetGoalNode();

        //BFS algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //DFS algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        AStar algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //RandomWalk algo = new RandomWalk(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        
        new Thread(new Runnable() {
            public void run() {
                try {
                    algo.startSearch();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
}
```

## ControlPanel

The ControlPanel is another JPanel used to contain the buttons and fields that the user will interact with to change the processes of the algorithms. This is created with a set width and will run from the top of the frame, to the bottom of the frame. It is set to the right side of the main GridPanel. The buttons are implemented using JButtons and they each have an ActionListener to perform a certain function. Below is an example of one.

``` java
JButton resetButton = new JButton("Reset Grid");
resetButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        gridPanel.wipeGrid();
    }
});
```


