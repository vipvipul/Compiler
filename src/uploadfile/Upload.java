package uploadfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request); 										// comment this for 2nd upload option
		PrintWriter out = response.getWriter();
		if (isMultipart) { 																							// comment this for 2nd upload option
			try {
				// List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request); // remove comment for 2nd upload option
				ServletFileUpload servlet = new ServletFileUpload(); 												// comment this for 2nd upload option
				FileItemIterator fii = servlet.getItemIterator(request); 											// comment this for 2nd upload option
				// for (FileItem file : items) { //remove comment for 2nd upload option
				while (fii.hasNext()) { 																			// comment this for 2nd upload option
					FileItemStream file = fii.next(); 																// comment this for 2nd upload option
					if (file.isFormField()) {

					} else {
						if (file.getFieldName().equals("program")) {
							File newfile = DocUpload.writeFile(file); 												// change parameter type in writeFile() definition as per upload option choosen
							ArrayList<File> outfile = Compile.generateOutput(newfile);

							Iterator<File> it = outfile.iterator();
							boolean error = false;
							while (it.hasNext()) {
								File ofile = it.next();
								if (ofile.getName().contains("error")) {
									error = true;
									break;
								}
							}
							if (error == true) {
								out.print("<p style=\"color:red;font-size:20px;font-weight:bold;padding:0px 0px\">ERROR</p>");
							} else {
								out.print("<p style=\"color:#76FF03;font-size:20px;font-weight:bold;padding:0px 0px\">SUCCESS</p>");
							}

							if (outfile.size() == 1) {
								String s;
								File ofile = outfile.get(0);
								BufferedReader br = new BufferedReader(new FileReader(ofile));
								while ((s = br.readLine()) != null) {
									out.println(s);
								}

								br.close();
							} else {
								ArrayList<String> list = Output.check(outfile);
								Iterator<String> itr = list.iterator();
								while (itr.hasNext()) {
									String s = itr.next();
									if (itr.hasNext() == true)
										out.println(s);
									else
										out.print(s);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
