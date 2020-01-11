package TryDictionary;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public abstract class DictionaryFrame<KeyT, ValT> extends JFrame{
	


		// body components
		String searchPrompt = "Search";
		JTextField searchTextField;
		String searchText = "";
		JButton searchButton;
		String listPrompt = "List";
		JLabel listLabel;
		JList keysList;
		boolean isTriggeredFromListView = false;
		String contentPrompt = "Meaning";
		JTextArea contentTextArea;
		int defaultTextFieldLength = 20;


		// listener
		KeyListener listener;

		


		public DictionaryFrame(){
			
			create();
		}

		public void create(){
			// CREATE COMPONENTS

			// create key listener
			listener = new KeyListener(){
				public void keyPressed(KeyEvent e) {}

				public void keyReleased(KeyEvent e) {

				    int keyCode = e.getKeyCode();
				    if(keyCode == KeyEvent.VK_ENTER){
				    	System.out.println("keyRealsed-------------ENTER----------");
				    	enterKeyPressed();
				    }

				}

				public void keyTyped(KeyEvent e) {}
			};

	        

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


			// ADD COMPONENTS TO FRAME
			// get main pane of frame
			Container pane = this.getContentPane();
			pane.setLayout(new BorderLayout());
			
			// add whole body panel to frame
			pane.add(bodyPanel, BorderLayout.CENTER);

			// set close operation, size, visible
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.pack();

			this.setVisible(false);
			
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

			searchTextField.addKeyListener(listener);


			searchButton = new JButton("Search");
			searchButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					searchButtonClicked();
				}
			});
			searchButton.addKeyListener(listener);

			searchPanel.add(new JLabel(searchPrompt));
			searchPanel.add(searchTextField);
			searchPanel.add(searchButton);

			return searchPanel;
		}

		private JPanel createListPanel(){
			JPanel listPanel = new JPanel();

			String[] keys = new String[0];
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
			keysList.addKeyListener(listener);
			JScrollPane scrollPanel = new JScrollPane(keysList);

			// label
			listLabel = new JLabel(listPrompt);

			// add to
			listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
			listPanel.add(listLabel);
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

		


    	///// belows are needed to be implemented


    	 // event handlers
	    public abstract void enterKeyPressed();

	    public abstract void mouseClickedOnListOfKeys();

		public abstract void mouseDoubleClickedOnListOfKeys();

		public abstract void searchTextFieldChanged();

		public abstract void showRelatedKeysOnListOfKeys();


	    public abstract void searchButtonClicked();


	    // dictionary
		IDictionaryHelper<KeyT, ValT> dictionaryBus;
		public abstract void update();
}