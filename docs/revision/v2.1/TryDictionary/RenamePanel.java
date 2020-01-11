package TryDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RenamePanel extends OperationPanel{

	String headerString = "Rename a word";
	String keyLabelString = "Word";
	JTextField keyTextField;
	String valueLabelString = "New name";
	JTextField  valueTextArea;
	String submitString = "Rename";

	public RenamePanel(String renamedWord){
		// header
		this.headerLabel.setText(headerString);


		// body
		keyTextField = new JTextField(renamedWord, this.defaultTextFieldWidth);
		keyTextField.setEditable(false);

		valueTextArea = new JTextField();


		this.bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		this.bodyPanel.add(new JLabel(keyLabelString));
		this.bodyPanel.add(keyTextField);
		this.bodyPanel.add(new JLabel(valueLabelString));
		this.bodyPanel.add(valueTextArea);
		bodyPanel.setMaximumSize(bodyPanel.getPreferredSize());

		// tailor
		this.submitButton.setText(submitString);
	}

	public void submitButton_Clicked(ActionEvent e){
		//JOptionPane.showMessageDialog(null, "Added");
		submitClicked.invoke(new String[]{
			keyTextField.getText(),
			valueTextArea.getText()
		});
	}
}