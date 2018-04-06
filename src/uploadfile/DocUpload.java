package uploadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemStream;

public class DocUpload {

    public static File writeFile(FileItemStream file /*FileItem file //remove comment for 2nd upload option*/) throws FileNotFoundException, IOException {
        File newFile=null;
        try {
            String path = "E:\\OneDrive\\Documents\\JAVA\\Java_EE\\My_Projects_Eclipse\\Compiler\\Programs_Folder";

            File fi = new File(path);
            if (!fi.exists()) {
                fi.mkdir();
            }

            String[] temp = file.getName().split("\\.");  // to split with . we use \\.
            String filename = temp[0] + "@";              // @ to get the original file name
            String extension = "." + temp[1];
            newFile = File.createTempFile(filename, extension, fi);
            //System.out.println(newFile);
            //System.out.println(newFile.getName());

            InputStream i = file.openStream();
            //InputStream i = file.getInputStream();        //remove comment for 2nd upload option
            FileOutputStream fout = new FileOutputStream(newFile);
            StringBuilder sb = new StringBuilder();
            int x = 0;
            while ((x = i.read()) != -1) {
                sb.append((char) x);
            }
            String s = sb.toString();
            fout.write(s.getBytes());
            
            fout.flush();
            fout.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return newFile;
    }
}