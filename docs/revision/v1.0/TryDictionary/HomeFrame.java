package TryDictionary;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HomeFrame extends JFrame{

	
	// body components
	String searchPrompt = "Search";
	JTextField searchTextField;
	String searchText = "";
	JButton searchButton;
	String listPrompt = "List";
	JList keysList;
	boolean isTriggeredFromListView = false;
	String contentPrompt = "Meaning";
	JTextArea contentTextArea;
	int defaultTextFieldLength = 20;

	// menu & toolbar
	JMenuBar menuBar;
	JMenu fileMenu, editMenu, findMenu, helpMenu;
	JToolBar toolBar;

	// dictionary
	DictionaryHelper dictionaryBus;

	public HomeFrame(String title){
		super(title);
		
		//this.create();

	}

	public void setBus(DictionaryHelper bus){
		this.dictionaryBus = bus;
		this.create();
	}

	public void create(){
		// get main pane of frame
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		

        // create a menubar 
        menuBar = createMenuBar();
        // add menubar to frame 
        this.setJMenuBar(menuBar); 


        // create toolbar 
	    JToolBar toolBar = createToolBar();
        pane.add(toolBar, BorderLayout.PAGE_START);



        /// body
        // create search box
        JPanel searchPanel = createSeachPanel();
        searchPanel.setMaximumSize(searchPanel.getPreferredSize());

        // create list view
		JPanel listPanel = createListPanel();
		//listPanel.setMaximumSize(listPanel.getPreferredSize());

		// create section which displays content
		JPanel contentPanel = createContentPanel();
		

		// create wrapper panel in the left
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(searchPanel);
		leftPanel.add(listPanel);

		// create wrapper panel for whole body
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
		bodyPanel.add(leftPanel);
		bodyPanel.add(contentPanel);


		// add whole body panel to frame
		pane.add(bodyPanel, BorderLayout.CENTER);
	}


	private JPanel createSeachPanel(){
		JPanel searchPanel = new JPanel();

		searchText = "";
		searchTextField = new JTextField(defaultTextFieldLength);

		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
		    searchTextFieldChanged();
		  }
		  public void removeUpdate(DocumentEvent e) {
		    searchTextFieldChanged();
		  }
		  public void insertUpdate(DocumentEvent e) {
		    searchTextFieldChanged();
		  }

		  
		});


		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				searchButtonClicked();
			}
		});

		searchPanel.add(new JLabel(searchPrompt));
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);

		return searchPanel;
	}

	private JPanel createListPanel(){
		JPanel listPanel = new JPanel();

		String[] keys = getKeysFromDictionary();
		keysList = new JList(keys);//(new String[]{"1753102", "1753104", "1753116"});
		keysList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        //JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            //int index = keysList.locationToIndex(evt.getPoint());

		            // System.out.println(dictionaryBus.get(keysList.getSelectedIndex()));
		            mouseDoubleClickedOnListOfKeys();

		        } else if (evt.getClickCount() == 1) {

		            mouseClickedOnListOfKeys();
		        }
		    }
		});
		JScrollPane scrollPanel = new JScrollPane(keysList);

		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.add(new JLabel(listPrompt));
		listPanel.add(scrollPanel);

		return listPanel;
	}

	



	private JPanel createContentPanel(){
		JPanel contentPanel = new JPanel();

		contentTextArea = new JTextArea();
		contentTextArea.setLineWrap(true);
		contentTextArea.setEditable(false);
		JScrollPane scrollPanel2 = new JScrollPane(contentTextArea);
		scrollPanel2.setPreferredSize(new Dimension(200, 200));

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(new JLabel(contentPrompt));
		contentPanel.add(scrollPanel2);

		return contentPanel;
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
		        String s = e.getActionCommand(); 
		  
		        // set the label to the menuItem that is selected 
		        JOptionPane.showMessageDialog(null, s);
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
		        String s = e.getActionCommand(); 
		  
		        // set the label to the menuItem that is selected 
		        JOptionPane.showMessageDialog(null, s);
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
        m1 = new JMenuItem("Icon"); 
        m2 = new JMenuItem("Content"); 
  		m1.addActionListener(new ActionListener(){
  			@Override
  			public void actionPerformed(ActionEvent e) 
		    { 
		        String s = e.getActionCommand(); 
		  
		        // set the label to the menuItem that is selected 
		        JOptionPane.showMessageDialog(null, s);
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
		        String s = e.getActionCommand(); 
		  
		        // set the label to the menuItem that is selected 
		        JOptionPane.showMessageDialog(null, s);
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

	private JToolBar createToolBar(){
		JToolBar tb = new JToolBar(); 
  

        // create new buttons 
        JButton b1 = new JButton("Find"); 
        JButton b2 = new JButton("Add icon"); 
        JButton b3 = new JButton("Remove icon"); 
        JButton b4 = new JButton("Edit icon"); 
        JButton b5 = new JButton("Rename icon"); 
        JButton b6 = new JButton("History"); 
        JButton b7 = new JButton("Export icon"); 
        JButton b8 = new JButton("About"); 
  		b1.addActionListener(new ActionListener(){
  			@Override
  			public void actionPerformed(ActionEvent e) 
		    { 
		        // String s = e.getActionCommand(); 
		  
		        // // set the label to the menuItem that is selected 
		        // JOptionPane.showMessageDialog(null, s);
		        createNewWindow(e);
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

	

    private String[] getKeysFromDictionary(){
    	String[] result = null;

    	if(dictionaryBus != null){
    		java.util.List<String> list = dictionaryBus.getList();
    		result = list.toArray(new String[0]);
    	}
    	return result;
    }


    // event handlers
    private void createNewWindow(ActionEvent e){
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

    private void mouseClickedOnListOfKeys(){

		String key = keysList.getSelectedValue().toString();

		isTriggeredFromListView = true;
		searchTextField.setText(key);
		isTriggeredFromListView = false;
	}

	private void mouseDoubleClickedOnListOfKeys(){

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

	private void showRelatedKeysOnListOfKeys(){
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

    

    private void searchButtonClicked(){
    	//String key = searchTextField.getText();
    	String key = keysList.getSelectedValue().toString();
    	String meaning = dictionaryBus.search(key);
    	contentTextArea.setText(key + "\n\n" + meaning);
    }
    
}