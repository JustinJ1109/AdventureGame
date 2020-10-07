import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Adventure Game";

    private Typewriter tw;
    private Handler handler;
    private UI ui;

    private boolean running = false;
    private Thread thread;


    private static int tickCount = 0;
    private static int tickMax = 200;

    public void init() {
        tw = new Typewriter();
        handler = new Handler();

        addKeyListener(new KeyInput(tw, handler));
        ui = new UI(handler);

    }

    public synchronized void start() {

        if (running)
            return;


        init();
        running = true;
        thread = new Thread(this);

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


        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }


            tick();


        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        /////////////////////////////


        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        ui.render(g);

        tw.render(g);

        // render all letters in currtext

        /////////////////////////////
        g.dispose();
        bs.show();
    }

    public void tick() {

        if (tickCount >= tickMax) {
            tw.tick();
            tickCount = 0;
        }
        tickCount++;
    }

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, TITLE, new Game());

    }
}
