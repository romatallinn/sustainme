package model;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DemoModelTest {

    private DemoModel model;

    @Before
    public void setup() {

        try {
            model = new DemoModel();
        } catch (FirebaseException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void constructorTest() {

        Boolean exThrown = false;

        try {
            new DemoModel();
        } catch (FirebaseException e) {

            System.out.println(e.getMessage());
            exThrown = true;

        }

        Assert.assertFalse(exThrown);

    }

    @Test
    public void retrieveDataSuccessTest() {

        Boolean exThrown = false;

        try {

            String data = model.retrieveData("test");
            String result = "{\"success\":true}";

            Assert.assertEquals(data, result);

        } catch (FirebaseException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        }

        Assert.assertFalse(exThrown);

    }

    @Test
    public void retrieveWholeDataSuccessTest() {

        Boolean exThrown = false;

        try {

            String data = model.retrieveData();
            Assert.assertNotNull(data);

        } catch (FirebaseException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        }

        Assert.assertFalse(exThrown);

    }

    @Test
    public void retrieveDataFailureTest() {

        Boolean exThrown = false;

        try {

            String data = model.retrieveData("invalid_path");
            String result = "null";

            Assert.assertEquals(data, result);

        } catch (FirebaseException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        }

        Assert.assertFalse(exThrown);


    }

    @Test
    public void putDataTest() {

        Boolean exThrown = false;

        try {

            Map<String, Object> putData = new LinkedHashMap<>();
            putData.put("success", true);

            FirebaseResponse response = model.putData("test", putData);
            Assert.assertTrue(response.getSuccess());

        } catch (FirebaseException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        } catch (JacksonUtilityException e) {
            System.out.println(e.getMessage());
            exThrown = true;
        }

        Assert.assertFalse(exThrown);

    }

}
