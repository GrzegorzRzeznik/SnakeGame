import java.awt.*;

public class HelpScreen {

    public void render(Graphics g) {
        Font titleFont = new Font("arial", Font.BOLD, 50);
        Font smallFont = new Font("arial", Font.BOLD, 18);
        g.setColor(Color.LIGHT_GRAY);
        g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.setFont(titleFont);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Help", GamePanel.WIDTH / 2 - 50, 100);
        g.setFont(smallFont);
        g.drawString("Player 1 controls: UP, DOWN, LEFT, RIGHT", GamePanel.WIDTH /2 - 170, 200);
        g.drawString("Player 2 controls: W, S, A, D ", GamePanel.WIDTH /2 - 170, 250);
        g.drawString("Exit game: ESC", GamePanel.WIDTH /2 - 170, 300);

        ButtonMaker.drawButton(150, 400, "Back to menu", true, g);
    }
}
