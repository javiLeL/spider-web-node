package windows;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import inputs.Keyboard;
import inputs.Mouse;
import states.State;
import states.StateProfile;

/**
 * MainWindow
 */
public class MainWindow extends JFrame implements Runnable {
    public static int WIDTH = 1080, HEIGHT = 720;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private final int FPS = 60;
    private double TARGETTIME = 1000000000 / FPS;
    private double delta = 0;
    public int AVERAGEFPS = FPS;
    public int FRAMECOUNT = 0;
    private State state;
    // inputs
    Keyboard keyboard;
    Mouse mouse;

    public MainWindow() {
        setVisible(true);
        setTitle("Motor");
        setSize(new java.awt.Dimension(WIDTH + 10, HEIGHT + 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        // if (false) {
        // this.setExtendedState(Frame.MAXIMIZED_BOTH);
        // WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        // HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        // }
        keyboard = new Keyboard();
        mouse = new Mouse();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true);
        add(canvas);
        this.canvas.addKeyListener(keyboard);
        this.canvas.addMouseListener(mouse);
        this.canvas.addMouseMotionListener(mouse);
        this.canvas.addMouseWheelListener(mouse);
    }

    private void update() {
        FRAMECOUNT++;
        state.update();
    }

    private void draw() {
        if (bs == null) {
            canvas.createBufferStrategy(3);
            bs = canvas.getBufferStrategy();
        }
        g = bs.getDrawGraphics();
        state.draw(g);
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;
            time += (now - lastTime);
            lastTime = now;
            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
            }
            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
        }
        stop();
    }

    public void start() {
        state = new StateProfile();
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            System.out.println("ERROR TO CLOSE");
        }
    }
}