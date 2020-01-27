package ServletsLearning;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("hello")
class AverageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();

        String wartosc = req.getParameter("average");

        if(wartosc != null)
        {
            List<Integer> liczby = new ArrayList<>();

            String[] tabString = wartosc.split(",");

            for(String element: tabString)
            {
                liczby.add(Integer.parseInt(element));
            }

            float suma = 0;
            for(int element: liczby)
            {
                suma += element;
            }

            out.println(suma/liczby.size());
        }

    }

}
