import main.java.com.sam.util.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void setAsStart() {
        Node n = new Node(1,2);
        n.setAsStart();
        assertTrue(n.isStart);
    }

    @Test
    void setAsGoal() {
        Node n = new Node(1,2);
        n.setAsGoal();
        assertTrue(n.isGoal);
    }

    @Test
    void setAsWall() {
        Node n = new Node(1,2);
        n.setAsWall();
        assertTrue(n.isWall);
    }

    @Test
    void setAsSwamp() {
        Node n = new Node(1,2);
        n.setAsSwamp();
        assertTrue(n.isSwamp);
    }

    @Test
    void setAsDefault() {
        Node n = new Node(1,2);
        n.setAsDefault();
        assertFalse(n.isStart);
        assertFalse(n.isGoal);
        assertFalse(n.isWall);
        assertFalse(n.isSwamp);
        assertFalse(n.isPath);
    }

    @Test
    void setAsPath() {
        Node n = new Node(1,2);
        n.setAsPath();
        assertTrue(n.isPath);
    }

    @Test
    void compareGreaterThan() {
        Node n = new Node(1,2);
        Node n2 = new Node(3,4);
        n.fCost = 20;
        n2.fCost = 10;

        assertEquals(1,n.compareTo(n2));

    }

    @Test
    void compareEqualTo() {
        Node n = new Node(1,2);
        Node n2 = new Node(3,4);
        n.fCost = 20;
        n2.fCost = 20;

        assertEquals(0,n.compareTo(n2));
    }

    @Test
    void compareLessThan() {
        Node n = new Node(1,2);
        Node n2 = new Node(3,4);
        n.fCost = 10;
        n2.fCost = 20;

        assertEquals(-1,n.compareTo(n2));
    }

    @Test
    void compareGreaterThanWithEqualHCost() {
        Node n = new Node(1, 2);
        Node n2 = new Node(3, 4);
        n.fCost = 20;
        n.hCost = 15;
        n2.hCost = 15;
        n2.fCost = 10;

        assertEquals(1, n.compareTo(n2));
    }

    @Test
    void compareEqualToWithEqualFCostAndHCost() {
        Node n = new Node(1, 2);
        Node n2 = new Node(3, 4);
        n.fCost = 20;
        n.hCost = 15;
        n2.fCost = 20;
        n2.hCost = 15;

        assertEquals(0, n.compareTo(n2));
    }

    @Test
    void compareLessThanWithEqualFCost() {
        Node n = new Node(1, 2);
        Node n2 = new Node(3, 4);
        n.fCost = 10;
        n.hCost = 15;
        n2.fCost = 10;
        n2.hCost = 20;

        assertEquals(-1, n.compareTo(n2));
    }


}