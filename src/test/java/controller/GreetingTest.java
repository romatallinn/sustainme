package controller;
import org.junit.*;
import static org.junit.Assert.*;

public class GreetingTest {

    @Test
    public void getIdTest() {
        Greeting greeting = new Greeting(1,"Hello");
        assertEquals(greeting.getId(), 1);
    }

    @Test
    public void getContentTest() {
        Greeting greeting = new Greeting(1,"Hello");
        assertEquals(greeting.getContent(), "Hello");
    }

    @Test
    public void emptyConstructorTest() {
        Greeting greeting = new Greeting();
        assertNull(greeting.getContent());
    }
}
