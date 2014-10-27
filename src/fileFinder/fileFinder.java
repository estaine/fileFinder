package fileFinder;

import java.io.IOException;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class fileFinder {

	private static final int minLength = 1488 * 1024;
	private static ArrayList<File> longFiles;

	
	static void processFile(ArrayList<File> fileList, File file) {
		 if(file.length() > minLength)
			 fileList.add(file);
	 }
	 
	 static void processDirectory(ArrayList<File> fileList, File directory) {
		 File[] directoryFileList = directory.listFiles();
		 if(directoryFileList != null) {
			 for(File directoryFile : directoryFileList)
			 {
				 if(directoryFile.isFile())
					 processFile(fileList, directoryFile);
				 else if(directoryFile.isDirectory())
					 processDirectory(fileList, directoryFile);
				 
			 }
		 }
	 }
	 
	
	 static ArrayList<File> getList(File logicalDrive) {		 
	    ArrayList<File> appropriateFiles = new ArrayList<File>();
	    processDirectory(appropriateFiles, logicalDrive);
	    return appropriateFiles;	
	 }
	 
	 public static void main(String[] args) throws IOException {
		  
			System.out.println("Logical drive list:");		
			File[] logicalDrives = File.listRoots();
														
			for (File file : logicalDrives)
				System.out.println(file);
			
			Scanner scan = new Scanner(System.in);
			String chosenDriveName;
			
			System.out.println("Choose the drive.");
		
				chosenDriveName = scan.nextLine();
				scan.close();
			boolean inputIsCorrect = false;
			File chosenDrive = null;
			for(File drive : logicalDrives)
				if(drive.toString().toUpperCase().contains(chosenDriveName.toUpperCase())) {
					inputIsCorrect = true;
					chosenDrive = drive;
				}
					
			
			if(!inputIsCorrect)
			{
				System.out.println("Fuck yourself");
				return;
			}
			
			longFiles = getList(chosenDrive);
			
			System.out.println("OK");
			Collections.sort(longFiles, new fileComparator());
			
			Date lastModified = new Date();
			for(File file : longFiles) {
				lastModified = new Date(file.lastModified());
				System.out.println(file.getPath() + "\t\t" + file.length() + "\t\t" + lastModified);
			}
			

	  }
}
		
		


