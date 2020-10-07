import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static int WIDTH = 800, HEIGHT = 600;
    private static String TITLE = "Adventure Game";
    private boolean running = false;
    private Typewriter tw;
    private Thread thread;

    public void init() {
        tw = new Typewriter(150, thread);
        addKeyListener(new KeyInput(tw));


    }

    public synchronized void start() {

        if(running)
            return;


        running = true;
        thread = new Thread(this);
        init();
        thread.start();

    }

    public synchronized void stop() {
        running = false;
    }
    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        /////////////////////////////

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (tw.getTextLength() > 0) {

            tw.writeText(g);

        }


        /////////////////////////////
        g.dispose();
        bs.show();
    }

    public void tick() {

    }

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, TITLE, new Game());

    }
}
