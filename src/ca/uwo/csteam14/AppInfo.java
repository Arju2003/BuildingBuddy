package ca.uwo.csteam14;

import javax.swing.*;

public class AppInfo extends JLabel {
    private JLabel title = new JLabel();
    private JLabel content = new JLabel();



    public AppInfo(String title, String content) {
        this.title.setText(title);
        this.content.setText(content);
    }

    public void setTitle(String newTitle) {
        title.setText(newTitle);
    }
    public void setContent(String newContent) {
        content.setText(newContent);
    }
}
