import database.EmployeeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        var session = request.getSession();
        String userID = (String) session.getAttribute("USER_ID");
        if (userID == null){
            response.sendRedirect("./SigninServlet");
            return;
        }
        var dao = new EmployeeDAO(userID);
        if (dao.getError() != null){
            request.setAttribute("ERROR", "問題が発生しました。再度ログインしてください");
            RequestDispatcher rd = request.getRequestDispatcher("./signin.jsp");
            rd.forward(request, response);
            return;
        }
        request.setAttribute("NAME", dao.getName());
        request.setAttribute("BIRTHDATE", dao.getBirthdate().toString());
        request.setAttribute("ADDRESS", dao.getAddress());
        request.setAttribute("TEL", dao.getTel());
        RequestDispatcher rd = request.getRequestDispatcher("./dashboard.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
