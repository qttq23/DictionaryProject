package TryDictionary;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.imageio.*;

	public class HomeFrame extends DictionaryFrame<String, String>{//extends JFrame{

		// menu & toolbar
		JMenuBar menuBar;
		JMenu fileMenu, editMenu, findMenu, helpMenu;
		JToolBar toolBar;

		boolean isHistory = false;

		String infoFile = "info.txt";
		String aboutString = "";

		String dictionaryiconSource = "./icons/dictionary.jpg";
		public HomeFrame(String title, IDictionaryHelper<String, String> bus){
				// title of program
				//super(title);
				this.setTitle(title);
				this.dictionaryBus = bus;
				this.update();

				this.aboutString = IOHelper.readStringFile(infoFile);

				this.setIconImage(createIcon(dictionaryiconSource, 50, 50).getImage());
		}

		

		public void update(){
			// update listview
			keysList.setListData(getKeysFromDictionary());
			keysList.setSelectedIndex(0);
			keysList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


			// create a menubar 
	        menuBar = createMenuBar();
	    

	        // create toolbar 
		    toolBar = createToolBar();


	       	// add to frame
	       	Container pane = this.getContentPane();
		    // add menubar to frame 
	        this.setJMenuBar(menuBar); 
	        // add toolbar
	        pane.add(toolBar, BorderLayout.PAGE_START);


	        // set focus on text input
	        // things done after initialize window
			this.addWindowListener( new WindowAdapter() {
			    public void windowOpened( WindowEvent e ){

			    	// focus on text input
			        searchTextField.requestFocusInWindow();
			    }
			});

			// resize frame
			this.pack();
			this.setVisible(true);
		}

		public void saveAllWords(){
			dictionaryBus.saveDataFromDictionary();
			JOptionPane.showMessageDialog(null, "Saved all");
		}

		private JMenuBar createMenuBar(){
			JMenuBar menuBar = new JMenuBar(); 
	  
	        // create a menu 
	        fileMenu = new JMenu("File"); 

	        // create menuitems 
	        JMenuItem m1 = new JMenuItem("History"); 
	        JMenuItem m2 = new JMenuItem("Save all"); 
	        JMenuItem m3 = new JMenuItem("Exit"); 
	  		m1.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			    	isHistory = true;
			        changeToHistoryMode();
			    }
	  		});
	  		m2.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        saveAllWords();
			    }
	  		});
	  		m3.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			    	// exit window
			    	exitFrame();
			  
			    }
	  		});
	  	
	        // add menu items to menu 
	        fileMenu.add(m1); 
	        fileMenu.add(m2); 
	        fileMenu.add(m3);


	        editMenu = new JMenu("Edit"); 

	        // create menuitems 
	        m1 = new JMenuItem("Add icon"); 
	        m2 = new JMenuItem("Remove icon"); 
	        m3 = new JMenuItem("Edit icon"); 
	        JMenuItem m4 = new JMenuItem("Rename icon"); 
	        JMenuItem m5 = new JMenuItem("Export icon"); 
	  		m1.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        createAddWindow(e);
			    }
	  		});
	  		m2.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        createRemoveWindow(e);
			    }
	  		});
	  		m3.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        createEditWindow(e);
			    }
	  		});
	  		m4.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        createRenameWindow(e);
			    }
	  		});
	  		m5.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        createExportWindow(e);
			    }
	  		});
	  		
	        // add menu items to menu 
	        editMenu.add(m1); 
	        editMenu.add(m2); 
	        editMenu.add(m3);
	        editMenu.add(m4);
	        editMenu.add(m5);


	        findMenu = new JMenu("Find"); 
	  
	        // create menuitems 
	        m1 = new JMenuItem("Word"); 
	        m2 = new JMenuItem("Meaning"); 
	  		m1.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        isHistory = false;
			        changeToFindMode();
			    }
	  		});
	  		m2.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 

			        JOptionPane.showMessageDialog(null, "This option is not available now.");
			    }
	  		});
	  		
	        // add menu items to menu 
	        findMenu.add(m1); 
	        findMenu.add(m2); 


	        helpMenu = new JMenu("Help"); 
	  
	        // create menuitems 
	        m1 = new JMenuItem("About"); 
	  		m1.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        createAboutWindow(e);
			    }
	  		});
	  		
	        // add menu items to menu 
	        helpMenu.add(m1); 


	  
	        // add menu to menu bar 
	        menuBar.add(fileMenu); 
	        menuBar.add(editMenu); 
	        menuBar.add(findMenu); 
	        menuBar.add(helpMenu); 

	        return menuBar;
		}
		private void exitFrame(){
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}

		private ImageIcon createIcon(String source, int width, int height){
			ImageIcon addIcon = new ImageIcon(source);
	  		Image img = addIcon.getImage();  
		    Image resizedImage = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);  
		    return new ImageIcon(resizedImage);
		}
		private JToolBar createToolBar(){
			JToolBar tb = new JToolBar(); 
	  
	  		// create icons
	  		// Icon addIcon = new ImageIcon("./icons/plusicon.jpg");

			int width = 20;
			int height = 20;

	  		ImageIcon findicon = createIcon("./icons/findicon.png", width, height);
	  		ImageIcon plusicon = createIcon("./icons/plusicon.jpg", width, height);
	  		ImageIcon removeicon = createIcon("./icons/removeicon.png", width, height);
	  		ImageIcon editicon = createIcon("./icons/editicon.png", width, height);
	  		ImageIcon renameicon = createIcon("./icons/renameicon.jpg", width, height);
	  		ImageIcon historyicon = createIcon("./icons/historyicon.png", width, height);
	  		ImageIcon exporticon = createIcon("./icons/exporticon.png", width, height);
	  		ImageIcon abouticon = createIcon("./icons/abouticon.png", width, height);




	        // create new buttons 
	        JButton b1 = new JButton(findicon); 
	        JButton b2 = new JButton(plusicon);//("Add icon");
	        //b2.setPreferredSize(new Dimension(20,20));

	        JButton b3 = new JButton(removeicon); 
	        JButton b4 = new JButton(editicon); 
	        JButton b5 = new JButton(renameicon); 
	        JButton b6 = new JButton(historyicon); 
	        JButton b7 = new JButton(exporticon); 
	        JButton b8 = new JButton(abouticon); 


	        b1.setToolTipText("Find by word");
	        b2.setToolTipText("Add new word");
	        b3.setToolTipText("Remove word");
	        b4.setToolTipText("Edit meaning");
	        b5.setToolTipText("Rename word");
	        b6.setToolTipText("See history");
	        b7.setToolTipText("Export word");
	        b8.setToolTipText("About this software");

	        b1.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        // String s = e.getActionCommand(); 
			  
			        // // set the label to the menuItem that is selected 
			        // JOptionPane.showMessageDialog(null, s);
			        if(isHistory == true){
			        	isHistory = false;
			        	changeToFindMode();
			        }

			    }
	  		});
	  		b2.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        // String s = e.getActionCommand(); 
			  
			        // // set the label to the menuItem that is selected 
			        // JOptionPane.showMessageDialog(null, s);
			        createAddWindow(e);
			    }
	  		});
	  		b3.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        // String s = e.getActionCommand(); 
			  
			        // // set the label to the menuItem that is selected 
			        // JOptionPane.showMessageDialog(null, s);
			        createRemoveWindow(e);
			    }
	  		});
	  		b4.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        // String s = e.getActionCommand(); 
			  
			        // // set the label to the menuItem that is selected 
			        // JOptionPane.showMessageDialog(null, s);
			        createEditWindow(e);
			    }
	  		});
	  		b5.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        // String s = e.getActionCommand(); 
			  
			        // // set the label to the menuItem that is selected 
			        // JOptionPane.showMessageDialog(null, s);
			        createRenameWindow(e);
			    }
	  		});
	  		b6.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 
			        if(isHistory == false){
			        	isHistory = true;
			        	changeToHistoryMode();
			        }
			        else{
			        	isHistory = false;
			        	changeToFindMode();
			        }

			    }
	  		});

	  		b7.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 

			        createExportWindow(e);
			    }
	  		});
	  		b8.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
			    { 

			        createAboutWindow(e);
			    }
	  		});



	        tb.add(b1); 
	        tb.add(b2); 
	        tb.add(b3); 
	        tb.add(b4); 
	        tb.add(b5); 
	        tb.add(b6); 
	        tb.add(b7); 
	        tb.add(b8); 

	        return tb;

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
	    private String[] getKeysFromHistory(){
	    	String[] result = null;

	    	if(dictionaryBus == null){
	    		result = new String[]{""};
	    	}
	    	else{//if(dictionaryBus != null){
	    		java.util.List<String> list = dictionaryBus.history();
	    		result = list.toArray(new String[0]);
	    	}
	    	return result;
	    }


	    // event handlers
	    public void enterKeyPressed(){
			searchButton.doClick();
		}

		public void changeToFindMode(){
			// create a dialog Box 
	        keysList.setListData(getKeysFromDictionary());
			keysList.setSelectedIndex(0);

			listLabel.setText("List");
			searchTextField.setEditable(true);
		}

		public void changeToHistoryMode(){
			keysList.setListData(getKeysFromHistory());
				keysList.setSelectedIndex(0);
				
				searchTextField.setEditable(false);
				listLabel.setText("History");
		}

	    public void createAddWindow(ActionEvent e){
			// create a dialog Box 
			
	        JDialog d = new JDialog(this, "Add", Dialog.ModalityType.DOCUMENT_MODAL); 

	        OperationPanel addPanel = new AddPanel();
	        addPanel.submitClicked = (String[] list)->{
									// JOptionPane.showMessageDialog(null, list);

									int result = dictionaryBus.add(list[0], list[1]);
									if(result == 0){
										System.out.println("---> added new word");
										JOptionPane.showMessageDialog(d, "'" + list[0] + "'" + " has been added!");

										// reload list of keys
										keysList.setListData(getKeysFromDictionary());
									}
									else{
										System.out.println("---> Failed adding.");
										JOptionPane.showMessageDialog(d, "Failed. Word maybe already exists.",
																		"Error", JOptionPane.ERROR_MESSAGE);
									}
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

		public void createRemoveWindow(ActionEvent e){
			// create a dialog Box 
			
	        //JDialog d = new JDialog(this, "Remove", Dialog.ModalityType.DOCUMENT_MODAL); 

	        String key = (String)keysList.getSelectedValue();
            int result = JOptionPane.showConfirmDialog(this ,"Are you sure to remove '" + key + "'?", "Remove",
            											JOptionPane.WARNING_MESSAGE);  
			if(result == JOptionPane.YES_OPTION){  
				// remove
				result = dictionaryBus.remove(key);
				if(result == 0){
					System.out.println("---> Removed "  +  key);
			    	JOptionPane.showMessageDialog(null, "'" + key + "'" + " has been removed!");
			    	// update list
			    	keysList.setListData(getKeysFromDictionary());
			    	keysList.setSelectedIndex(0);
			    }
			    else{
			    	System.out.println("---> Removed failed");
			    	JOptionPane.showMessageDialog(null, "Failed to remove" +"'" + key + "'");
			    }
			}  
		}

		public void createEditWindow(ActionEvent e){
			// create a dialog Box 
			
	        JDialog d = new JDialog(this, "Edit", Dialog.ModalityType.DOCUMENT_MODAL); 

	        String key = (String)keysList.getSelectedValue();
	        OperationPanel panel = new EditPanel(key);
	        panel.submitClicked = (String[] list)->{
									// JOptionPane.showMessageDialog(null, list);

									int result = dictionaryBus.edit(list[0], list[1]);
									if(result == 0){
										System.out.println("---> Meaning of '" + list[0] + "' has been edited.");
										JOptionPane.showMessageDialog(d, "Meaning of '" + list[0] + "' has been edited.");

									}
									else{
										System.out.println("---> Failed editing.");
										JOptionPane.showMessageDialog(d, "Failed. Word maybe not exists.",
																		"Error", JOptionPane.ERROR_MESSAGE);
									}
								};
			panel.closeClicked = (String[] list)->{
									d.dispose();
								};

	       	d.add(panel);
	        // setsize of dialog 
	        d.pack();
	        //d.setSize(100, 100); 
	        // set visibility of dialog 
	        d.setVisible(true);
	        d.setAlwaysOnTop(true);
		}

		public void createRenameWindow(ActionEvent e){
			// create a dialog Box 
			
	        JDialog d = new JDialog(this, "Rename", Dialog.ModalityType.DOCUMENT_MODAL); 

	        String key = (String)keysList.getSelectedValue();
	        OperationPanel addPanel = new RenamePanel(key);
	        addPanel.submitClicked = (String[] list)->{
									// JOptionPane.showMessageDialog(null, list);

									int result = dictionaryBus.rename(list[0], list[1]);
									if(result == 0){
										System.out.println("---> renamed.");
										JOptionPane.showMessageDialog(d, "'" + list[0] + "'" + " has been renamed to '" 
																			+ list[1] + "'");

										// reload list of keys
										keysList.setListData(getKeysFromDictionary());
									}
									else{
										System.out.println("---> Failed renaming.");
										JOptionPane.showMessageDialog(d, "Failed. Word maybe not exists.",
																		"Error", JOptionPane.ERROR_MESSAGE);
									}
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




		public void createExportWindow(ActionEvent e){
			// create a dialog Box 
	        String key = (String)keysList.getSelectedValue();

			int result = dictionaryBus.export(key);
			if(result == 0){
				System.out.println("---> Exported '" + key + "' to '" 
        							+ dictionaryBus.getOutputFileName() + "'");
		    	JOptionPane.showMessageDialog(null, "Exported '" + key + "' to '" 
        							+ dictionaryBus.getOutputFileName() + "'");

		    }
		    else{
		    	System.out.println("---> Exported failed");
		    	JOptionPane.showMessageDialog(null, "Failed to export");
		    } 
		}

		public void createAboutWindow(ActionEvent e){
			// create a dialog Box 
			ImageIcon icon = createIcon(dictionaryiconSource, 300, 300);
			JOptionPane.showMessageDialog(null, aboutString, "About", JOptionPane.INFORMATION_MESSAGE, icon);
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
	    	String text = searchTextField.getText();
	    	if(text != null & text.length() != 0){
	    		String key = keysList.getSelectedValue().toString();
		    	String meaning = dictionaryBus.search(key);
		    	contentTextArea.setText(key + "\n\n" + meaning);
	    	}
	    	else{
	    		// exception
	    	}
	    }
    
}