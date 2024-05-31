package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import inputs.Mouse;
import racoon.PVector;
import states.StateProfile;

/**
 * class Node
 * This class can make a node
 * 
 * @author JaviLeL
 * @version 1.0
 */
public class Node extends UI {
    private PVector loc;
    private Color col;
    private double size;
    private boolean pressed;
    private static boolean onepressed = false;

    public Node(double x, double y, double size) {
        this.loc = new PVector(x, y);
        this.col = new Color(255, 255, 255);
        this.size = size;
        this.pressed = false;
    }

    @Override
    public void update() {
        if (Mouse.mousePressed && Mouse.left) {
            if (loc.disct(new PVector(Mouse.x, Mouse.y)) <= size / 2 && (!onepressed || pressed)) {
                pressed = true;
                onepressed = true;
            }
            if (pressed) {
                loc = new PVector(Mouse.x, Mouse.y);
            }
        } else {
            pressed = false;
            onepressed = false;
        }
        if (Mouse.mousePressed && Mouse.center && loc.disct(new PVector(Mouse.x, Mouse.y)) <= size / 2) {
            StateProfile.nodes.remove(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(col);
        drawLines(g);
        g.fillOval(loc.x.intValue() - (int) size / 2, loc.y.intValue() - (int) size / 2, (int) size, (int) size);
    }

    public void drawLines(Graphics g) {
        List<Node> nodes = StateProfile.nodes;
        for (Node node : nodes) {
            if (node != this) {
                g.drawLine(loc.x.intValue(), loc.y.intValue(), node.loc.x.intValue(), node.loc.y.intValue());
            }
        }
    }
}
