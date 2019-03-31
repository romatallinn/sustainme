package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {

    /**
     * Generic request to the coolclimate api to calculate co2 emission.
     * @param distance - distance travelled with transportation other than car.
     * @param bus - distance travelled by bus.
     * @param rail - distance travelled by rail.
     * @return co2 reduction.
     * @throws Exception when the connection fails
     */
    public static String apiRequest(String distance, String bus, String rail) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://apis.berkeley.edu/coolclimate/footprint?"
                + "input_footprint_transportation_miles2=0&"
                + "input_footprint_transportation_miles3=0&"
                + "input_footprint_transportation_miles4=0&"
                + "input_footprint_transportation_miles5=0&"
                + "input_footprint_transportation_miles6=0&"
                + "input_footprint_transportation_miles7=0&"
                + "input_footprint_transportation_miles8=0&"
                + "input_footprint_transportation_miles9=0&"
                + "input_footprint_transportation_miles10=0&"
                + "input_footprint_transportation_airtype=0&"
                + "input_footprint_transportation_airtotal=0&"
                + "input_footprint_transportation_groundtype=1&"
                + "internal_state_abbreviation=DC&"
                + "input_footprint_housing_electricity_type=0&"
                + "input_footprint_housing_electricity_dollars=0&"
                + "input_footprint_housing_cleanpercent=0&"
                + "input_footprint_housing_naturalgas_type=0&"
                + "input_footprint_housing_naturalgas_dollars=0&"
                + "input_footprint_housing_heatingoil_type=0&"
                + "input_footprint_housing_heatingoil_dollars=0&"
                + "input_footprint_housing_heatingoil_dollars_per_gallon=0&"
                + "input_footprint_housing_watersewage=0&"
                + "input_footprint_housing_squarefeet=0&"
                + "input_size=0&"
                + "input_footprint_household_adults=0&"
                + "input_footprint_household_children=0&"
                + "input_changed=0&"
                + "input_footprint_shopping_food_meattype=0&"
                + "input_footprint_shopping_food_meatfisheggs=0&"
                + "input_footprint_shopping_food_dairy=0&"
                + "input_footprint_shopping_food_otherfood=0&"
                + "input_footprint_shopping_food_fruitvegetables=0&"
                + "input_footprint_shopping_food_cereals=0&"
                + "input_footprint_shopping_goods_default_furnitureappliances=0&"
                + "input_footprint_shopping_goods_default_clothing=0&"
                + "input_footprint_shopping_goods_default_other_entertainment=0&"
                + "input_footprint_shopping_goods_default_other_office=0&"
                + "input_footprint_shopping_goods_default_other_personalcare=0&"
                + "input_footprint_shopping_goods_default_other_autoparts=0&"
                + "input_footprint_shopping_goods_default_other_medical=0&"
                + "input_footprint_shopping_goods_type=0&"
                + "input_footprint_shopping_goods_total=0&"
                + "input_footprint_shopping_services_type=0&"
                + "input_footprint_shopping_services_total=0&"
                + "input_footprint_housing_gco2_per_kwh=0&"
                + "input_footprint_housing_hdd=0&"
                + "input_footprint_housing_cdd=0&"
                + "input_footprint_transportation_mpg2=0&"
                + "input_footprint_transportation_mpg3=0&"
                + "input_footprint_transportation_mpg4=0&"
                + "input_footprint_transportation_mpg5=0&"
                + "input_footprint_transportation_mpg6=0&"
                + "input_footprint_transportation_mpg7=0&"
                + "input_footprint_transportation_mpg8=0&"
                + "input_footprint_transportation_mpg9=0&"
                + "input_footprint_transportation_mpg10=0&"
                + "input_footprint_transportation_num_vehicles=1&"
                + "input_footprint_transportation_fuel1=1&" //Gasoline
                + "input_footprint_transportation_mpg1=36&" //average mpg gasoline
                + "input_footprint_transportation_intercity=0&"
                +  "input_footprint_transportation_transit=0&"
                + "input_footprint_transportation_miles1=" + distance + "&"
                + "input_footprint_transportation_bus=" + bus + "&"
                + "input_footprint_transportation_commuter=" + rail);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "application/json");
        conn.setRequestProperty("app_id", "bca8cc1a"); //insert app_id
        conn.setRequestProperty("app_key", "7d58f5d3a0247bd9fdf90094cbbfa5c7"); //insert app_key
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /**
     * Send a request to the CoolClimate api to calculate the co2 reduction
     * for taking the bike instead of taking the car.
     * of using a bike instead of an average car.
     * @param distance distance cycled
     * @return co2 reduction of using a bike instead of a car
     * @throws Exception when the connection fails
     */
    public static double requestBike(String distance) throws Exception {
        String result = ApiRequest.apiRequest(distance,"0","0");
        double test = Double.parseDouble(result
                .split("result_motor_vehicles_direct>")[1].split("<")[0]);
        System.out.println();
        return test;
    }

    /**
     * Send a request to the CoolClimate api to calculate the co2 reduction
     * for taking public transport instead of taking the car.
     * @param distance distance travelled by rail or bus.
     * @param type boolean to indicate travel by bus or rail.
     * @return Difference between co2 emission by car and either bus or rail.
     * @throws Exception when the connection fails.
     */
    public static double requestPublicTrans(String distance, boolean type) throws Exception {
        String result;
        if (type) {
            result = ApiRequest.apiRequest(distance,distance,"0");
        } else {
            result = ApiRequest.apiRequest(distance,"0",distance);
        }
        double avgCar = Double.parseDouble(result
                .split("result_motor_vehicles_direct>")[1].split("<")[0]);
        double publicTrans = Double.parseDouble(result
                .split("result_publictrans_direct>")[1].split("<")[0]);
        return avgCar - publicTrans;
    }

}
