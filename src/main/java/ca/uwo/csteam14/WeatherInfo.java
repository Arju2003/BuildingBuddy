/**
 * The WeatherInfo class is responsible for retrieving and displaying
 * weather information for a specific location. The class uses the
 * WeatherAPI to get the current weather information for the location
 * specified in the API URL.
 *  @author Jason B. Shew
 *  @version 1.0.0
 *  @since 2023-03-07
 */

package ca.uwo.csteam14;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import org.json.JSONObject;

public class WeatherInfo {

    /**
     * Creates a new WeatherInfo object and displays the current weather
     * information for the specified location.
     *
     * @throws IOException if there is an error connecting to the WeatherAPI
     */
    public WeatherInfo() throws IOException {
        JWindow window = new JWindow();
        window.setSize(480, 120);
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Centers the window on the screen
                window.setLocationRelativeTo(null);
            }
        });


        JPanel panel = new JPanel();
        panel.setBackground(new Color(78,37,130,80));
        panel.setOpaque(true);
        JLabel weatherIcon = new JLabel();
        panel.add(weatherIcon);
        JLabel temperatureLabel = new JLabel();
        panel.add(temperatureLabel);

        JLabel humidityLabel = new JLabel();
        panel.add(humidityLabel);

        JButton hideButton = new JButton("Hide");

        // WeatherAPI ID
        String API_URL = "https://api.weatherapi.com/v1/current.json?key=4d6a2621f6f84a82a79121544231203&q=43.005753,-81.266085&aqi=yes";

        try {
            URL url = new URL(API_URL + "London,Ontario"); // Gets weather info at Western University
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject weatherData = new JSONObject(content.toString());
            JSONObject current = weatherData.getJSONObject("current");
            double temp = current.getDouble("temp_c");
            int humidity = current.getInt("humidity");
            String wURL = "https:"+current.getJSONObject("condition").getString("icon");
            URL conditionIconURL = new URL(wURL);
            ImageIcon icon = new ImageIcon(conditionIconURL);
            Image image = icon.getImage(); // transforms weather condition icon
            Image newimg = image.getScaledInstance(80, 80,  Image.SCALE_SMOOTH); // scales it the smooth way
            icon = new ImageIcon(newimg);  // transforms it back
            weatherIcon.setIcon(icon);
            weatherIcon.setVisible(true);
            temperatureLabel.setText("Temperature: " + temp + "°C\n");
            humidityLabel.setText("Humidity: " + humidity + "%\n");
        } catch (IOException | RuntimeException e) {
            // If internet is not available, then gives user a prompt rather than throws an exception.
            ImageIcon originalIcon = new ImageIcon("./images/no_internet.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            weatherIcon.setIcon(scaledIcon);
            temperatureLabel.setText("Weather is unavailable:");
            humidityLabel.setText("Check your Internet connection");
        }

        window.setVisible(true);
        hideButton.addActionListener(e -> window.setVisible(false)); // A button to hide the weather info window
        panel.add(hideButton);
        window.add(panel);
        // Stylizes the weather info panel
        Font font = new Font("Arial", Font.PLAIN, 18);
        temperatureLabel.setFont(font);
        humidityLabel.setFont(font);
        hideButton.setFont(font);
        window.pack();
        AppMenu.clearWindows();
        window.setVisible(true);
    }
}