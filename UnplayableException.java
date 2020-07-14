import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UnplayableException extends Exception {
	//instance variables
		protected String playlistName;
		protected double durationInSeconds = 0.0;
		protected ArrayList<Recording> recordingList;
		static String line;
		
		

		
		
		//adds an entire set of recording given file
		public boolean add(String fileName) {
			if(recordingList!=null && fileName != null) {
				try {
					File playlistFile = new File(fileName);
					Scanner fileScanner = new Scanner(playlistFile);
					System.out.println("Processing playlist file " + playlistFile + ":");
					while(fileScanner.hasNextLine()) {
						line = fileScanner.nextLine();
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
											}
										}
										if(lineAsArray[0].equals("V")) {
											VideoRecording newRecording = new VideoRecording(artist, name, durationInSeconds, rate);
											if(equals(newRecording)==false) { //no duplicate artist and name
											this.recordingList.add(newRecording);
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
		
		public UnplayableException() {
			System.out.println("UnplaybleException thrown: ");
			System.out.println("ERROR: Number format exception. Recording rejected (" + line + ").");
		}
	
		
}
