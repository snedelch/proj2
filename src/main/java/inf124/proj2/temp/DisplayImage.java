package inf124.proj2.temp;

//References: https://stackoverflow.com/questions/36375143/how-to-output-an-image-on-file-as-an-html-img-from-a-java-servlet/36377224

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DisplayImage extends HttpServlet {
    public final String imagesBase = "C:\\Users\\slavy\\Desktop\\INF 124\\ass2\\src\\main\\webapp\\front-end\\images";

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String URLAfterWebDomain = request.getRequestURI();

        //Only accept mappings as src="/images/whatever.jpg", even if web.xml has other mappings to this servlet.
        if(URLAfterWebDomain.startsWith("/images/") == false)
            return;

        //get the image name, or even directory and image, e.g. /images/music/beethoven.jpg:
        String relativeImagePath = URLAfterWebDomain.substring("/images/".length());  //will get "music/beethoven.jpg"

        System.out.println("\nFetching image from "+imagesBase+relativeImagePath);
        response.setContentType("image/jpeg"); //as far as I know, this works for PNG as well. You might want to change the mapping to /images/*.jpg if it's giving problems

        ServletOutputStream outStream;
        outStream = response.getOutputStream();
        FileInputStream fin = new FileInputStream(imagesBase+relativeImagePath);

        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outStream);
        int ch =0; ;
        while((ch=bin.read())!=-1)
            bout.write(ch);

        bin.close();
        fin.close();
        bout.close();
        outStream.close();
    }
}
