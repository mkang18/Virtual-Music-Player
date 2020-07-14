import java.util.*;

public class StreamingSystem {
	ArrayList<User>users;
	User person;
	private static int numOfUsers=0;
	
	
	public StreamingSystem() {
		users = new ArrayList<User>();
	}
	
	public void run() {
		Scanner scan = new Scanner(System.in);
		String option;
		String choice = "go back to main menu";
		String logInName;
		int logInID;
		int index=0;
		do {
			System.out.println("\n\nchoose one of the following options: \nadd user \nremove user \nlist all users \nuser \nexit\n\n");
			option = scan.nextLine();
			switch(option) {
			
			
			
			case "add user" :
				System.out.println("Provide username: ");
				String usernameToBeAdded = scan.nextLine();
				System.out.println("Provide playlistname: ");
				String playlistnameToBeAdded = scan.nextLine();
				add(usernameToBeAdded, playlistnameToBeAdded, numOfUsers);
				break;
				
			
				
			case "remove user" :
				if(users.size()>=0) {
					System.out.println("provide username or userID: ");
					if(scan.hasNextInt()) {
						int userIDToBeRemoved;
						userIDToBeRemoved = scan.nextInt();
						remove(userIDToBeRemoved);
					}
					
					else if(scan.hasNext()) {
						String usernameToBeRemoved;
						usernameToBeRemoved = scan.nextLine();
						remove(usernameToBeRemoved);
					}
					

				}
				else if (users.size()<0) {
					System.out.println("There are no users to be removed");
				}
				break;
				
				
			case "list all users" :
				list();
				break;
			
			case "user" : 
					
				do  {
				System.out.println("\n\nEnter the following username or id to log into: \n") ;
				for(int i=0; i<users.size(); i++) {
					System.out.println("username: " + users.get(i).getUserName() + " | id: " + users.get(i).getID());
				}
				
				
				if(scan.hasNextInt()) {
					logInID = scan.nextInt();
					int k = 0;
					while(k<users.size()) {
						if(users.get(k).getID()==logInID) {
							index = k;
							break;
						}
						else {
							k++;
						}
						if(k==users.size()-1 && users.get(k).getID()!=logInID) {
							System.out.println("User does not exist");
							System.out.println(k);
							index = -1;
						}
					}
					if(index>=0) {
						
						do {
						
						System.out.println("\n\n------Login information:------\n " + "username: " + users.get(index).getUserName() + "\nid: " + users.get(index).getID());	
						
					
						System.out.println("\n\nchoose one of the following options: \nadd recording \nadd playlist from file \nadd playlist from another user "
							+ "\nremove recording from playlist \nplay individual recording \nplay entire playlist once \nshuffle entire playlist once "
							+ "\nsave playlist to a file \ndisplay playlist stats \ngo back to login \ngo back to main menu\n");
							choice = scan.nextLine();
							
							
							
							switch (choice) {
							

							
							case "add recording" :
								System.out.println("choose audio or video");
								String typeOfRecording = scan.nextLine();
								
								
								if(typeOfRecording.equals("audio")) {
									AudioRecording newAudioRecording;
									System.out.println("provide artist name, recording name, duration (s), bitrate");
									String recordingInfo = scan.nextLine();
									String [] recording = recordingInfo.split("\\s*,\\s*");
									String artistName = recording[0];
									String recordingName = recording[1];
									String duration = recording[2];
									String bitrate = recording[3];
									int durationInt = Integer.parseInt(duration);
									double bitrationDouble = Double.parseDouble(bitrate);
									newAudioRecording = new AudioRecording (artistName, recordingName, durationInt, bitrationDouble);
									users.get(index).add(newAudioRecording);
									System.out.println(users);
		
								}
							
								else if(typeOfRecording.equals("video")) {
									VideoRecording newVideoRecording;
									System.out.println("provide artist name, recording name, duration (s), framerate");
									String recordingInfo = scan.nextLine();
									String [] recording = recordingInfo.split("\\s*,\\s*");
									String artistName = recording[0];
									String recordingName = recording[1];
									String duration = recording[2];
									String framerate = recording[3];
									int durationInt = Integer.parseInt(duration);
									double framerateDouble = Double.parseDouble(framerate);
									newVideoRecording = new VideoRecording (artistName, recordingName, durationInt, framerateDouble);
									users.get(index).add(newVideoRecording);
									System.out.println(users);
								}
						
								
								break;
								
							
								
								
							case "add playlist from file" :
								System.out.println("type in the name of the file: ");
								String filename = scan.nextLine();
								users.get(index).add("src/"+filename);
								System.out.println("\n"+users);
								
								break;
								
								
							case "add playlist from another user" :
								System.out.println("type in the userID to add playlist from");
								int userPlaylistID = scan.nextInt();
								add(index,userPlaylistID);
								System.out.println("\n"+users);
								break;
								
								
							case "remove recording from playlist" :
								System.out.println("type in the name or the index of the recording to remove");
								int recordingIndex=0;
								if(scan.hasNextInt()) {
									recordingIndex = scan.nextInt();
									System.out.println(recordingIndex);
									users.get(index).remove(recordingIndex);
								}
								else if(scan.hasNextLine()) {
									String recordingRemove = scan.nextLine();
									users.get(index).remove(recordingRemove);
								}
								
								break;
							
								
							case "play individual recording" :
								System.out.println("type in the name or the index of the recording to play");
								int recordingIndexPlay=0;
								if(scan.hasNextInt()) {
									recordingIndexPlay = scan.nextInt();
									users.get(index).play(recordingIndexPlay);
								}
								else if(scan.hasNextLine()) {
									String nameToPlay = scan.nextLine();
									System.out.println(nameToPlay);
									users.get(index).play(nameToPlay);
								}
								break;
								
													
							case "play entire playlist once" :
								users.get(index).play();
								break;
								
								
							case "shuffle entire playlist once" :
								users.get(index).shuffle();
								break;
								
								
							case "save playlist to a file" :
								users.get(index).save();
								break;
							
								
							case "display playlist stats" :
								users.get(index).display();
								break;
							
								
							case "go back to login" :
								break;
								
								
							
							
							} //end of switch choice  
			
					} //end of do loop for login option page
						while(option.equals("user") && choice.equals("go back to main menu")==false && choice.equals("go back to login")==false);
							 
					
					} //end of if index>=0 loop					
					
					
					
				} //end of else if scan.hasNextInt() loop
				
				
				
				
				else if(scan.hasNextLine()) {
					logInName = scan.nextLine();
					int j = 0;
					while(j<users.size()) {
						if(users.get(j).getUserName().equals(logInName)) {
							index = j;
							break;
						}
						else {
							j++;
						}
						if(j==users.size()-1 && users.get(j).getUserName().equals(logInName)==false) {
							System.out.println("User does not exist");
							index = -1;
						}
					}
				
					if(index>=0) {
								
						do {
						
						System.out.println("------Login information:------\n " + "username: " + users.get(index).getUserName() + "\nid: " + users.get(index).getID());	
						
					
						System.out.println("\n\nchoose one of the following options: \nadd recording \nadd playlist from file \nadd playlist from another user "
							+ "\nremove recording from playlist \nplay individual recording \nplay entire playlist once \nshuffle entire playlist once "
							+ "\nsave playlist to a file \ndisplay playlist stats \ngo back to login \ngo back to main menu\n");
						choice = scan.nextLine();
							
							
							
							switch (choice) {
							

							
							case "add recording" :
								
								System.out.println("choose audio or video");
								String typeOfRecording = scan.nextLine();
								
								
								if(typeOfRecording.equals("audio")) {
									AudioRecording newAudioRecording;
									System.out.println("provide artist name, recording name, duration (s), bitrate");
									String recordingInfo = scan.nextLine();
									String [] recording = recordingInfo.split("\\s*,\\s*");
									String artistName = recording[0];
									String recordingName = recording[1];
									String duration = recording[2];
									String bitrate = recording[3];
									int durationInt = Integer.parseInt(duration);
									double bitrationDouble = Double.parseDouble(bitrate);
									newAudioRecording = new AudioRecording (artistName, recordingName, durationInt, bitrationDouble);
									users.get(index).add(newAudioRecording);
									System.out.println(users);
								}	
								
								else if(typeOfRecording.equals("video")) {
									VideoRecording newVideoRecording;
									System.out.println("provide artist name, recording name, duration (s), framerate");
									String recordingInfo = scan.nextLine();
									String [] recording = recordingInfo.split("\\s*,\\s*");
									String artistName = recording[0];
									String recordingName = recording[1];
									String duration = recording[2];
									String framerate = recording[3];
									int durationInt = Integer.parseInt(duration);
									double framerateDouble = Double.parseDouble(framerate);
									newVideoRecording = new VideoRecording (artistName, recordingName, durationInt, framerateDouble);
									users.get(index).add(newVideoRecording);
									System.out.println(users);
								}
								
								
								break;
								
								
								
							case "add playlist from file" :
								System.out.println("type in the name of the file: ");
								String filename = scan.nextLine();
								users.get(index).add("src/"+filename);
								System.out.println("\n"+users);
								
								break;
								
								
							case "add playlist from another user" :
								System.out.println("type in the userID to add playlist from");
								int userPlaylistID = scan.nextInt();
								add(index,userPlaylistID);
								System.out.println("\n"+users);
								break;
								
								
							case "remove recording from playlist" :
								System.out.println("type in the name or the index of the recording to remove");
								int recordingIndex=0;
								if(scan.hasNextInt()) {
									recordingIndex = scan.nextInt();
									System.out.println(recordingIndex);
									users.get(index).remove(recordingIndex);
								}
								else if(scan.hasNextLine()) {
									String recordingRemove = scan.nextLine();
									users.get(index).remove(recordingRemove);
								}
								
								break;
							
								
							case "play individual recording" :
								System.out.println("type in the name or the index of the recording to play");
								int recordingIndexPlay=0;
								if(scan.hasNextInt()) {
									recordingIndexPlay = scan.nextInt();
									users.get(index).play(recordingIndexPlay);
								}
								else if(scan.hasNextLine()) {
									String nameToPlay = scan.nextLine();
									System.out.println(nameToPlay);
									users.get(index).play(nameToPlay);
								}
								break;
							
							
							case "play entire playlist once" :
								users.get(index).play();
								break;
								
								
							case "shuffle entire playlist once" :
								users.get(index).shuffle();
								break;
								
								
							case "save playlist to a file" :
								users.get(index).save();
								break;
							
								
							case "display playlist stats" :
								users.get(index).display();
								break;
							
								
							case "go back to login" :
								break;
								
								
							
							
							} //end of switch choice  
			
					} //end of do loop for login option page
						while(option.equals("user") && choice.equals("go back to main menu")==false && choice.equals("go back to login")==false);
							 
					
					} //end of if index>=0 loop
					} //end of if scan.hasNext() loop
					

				
				
				
				
				
				} //end of do for login page
				while (option.equals("user") && choice.equals("go back to login") && choice.equals("go back to main menu")==false);
				
	
				
						
			break;
			
			} //end of switch

				
			} //end of do
		
		while (option.equals("add user") || option.equals("remove user") || option.equals("list all users") || option.equals("user") || choice.equals("go back to main menu") && option.equals("exit")==false);
	}
					
					

				
			



		
	
	
	//accessors
	public ArrayList<User> getUsers() {
		return this.users;
	}


	
	
