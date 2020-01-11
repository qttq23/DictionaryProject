package TryDictionary;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

	public class HomeFrame extends DictionaryFrame<String, String>{//extends JFrame{

		public HomeFrame(String title, IDictionaryHelper<String, String> bus){
				// title of program
				//super(title);
				this.setTitle(title);
				this.dictionaryBus = bus;
				this.update();
		}

		

		public void update(){
			// update listview
			keysList.setListData(getKeysFromDictionary());

		}


		
		// bus
	    private String[] getKeysFromDictionary(){
	    	String[] result = null;

	    	if(dictionaryBus == null){
	    		result = new String[]{""};
	    	}
	    	else{//if(dictionaryBus != null){
	    		java.util.List<String> list = dictionaryBus.getList();
	    		result = list.toArray(new String[0]);
	    	}
	    	return result;
	    }


	    // event handlers
	    public void enterKeyPressed(){
			searchButton.doClick();
		}


	    public void createNewWindow(ActionEvent e){
			// create a dialog Box 
			
	        JDialog d = new JDialog(this, "Add", Dialog.ModalityType.DOCUMENT_MODAL); 

	        OperationPanel addPanel = new AddPanel();
	        addPanel.submitClicked = (String[] list)->{
									JOptionPane.showMessageDialog(null, list);
								};
			addPanel.closeClicked = (String[] list)->{
									d.dispose();
								};

	       	d.add(addPanel);
	        // setsize of dialog 
	        d.pack();
	        //d.setSize(100, 100); 
	        // set visibility of dialog 
	        d.setVisible(true);
	        d.setAlwaysOnTop(true);
		}

		public void actionPerformed(ActionEvent e) 
	    { 
	        String s = e.getActionCommand(); 
	  
	        // set the label to the menuItem that is selected 
	        JOptionPane.showMessageDialog(null, s);
	    }



	    public void mouseClickedOnListOfKeys(){

			String key = keysList.getSelectedValue().toString();

			isTriggeredFromListView = true;
			searchTextField.setText(key);
			isTriggeredFromListView = false;
		}

		public void mouseDoubleClickedOnListOfKeys(){

			String key = keysList.getSelectedValue().toString();
			String meaning = dictionaryBus.search(key);

			isTriggeredFromListView = true;
			searchTextField.setText(key);
			contentTextArea.setText(key + "\n\n" + meaning);
			isTriggeredFromListView = false;
		}

		public void searchTextFieldChanged() {
			// update buffer
			searchText = searchTextField.getText();
			System.out.println(searchText);

			// scroll listview to selected item
			if(isTriggeredFromListView == false){
				showRelatedKeysOnListOfKeys();
			}
		}

		public void showRelatedKeysOnListOfKeys(){
			// find index of related key
			int index = -1;
	        if(searchText == null || searchText.length() == 0){
	        	index = 0;
	        }
	        else{
	        	index = dictionaryBus.indexOfWordStartWith(searchText);

	    	}

	    	// show in listview
	        keysList.setSelectedIndex(index);
	        keysList.ensureIndexIsVisible(keysList.getSelectedIndex());
		}

	    public void searchButtonClicked(){
	    	//String key = searchTextField.getText();
	    	String key = keysList.getSelectedValue().toString();
	    	String meaning = dictionaryBus.search(key);
	    	contentTextArea.setText(key + "\n\n" + meaning);
	    }
    
}