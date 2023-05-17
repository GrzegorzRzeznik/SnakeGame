import java.awt.*;

public class BodyPart {
    private final int xPosition, yPosition;
    private final int width, height;
    private final Color color;

    public BodyPart(int xPosition, int yPosition, int tileSize, Color color) {
        this.color = color;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        width = tileSize;
        height = tileSize;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(xPosition * 10, yPosition * 10, width, height);
    }

    public int getPositionX() {
        return xPosition;
    }

    public int getPositionY() {
        return yPosition;
    }

    @Override
    public String toString() {
        return "BodyPart{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }
}
