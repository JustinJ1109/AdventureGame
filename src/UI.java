import java.awt.*;

public class UI {

    private Game game;

    private final int HP_WIDTH = 20, HP_HEIGHT = 20;
    private final int HP_SPACING = 30;

    private final int textBoxX;
    private final int textBoxY;
    private final int textBoxWidth;
    private final int textBoxHeight;

    private Handler handler;

    public UI(Handler handler, Game game) {

        this.handler = handler;
        this.game = game;

        textBoxX = game.getWidth() / 4; // LEFT BOUND.
        textBoxY = game.getHeight() * 4 / 5; // TOP BOUND
        textBoxWidth = game.getWidth() / 2; // RIGHT BOUND
        textBoxHeight = game.getHeight() * 3 / 8 - 150; // BOTTOM BOUND
    }

    public int getTextBoxX() {
        return textBoxX;
    }

    public int getTextBoxY() {
        return textBoxY;
    }

    public int getTextBoxWidth() {
        return textBoxWidth;
    }

    public int getTextBoxHeight() {
        return textBoxHeight;
    }

    public void render(Graphics g) {

        drawHealth(g);
        drawTextBox(g);
    }

    private void drawHealth(Graphics g) {
        g.setColor(Color.RED);

        for(int i = 0; i < handler.getHP(); i++) {

            g.fillRect(40 + i * HP_SPACING, 20, HP_WIDTH, HP_HEIGHT);
        }
    }

    private void drawTextBox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(textBoxX, textBoxY, textBoxWidth, textBoxHeight);
    }

    public void tick() {

    }



}
