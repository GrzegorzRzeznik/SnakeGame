import java.awt.*;


public class MainMenu {

    private boolean playButtonHighlighted = true;
    private boolean optionsButtonHighlighted = false;
    private boolean helpButtonHighlighted = false;
    private boolean quitButtonHighlighted = false;

    public boolean isPlayButtonHighlighted() {
        return playButtonHighlighted;
    }

    public boolean isOptionsButtonHighlighted() {
        return optionsButtonHighlighted;
    }

    public boolean isHelpButtonHighlighted() {
        return helpButtonHighlighted;
    }

    public boolean isQuitButtonHighlighted() {
        return quitButtonHighlighted;
    }

    public void render(Graphics g) {
        Font titleFont = new Font("arial", Font.BOLD, 50);
        g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT + 80);
        g.setFont(titleFont);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Snake Clone", GamePanel.WIDTH / 2 - 150, 100);
        Font buttonFont = new Font("arial", Font.BOLD, 20);
        g.setFont(buttonFont);

        ButtonMaker.drawButton(150, 170, "Play", playButtonHighlighted, g);
        ButtonMaker.drawButton(150, 240, "Options", optionsButtonHighlighted, g);
        ButtonMaker.drawButton(150, 310, "Help", helpButtonHighlighted, g);
        ButtonMaker.drawButton(150, 380, "Quit", quitButtonHighlighted, g);
    }

    public void moveDownThroughMenu() {
        if (playButtonHighlighted) {
            playButtonHighlighted = false;
            optionsButtonHighlighted = true;
            helpButtonHighlighted = false;
            quitButtonHighlighted = false;
        } else if (optionsButtonHighlighted) {
            optionsButtonHighlighted = false;
            helpButtonHighlighted = true;
            quitButtonHighlighted = false;
        } else if (helpButtonHighlighted) {
            helpButtonHighlighted = false;
            quitButtonHighlighted = true;
        } else if (quitButtonHighlighted) {
            playButtonHighlighted = true;
            quitButtonHighlighted = false;
        }
    }

    public void moveUpThroughMenu() {
        if (playButtonHighlighted) {
            playButtonHighlighted = false;
            optionsButtonHighlighted = false;
            helpButtonHighlighted = false;
            quitButtonHighlighted = true;
        } else if (optionsButtonHighlighted) {
            playButtonHighlighted = true;
            optionsButtonHighlighted = false;
            helpButtonHighlighted = false;
            quitButtonHighlighted = false;
        } else if (helpButtonHighlighted){
            optionsButtonHighlighted = true;
            helpButtonHighlighted = false;
            quitButtonHighlighted = false;
        }else if (quitButtonHighlighted) {
            helpButtonHighlighted = true;
            quitButtonHighlighted = false;
        }
    }
}
