import java.awt.*;

public class Apple {

    private final int applePositionX, applePositionY;
    private final int width, height;

    public Apple(int applePositionX, int applePositionY, int tileSize) {

        this.applePositionX = applePositionX;
        this.applePositionY = applePositionY;
        width = tileSize;
        height = tileSize;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(applePositionX * width, applePositionY * height, width, height);
    }

    public int getApplePositionX() {
        return applePositionX;
    }

    public int getApplePositionY() {
        return applePositionY;
    }
}
