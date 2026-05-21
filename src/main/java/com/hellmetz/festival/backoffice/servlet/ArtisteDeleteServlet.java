package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ArtisteDao;
import com.hellmetz.festival.backoffice.model.Artiste;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/artistes/delete")
public class ArtisteDeleteServlet extends HttpServlet {

    private ArtisteDao artisteDao = new ArtisteDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);


            Artiste artisteASupprimer = new Artiste();
            artisteASupprimer.setId(id);

            // 3. Appel de la méthode delete du DAO
            artisteDao.delete(artisteASupprimer);
        }

        // 4. Redirection vers la liste des scènes
        response.sendRedirect(request.getContextPath() + "/backoffice/artistes");
    }
}