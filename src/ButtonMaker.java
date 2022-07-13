import java.awt.*;

public class ButtonMaker {


    public static void drawButton(int xPosition, int yPosition, String name, boolean highlighted, Graphics g) {
        Color color = highlighted ? Color.RED : Color.DARK_GRAY;
        g.setColor(color);
        g.drawString(name, xPosition + 10, yPosition + 32);
        g.drawRect(xPosition, yPosition, 200, 50);
        g.setColor(Color.DARK_GRAY);
    }

    public static void drawCheckbox(int height, boolean checked, Graphics g) {
        if (checked) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(350, height, 50, 50);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(350, height, 50, 50);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(350, height, 50, 50);
        }
    }
}
