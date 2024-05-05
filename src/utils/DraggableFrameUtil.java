package utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class DraggableFrameUtil {

	public static void makeDraggable(JFrame frame) {
		final int[] pos = new int[2]; // Array to store posX and posY

		// Add a mouse listener to the frame
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Get the current position of the mouse
				pos[0] = e.getX();
				pos[1] = e.getY();
			}
		});

		// Add a mouse motion listener to the frame
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				// Get the new position of the mouse
				int newX = e.getXOnScreen() - pos[0];
				int newY = e.getYOnScreen() - pos[1];
				frame.setLocation(newX, newY); // Update the frame's position
			}
		});
	}
}
