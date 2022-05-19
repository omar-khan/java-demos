 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;

 public class nu_exam_keyEventDemo extends JFrame {
 	private KeyboardPanel keyboardPanel;
 	
	/** Initialize UI */
	public nu_exam_keyEventDemo() {
		setSize(200,100);
		keyboardPanel = new KeyboardPanel(getWidth(), getHeight());
		add(keyboardPanel);
		setLocationRelativeTo(null); // Center the frame
 		keyboardPanel.setFocusable(true);
	}
 
 	/** Main method */
 	public static void main(String[] args) {
 		nu_exam_keyEventDemo frame = new nu_exam_keyEventDemo();
 		frame.setTitle("AP Exam");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
 
 	// Inner class: KeyboardPanel for receiving key input
 	static class KeyboardPanel extends JPanel {
 		private int x;
 		private int y;
 		private String keyChar = ""; // Default key
 		
		public KeyboardPanel(int a, int b) {
			x = a/2;
			y = b/2; 
			addKeyListener(new KeyAdapter() {
 				public void keyPressed(KeyEvent e) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_DOWN: y += 10; break;
						case KeyEvent.VK_UP: y -= 10; break;
						case KeyEvent.VK_LEFT: x -= 10; break;
						case KeyEvent.VK_RIGHT: x += 10; break;
						case KeyEvent.VK_BACK_SPACE: keyChar = keyChar.substring(0, keyChar.length()-1); break;
						case KeyEvent.VK_SHIFT: 
						case KeyEvent.VK_ALT:		break;
						default: keyChar += e.getKeyChar();
 					}
 					repaint();
 				}
 			} );
 		}

		public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
			Font f1 = new Font("TimesRoman", Font.BOLD, 18);
 			g.setFont(f1);
			FontMetrics m = g.getFontMetrics(f1);
    		if(m.stringWidth(text) < lineWidth) {
        		g.drawString(text, x, y);
    		} else {
    		    String[] words = text.split(" ");
    		    String currentLine = words[0];
    		    for(int i = 1; i < words.length; i++) {
    		        if(m.stringWidth(currentLine+words[i]) < lineWidth) {
    		            currentLine += " "+words[i];
    		        } else {
    		            g.drawString(currentLine, x, y);
        		        y += m.getHeight();
            		    currentLine = words[i];
            		}
        		}
        		if(currentLine.trim().length() > 0) {
        		    g.drawString(currentLine, x, y);
        		}
    		}
		}


 		/** Draw the character */
 		protected void paintComponent(Graphics g) {
 			super.paintComponent(g);
			drawStringMultiLine(g, keyChar, this.getWidth(), x, y);
			Font f1 = new Font("TimesRoman", Font.BOLD, 18);
 			g.setFont(f1);
			FontMetrics fm = g.getFontMetrics(f1);
			int fontWidth  = fm.stringWidth(keyChar);
			int fontHeight = fm.getHeight();

			if (fontWidth < this.getWidth()) 
				g.drawString(keyChar, x, y);
			else {
				String currentLine = keyChar.substring(0, 1);
				for (int i = 1; i < keyChar.length()-1; i++) {
					if (fm.stringWidth(currentLine + keyChar.substring(i, i+1)) < this.getWidth()) {
						currentLine += keyChar.substring(i,i+1);
					}
					else {
						g.drawString(currentLine, x, y);
						y += fontHeight;
						currentLine = keyChar.substring(i,i+1);
					}
				}
				if (currentLine.trim().length() > 0) {
					g.drawString(currentLine, x, y);
				}
			}
 		}
 	}
 }

