import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Typewriter tw;
    Handler handler;
    StoryTeller st;
    Game game;

    public KeyInput(Typewriter tw, Handler handler, Game game) {

        this.tw = tw;
        this.handler = handler;
        this.game = game;
        st = new StoryTeller();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            if (!tw.isTyping())
                tw.setText(st.getNextPara());
            else
                tw.skipTypeWriter();
        }

        if (key == KeyEvent.VK_F) {
            tw.clearText();
        }

        if (key == KeyEvent.VK_S) {
            //tw.wrapText();
        }

        if (key == KeyEvent.VK_A) {
            //tw.setText("Let's try one more. Just to see how it handles multiple inputs at once.");
        }

        if (key == KeyEvent.VK_Y) {
            handler.removeHealth(1);
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
