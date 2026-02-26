package org.ee.jakarta.homework_15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/notebook")
public class NoteBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String manufacturer = req.getParameter("manufacturer");
        String ssd = req.getParameter("ssd");
        String videoCard = req.getParameter("videoCard");
        String processor = req.getParameter("processor");
        String os = req.getParameter("os");
        String matrixType = req.getParameter("matrixType");
        String resolution = req.getParameter("resolution");

        String touchScreen = req.getParameter("touchScreen") != null ? "Да" : "Нет";
        String keyBackLight = req.getParameter("keyBacklight") != null ? "Да" : "Нет";

        req.setAttribute("manufacturer", manufacturer);
        req.setAttribute("ssd", ssd);
        req.setAttribute("videoCard", videoCard);
        req.setAttribute("processor", processor);
        req.setAttribute("os", os);
        req.setAttribute("matrixType", matrixType);
        req.setAttribute("resolution", resolution);
        req.setAttribute("touchScreen", touchScreen);
        req.setAttribute("keyBacklight", keyBackLight);

        req.getRequestDispatcher("/output.jsp").forward(req, resp);
    }
}
