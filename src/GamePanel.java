import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 500, HEIGHT = 500;
    public static State gameState = State.MENU;

    private static boolean multiplayerOn = false;
    private static boolean playerCollisionOn = true;
    private static boolean borderCollisionOn = true;
    private static boolean running = false;

    private static final Snake PLAYER_1 = new Snake(40, 40, Direction.UP, Color.ORANGE, "Player 1");
    private static final Snake PLAYER_2 = new Snake(10, 10, Direction.RIGHT, Color.LIGHT_GRAY, "Player 2");

    private static int totalScore = 0;
    private static int level = 1;
    private static final int scoreGoalForMultiplayer = 100;

    private static String gameOverReason;

    private final Thread thread = new Thread(this);
    private final MainMenu mainMenu;
    private final OptionsMenu optionsMenu;
    private final HelpScreen helpScreen;
    private final GameOverScreen gameOverScreen;

    private final ArrayList<Apple> apples;

    private final Random random;

    private final int[] speedLevels = {100, 90, 80, 70, 65, 58, 52, 46, 38, 25};


    public GamePanel() {

        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 80));
        mainMenu = new MainMenu();
        optionsMenu = new OptionsMenu();
        gameOverScreen = new GameOverScreen();
        helpScreen = new HelpScreen();
        apples = new ArrayList<>();
        random = new Random();

        start();
    }

    public static String getGameOverReason() {
        return gameOverReason;
    }

    public static Snake getPlayer1() {
        return PLAYER_1;
    }

    public static Snake getPlayer2() {
        return PLAYER_2;
    }

    public static boolean isMultiplayerOn() {
        return multiplayerOn;
    }

    public static boolean isPlayerCollisionOn() {
        return playerCollisionOn;
    }

    public static boolean isBorderCollisionOn() {
        return borderCollisionOn;
    }

    public static void switchMultiplayer() {
        multiplayerOn = !multiplayerOn;
    }

    public static void switchPlayerCollision() {
        playerCollisionOn = !playerCollisionOn;
    }

    public static void setBorderCollisionOn() {
        borderCollisionOn = !borderCollisionOn;
    }


    public void start() {
        running = true;
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() throws InterruptedException {
        if (multiplayerOn) {
            PLAYER_1.createSnake();
            PLAYER_2.createSnake();
            Thread.sleep(speedLevels[level - 1]);
            PLAYER_1.moveSnake();
            PLAYER_2.moveSnake();
            spawnAppleIfEaten();
            checkIfAppleFound(PLAYER_1);
            checkIfAppleFound(PLAYER_2);
            checkIfScoreGoalReached(PLAYER_1);
            checkIfScoreGoalReached(PLAYER_2);
            checkForSelfCollision(PLAYER_1);
            checkForSelfCollision(PLAYER_2);
            if (playerCollisionOn) {
                checkForCollisionWithOtherPlayer(PLAYER_1, PLAYER_2);
                checkForCollisionWithOtherPlayer(PLAYER_2, PLAYER_1);
            }
            if (borderCollisionOn) {
                checkForBorderCollision(PLAYER_1);
                checkForBorderCollision(PLAYER_2);
            }
        } else {
            PLAYER_1.createSnake();
            Thread.sleep(speedLevels[level - 1]);
            PLAYER_1.moveSnake();
            spawnAppleIfEaten();
            checkIfAppleFound(PLAYER_1);
            checkForSelfCollision(PLAYER_1);
            if (borderCollisionOn) {
                checkForBorderCollision(PLAYER_1);
            }
        }
    }

    private void checkIfScoreGoalReached(Snake snake) {
        if (snake.getScore() == scoreGoalForMultiplayer) {
            gameOverReason = snake.getName() + " reached " + scoreGoalForMultiplayer + " points and won";
            resetGame();
            gameState = State.GAME_OVER;
        }
    }

    private void spawnAppleIfEaten() {
        if (apples.size() == 0) {
            int applePositionX = random.nextInt(49);
            int applePositionY = random.nextInt(49);

            Apple apple = new Apple(applePositionX, applePositionY, 10);
            apples.add(apple);
        }
    }

    private void checkIfAppleFound(Snake player) {
        for (int i = 0; i < apples.size(); i++) {
            if (player.getHeadPositionX() == apples.get(i).getApplePositionX() && player.getHeadPositionY() == apples.get(i).getApplePositionY()) {
                player.increaseSize(1);
                apples.remove(i);
                totalScore += 10;
                player.addScore();
                increaseDifficulty();
                i++;
            }
        }
    }

    private void checkForSelfCollision(Snake player) {
        for (int i = 0; i < player.getSnake().size() - 1; i++) {
            if (player.getHeadPositionX() == player.getSnake().get(i).getPositionX() && player.getHeadPositionY() == player.getSnake().get(i).getPositionY()) {
                gameOverReason = "   " + player.getName() + " " + "collided with himself!";
                resetGame();
                gameState = State.GAME_OVER;
                break;
            }
        }
    }

    private void checkForCollisionWithOtherPlayer(Snake player1, Snake player2) {
        if (player1.getHeadPositionX() == player2.getHeadPositionX() && player1.getHeadPositionY() == player2.getHeadPositionY()) {
            gameOverReason = "Head colision!";
            resetGame();
            gameState = State.GAME_OVER;
        } else {
            for (int i = 0; i < player2.getSnake().size() - 1; i++) {
                if (player1.getHeadPositionX() == player2.getSnake().get(i).getPositionX()
                        && player1.getHeadPositionY() == player2.getSnake().get(i).getPositionY()) {
                    gameOverReason = "   " + player1.getName() + " collided with " + player2.getName() + "!";
                    resetGame();
                    gameState = State.GAME_OVER;
                    break;
                }
            }
        }
    }

    private void checkForBorderCollision(Snake player) {
        if (player.getHeadPositionX() < 0 || player.getHeadPositionX() > 49 || player.getHeadPositionY() < 0 || player.getHeadPositionY() > 49) {
            gameOverReason = player.getName() + " collided with the border!";
            resetGame();
            gameState = State.GAME_OVER;
        }
    }

    public void increaseDifficulty() {
        if (Math.round(totalScore / 100) < 1) {
            level = 1;
        } else level = Math.min(Math.round(totalScore / 100), 10);
    }

    private void resetGame() {
        level = 1;
        totalScore = 0;
        PLAYER_1.resetSnake(40, 40, Direction.UP);
        PLAYER_2.resetSnake(10, 10, Direction.RIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {

        if (gameState == State.GAME) {
            render(g);
        }
        if (gameState == State.GAME_OVER) {
            gameOverScreen.render(g);
        }
        if (gameState == State.MENU) {
            resetGame();
            mainMenu.render(g);
        }
        if (gameState == State.OPTIONS) {
            optionsMenu.render(g);
        }
        if (gameState == State.HELP) {
            helpScreen.render(g);
        }

    }

    private void render(Graphics g) {

        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.DARK_GRAY);
        for (int j = 0; j < WIDTH; j += 10) {
            g.drawLine(j, 0, j, HEIGHT);
        }
        for (int k = 0; k < HEIGHT; k += 10) {
            g.drawLine(0, k, HEIGHT, k);
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, HEIGHT + 1, WIDTH, 80);
        Font font = new Font("Serif", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Level: " + level, 10, HEIGHT + 45);
        g.drawString("P1 score: " + PLAYER_1.getScore(), 150, HEIGHT + 45);

        for (int i = 0; i < PLAYER_1.getSnake().size(); i++) {
            PLAYER_1.getSnake().get(i).draw(g);
        }
        if (multiplayerOn) {
            for (int i = 0; i < PLAYER_2.getSnake().size(); i++) {
                PLAYER_2.getSnake().get(i).draw(g);
            }
            g.setColor(Color.BLACK);
            g.drawString("P2 score: " + PLAYER_2.getScore(), 350, HEIGHT + 45);
        }
        for (Apple apple : apples) {
            apple.draw(g);
        }
    }

    public void run() {
        while (running) {
            try {
                if (gameState == State.GAME) {
                    tick();
                }
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (gameState == State.GAME) {
            Controls.controlGame(key);
        } else if (gameState == State.GAME_OVER) {
            Controls.controlGameOverScreen(key);
        } else if (gameState == State.MENU) {
            Controls.controlMainMenu(mainMenu, key);
        } else if (gameState == State.OPTIONS) {
            Controls.controlOptionsMenu(optionsMenu, key);
        } else if (gameState == State.HELP) {
            Controls.controlHelpScreen(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
