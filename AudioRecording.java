public class AudioRecording extends Recording {
	//instance variable
	private final double BITRATE;
	
	//default constructor
	public AudioRecording() {
		super();
		this.BITRATE = 0.0;
	}
	
	//non-default constructor
	public AudioRecording(String artist, String recordingName, int durationInSeconds, double bitrate) {
		//check if inputs are legit
		if(artist != null && recordingName != null && durationInSeconds > 0 && bitrate > 0.0) {
			this.artist = artist;
			this.recordingName = recordingName;
			this.durationInSeconds = durationInSeconds;
			this.BITRATE = bitrate;
		}
		// if not, use default constructor
		else {
			this.artist = "Unknown";
			this.recordingName = "Unknown";
			this.durationInSeconds = 0;
			this.BITRATE = 0.0;
		}
	}
	
	//accessor
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
	@Override
	public double getBitrate() {
		return this.BITRATE;
	}
	@Override
	public double getFrameRate() {
		return 0;
	}
	
	
	//equals
//	@Override
//	public boolean equals() {
//		
//	}
		
	
	//toString
	@Override
	public String toString() {
		return this.artist + " - " + this.recordingName + " [" + 
		((int)Math.floor(this.durationInSeconds/60)) + "m" + (this.durationInSeconds % 60) + "s]"
		+ " [AUDIO | bitrate: " + this.BITRATE + " kbps]";
	}



	
}
