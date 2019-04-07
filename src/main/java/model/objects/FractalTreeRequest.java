package model.objects;

public class FractalTreeRequest {

    private String uid;

    /**
     * Constructor for FractalTreeRequest.
     * @param uid               - user identification
     */
    public FractalTreeRequest(
        String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

}

