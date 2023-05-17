import java.awt.event.KeyEvent;

public class Controls {

    public static void controlOptionsMenu(OptionsMenu optionsMenu, int key) {
        if (key == KeyEvent.VK_DOWN) {
            optionsMenu.moveDownThroughMenu();
        }
        if (key == KeyEvent.VK_UP) {
            optionsMenu.moveUpThroughMenu();
        }
        if (key == KeyEvent.VK_ENTER) {
            if (optionsMenu.isMultiplayerSettingsHighlighted()) {
                GamePanel.switchMultiplayer();
            }
            if (optionsMenu.isPlayerCollisionSettingsHighlighted()) {
                GamePanel.switchPlayerCollision();
            }
            if (optionsMenu.isBorderCollisionSettingsHighlighted()) {
                GamePanel.setBorderCollisionOn();
            }
            if (optionsMenu.isBackButtonHighlighted()) {
                GamePanel.gameState = State.MENU;
            }
        }
    }

    public static void controlMainMenu(MainMenu mainMenu, int key) {
        if (key == KeyEvent.VK_DOWN) {
            mainMenu.moveDownThroughMenu();
        }
        if (key == KeyEvent.VK_UP) {
            mainMenu.moveUpThroughMenu();
        }
        if (key == KeyEvent.VK_ENTER) {
            if (mainMenu.isPlayButtonHighlighted()) {
                GamePanel.gameState = State.GAME;
            }
            if (mainMenu.isOptionsButtonHighlighted()) {
                GamePanel.gameState = State.OPTIONS;
            }
            if (mainMenu.isHelpButtonHighlighted()) {
                GamePanel.gameState = State.HELP;
            }
            if (mainMenu.isQuitButtonHighlighted()) {
                System.exit(0);
            }
        }
    }

    public static void controlGameOverScreen(int key) {
        if (key == KeyEvent.VK_ENTER) {
            GamePanel.gameState = State.MENU;
            GamePanel.getPlayer1().setScore(0);
            GamePanel.getPlayer2().setScore(0);
        }
    }
    public static void controlHelpScreen(int key) {
        if (key == KeyEvent.VK_ENTER) {
            GamePanel.gameState = State.MENU;
        }
    }

    public static void controlGame(int key) {
        if (key == KeyEvent.VK_UP && GamePanel.getPlayer1().isNextMoveAllowed()) {
            GamePanel.getPlayer1().changeDirection(Direction.UP);
        }
        if (key == KeyEvent.VK_DOWN && GamePanel.getPlayer1().isNextMoveAllowed()) {
            GamePanel.getPlayer1().changeDirection(Direction.DOWN);
        }
        if (key == KeyEvent.VK_RIGHT && GamePanel.getPlayer1().isNextMoveAllowed()) {
            GamePanel.getPlayer1().changeDirection(Direction.RIGHT);
        }
        if (key == KeyEvent.VK_LEFT && GamePanel.getPlayer1().isNextMoveAllowed()) {
            GamePanel.getPlayer1().changeDirection(Direction.LEFT);
        }
        if (key == KeyEvent.VK_ESCAPE){
            GamePanel.gameState = State.MENU;
        }
        if (GamePanel.isMultiplayerOn()) {
            if (key == KeyEvent.VK_W && GamePanel.getPlayer2().isNextMoveAllowed()) {
                GamePanel.getPlayer2().changeDirection(Direction.UP);
            }
            if (key == KeyEvent.VK_S && GamePanel.getPlayer2().isNextMoveAllowed()) {
                GamePanel.getPlayer2().changeDirection(Direction.DOWN);
            }
            if (key == KeyEvent.VK_D && GamePanel.getPlayer2().isNextMoveAllowed()) {
                GamePanel.getPlayer2().changeDirection(Direction.RIGHT);
            }
            if (key == KeyEvent.VK_A && GamePanel.getPlayer2().isNextMoveAllowed()) {
                GamePanel.getPlayer2().changeDirection(Direction.LEFT);
            }
        }
    }
}
