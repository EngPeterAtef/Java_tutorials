import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;


public class MatOperations extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String data = request.getParameter("num");
        response.setContentType("text/html");

        String page = "<!doctype html> <html> <body> <h1>" + data +" </h1> </body></html>";
        response.getWriter().println(page);
    }

}
