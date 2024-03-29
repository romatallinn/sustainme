package server.supporting;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.objects.UserData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        Map<String, Boolean> badges = new HashMap<>();
        badges.put("distanceByBikeBadge", false);
        badges.put("vegetarianMealBadge", false);
        badges.put("kmNoCarUsedBadge", false);
        badges.put("co2ReducedBadge", false);
        badges.put("levelHundredBadge", false);
        badges.put("distancePublicBadge", false);


        Map<String, Object> features = new HashMap<>();
        features.put("vegmeals", 0);
        features.put("vegmealsCO2", 0);
        features.put("localproduce", 0);
        features.put("localproduceCO2", 0);
        features.put("bike", 0);
        features.put("bikeCO2", 0);
        features.put("public", 0);
        features.put("publicCO2", 0);
        features.put("paperrecycling", 0);
        features.put("paperrecyclingCO2", 0);
        features.put("plasticrecycling", 0);
        features.put("plasticrecyclingCO2", 0);
        features.put("solararea", 0);
        features.put("solarareaCO2", 0);
        features.put("temperature", 21);
        features.put("temperatureCO2", 0);
        features.put("lastupdate", LocalDate.now().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("fname", fname);
        data.put("lname", lname);
        data.put("experience", 0);
        data.put("co2red", 0);
        data.put("features", features);
        data.put("badges", badges);


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
    public static double increaseFeatureCounter(String uid, String feature, double amount)
            throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("features/" + feature);

        double newVal = retrieveValueAt(ref, Double.class) + amount;
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
     * Retrieves badges out of database.
     * @param uid                   - user id
     * @param badges                - badges
     * @return val                  - badge
     * @throws InterruptedException - exception
     */
    public static Boolean retrieveBadges(String uid, String badges)
        throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("badges/" + badges);
        Boolean val = retrieveValueAt(ref, Boolean.class);

        return val;

    }

    /**
     * Updates the badges in the database to true.
     * @param uid       - user id
     * @param badges    - badges
     */
    public static void updateBadges(String uid, String badges) {
        DatabaseReference ref = db.getReference("users/" + uid + "/badges/" + badges);

        ref.setValueAsync(true);

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
     * Updates children at the given path with the given data.
     * @param path - path to the children in the database.
     * @param data - data that should be uploaded instead.
     */
    public static void updateChildren(String path, Map<String, Object> data) {

        DatabaseReference ref = db.getReference(path);

        if (data == null) {
            ref.removeValueAsync();
            return;
        }

        ref.updateChildrenAsync(data);
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
     * Retrieve value at the specific reference from the database using the string path.
     * @param path - path to the value.
     * @param retClass - the class that the value represents.
     * @param <T> - the class that the value represents.
     * @return the value of the given type from the given reference.
     * @throws InterruptedException - exception.
     */
    public static <T> T retrieveValueAt(String path, Class<T> retClass)
            throws InterruptedException {

        DatabaseReference ref = db.getReference(path);
        return retrieveValueAt(ref, retClass);

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

    /**
     * Updates the home temperature in the database.
     * @param uid user id of the user
     * @param temperature new temperature
     * @return the updated temperature
     * @throws InterruptedException - database exception
     */
    public static double setTemperature(String uid, double temperature)
            throws InterruptedException {

        DatabaseReference ref = db.getReference("users/").child(uid).child("/features/"
                + "temperature");

        ref.setValueAsync(temperature);
        double newVal = retrieveValueAt(ref, Double.class);

        return newVal;

    }

    /**
     * Updates the last visited time of the user.
     * @param uid user id of the user
     * @return the difference in time between now and last visited
     * @throws InterruptedException - database exception
     */
    public static int updateTime(String uid) throws InterruptedException {

        DatabaseReference ref = db.getReference("users").child(uid).child("features/lastupdate");

        LocalDate oldTime = LocalDate.parse(retrieveValueAt(ref, String.class));
        LocalDate newTime = LocalDate.now();
        ref.setValueAsync(newTime.toString());

        return (int) ChronoUnit.DAYS.between(oldTime, newTime);
    }


}
