public class VideoRecording extends Recording {
	//instance variable
	private final double FRAMERATE;
	
	//default constructor
	public VideoRecording() {
		super();
		FRAMERATE = 0.0;
	}
	
	//non-default constructor
	public VideoRecording(String artist, String recordingName, int durationInSeconds, double frameRate) {
		//check if inputs are legit
		if(artist != null && recordingName != null && durationInSeconds > 0 && frameRate > 0.0) {
			this.artist = artist;
			this.recordingName = recordingName;
			this.durationInSeconds = durationInSeconds;
			this.FRAMERATE = frameRate;
		}
		else {
			this.artist = "Unknown";
			this.recordingName = "Unknown";
			this.durationInSeconds = 0;
			this.FRAMERATE = 0.0;
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
	public double getFrameRate() {
		return this.FRAMERATE;
	}
	@Override
	public double getBitrate() {
		return 0;
	}
	

	
	//toString
	@Override
	public String toString() {
		return this.artist + " - " + this.recordingName + " [" + ((int)Math.floor(this.durationInSeconds/60)) + "m" +
		(this.durationInSeconds % 60) + "s]"+ " [VIDEO | framerate: " + this.FRAMERATE + " fps]";
	}


}
