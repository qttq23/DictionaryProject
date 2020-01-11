package TryDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationPanel extends CustomPanel{

	JLabel headerLabel;
	String headerString;

	JPanel bodyPanel;
	
	JButton submitButton;
	String submitString = "Submit";

	JButton closeButton;
	String closeString = "Close";

	int defaultTextFieldWidth = 20;
	IButtonClickedEvent submitClicked;
	IButtonClickedEvent closeClicked;


	public OperationPanel(){
		// JOptionPane.showMessageDialog(null, "Operation constructor");

		headerLabel = new JLabel(headerString);
		bodyPanel = new JPanel();
		submitButton = new JButton(submitString);
		submitButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
				
					submitButton_Clicked(e);			
					}
		});

		closeButton = new JButton(closeString);
		closeButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
				
					closeButton_Clicked(e);			
					}
		});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(submitButton);
		buttonsPanel.add(closeButton);
		buttonsPanel.setMaximumSize(buttonsPanel.getPreferredSize());

		// add to main panel
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		this.add(headerLabel, BorderLayout.PAGE_START);
		this.add(bodyPanel, BorderLayout.CENTER);
		//this.add(submitButton);
		this.add(buttonsPanel, BorderLayout.PAGE_END);

		// headerLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		// bodyPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		// submitButton.setAlignmentX(JPanel.LEFT_ALIGNMENT);

	}

	public void submitButton_Clicked(ActionEvent e){
		submitClicked.invoke(new String[]{});
	}

	public void closeButton_Clicked(ActionEvent e){
		closeClicked.invoke(new String[]{});
	}
}