	public boolean add(String username, String playlistname, int num) { //add given username
		if(username!= null && playlistname!=null) {
			person = new User(username, playlistname, numOfUsers);
			this.users.add(person);
			numOfUsers++;
			return true;
		}
		return false;
	}
	

	
	public boolean remove(String username) { //remove given username
		if(username!=null && users.size()>0) {
			for(int i=0; i<users.size(); i++) {
				if(users.get(i).getUserName().equals(username)) {
					int index = i;
					this.users.remove(index);
					return true;
				}
			}
			return true;
		}
		else {
			System.out.println("There are no users to be removed");
		}
		return false;
	}
	
	public boolean remove(int num) { //remove given userID
		if(num>=0) {
			for(int i=0; i<users.size(); i++) {
				if(users.get(i).getID()==num) {
					System.out.println(users.get(i).getID());
					int index = i;
					this.users.remove(index);
					return true;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean list() {
		for(int i=0; i<users.size(); i++) {
			System.out.println(users.get(i));
			System.out.println(users.get(i).getUserName());
		}
		return true;
	}
	
	
	public boolean add(int toUser, int fromUser) { //add playlist from another user
		if(fromUser>=0) {
		
			for(int i=0; i<users.get(fromUser).getRecordingList().size();i++) {
				users.get(toUser).add(users.get(fromUser).getRecordingList().get(i));
			}
			
			return true;
		}
		return false;
	}
	

	
	
}
