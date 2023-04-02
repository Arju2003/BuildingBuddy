/**
 * @author Jason
 * WeatherInfo Class
 * Connect weather API to application
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
     * @throws IOException
     */
    public WeatherInfo() throws IOException {
        JWindow window = new JWindow();
        window.setSize(480, 120);
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Center the window on the screen
                window.setLocationRelativeTo(null);
            }
        });


        JPanel panel = new JPanel();
        JLabel weatherIcon = new JLabel();
        panel.add(weatherIcon);
        JLabel temperatureLabel = new JLabel();
        panel.add(temperatureLabel);

        JLabel humidityLabel = new JLabel();
        panel.add(humidityLabel);

        JButton hideButton = new JButton("Hide");

        String API_URL = "https://api.weatherapi.com/v1/current.json?key=4d6a2621f6f84a82a79121544231203&q=43.005753,-81.266085&aqi=yes";

        try {
            URL url = new URL(API_URL + "London,Ontario");
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
            Image image = icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(80, 80,  Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newimg);  // transform it back
            weatherIcon.setIcon(icon);
            weatherIcon.setVisible(true);
            temperatureLabel.setText("Temperature: " + temp + "°C\n");
            humidityLabel.setText("Humidity: " + humidity + "%\n");
        } catch (IOException | RuntimeException e) {
            ImageIcon originalIcon = new ImageIcon("./images/no_internet.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            weatherIcon.setIcon(scaledIcon);
            temperatureLabel.setText("Weather is unavailable:");
            humidityLabel.setText("Check your Internet connection");
        }

        window.setVisible(true);
        hideButton.addActionListener(e -> window.setVisible(false));
        panel.add(hideButton);
        window.add(panel);
        Font font = new Font("Arial", Font.PLAIN, 18);
        temperatureLabel.setFont(font);
        humidityLabel.setFont(font);
        hideButton.setFont(font);
        window.pack();
        AppMenu.clearWindows();
        window.setVisible(true);
    }
}