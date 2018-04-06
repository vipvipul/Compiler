package uploadfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Compile {

	static ArrayList<File> generateOutput(File newFile) throws InterruptedException, IOException {
		ArrayList<File> outfile = new ArrayList<File>();

		String path = "E:\\OneDrive\\Documents\\JAVA\\Java_EE\\My_Projects_Eclipse\\Compiler\\Programs_Folder\\";
		File file = newFile;
		String[] temp = file.getName().split("@", 2);

		File classfile = new File(path + temp[0] + ".class");
		if (classfile.exists() && classfile.isFile()) {
			classfile.delete();
		}

		String javac = "javac -d " + path + " " + path;
		String filename1 = file.getName();
		int j = compile(javac + filename1, file, 0, outfile);
//		System.out.println(javac + filename1);

		if (j == 1) {
			return outfile;
		}

		String java = "java -cp " + path + " ";
		String filename = temp[0];
		compile(java + filename, file, 0, outfile);
//		System.out.println(java + filename);
		return outfile;
	}

	static int compile(String command, File file, int j, ArrayList<File> outfile) throws IOException, InterruptedException {
		String[] s = command.split(" ");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < s.length; i++)
			list.add(s[i]);
		ProcessBuilder build = new ProcessBuilder(list);

		File input = new File("fake.txt");
		if (command.contains("java ") == true) {
			for (int i = 1; i <= 2; i++) {
				j = 0;
				input = new File("E:\\OneDrive\\Documents\\JAVA\\Java_EE\\My_Projects_Eclipse\\Compiler\\MyInput\\input" + i + ".txt");
				Process p = build.redirectInput(input).start();
				//long StartTime = System.currentTimeMillis() / 1000;
				
				j = printOutput(p.getErrorStream(), file, j, 1, input, outfile);
				j = printOutput(p.getInputStream(), file, j, 0, input, outfile);
				p.waitFor();
			}
		} else {
			Process p = build.start();
			j = printOutput(p.getErrorStream(), file, j, 1, input, outfile);
			j = printOutput(p.getInputStream(), file, j, 0, input, outfile);
			p.waitFor();
		}

		return j;
	}

	static int printOutput(InputStream is, File file, int j, int i, File input, ArrayList<File> outfile) throws IOException {
		File tempfile = null;
		String path = "E:\\OneDrive\\Documents\\JAVA\\Java_EE\\My_Projects_Eclipse\\Compiler\\Output_Folder\\";
		if (input.getName().equals("fake.txt")) {
			if (i == 0) {
				tempfile = File.createTempFile(file.getName().split("@", 2)[0] + "_output", ".txt", new File(path));
			} else {
				tempfile = File.createTempFile(file.getName().split("@", 2)[0] + "_error", ".txt", new File(path));
			}
		} else {
			String count = input.getName().split(".txt")[0].split("input")[1];
			if (i == 0) {
				tempfile = File.createTempFile(file.getName().split("@", 2)[0] + "_output" + count + "@", ".txt", new File(path));
			} else {
				tempfile = File.createTempFile(file.getName().split("@", 2)[0] + "_error" + count + "@", ".txt", new File(path));
			}
		}

		FileOutputStream fout = new FileOutputStream(tempfile);
		StringBuilder sb = new StringBuilder();
		int n;
		while ((n = is.read()) != -1) {
			sb.append((char) n);
		}
		String s;
		if (i == 1 && j == 0 && sb.length() != 0) {						// for compilation or runtime error
			s = sb.toString().replaceAll("\\\\", "/");
			String filename = file.toString().replace("\\", "/");
			String error = s.replaceAll(filename, file.getName().split("@", 2)[0] + ".java");
			if (error.contains(file.getName())) {
				error = error.replaceAll(file.getName(), file.getName().split("@", 2)[0] + ".java");
			}
			fout.write(error.getBytes());
			outfile.add(tempfile);
			j = 1;
		} else if (i == 0 && sb.length() != 0) {						// for output after successful compilation
			fout.write(sb.toString().getBytes());
			outfile.add(tempfile);
		}
		fout.flush();
		fout.close();

		if (tempfile.length() == 0) {
			tempfile.delete();
		}
		return j;
	}
}
