package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocalProduceRequestTest {

    @Test
    public void getUidTest() {
        LocalProduceRequest localProduceRequest = new LocalProduceRequest("testId", 1.5);
        assertEquals(localProduceRequest.getUid(), "testId");
    }

    @Test
    public void getWeightTest() {
        LocalProduceRequest localProduceRequest = new LocalProduceRequest("testId", 1.5);
        assertEquals(localProduceRequest.getWeight(), 1.5, 0.0001);
    }
}
