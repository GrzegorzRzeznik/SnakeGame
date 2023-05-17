import java.awt.*;


public class OptionsMenu {

    private boolean multiplayerSettingsHighlighted = true;
    private boolean playerCollisionSettingsHighlighted = false;
    private boolean borderCollisionSettingsHighlighted = false;
    private boolean backButtonHighlighted = false;

    public boolean isMultiplayerSettingsHighlighted() {
        return multiplayerSettingsHighlighted;
    }

    public boolean isPlayerCollisionSettingsHighlighted() {
        return playerCollisionSettingsHighlighted;
    }

    public boolean isBorderCollisionSettingsHighlighted() {
        return borderCollisionSettingsHighlighted;
    }

    public boolean isBackButtonHighlighted() {
        return backButtonHighlighted;
    }


    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        Font titleFont = new Font("arial", Font.BOLD, 50);
        g.setFont(titleFont);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Snake Clone", GamePanel.WIDTH / 2 - 150, 100);
        Font buttonFont = new Font("arial", Font.BOLD, 20);
        g.setFont(buttonFont);

        ButtonMaker.drawButton(110, 130, "Multiplayer", multiplayerSettingsHighlighted, g);
        ButtonMaker.drawButton(110, 200, "Player collision", playerCollisionSettingsHighlighted, g);
        ButtonMaker.drawButton(110, 270, "Border collision", borderCollisionSettingsHighlighted, g);
        ButtonMaker.drawButton(110, 340, "Back", backButtonHighlighted, g);

        ButtonMaker.drawCheckbox(130, GamePanel.isMultiplayerOn(), g);
        ButtonMaker.drawCheckbox(200, GamePanel.isPlayerCollisionOn(), g);
        ButtonMaker.drawCheckbox(270, GamePanel.isBorderCollisionOn(), g);
    }

    public void moveDownThroughMenu() {
        if (multiplayerSettingsHighlighted) {
            multiplayerSettingsHighlighted = false;
            playerCollisionSettingsHighlighted = true;
            borderCollisionSettingsHighlighted = false;
            backButtonHighlighted = false;
        } else if (playerCollisionSettingsHighlighted) {
            playerCollisionSettingsHighlighted = false;
            borderCollisionSettingsHighlighted = true;
            backButtonHighlighted = false;
        } else if (borderCollisionSettingsHighlighted) {
            borderCollisionSettingsHighlighted = false;
            backButtonHighlighted = true;
        } else if (backButtonHighlighted) {
            multiplayerSettingsHighlighted = true;
            backButtonHighlighted = false;
        }
    }

    public void moveUpThroughMenu() {
        if (multiplayerSettingsHighlighted) {
            multiplayerSettingsHighlighted = false;
            playerCollisionSettingsHighlighted = false;
            borderCollisionSettingsHighlighted = false;
            backButtonHighlighted = true;
        } else if (playerCollisionSettingsHighlighted) {
            multiplayerSettingsHighlighted = true;
            playerCollisionSettingsHighlighted = false;
            borderCollisionSettingsHighlighted = false;
            backButtonHighlighted = false;
        } else if (borderCollisionSettingsHighlighted) {
            playerCollisionSettingsHighlighted = true;
            borderCollisionSettingsHighlighted = false;
            backButtonHighlighted = false;
        } else if (backButtonHighlighted) {
            borderCollisionSettingsHighlighted = true;
            backButtonHighlighted = false;
        }
    }

}
