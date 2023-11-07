import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void setAsStart() {
        Node n = new Node(1,2);
        n.setAsStart();
        assertTrue(n.start);
    }

    @Test
    void setAsGoal() {
        Node n = new Node(1,2);
        n.setAsGoal();
        assertTrue(n.goal);
    }

    @Test
    void ssetAsWall() {
        Node n = new Node(1,2);
        n.setAsWall();
        assertTrue(n.wall);
    }

    @Test
    void setAsDefault() {
        Node n = new Node(1,2);
        n.setAsDefault();
        assertFalse(n.start);
        assertFalse(n.goal);
        assertFalse(n.wall);
        assertFalse(n.swamp);
        assertFalse(n.path);
    }

    @Test
    void setAsPath() {
        Node n = new Node(1,2);
        n.setAsPath();
        assertTrue(n.path);
    }

    @Test
    void setAsSearched() {

    }

    @Test
    void testToString() {


    }

    @Test
    void verifyStartPressedWithStartSelected() {

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
}