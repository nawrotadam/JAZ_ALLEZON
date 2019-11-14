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
        PrintWriter out = resp.getWriter();  // instancja wypisujaca na ekran

        String wartosc = req.getParameter("average");  // pobieramy dane z przeslane metoda get z url

        if(wartosc != null)  // zapobieganie null pointer exception
        {
            List<Integer> liczby = new ArrayList<>();  // lista przechowujaca liczby w formacie Integer

            String[] tabString = wartosc.split(",");  // zamien string oddzielony przecinkami na tablice wartosci

            for(String element: tabString) // konwersja String na int
            {
                liczby.add(Integer.parseInt(element));
            }

            float suma = 0;
            for(int element: liczby)
            {
                suma += element;  // zwiekszamy sume
            }

            out.println(suma/liczby.size());  // zwracamy srednia
        }

    }

}
