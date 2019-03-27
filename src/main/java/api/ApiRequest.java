package api;

import java.io.*;
import java.net.*;

public class ApiRequest {

        public static void main(String[] args) throws Exception {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://apis.berkeley.edu/coolclimate/footprint?" +
                    "input_footprint_transportation_miles2=0&" +
                    "input_footprint_transportation_miles3=0&" +
                    "input_footprint_transportation_miles4=0&" +
                    "input_footprint_transportation_miles5=0&" +
                    "input_footprint_transportation_miles6=0&" +
                    "input_footprint_transportation_miles7=0&" +
                    "input_footprint_transportation_miles8=0&" +
                    "input_footprint_transportation_miles9=0&" +
                    "input_footprint_transportation_miles10=0&" +
                    "input_footprint_transportation_airtype=0&" +
                    "input_footprint_transportation_airtotal=0&" +
                    "input_footprint_transportation_groundtype=0&" +
                    "input_footprint_transportation_publictrans=0&" +
                    "internal_state_abbreviation=DC&" +
                    "input_footprint_housing_electricity_type=0&" +
                    "input_footprint_housing_electricity_dollars=0&" +
                    "input_footprint_housing_cleanpercent=0&" +
                    "input_footprint_housing_naturalgas_type=0&" +
                    "input_footprint_housing_naturalgas_dollars=0&" +
                    "input_footprint_housing_heatingoil_type=0&" +
                    "input_footprint_housing_heatingoil_dollars=0&" +
                    "input_footprint_housing_heatingoil_dollars_per_gallon=0&" +
                    "input_footprint_housing_watersewage=0&" +
                    "input_footprint_housing_squarefeet=0&" +
                    "input_size=0&" +
                    "input_footprint_household_adults=0&" +
                    "input_footprint_household_children=0&" +
                    "input_changed=0&" +
                    "input_footprint_shopping_food_meattype=0&" +
                    "input_footprint_shopping_food_meatfisheggs=0&" +
                    "input_footprint_shopping_food_dairy=0&" +
                    "input_footprint_shopping_food_otherfood=0&" +
                    "input_footprint_shopping_food_fruitvegetables=0&" +
                    "input_footprint_shopping_food_cereals=0&" +
                    "input_footprint_shopping_goods_default_furnitureappliances=0&" +
                    "input_footprint_shopping_goods_default_clothing=0&" +
                    "input_footprint_shopping_goods_default_other_entertainment=0&" +
                    "input_footprint_shopping_goods_default_other_office=0&" +
                    "input_footprint_shopping_goods_default_other_personalcare=0&" +
                    "input_footprint_shopping_goods_default_other_autoparts=0&" +
                    "input_footprint_shopping_goods_default_other_medical=0&" +
                    "input_footprint_shopping_goods_type=0&" +
                    "input_footprint_shopping_goods_total=0&" +
                    "input_footprint_shopping_services_type=0&" +
                    "input_footprint_shopping_services_total=0&" +
                    "input_footprint_housing_gco2_per_kwh=0&" +
                    "input_footprint_housing_hdd=0&" +
                    "input_footprint_housing_cdd=0&" +
                    "input_footprint_transportation_mpg2=0&" +
                    "input_footprint_transportation_mpg3=0&" +
                    "input_footprint_transportation_mpg4=0&" +
                    "input_footprint_transportation_mpg5=0&" +
                    "input_footprint_transportation_mpg6=0&" +
                    "input_footprint_transportation_mpg7=0&" +
                    "input_footprint_transportation_mpg8=0&" +
                    "input_footprint_transportation_mpg9=0&" +
                    "input_footprint_transportation_mpg10=0&" +
                    "input_footprint_transportation_num_vehicles=1&" +
                    "input_footprint_transportation_fuel1=1&" + //Gasoline
                    "input_footprint_transportation_mpg1=36&" + //average mpg gasoline
                    "input_footprint_transportation_miles1=12.42");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("app_id", ""); //insert app_id
            conn.setRequestProperty("app_key", ""); //insert app_key
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result.toString());
        }

}
