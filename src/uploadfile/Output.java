package uploadfile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class Output {
	static ArrayList<String> check(ArrayList<File> outfile) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Iterator<File> it = outfile.iterator();
			File genOutput;
			for (int i = 1; i <= 2 && it.hasNext(); i++) {
				genOutput = it.next();
				File myOutput = new File("E:\\OneDrive\\Documents\\JAVA\\Java_EE\\My_Projects_Eclipse\\Compiler\\MyOutput\\output" + i + ".txt");
				if (genOutput.getName().contains("error"))
					list.add("Test Case " + i + ": Runtime Error!");
				else {
					boolean flag = FileUtils.contentEquals(genOutput, myOutput);
					if (flag == true)
						list.add("Test Case " + i + ": Correct!");
					else
						list.add("Test Case " + i + ": Wrong!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
