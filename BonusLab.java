/* Name of Program: BonusLab.java
 * Purpose of Program: Design a SmileyFace GUI
 * Author of Program: B. Yacoob
 */

// Import of packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BonusLab extends JFrame {
	// Declaration of variables
	private JTextField timerTextField; // Giving text field to the timer event
	private JLabel timerLabel; // Label
	private JButton timerBtn; // Timer button
	private JPanel mainPanel, bottomPanel; // Panels
	private boolean wink = false;
	private Timer timer; // Use for time-tracking
	private final int WINDOW_WIDTH = 400; // Window width
	private final int WINDOW_HEIGHT = 400; // Window height

	// Constructor
	public BonusLab() {
		setTitle("Hello There!"); // Setting the title

		// Setting size of the window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// What happens when close button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Do nothing so the next pop-up screen of making sure
														// they want to exit is shown
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// Building the panel and adding it to the main frame
		buildPanel();
		add(mainPanel);

		// Visibility is shown for the window
		setVisible(true);
	}

	// What builds the panel with all its components
	private void buildPanel() {
		// Important part is to now build class and assign to panel to reference it
		bottomPanel = new JPanel();

		bottomPanel.setLayout(new GridLayout(1, 3));

		// Create label with instructions on it
		timerLabel = new JLabel("Time = ");

		// Create text field for the timer
		timerTextField = new JTextField(10); // Considering input is not long
		timerTextField.setText("" + 0);

		// Create button for the timer and an action listener to start timer and change
		// facial expression
		timerBtn = new JButton("Timer");
		timerBtn.addActionListener(new WinkAction());

		// Adding the text field and label components to the panel
		bottomPanel.add(timerLabel);
		bottomPanel.add(timerTextField);
		bottomPanel.add(timerBtn);

		// Adding the panel to the content pane
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	}

	public void paint(Graphics g) {
		// Call the superclass paint method
		super.paint(g);

		// Draw two blue filled rectangles
		g.setColor(Color.BLUE);
		g.fillRect(10, 40, 50, 50);

		g.setColor(Color.BLUE);
		g.fillRect(340, 40, 50, 50);

		// Draw two red filled circles
		g.setColor(Color.RED);
		g.fillOval(10, 315, 50, 50);

		g.setColor(Color.RED);
		g.fillOval(340, 315, 50, 50);

		// Drawing the face
		g.drawOval(100, 100, 200, 200);

		// Drawing the eyes
		g.setColor(Color.BLACK);
		g.fillOval(155, 160, 20, 10); // Right

		if (wink) { // When we want the wink to occur
			g.drawLine(230, 160, 250, 160);
		} else {
			g.setColor(Color.BLACK);
			g.fillOval(230, 160, 20, 10); // Left
		}

		// Drawing the mouth
		g.drawArc(150, 200, 100, 50, 180, 180);
	}

	// Action Listener for the wink when btn is pressed
	private class WinkAction implements ActionListener {
		// When action is performed
		public void actionPerformed(ActionEvent e) {
			// Button command
			if (e.getSource() == timerBtn) {
				// Create a new timer
				timer = new Timer();

				// Schedule a task to be executed after a 5-second delay
				timer.schedule(new TimerTask() {
					public void run() {
						try {
							// Loop forever so that the face keeps switching facial expressions
							while (true) {
								wink = !wink;
								repaint(); // Show the new facial expression

								// When text is re-entered, validate to make sure the code does not break
								if (!timerTextField.getText().isEmpty()) {
									Thread.sleep(Integer.parseInt(timerTextField.getText())); // Delay for 1 second
									cancel();	// Cancel current timer task
									new Timer();	// Start timer again
								}
							}
						} catch (InterruptedException e) {
							// Handle the exception
							System.out.print(e.getMessage());
						}
					}
				}, Integer.parseInt(timerTextField.getText()));
			}
		}
	}

	public static void main(String[] args) {
		BonusLab bonusLab = new BonusLab(); // Calling the class in main
		bonusLab.setVisible(true);
	}
}
