import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Playlist extends Recording implements Playable {
	//instance variables
	protected String playlistName;
	protected double durationInSeconds = 0.0;
	protected ArrayList<Recording> recordingList;
	
	//default constructor
	public Playlist() {
		this.playlistName= "Unknown";
		this.recordingList = new ArrayList<Recording>(0);
	}
	
	//non-default constructor
	public Playlist(String name) {
		//check if argument is valid
		if(name != null) {
			this.playlistName = name;
			this.recordingList = new ArrayList<Recording>();
		}
		else {
			this.playlistName = "Unknown";
		}
	}
	
	//accessors
	public String getPlayListName() {
		return this.playlistName;
	}
	
	@Override
	public String getArtist() {
		return this.artist;
	}
	@Override
	public String getName() {
		return this.recordingName;
	}
	@Override
	public int getNumOfPlays() {
		return this.numOfPlays;
	}
	
	public ArrayList<Recording> getRecordingList() {
		return this.recordingList;
	}
	

	
	//checks for duplicate of both artist and name given Recording
	public boolean equals(Recording newRecording) {
		for(int i=0; i<recordingList.size(); i++) {
			if(recordingList.get(i).getArtist().equals(newRecording.getArtist()) || recordingList.get(i).getName().equals(newRecording.getName())) {
				return true;
			}
		}
		return false;
	}
	
	
	//adds individual recording given recording
	public boolean add(Recording newRecording) { 
		if(recordingList!=null && newRecording!=null && equals(newRecording)==false) {//recordingList is not empty and does not have duplicates
			this.recordingList.add(newRecording); //add recording to the arraylist of recordingList
			//add duration to total playlist duration
			this.durationInSeconds += newRecording.getDuration();
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//adds an entire set of recording given file
	public boolean add(String fileName) {
		if(recordingList!=null && fileName != null) {
			try {
				File playlistFile = new File(fileName);
				Scanner fileScanner = new Scanner(playlistFile);
				System.out.println("Processing playlist file " + playlistFile + ":");
				while(fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					if(line != null ) {
						String [] lineAsArray = line.split(",");
						if(lineAsArray != null && lineAsArray.length == 5) {
							if(lineAsArray[0].contentEquals("A") || lineAsArray[0].equals("V")) {
								String name = lineAsArray[1];
								String artist = lineAsArray[2];
								try {
									int durationInSeconds = Integer.parseInt(lineAsArray[3]);
									double rate = Double.parseDouble(lineAsArray[4]);
									
									if(lineAsArray[0].equals("A")) {
										AudioRecording newRecording = new AudioRecording(artist, name, durationInSeconds, rate);
										if(equals(newRecording)==false) { //no duplicate artist and name
											this.recordingList.add(newRecording);

											throw new UnplayableException();

										}
									}
									if(lineAsArray[0].equals("V")) {
										VideoRecording newRecording = new VideoRecording(artist, name, durationInSeconds, rate);
										if(equals(newRecording)==false) { //no duplicate artist and name
										this.recordingList.add(newRecording);
										}
									}
								} catch (UnplayableException e) {
									System.out.println(e);
								}
							}
						}
					}
				}
			} catch (FileNotFoundException fnfe) {
				System.out.println("ERROR: File " +fileName + " not found!");
			}
			
		}
		return true;
		}
																
//								} catch (NumberFormatException nfe) {
//									System.out.println("ERROR: Number format exception. Recording rejected (" + line + ").");
//								}
//							} else {
//								System.out.println("ERROR: Unknown recording type data (" + line + ").");
//							}
//							
//						} else {
//							System.out.println("ERROR: Inconsistent or no data read (" + line + ").");
//						}
//					} else {
//						System.out.println("ERROR: Empty line read from a file");
//					}
//				}
//				fileScanner.close();
//			} catch (FileNotFoundException fnfe) {
//				System.out.println("ERROR: File " +fileName + " not found!");
//			}
//		} else {
//			System.out.println("ERROR: No file name provided.");
//		}

	
	//add an entire set of recordings from another playlist to the playlist
	
	//removes recording given index 
	public boolean remove(int index) { 
		if(recordingList!=null && index>=0) {
			recordingList.remove(index);
			return true;
		}
		else {
			return false;
		}
	}
	
	//removes recording given name
	public boolean remove(String name) {
		if(recordingList!=null && name !=null) {
				int j=0;
				while(j<recordingList.size()) {
					if(recordingList.get(j).getName().equals(name)) {
						recordingList.remove(j);
						j++;
					}
					else {
						j++;
					}
				
				}
				if(j==recordingList.size()-1 && recordingList.get(j).getName().equals(name)==false) {
					System.out.println("The recording does not exist");
					return false;
				}
		}
		return false;
	}
	
	//play based on its index
	public boolean play(int index) {
		if(recordingList!=null && index>=0) {
			this.recordingList.get(index).play();
			return true;
		}
		else {
			System.out.println("ERROR: Empty playlist.");
			return false;
		}
	}

	//play based on its name
	public boolean play(String name) {
		if(recordingList!=null && name!=null) {
			int j=0;
			while(j<recordingList.size()) {
				if(recordingList.get(j).getName().equals(name)) {
					this.recordingList.get(j).play();
					j++;
					return true;
					
				}
				else {
					System.out.println(recordingList.get(j).getName());
					j++;
				}
			
			}
			if(j==recordingList.size()-1 && recordingList.get(j).getName().equals(name)==false) {
				System.out.println("The recording does not exist");
				return false;
			}
		}
		return false;
	}
	
	//play entire playlist in order from the order it was populated
	@Override
	public void play() {
		if(recordingList!=null) {
			for(int i=0; i<recordingList.size(); i++) {
				this.recordingList.get(i).play();
			}
		}
	}	
	
	
	//shuffle
	public void shuffle(){
		Random rand = new Random();
		int [] array = null;
		int [] random = null;
		ArrayList<Recording>shuffledRecordingList = null;
		//check if playlist is empty
		if(recordingList!=null) {	
			array = new int [recordingList.size()];
			random = new int [recordingList.size()];
			shuffledRecordingList = new ArrayList<Recording>(recordingList.size());
			for(int i=0; i<recordingList.size(); i++) {
				random[i] = i; // has values in corresponding order
			}
			int num; // random int 
			for(int i=0; i<recordingList.size(); i++) {
				num = rand.nextInt(recordingList.size());
				while(random[num]==-1) {
					num = rand.nextInt(recordingList.size());
				}
				if(random[num]!=-1) {
					array[i] = random[num];
					random[num]=-1;
				}
			}
			for(int i=0; i<array.length; i++) {
				shuffledRecordingList.add(i, recordingList.get(array[i]));
				shuffledRecordingList.get(i).play();
			}
		}
		else {
			//if empty, display error message
			System.out.println("ERROR: Empty playlist.");
		}
	}
	
	
	// toString method
	public String toString(){
		String str = "Playlist: " + this.playlistName + " [" + ((int) Math.floor(this.durationInSeconds/60)) + "m" + (this.durationInSeconds % 60) + "s]:\n";
		if (this.recordingList!=null) {
			for (int i=0; i<recordingList.size(); i++){
				str += recordingList.get(i).toString() + "\n";
			}
		} 
		return str;
	}

	@Override
	public double getFrameRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBitrate() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	}
	

