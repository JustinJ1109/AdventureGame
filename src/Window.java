import javax.swing.*;
import java.awt.*;

public class Window {

    private Game game;

    public Window(int w, int h, String title, Game game) {
        this.game = game;

        Dimension windowSize = new Dimension(w, h);

        JFrame frame = new JFrame(title);

        frame.add(game);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(windowSize);
        frame.setMaximumSize(windowSize);
        frame.setPreferredSize(windowSize);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.requestFocus();

        game.start();
    }

}
