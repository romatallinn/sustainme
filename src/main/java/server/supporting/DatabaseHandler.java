package server.supporting;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.objects.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * The class that handles all requests to the Firebase Database.
 */
public class DatabaseHandler {

    private static FirebaseDatabase db;

    // Returned Data (for sync purposes)
    private static Object valueObj;

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

        DatabaseReference ref = db.getReference("users").child(uid);
        DataSnapshot snapshot = retrieveDataSnapshotAt(ref);

        UserData data = snapshot.getValue(UserData.class);
        data.uid = snapshot.getKey();

        return data;

    }

    /**
     * Initialize the user's data location in db with default variables.
     * @param uid - user id.
     * @param fname - first name.
     * @param lname - last name.
     */
    public static void initUser(String uid, String fname, String lname) {

        final DatabaseReference ref = db.getReference("users").child(uid);

        Map<String, Object> features = new HashMap<>();
        features.put("vegmeals", 0);
        features.put("localproduce", 0);
        features.put("bike", 0);
        features.put("public", 0);
        features.put("paperrecycling", 0);
        features.put("plasticrecycling", 0);

        Map<String, Object> data = new HashMap<>();
        data.put("fname", fname);
        data.put("lname", lname);
        data.put("experience", 0);
        data.put("co2red", 0);
        data.put("features", features);


        ref.setValueAsync(data);

    }

    /**
     * Increases experience of the user with the given id by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static int increaseExpBy(String uid, int amount) throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("experience");

        int newVal = retrieveValueAt(ref, Integer.class) + amount;
        ref.setValueAsync(newVal);

        return newVal;

    }

    /**
     * Increases CO2 reduction attribute of the user with the given id by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static double increaseCO2RedBy(String uid, double amount) throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("co2red");

        double newVal = retrieveValueAt(ref, Double.class) + amount;
        ref.setValueAsync(newVal);

        return newVal;

    }

    /**
     * Increases the feature count of the vegetarian meals of the user
     * with given uid by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static int increaseFeatureCounter(String uid, String feature, int amount)
            throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);

        int newVal = retrieveValueAt(ref, Integer.class) + amount;
        ref.setValueAsync(newVal);

        return  newVal;

    }

    /**
     * Increases the feature count of the vegetarian meals of the user
     * with given uid by the given amount.
     * @param uid - user id
     * @param amount - increasing amount.
     */
    public static float increaseFeatureCounter(String uid, String feature, float amount)
            throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);

        float newVal = retrieveValueAt(ref, Float.class) + amount;
        ref.setValueAsync(newVal);

        return newVal;

    }

    /**
     * Retrieves the amount of eaten vegetarian meals by the user with given uid from DB.
     * @param uid - user id.
     * @return number of eaten vegetarian meals.
     * @throws InterruptedException - exception.
     */
    public static int retrieveFeatureCounter(String uid, String feature)
            throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);
        int val = retrieveValueAt(ref, Integer.class);

        return val;

    }

    /**
     * Adds a new friend with given email to the user of the given user id.
     * @param uid - user id.
     * @param friendEmail - email of the friend to add.
     * @return response message.
     * @throws InterruptedException - exception.
     * @throws ExecutionException - exception.
     */
    public static boolean addFriendByEmail(String uid, String friendEmail) {

        try {

            String fid = FirebaseAuth.getInstance().getUserByEmailAsync(friendEmail).get().getUid();
            DatabaseReference ref = db.getReference("users/" + uid + "/friends/" + fid);

            ref.setValueAsync(true);
            return true;

        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }


    }

    /**
     * Retrieves the data of the friend users of the user with given id.
     * @param uid - user id.
     * @return UserData objects of all friends.
     * @throws InterruptedException - exception.
     */
    public static List<UserData> retrieveFriendsDataObjects(String uid)
            throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        List<String> fids = new ArrayList<>();

        DatabaseReference ref = db.getReference("users/" + uid + "/friends");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    fids.add(child.getKey());
                }

                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        latch.await();

        List<UserData> friends = new ArrayList<>(fids.size());
        for (String fid : fids) {
            friends.add(getUserData(fid));
        }

        return friends;

    }

    /**
     * Retrieves the amount of eaten vegetarian meals by the user with given uid from DB.
     * @param uid - user id.
     * @return number of eaten vegetarian meals.
     * @throws InterruptedException - exception.
     */
    public static double retrieveDoubleFeatureCounter(String uid, String feature)
            throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);
        double val = retrieveValueAt(ref, Double.class);

        return val;

    }

    /**
     * Retrieve value at the specific reference from the database.
     * @param ref - reference to the value.
     * @param retClass - the class that the value represents.
     * @param <T> - the class that the value represents.
     * @return the value of the given type from the given reference.
     * @throws InterruptedException - exception.
     */
    private static <T> T retrieveValueAt(DatabaseReference ref, Class<T> retClass)
            throws InterruptedException {

        DataSnapshot snapshot = retrieveDataSnapshotAt(ref);
        return snapshot.getValue(retClass);

    }

    /**
     * The method retrieves the DataSnapshot from the given reference at DB.
     * @param ref - reference to the data location.
     * @return DataSnapshot from the given reference.
     * @throws InterruptedException - exception.
     */
    private static DataSnapshot retrieveDataSnapshotAt(DatabaseReference ref)
            throws  InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                valueObj = dataSnapshot;
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DatabaseError: " + databaseError.getMessage());
            }

        });

        latch.await();

        return (DataSnapshot)valueObj;

    }


}
