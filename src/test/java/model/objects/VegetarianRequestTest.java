package model.objects;

import org.junit.*;
import static org.junit.Assert.*;

public class VegetarianRequestTest {

        @Test
        public void getUidTest() {
            VegetarianRequest vegetarianRequest = new VegetarianRequest("testId", 1);
            assertEquals(vegetarianRequest.getUid(), "testId");
        }

        @Test
        public void getAmountTest() {
            VegetarianRequest vegetarianRequest = new VegetarianRequest("testId", 1);
            assertEquals(vegetarianRequest.getAmount(), 1);
        }

}
