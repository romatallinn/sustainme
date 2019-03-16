package model;

public class SingletonUser {

    private static UserProfile user = new UserProfile();

    private SingletonUser() {}

    public static UserProfile getInstance() {
        return user;
    }

}

