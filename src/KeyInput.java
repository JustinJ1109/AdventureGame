import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Typewriter tw;

    public KeyInput(Typewriter tw) {

        this.tw = tw;

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            System.out.println("Space Pressed!");
            tw.setText("This is sample text");
        }

        if (key == KeyEvent.VK_F) {
            tw.setWaitingForInput(false);
            tw.clearText();
        }

        if (key == KeyEvent.VK_S) {
            tw.setText("A Second paragraph here, for testing and such. This one is a bit longer.");
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
