import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMessageGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;

	public String Encode(String message, int k) {
		String out = " ";
		char key = (char) k;

		for (int i = 0; i < message.length(); i++) {
			char in = message.charAt(i);
			if (in >= 'A' && in <= 'Z') {
				in += key;
				if (in < 'A')
					in += 26;
				else if (in > 'Z')
					in -= 26;
			} else if (in >= 'a' && in <= 'z') {
				in += key;
				if (in < 'a')
					in += 26;
				else if (in > 'z')
					in -= 26;
			} else if (in >= '0' && in <= '9') {
				in += (k%10);
				if (in < '0')
					in += 10;
				if (in > '9')
					in -= 10;
			}
			out += in;
		}
		return out;
	}

	public SecretMessageGUI() {
		setLayout(null);

		txtIn = new JTextArea();
		txtIn.setBounds(10, 11, 430, 87);
		add(txtIn);

		JLabel lblKey = new JLabel("key:");
		lblKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKey.setBounds(194, 124, 50, 31);
		add(lblKey);

		txtKey = new JTextField();
		txtKey.setText("0");
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setBounds(254, 123, 44, 32);
		add(txtKey);
		txtKey.setColumns(10);

		JButton btnEncodedecode = new JButton("Encode/decode");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the message from txtIn
				String message = txtIn.getText();

				// get the key from txtKey
				String kText = txtKey.getText();
				int k = Integer.parseInt(kText);

				// encode/decode the message
				String output = Encode(message, k);

				// output message in txtOut
				txtOut.setText(output);
			}
		});
		btnEncodedecode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEncodedecode.setBounds(310, 124, 130, 31);
		add(btnEncodedecode);

		txtOut = new JTextArea();
		txtOut.setBounds(10, 175, 430, 87);
		add(txtOut);
		setPreferredSize(new Dimension(450, 320));

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtKey.setText("" + slider.getValue());
			}
		});
		slider.setValue(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setBounds(20, 124, 170, 45);
		add(slider);
	}

	public static void main(String[] args) {
		// set up a window JFrame fo the app
		JFrame frame = new JFrame("Rebeka's Secret message App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// add the encoder panel to the frame
		frame.getContentPane().add(new SecretMessageGUI());
		// prepare and show the frame
		frame.pack();
		frame.setVisible(true);
	}
}
