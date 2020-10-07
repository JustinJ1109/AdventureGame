import java.awt.*;
import java.awt.image.BufferStrategy;

public class Typewriter {

    private static int index;
    private static String curText;
    private static String text;
    private int sleepTime;
    private Thread thread;
    private boolean waitingForInput = false;

    private boolean running = false;

    private final int kern = 4;

    public Typewriter(int sleepTime, Thread thread) {
        this.sleepTime = sleepTime;
        this.thread = thread;
        curText = "";
        index = 0;
        text = "";
    }

    public Typewriter(int sleepTime, Thread thread, String text) {
        this.sleepTime = sleepTime;
        this.thread = thread;
        curText = "";
        index = 0;
        Typewriter.text = text;
    }

    public void setText(String text) {
        System.out.println("Setting text");
        Typewriter.text = text;
    }

    public void clearText() {
        curText = "";
        text = "";
        index = 0;
    }

    public void skipTypeAni() {
        curText = text;
    }

    public void writeText(Graphics g) {
        if (curText.length() < text.length()) {
            try {
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curText += text.charAt(index);

            g.setColor(Color.white);
            g.drawString(curText, 40, 40);

            index++;
        } else if (curText.length() == text.length()) {
            g.setColor(Color.white);
            g.drawString(curText, 40, 40);

            waitingForInput = true;
        }
    }

    public boolean isWaitingForInput() {
        return waitingForInput;
    }

    public void setWaitingForInput(boolean val) {
        waitingForInput = val;
    }

    public int getTextLength() {
        return text.length();
    }
}
