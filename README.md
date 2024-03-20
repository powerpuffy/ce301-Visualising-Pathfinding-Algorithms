# Visualising Search Algorithms

## Summary

This is a Java based tool that can be used by students/teachers to help understand the proccesses of various pathfinding/search algortihms. It is a GUI application that features an interactive 2D grid where the user can place nodes and run different search algorithms on it. It will be made into an exectuable file to be run from any desktop with Java installed.

## Features


- Interactive 2D grid
- Ability to place start nodes, goal nodes, wall nodes, swamp nodes
- Ability to perform a variety of algorithms: algorithms.saa.BFS, algorithms.main.java.com.sam.DFS, algorithms.main.java.com.sam.Dijkstra's, A*, Random Walk
- Ability to save and retrieve pre-made maps
- Ability to control the speed the algorithms operate at
- Dynamic colour alterations of nodes


# Pathfinding Algorithms

To implement the different algorithms, I created an individual class for each one. When the program needs to use a specific algorithm, it will create one of the algorithm classes. Each class hasa set of standard functions (In the fututre I will create a parent Algorithm class which contains the methods each sub-class should use). Each algorithm class currently contains a constructor that sets the start node, goal node, and a complete array of nodes in the graph. Each algorithm class also has a startSearch() method which will commence the search process. This is where the implementation differs for each different algorithm.

A common method needed for each algorithm is a method to find a node's neighbours. I created this function which gets the node above the current node, the node right of the current node, the node below the current node, and the node to the left of the current node, then returns these in an ArrayList of Nodes.

``` java
public ArrayList<main.java.com.sam.util.Node> getNeighbours(main.java.com.sam.util.Node n){

        ArrayList<main.java.com.sam.util.Node> neighbourList = new ArrayList<>();
        
        if (n.row - 1 >= 0){
            main.java.com.sam.util.Node uppernode = nodeArray[n.col][n.row-1];
            if (!uppernode.wall){
                neighbourList.add(uppernode);
            }
        }

        if (n.col + 1 < maxCol){
            main.java.com.sam.util.Node rightnode = nodeArray[n.col+1][n.row];
            if (!rightnode.wall){
                neighbourList.add(rightnode);
            }
        }

        if (n.row + 1 < maxRow){
            main.java.com.sam.util.Node downnode = nodeArray[n.col][n.row+1];
            if (!downnode.wall){
                neighbourList.add(downnode);
            }
        }

        if (n.col - 1 >= 0){
            main.java.com.sam.util.Node leftnode = nodeArray[n.col-1][n.row];
            if (!leftnode.wall){
                neighbourList.add(leftnode);
            }
        }

        return neighbourList;
}
```

The common algorithms such as algorithms.main.java.com.sam.DFS, algorithms.saa.BFS, algorithms.main.java.com.sam.Dijkstra, and A* all find a path to the goal. To actually find the path taken when it hits the goal, the algorithm will need to keep track of the parent of each node visited. Now that the parent is known for each node, we can call a backTrackPath() method once the algorithm hits the goal. This function will loop continously until the current node is set to the start node. It will intitially be performed on the goal node, then continue to find the parent of each node from there. I have added a Thread.Sleep() so that the user can see the process of the back-tracking.

``` java
public void backTrackPath() throws InterruptedException {
        main.java.com.sam.util.Node cur = goalNode;

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
public int calculateHManhattan(main.java.com.sam.util.Node cur, main.java.com.sam.util.Node goalNode){
        return Math.abs(cur.col - goalNode.col) + Math.abs(cur.row - goalNode.row);
}
```
``` java
public int calculateHEuclidean(main.java.com.sam.util.Node cur, main.java.com.sam.util.Node goalNode){
        return (int) Math.sqrt((Math.pow(cur.col - goalNode.col,2) + Math.pow(cur.row - goalNode.row,2)));
}
```


# Graphical User Interface

## panels.saa.GridPanel

My main frame currently features 2 JPanels, one for the panels.saa.GridPanel and one for the panels.saa.ControlPanel. To create the grid panel I set the maximum number of rows and columns, then set the panel size accordingly. Inside the panels.saa.GridPanel constructor, a main.java.com.sam.util.Node array will be created with each node representing a column number and row number. Then it will go on to set any change any node to a start node or goal node according to what the user selects. If the user presses the "Go" button, the panels.saa.GridPanel will perform the samSearch() function. This will reset the grid incase there were any previous searches, then create an algorithm based on the algorithms I have implemented above. This algorithm will then run on the panels.saa.GridPanel until it finds the goal or searches every possible node.

``` java
public void samSearch() throws InterruptedException {

        resetSearch();
        resetButtonText();

        samSetStartNode();
        samSetGoalNode();

        //algorithms.saa.BFS algo = new algorithms.saa.BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //algorithms.main.java.com.sam.DFS algo = new algorithms.main.java.com.sam.DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        algorithms.saa.AStar algo = new algorithms.saa.AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //main.java.com.sam.algorithms.RandomWalk algo = new main.java.com.sam.algorithms.RandomWalk(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        
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

## panels.saa.ControlPanel

The panels.saa.ControlPanel is another JPanel used to contain the buttons and fields that the user will interact with to change the processes of the algorithms. This is created with a set width and will run from the top of the frame, to the bottom of the frame. It is set to the right side of the main panels.saa.GridPanel. The buttons are implemented using JButtons and they each have an ActionListener to perform a certain function. Below is an example of one.

``` java
JButton resetButton = new JButton("Reset Grid");
resetButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        gridPanel.wipeGrid();
    }
});
```


# Map Saving/Retrieving

To implement map saving/retrieving, I created some methods inside the panels.saa.ControlPanel class to handle this functionality. To save a map, the user will need to enter an ID in the JTextField. When the save button is pressed, the program will open the saves.txt file, then write the map of the current grid panel to the file. To get the map, I created a function that checks through the grid's nodeArray then sets creates a new array of nodes with a 1 being set for each wall node, and a 0 being set for any other node. This map then gets returned and writen to the file.

``` java
public int[][] getMap(){
    int[][] map = new int[this.maxCol][this.maxRow];
    for (int i = 0; i < this.nodeArray.length; i++) {
        for (int j = 0; j < this.nodeArray[i].length; j++){
            if (nodeArray[i][j].wall){
                map[i][j] = 1;
            } else{
                map[i][j] = 0;
            }
        }
    }
    return map;
}
```

``` java
JButton saveButton = new JButton("Save Map");
saveButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (t.getText() != null){
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("saves.txt",true));

                out.write("\n" + t.getText() + Arrays.deepToString(gridPanel.getMap()));
                out.close();
                        
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
});
```

To implement map retrieving, the program will open the saves.txt file, then scan through it until it finds the ID that the user has placed in the JTextField. When this is found, it will wipe the current grid, then fill the grid with the new map.

``` java
public void fillMap(int[][] maparray){
    for (int i = 0; i < maparray.length; i++) {
        for (int j = 0; j < maparray[i].length; j++){
            if (maparray[i][j] == 1){
                nodeArray[i][j].setAsWall();
            }
        }
    }
}
```

``` java
JButton retrieveButton = new JButton("Retrieve Map");
retrieveButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (t.getText() != null){
            try {
                File file = new File("saves.txt");
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()){
                    String nl = sc.nextLine();
                    if (Objects.equals(getID(nl), t.getText())){
                        gridPanel.wipeGrid();
                        gridPanel.fillMap(getMapArray(nl));
                    }
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
});
```


# Maze Generation

To implement maze generation...
