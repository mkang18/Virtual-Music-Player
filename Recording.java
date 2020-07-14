public abstract class Recording implements Playable {
	//instance variables
	protected String artist;
	protected String recordingName;
	protected int durationInSeconds;
	protected int numOfPlays=0;
	
	//default constructor
	public Recording() {
		this.artist = "Unknown";
		this.recordingName = "Unknown";
		this.durationInSeconds = 0;
	}
	
	//non-default constructor
	public Recording(String artist, String recordingName, int durationInSeconds) {
		//check if inputs are legit
		if(artist != null && recordingName != null && durationInSeconds > 0) {
			this.artist = artist;
			this.recordingName = recordingName;
			this.durationInSeconds = durationInSeconds;
		}
		else {
			this.artist = "Unknown";
			this.recordingName = "Unknown";
			this.durationInSeconds = 0;
		}
	}
	
	//accessors
	
	public abstract String getArtist();
	public abstract String getName();
	public abstract int getNumOfPlays();
	public abstract double getFrameRate();
	public abstract double getBitrate();
	public int getDuration() {
		return  this.durationInSeconds;
	}
	
	
	//play
	@Override
	public void play() {
		if(this.durationInSeconds > 0) {
			System.out.println("Now playing: " + this.toString());
			numOfPlays++;
		}
		else
			System.out.println("ERROR: Cannot play this recording.");
	}
	
	//equals
//	public abstract boolean equals();
	
	//toString
	//Overridden by AudioRecording and VideoRecording Class
	public abstract String toString();

		
}
