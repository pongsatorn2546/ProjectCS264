package com.example.cs264.repository;

import com.example.cs264.model.Login;
import com.example.cs264.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Repository
public class LoginRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String loginConfirmation(Login login) {
        try {
            // Replace these values with the actual endpoint and access token
            String apiUrl = "https://restapi.tu.ac.th/api/v1/auth/Ad/verify";
            String accessToken = "{Access token}";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Application-Key", "TU2dce847e8d656aab1b9ac485aadc7a607cff975ee7f8455de5188219c06dc865143f543ba30033c336972a8d26975a69");

            // Enable input/output streams
            connection.setDoOutput(true);

            String username = login.getUsername();
            String password = login.getPassword();

            // Create the JSON payload
            String jsonInputString = "{\"UserName\":\"" + username + "\",\"PassWord\":\"" + password + "\"}";

            /*  How body should look
            {
                "username" : "studentID",
                "password": "password"
            }
             */

            // Write the JSON payload to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes();
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response content
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Now, 'response' contains the data fetched from the API
                //System.out.println("API Response: " + response.toString());
                connection.disconnect();
                return response.toString();
            } else {
                //System.out.println("Failed to authenticate. Response Code: " + responseCode);
                connection.disconnect();
                return ("Failed to authenticate. Response Code: " + responseCode);
            }



        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
