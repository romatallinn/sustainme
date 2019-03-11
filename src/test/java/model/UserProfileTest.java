package model;
import org.junit.*;
import static org.junit.Assert.*;

public class UserProfileTest {

    @Test
    public void increaseScoreNoLevelUp() {
        UserProfile user = new UserProfile("","","");
        user.increaseScore(5);
        assertEquals(user.getExperience(),5);
    }
    @Test
    public void increaseScore1LvlUp() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.increaseScore(10);
        assertEquals(user.getExperience(),0);
        assertEquals(user.getLevel(),2);
    }
    @Test
    public void increaseScore2LvlUp() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.increaseScore(35);
        assertEquals(user.getExperience(),5);
        assertEquals(user.getLevel(),3);
    }

    @Test
    public void testCo2Reduction(){
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.reduceCo2(2.2);
        assertEquals(user.getCo2Reduction(),2.2,0.0);
    }

    @Test
    public void TestvegMeal(){
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        user.vegMeal();
        assertEquals(user.getCo2Reduction(),3.0,0.1);
    }

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
    public void getExperience() {
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getExperience(),0);
    }
    @Test
    public void getCo2Reduction(){
        UserProfile user = new UserProfile("Roderick","de Britto Heemskerk","Roderickmbh@gmail.com");
        assertEquals(user.getCo2Reduction(), 0.0, 0);
    }
}