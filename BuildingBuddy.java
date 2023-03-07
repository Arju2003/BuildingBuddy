import javax.swing.*;

public class BuildingBuddy {
    public static void main(String[] args) {
        JFrame frame =new JFrame();  // creating instance of JFrame

        JLabel welcomeText =new JLabel("Welcome to BuildingBuddy!");
        welcomeText.setFont(welcomeText.getFont().deriveFont(24f));  // set font size
        welcomeText.setBounds(450,100, 400,30);
        frame.add(welcomeText);

        JLabel guideText =new JLabel("Choose a building from the dropdown.");
        guideText.setFont(guideText.getFont().deriveFont(18f));  // set font size
        guideText.setBounds(440,150, 400,30);
        frame.add(guideText);

        String maps[]={"Middlesex College","Kresge Building","Physics & Astronomy"};
        JComboBox dropdown = new JComboBox(maps);
        dropdown.setBounds(450, 300,200,30);
        frame.add(dropdown);

        JButton confirmButton =new JButton("Go To Map");
        confirmButton.setBounds(650,300,95,30);
        frame.add(confirmButton);

        frame.setSize(1200,700);  // 400 width and 500 height
        frame.setLayout(null);  // using no layout managers
        frame.setVisible(true);  // making the frame visible
    }
}
