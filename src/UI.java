import java.awt.*;

public class UI {

    private final int HP_WIDTH = 20, HP_HEIGHT = 20;
    private final int HP_SPACING = 30;

    private Handler handler;

    public UI(Handler handler) {
        this.handler = handler;
    }

    public void render(Graphics g) {
        drawHealth(g);
    }

    private void drawHealth(Graphics g) {
        g.setColor(Color.RED);

        for(int i = 0; i < handler.getHP(); i++) {

            g.fillRect(40 + i * HP_SPACING, 20, HP_WIDTH, HP_HEIGHT);
        }
    }

    public void tick() {

    }



}
