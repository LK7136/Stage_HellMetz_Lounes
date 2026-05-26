package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ConcertDao;
import com.hellmetz.festival.model.Concert;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/concerts/delete")
public class ConcertDeleteServlet extends HttpServlet {

    private ConcertDao concertDao = new ConcertDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);


            Concert concertASupprimer = new Concert();
            concertASupprimer.setId_concert(id);

            // 3. Appel de la méthode delete du DAO
            concertDao.delete(concertASupprimer);
        }

        // 4. Redirection vers la liste des scènes
        response.sendRedirect(request.getContextPath() + "/backoffice/concerts");
    }
}