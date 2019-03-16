package model;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import org.junit.*;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class UserProfileTest {

    @Test
    public void increaseScoreNoLevelUp() throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
        UserProfile user = new UserProfile("","","");
        user.increaseScore(5);
        assertEquals(user.getExp(),5);
    }
    @Test
    public void increaseScore1LvlUp() throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.increaseScore(10);
        assertEquals(user.getExp(),0);
        assertEquals(10, user.getExperience());
        assertEquals(user.getLevel(),2);
    }
    @Test
    public void increaseScore2LvlUp() throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.increaseScore(35);
        assertEquals(user.getExp(),5);
        assertEquals(35, user.getExperience());
        assertEquals(user.getLevel(),3);
    }

    @Test
    public void testCo2Reduction() throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.reduceCo2(2.2);
        assertEquals(user.getCo2Reduction(),2.2,0.0);
    }

//    @Test
//    public void TestvegMeal(){
//        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
//        user.vegMeal();
//        assertEquals(user.getCo2Reduction(),3.0,0.1);
//    }

    @Test
    public void getFirstName() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getFirstName(),"Roderick");
    }

    @Test
    public void getLastName() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getLastName(),"de Britto Heemskerk");
    }

    @Test
    public void getEmailAddress() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getEmailAddress(),"Roderickmbh@gmail.com");
    }

    @Test
    public void getLevel() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getLevel(),1);
    }

    @Test
    public void getExp() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getExp(),0);
    }
    @Test
    public void getCo2Reduction(){
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getCo2Reduction(), 0.0, 0);
    }

    @Test
    public void getExperience() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getExperience(),0);
    }
}