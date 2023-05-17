import java.awt.*;

public class GameOverScreen {

    public void render(Graphics g) {
        Font titleFont = new Font("arial", Font.BOLD, 50);
        Font scoreFont = new Font("arial", Font.BOLD, 20);
        g.setColor(Color.LIGHT_GRAY);
        g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.setFont(titleFont);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Game Over", GamePanel.WIDTH / 2 - 130, 100);
        g.setFont(scoreFont);
        g.drawString(GamePanel.getGameOverReason(), GamePanel.WIDTH /2 - 150, 150);
        g.drawString("Player 1 score: " + GamePanel.getPlayer1().getScore(), GamePanel.WIDTH / 2 - 80, 200);
        if (GamePanel.isMultiplayerOn()) {
            g.drawString("Player 2 score: " + GamePanel.getPlayer2().getScore(), GamePanel.WIDTH / 2 - 80, 250);
        }
        ButtonMaker.drawButton(150, 340, "Press enter to reset", true, g);
    }
}
