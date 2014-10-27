package fileFinder;

import java.io.File;
import java.util.Comparator;
import java.util.Date;

public class fileComparator implements Comparator<File> {

	private static String getExtension(String str) {
		int separatorIndex = str.lastIndexOf('.');
		if(separatorIndex == -1)
			return "";
		String extension = str.substring(separatorIndex + 1);
		return extension;
	}
	@Override
	public int compare(File first, File second) {
		Date firstDate = new Date(first.lastModified());
		Date secondDate = new Date(second.lastModified());
		int diff = firstDate.compareTo(secondDate);
		return diff == 0 ? getExtension(first.getName()).compareToIgnoreCase(getExtension(second.getName())) : diff;
	}


	}
	


