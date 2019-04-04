package model.objects;

public class SolarResponse {

    private int experience;
    private int area;


    public SolarResponse(int experience, int area) {
        this.experience = experience;
        this.area = area;
    }

    public int getExperience() {
        return experience;
    }


    public int getArea() {
        return area;
    }
}
