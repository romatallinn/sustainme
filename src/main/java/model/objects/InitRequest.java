package model.objects;

public class InitRequest {

    private String uid;
    private String fname;
    private String lname;

    /**
     * Constructor for an InitRequest.
     * @param uid userId of user sending request
     * @param fname first name of user sending request
     * @param lname last name of user sending request
     */
    public InitRequest(String uid, String fname, String lname) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
    }

    public String getUid() {
        return uid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

}
