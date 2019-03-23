package server.supporting;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.objects.UserData;

import java.util.concurrent.CountDownLatch;

/**
 * The class that handles all requests to the Firebase Database.
 */
public class DatabaseHandler {

    private static CountDownLatch latch;
    private static FirebaseDatabase db;

    // Returned Data (for sync purposes)
    private static UserData userData;
    private static int featureCounter;

    /**
     * Initializes the database connection.
     */
    public static void init() {
        db = FirebaseDatabase.getInstance();
    }


    /**
     * Retrieve UserData object of the user with given user id.
     * @param uid - id of the user the data of whom needs to be retrieved.
     * @return UserData object of the user with given user id.
     * @throws InterruptedException exception.
     */
    public static UserData getUserData(String uid) throws InterruptedException {

        latch = new CountDownLatch(1);

        DatabaseReference ref = db.getReference("users").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(UserData.class);
                userData.uid = dataSnapshot.getKey();
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DatabaseError: " + databaseError.getMessage());
            }

        });

        latch.await();

        return userData;

    }

    /**
     * Increases experience of the user with the given id by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static void increaseExpBy(String uid, int amount) {

        DatabaseReference ref = db.getReference("users").child(uid).child("experience");
        increaseIntegerDataBy(ref, amount);

    }

    /**
     * Increases CO2 reduction attribute of the user with the given id by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static void increaseCO2RedBy(String uid, int amount) {

        DatabaseReference ref = db.getReference("users").child(uid).child("co2red");
        increaseIntegerDataBy(ref, amount);

    }

    /**
     * Increases the feature count of the vegetarian meals of the user
     * with given uid by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static void increaseFeatureCounter(String uid, String feature, int amount) {
        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);
        increaseIntegerDataBy(ref, amount);
    }

    /**
     * Retrieves the amount of eaten vegetarian meals by the user with given uid from DB.
     * @param uid - user id.
     * @return number of eaten vegetarian meals.
     * @throws InterruptedException - exception.
     */
    public static int retrieveFeatureCounter(String uid, String feature)
            throws InterruptedException {

        latch = new CountDownLatch(1);

        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                featureCounter = dataSnapshot.getValue(Integer.class);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DatabaseError: " + databaseError.getMessage());
            }

        });

        latch.await();

        return featureCounter;

    }

    /**
     * Helper function that increases the integer value in the DB by the given amount.
     * @param ref - reference to the attribute in DB.
     * @param amount - increasing amount.
     */
    private static void increaseIntegerDataBy(DatabaseReference ref, int amount) {

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer val = dataSnapshot.getValue(Integer.class);
                ref.setValueAsync(val + amount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DatabaseError: " + databaseError.getMessage());
            }

        });

    }

}
