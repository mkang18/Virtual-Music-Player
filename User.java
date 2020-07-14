import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class User extends Playlist implements Playable  {
	//instance variables
	protected String name;
	protected String userName;
	protected int id;
	protected String playlistName;
	protected double durationInSeconds = 0.0;
	Playlist user;

		
	//default constructor
	public User() {
		this.userName = null;
		this.playlistName = null;
		this.id = 0;
	}
	
	//non-default constructor
	public User(String username, String playlistname, int id) {
		//check if argument is valid
		if(username != null) {
			this.userName = username;
		}
		else {
			userName = "Unknown";
		}
		
		this.playlistName = playlistname;
		
		
		if(id>=0) {
			this.id = id;
		}
		else {
			this.id = -1;
		}
	}
	
	
	
	//accessors
	public String getUserName() {
		return this.userName;
	}
	public int getID() {
		return this.id;
	}
	public String getPlayListName() {
		return this.playlistName;
	}
	@Override
	public String getArtist() {
		return super.artist;
	}
	@Override
	public String getName() {
		return super.recordingName;
	}
	
	public ArrayList<Recording> getRecordingList() {
		return super.recordingList;
	}	
	
	
	
	//adds individual recording given recording
	public boolean add(Recording newRecording) { 
		if(recordingList!=null && newRecording!=null && equals(newRecording)==false) {//recordingList is not empty and does not have duplicates
			this.recordingList.add(newRecording); //add recording to the arraylist of recordingList
			//add duration to total playlist duration
			this.durationInSeconds += newRecording.getDuration();
			return true;
		}
		if(equals(newRecording)) {
			System.out.println("a recording with the same artist / name already exists");
			return false;
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
											this.durationInSeconds += newRecording.getDuration();
										}
									}
									if(lineAsArray[0].equals("V")) {
										VideoRecording newRecording = new VideoRecording(artist, name, durationInSeconds, rate);
										if(equals(newRecording)==false) { //no duplicate artist and name
										this.recordingList.add(newRecording);
										this.durationInSeconds += newRecording.getDuration();

										}
									}
																
								} catch (NumberFormatException nfe) {
									System.out.println("ERROR: Number format exception. Recording rejected (" + line + ").");
								}
							} else {
								System.out.println("ERROR: Unknown recording type data (" + line + ").");
							}
							
						} else {
							System.out.println("ERROR: Inconsistent or no data read (" + line + ").");
						}
					} else {
						System.out.println("ERROR: Empty line read from a file");
					}
				}
				fileScanner.close();
			} catch (FileNotFoundException fnfe) {
				System.out.println("ERROR: File " +fileName + " not found!");
			}
		} else {
			System.out.println("ERROR: No file name provided.");
		}
		return true;
	}

	
	
	
	//removes recording given index
	public boolean remove(int index) {
		super.remove(index);
		return true;
	}
	
	//removes recording given name
	public boolean remove(String name) {
		super.remove(name);
		return true;
	}
	
	//play based on its index
	public boolean play(int index) {
		super.play(index);
		
		return true;
	}
	
	//play based on its name
	public boolean play(String name) {
		super.play(name);
	
		return true;
	}
	
	//play entire playlist in order from the order it was populated
	@Override
	public void play() {
		super.play();
	}
	
	//shuffle
	public void shuffle() {
		super.shuffle();
	}
	
	
	//save
	public void save() {
		if(super.recordingList!=null) {
			FileWriter fw = null;
			try {
				Date Date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy");
				String CSVusername = this.getUserName();
				String CSVplaylist = this.getPlayListName();
				String CSVfilepath = "src/"+CSVusername+"_"+CSVplaylist+formatter.format(Date)+".csv";
				String filepath = CSVfilepath;
				fw = new FileWriter(filepath);
				PrintWriter pw = new PrintWriter(fw);
				
				
				List<String[]> data = new ArrayList<String[]>();
				for(int i=0; i<recordingList.size(); i++) {

					
					if(recordingList.get(i) instanceof VideoRecording) {
					data.add(new String[] {"V", recordingList.get(i).getName(), recordingList.get(i).getArtist(), 
							Integer.toString(recordingList.get(i).getDuration()), Double.toString(recordingList.get(i).getFrameRate())});	
					}
					else if(recordingList.get(i) instanceof AudioRecording) {
					data.add(new String[] {"A", recordingList.get(i).getName(), recordingList.get(i).getArtist(), 
							Integer.toString(recordingList.get(i).getDuration()), Double.toString(recordingList.get(i).getBitrate())});
					
					}
				}
				for(int i=0;i<data.size();i++) {
					pw.print(Arrays.toString(data.get(i))+"\n");
				}
		
			}
	
			
				
			
			//}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					fw.flush();
					fw.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	//display
	public void display() {
		if(recordingList!=null) {
			for(int i=0; i<recordingList.size(); i++) {
				System.out.println(recordingList.get(i).getArtist() + "\t" + recordingList.get(i).getName() + "\t" + recordingList.get(i).getNumOfPlays());
			}
		}
	}
	
	
	//toString
	public String toString() {
		String str = "User: " + userName + " Playlist name: " +         " | ID: " + id + " [" + ((int) Math.floor(this.durationInSeconds/60)) + "m" + (this.durationInSeconds % 60) + "s]:\n";
		
		for(int i=0; i<this.recordingList.size(); i++) {
			if(this.recordingList!=null) {
				str += this.recordingList.get(i);
				str += "\n";
			}
			if(i==this.recordingList.size()-1) {
				str += "\n";
			}
		}
		
		
		return str;
	}

	
}
