import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private final String name;
    private boolean nextMoveAllowed = true;
    private ArrayList<BodyPart> snake;
    private int size = 5;
    private int score = 0;
    private int headPositionX, headPositionY;
    private final Color color;
    private Direction direction;

    public Snake(int xPosition, int yPosition, Direction direction, Color color, String name) {
        this.color = color;
        this.name = name;
        this.direction = direction;
        changeDirection(direction);
        this.headPositionX = xPosition;
        this.headPositionY = yPosition;
        this.snake = new ArrayList<>();
    }

    public ArrayList<BodyPart> getSnake() {
        return snake;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getHeadPositionX() {
        return headPositionX;
    }

    public int getHeadPositionY() {
        return headPositionY;
    }

    public boolean isNextMoveAllowed() {
        return nextMoveAllowed;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void createSnake() {
        if (this.size == 0) {
            BodyPart bodyPart = new BodyPart(headPositionX, headPositionY, 10, color);
            snake.add(bodyPart);
        }
    }

    public void addScore() {
        score += 10;
    }

    public void resetSnake(int xPos, int yPos, Direction direction) {
        this.size = 5;
        this.snake = new ArrayList<>();
        this.headPositionX = xPos;
        this.headPositionY = yPos;
        this.direction = null;
        changeDirection(direction);
    }

    public void increaseSize(int amount) {
        this.size += amount;
    }

    public void moveSnake() {
        switch (direction) {
            case UP:
                headPositionY--;
                if (!GamePanel.isBorderCollisionOn() && headPositionY < 0) {
                    headPositionY = 49;
                }
                nextMoveAllowed = true;
                break;
            case DOWN:
                headPositionY++;
                if (!GamePanel.isBorderCollisionOn() && headPositionY > 49) {
                    headPositionY = 0;
                }
                nextMoveAllowed = true;
                break;
            case LEFT:
                headPositionX--;
                if (!GamePanel.isBorderCollisionOn() && headPositionX < 0) {
                    headPositionX = 49;
                }
                nextMoveAllowed = true;
                break;
            case RIGHT:
                headPositionX++;
                if (!GamePanel.isBorderCollisionOn() && headPositionX > 49) {
                    headPositionX = 0;
                }
                nextMoveAllowed = true;
                break;
        }
        BodyPart bodyPart = new BodyPart(headPositionX, headPositionY, 10, color);
        snake.add(bodyPart);

        if (snake.size() > size) {
            snake.remove(0);
        }
    }

    public void changeDirection(Direction direction) {
        switch (direction) {
            case UP:
                this.direction = this.direction == Direction.DOWN ? Direction.DOWN : direction;
                nextMoveAllowed = false;
                break;
            case DOWN:
                this.direction = this.direction == Direction.UP ? Direction.UP : direction;
                nextMoveAllowed = false;
                break;
            case LEFT:
                this.direction = this.direction == Direction.RIGHT ? Direction.RIGHT : direction;
                nextMoveAllowed = false;
                break;
            case RIGHT:
                this.direction = this.direction == Direction.LEFT ? Direction.LEFT : direction;
                nextMoveAllowed = false;
                break;
        }
    }
}
