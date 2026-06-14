package studentdb;

import java.applet.Applet;
import java.awt.Graphics;

public class StudentApplet extends Applet {

    public void paint(Graphics g) {

        g.drawString(
                "Student Database Report System",
                50,
                50);

        g.drawString(
                "Applet Demonstration",
                50,
                80);

        g.drawString(
                "File Handling + Swing Project",
                50,
                110);
    }
}