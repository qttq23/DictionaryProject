package TryDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeletePanel extends OperationPanel{

	String headerString = "Delete a word";
	String keyLabelString = "Word";
	JTextField keyTextField;
	String submitString = "Delete";

	public DeletePanel(){
		// header
		this.headerLabel.setText(headerString);


		// body
		keyTextField = new JTextField(this.defaultTextFieldWidth);
		

		this.bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		this.bodyPanel.add(new JLabel(keyLabelString));
		this.bodyPanel.add(keyTextField);
		bodyPanel.setMaximumSize(bodyPanel.getPreferredSize());

		// tailor
		this.submitButton.setText(submitString);
	}

	public void submitButton_Clicked(ActionEvent e){
		JOptionPane.showMessageDialog(null, "Deleted");
	}
}