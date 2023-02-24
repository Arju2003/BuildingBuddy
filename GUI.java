import javax.swing.*;
public class GUI {
    public static void main(String[] args) {
        JFrame frame =new JFrame();  // creating instance of JFrame

        JButton button =new JButton("the button.");  // creating instance of JButton
        button.setBounds(600,350,100, 40);  // x axis, y axis, width, height

        frame.add(button);//adding button in JFrame

        frame.setSize(1200,700);  // 400 width and 500 height
        frame.setLayout(null);  // using no layout managers
        frame.setVisible(true);  // making the frame visible
    }
}
