package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import inputs.Keyboard;
import inputs.Mouse;
import ui.Node;
import windows.MainWindow;

public class StateProfile extends State {
    public static List<Node> nodes;

    public StateProfile() {
        nodes = new ArrayList<>();
    }

    @Override
    public void update() {
        if (Mouse.mousePressed && Mouse.right) {
            nodes.add(new Node(Mouse.x, Mouse.y, 10));
            Mouse.mousePressed = false;
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).update();
        }
        if (Keyboard.key != null) {
            if (Keyboard.keyPressed && (Keyboard.key == 127 || Keyboard.key == 8)) {
                while (nodes.size() != 0) {
                    for (int i = 0; i < nodes.size(); i++) {
                        nodes.remove(i);
                    }
                }
                Keyboard.key = null;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(0, 0, MainWindow.WIDTH, MainWindow.HEIGHT);
        for (Node node : nodes) {
            node.draw(g);
        }
    }
}