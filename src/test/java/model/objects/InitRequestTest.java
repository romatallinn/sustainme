package model.objects;

import org.junit.*;
import static org.junit.Assert.*;

public class InitRequestTest {

    @Test
    public void getUidTest() {
        InitRequest initRequest = new InitRequest("testId", "firstname", "lastname");
        assertEquals(initRequest.getUid(), "testId");
    }

    @Test
    public void getFnameTest() {
        InitRequest initRequest = new InitRequest("testId", "firstname", "lastname");
        assertEquals(initRequest.getFname(), "firstname");
    }

    @Test
    public void getLnameTest() {
        InitRequest initRequest = new InitRequest("testId", "firstname", "lastname");
        assertEquals(initRequest.getLname(), "lastname");
    }
}
