/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		JLabel name = new JLabel("Name");
		add(name, NORTH);
		
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		
		add = new JButton("Add");
		add(add, NORTH);
		
		delete = new JButton("Delete");
		add(delete, NORTH);
		
		lookup = new JButton("Lookup");
		add(lookup, NORTH);
		
		statusField = new JTextField(TEXT_FIELD_SIZE);
		add(statusField, WEST);
		
		status = new JButton("Change Status");
		add(status, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		add(pictureField, WEST);
		
		picture = new JButton("Change Picture");
		add(picture, WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		addFriendField = new JTextField(TEXT_FIELD_SIZE);
		add(addFriendField, WEST);
		
		addFriend = new JButton("Add Friend");
		add(addFriend, WEST);
		
		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		addFriendField.addActionListener(this);
		
		addActionListeners();
		/* initialize database */
		database = new FacePamphletDatabase();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
    	
    	if (source == add) {
    		addProfile(nameField.getText());
    	} else if (source == delete) {
    		deleteProfile(nameField.getText());
    	} else if (source == lookup) {
    		lookupProfile(nameField.getText());
    	} else if (source == status || source == statusField) {
    		changeStatus(statusField.getText());
    	} else if (source == picture || source == pictureField) {
    		changePicture(pictureField.getText());
    	} else if (source == addFriend || source == addFriendField) {
    		addFriend(addFriendField.getText());
    	}
    	
	}
    
    private void addProfile (String name) {
    	if (!name.equals("")) {
    		
    		/* check if database contains profile of that name*/
    		if (!database.containsProfile(name)) {
    			
    			/* creating new profile object of specified name */
        		FacePamphletProfile profile = new FacePamphletProfile(name);
        		currentProfile = profile;
        		
        		/* add to the hashmap in the database */
        		database.addProfile(profile);
        		
        		/* display profile */
        		canvas.displayProfile(profile);
        		
        		// println("Add " + name);
        		println(profile.toString());
        		
    		} else {
    			println("Profile of name already exists");
    		}
    	}
    }
    
    private void deleteProfile (String name) {
    	if (!name.equals("")) {
    		
    		if (database.containsProfile(name) ) {
    			println("Delete " + name);
        		database.deleteProfile(name);
        		currentProfile = null;
    		} else {
    			println("This profile doesn't exist");
    			
    			String msg = "This profile doesn't exist";
    			
				/* show application message */
				canvas.showMessage(msg);
    		}
    		
    	}
    }
    
    private void lookupProfile(String name) {
    	if (!name.equals("")) {
    		if (database.containsProfile(name)) {
        		println("Lookup: " + name);
        		
        		currentProfile = database.getProfile(name);

        		/* display profile */
        		canvas.displayProfile(currentProfile);
        		
        		String msg = (currentProfile.toString());
        		
				/* show application message */
				canvas.showMessage(msg);
        		
    		} else {
    			println("Profile does't exist");
    			
    			String msg = "Profile doesn't exist";
				/* show application message */
				canvas.showMessage(msg);
    			currentProfile = null;
    		}
    	}
    }
    
    private void changeStatus(String newStatus) {
    	if (!newStatus.equals("")) {
    		
    		if (currentProfile != null) {
    			println("Change " + currentProfile.getName() + " status to " + newStatus);
        		
        		currentProfile.setStatus(newStatus);
        		
        		/* display profile */
        		canvas.displayProfile(currentProfile);
        		
        		String msg = (currentProfile.toString());
        		
				/* show application message */
				canvas.showMessage(msg);
    		} else {
    			String msg = "No profile selected.";
				/* show application message */
				canvas.showMessage(msg);
    		}
    		
    	}
    }
    
    private void changePicture(String filename) {
    	if (!filename.equals("")) {
    		GImage pic = null;
    		if (currentProfile != null) {
    			
    			try {
    				
    				
            		pic = new GImage(filename);
            		currentProfile.setImage(pic);
            		/* display profile */
            		canvas.displayProfile(currentProfile);
            		
            		/* show application message */
            		String msg = "Change " + currentProfile.getName() + "'s profile pic to " + filename;
            		canvas.showMessage(msg);
            		
    			} catch (ErrorException ex) {
    			 	String msg = "That image file cannot be opened";
					/* show application message */
					canvas.showMessage(msg);
    			}
    			
    		} else {
    			String msg = "No profile selected.";
    			
				/* show application message */
				canvas.showMessage(msg);
    		}
    		
    		
    	}
    }
    
    private void addFriend(String friendName) {
    	if (!friendName.equals("")) {
    		
    		if (currentProfile != null) {
    			
    			if (database.containsProfile(friendName)) {
    			
    			/* don't truly need this for the implementation of this method
    			 * but allows the user to see that person is already friends w/ another user
    			 * profile constructor checks is user is already friends
    			 */
    			Iterator<String> it = currentProfile.getFriends();
    				Boolean alreadyFriended = false;
    				
    				while (it.hasNext()) {
    					if (it.next() == friendName) {
    						alreadyFriended = true;
    						break;
    					}
    				}
    				
    				if (alreadyFriended) {
    					
    					String msg = currentProfile.getName() + " is already friends with " + friendName + ".";
    					
    					/* show application message */
                		canvas.showMessage(msg);
    				} else { 
    					currentProfile.addFriend(friendName);
    					
    					/* adds currentProfile to friends' friend list as well */
    					database.getProfile(friendName).addFriend(currentProfile.getName());
    	        		
    					/* display profile */
    	        		canvas.displayProfile(currentProfile);
    	        		
    					println("Yoo wassup " + friendName);
    					
    					
    					/* show application message */
    					String msg = "Added " + friendName + " to " + currentProfile.getName() + "'s friend list.";
    					canvas.showMessage(msg);
    				}
    				
    			} else {
    				String msg = "That profile doesn't exist";
					/* show application message */
					canvas.showMessage(msg);
    				
    			}
    			
    		} else {
    			String msg = "No profile selected";
    			
				/* show application message */
				canvas.showMessage(msg);
    		}

    	}
    }
    
    /* INSTANCE VARIABLES*/
    
    /* buttons/interactors */
    private JTextField nameField;
    private JButton add;
    private JButton delete;
    private JButton lookup;
    private JTextField statusField;
    private JButton status;
    private JTextField pictureField;
    private JButton picture;
    private JTextField addFriendField;
    private JButton addFriend;
    
    /* objects */
    private FacePamphletDatabase database;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;
}
