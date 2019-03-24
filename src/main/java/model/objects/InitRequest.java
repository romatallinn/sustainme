package model.objects;

public class InitRequest {

    private String uid;
    private String fname;
    private String lname;

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
