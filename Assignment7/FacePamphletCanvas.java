/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		//msg = "yerrrrr";
		GLabel message = new GLabel(msg);
		double messageX = (getWidth() - message.getWidth())/2;
		add(message, messageX, (getHeight() - BOTTOM_MESSAGE_MARGIN));
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		double nameLabelY = TOP_MARGIN*2;
		
		name = new GLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name, LEFT_MARGIN, nameLabelY);
		
		checkForProfileImage(profile);
		
		checkForStatus(profile);
		showFriends(profile);	
		
	}
	
	private void checkForProfileImage(FacePamphletProfile profile) {
		
		double nameLabelY = TOP_MARGIN*2;
		double imageBoxY = nameLabelY + IMAGE_MARGIN;
		GRect placeholder = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		
		noImage = new GLabel("No Image");
		noImage.setFont(PROFILE_IMAGE_FONT);
		double noImageX = ( (LEFT_MARGIN + IMAGE_WIDTH) - noImage.getWidth() ) / 2;
		double noImageY = (imageBoxY + IMAGE_HEIGHT + (noImage.getHeight() *2) ) / 2 ;
		
		GImage image = profile.getImage();
		
		
		// imageWidth * s = desired_IMAGE_WIDTH
		// imageWidth / desiredI
		// if image present, display placeholder box and label
		if (image == null) {
			
			add(placeholder, LEFT_MARGIN, imageBoxY);	
			add(noImage, noImageX, noImageY);
			
		} else {
			// else display the actual image w/ profile
			
			double imageWidthScale = image.getWidth()/IMAGE_WIDTH;
			double imageHeightScale = image.getHeight()/IMAGE_HEIGHT;
			
			image.scale(imageWidthScale, imageHeightScale);
		}
		

	}
	
	private void checkForStatus(FacePamphletProfile profile) {
		GLabel noStatus = new GLabel("No current status");
		GLabel currentStatus = new GLabel(profile.getStatus());
		
		noStatus.setFont(PROFILE_STATUS_FONT);
		currentStatus.setFont(PROFILE_STATUS_FONT);
		
		double statusX = LEFT_MARGIN; 
		double statusY = TOP_MARGIN*2 + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN*2;
		
		// check if status is empty string
		if (profile.getStatus().equals("")) {
			
			// if "" -> "no current status"
			add(noStatus, statusX, statusY);
			
		} else {
			
			// if not display current status
			add(currentStatus, statusX, statusY);
		
		}
	}
	
	private void showFriends(FacePamphletProfile profile) {
		GLabel header = new GLabel("Friends:");
		header.setFont(PROFILE_FRIEND_LABEL_FONT);
		double headerY = (TOP_MARGIN*2 + IMAGE_MARGIN);
		add(header, getWidth()/2, headerY);
		
		Iterator<String> friendIterator = profile.getFriends();
		
		while (friendIterator.hasNext()) {
			String name = friendIterator.next();
			GLabel nameLabel = new GLabel(name);
			double incrementalY = nameLabel.getHeight();
			
			headerY += incrementalY;
			add(nameLabel, getWidth()/2, headerY);
		}
	}
	
	private GLabel name;
	private GRect imageBox;
	private GLabel noImage;
	private GLabel displayStatus;
}
