package ca.uwo.csteam14;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;

import org.json.JSONObject;

public class WeatherInfo {
    private final String API_KEY = "4d6a2621f6f84a82a79121544231203";
    private final String API_URL = "https://api.weatherapi.com/v1/current.json?key=4d6a2621f6f84a82a79121544231203&q=43.005753,-81.266085&aqi=yes";

    private final JLabel weatherIcon = new JLabel();

    public WeatherInfo() throws IOException {
        JWindow window = new JWindow();
        window.setSize(400, 40);
        // Get the dimensions of the screen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenRect = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        int screenWidth = screenRect.width;
        int screenHeight = screenRect.height;

// Calculate the center position
        int windowX = (screenWidth - window.getWidth()) / 2;
        int windowY = (screenHeight - window.getHeight()) / 2;

// Set the position of the window to the center of the screen
        window.setLocation(windowX, windowY);

        JPanel panel = new JPanel();
        panel.add(weatherIcon);
        JLabel temperatureLabel = new JLabel();
        panel.add(temperatureLabel);

        JLabel humidityLabel = new JLabel();
        panel.add(humidityLabel);

        JButton hideButton = new JButton("Show");

        URL url = new URL(API_URL + "London,Ontario");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
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
        Image newimg = image.getScaledInstance(30, 30,  Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);  // transform it back
        weatherIcon.setIcon(icon);
        weatherIcon.setVisible(true);
        temperatureLabel.setText("Temperature: " + temp + "°C");
        humidityLabel.setText("Humidity: " + humidity + "%");

        hideButton.setText("Hide");
        window.setVisible(true);
        hideButton.addActionListener(new ActionListener() {
            private boolean weatherVisible = false;
            @Override
            public void actionPerformed(ActionEvent e) {



                        temperatureLabel.setText("");
                        humidityLabel.setText("");
                        weatherVisible = false;
                        weatherIcon.setVisible(false);
                        window.setVisible(false);

            }
        });
        panel.add(hideButton);
        window.add(panel);
        window.setVisible(true);
    }
}