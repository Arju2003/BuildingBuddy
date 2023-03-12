package ca.uwo.csteam14;

public class Component {
    private int componentID;
    private String componentName;
    private int height;
    private int width;

    public int getComponentID() {
        return componentID;
    }

    public void setComponentId(int newComponentID) {
        componentID = newComponentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String newComponentName) {
        componentName = newComponentName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }
}
