package TryDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditPanel extends OperationPanel{

	String headerString = "Edit meaning of a word";
	String keyLabelString = "Word";
	JTextField keyTextField;
	String valueLabelString = "Meaning";
	JTextArea  valueTextArea;
	String submitString = "Edit";

	public EditPanel(String editedKey, String value){
		// header
		this.headerLabel.setText(headerString);


		// body
		keyTextField = new JTextField(editedKey, this.defaultTextFieldWidth);
		keyTextField.setEditable(false);

		valueTextArea = new JTextArea(value);
		valueTextArea.setLineWrap(true);
      	valueTextArea.setWrapStyleWord(true);

		JScrollPane jScrollPane = new JScrollPane(valueTextArea);
		jScrollPane.getViewport().setPreferredSize(new Dimension(200, 200));

		this.bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		this.bodyPanel.add(new JLabel(keyLabelString));
		this.bodyPanel.add(keyTextField);
		this.bodyPanel.add(new JLabel(valueLabelString));
		this.bodyPanel.add(jScrollPane);
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