import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import database.AuthenticateDAO;

@WebServlet(name = "SigninServlet", value = "/SigninServlet")
public class SigninServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userID = request.getParameter("userID");
        String password = request.getParameter("passwd");
        String errMsg;
        if (userID == null && password == null) {
            RequestDispatcher rd = request.getRequestDispatcher("./signin.jsp");
            rd.forward(request, response);
            return;
        }
        if (StringUtils.isBlank(userID)) {
            errMsg = "IDを入力してください";
            request.setAttribute("ERROR", errMsg);
            RequestDispatcher rd = request.getRequestDispatcher("./signin.jsp");
            rd.forward(request, response);
            return;
        }
        if (StringUtils.isBlank(password)) {
            errMsg = "パスワードを入力してください";
            request.setAttribute("ERROR", errMsg);
            RequestDispatcher rd = request.getRequestDispatcher("./signin.jsp");
            rd.forward(request, response);
            return;
        }
        //password = DigestUtils.sha256Hex(password);
        var auth = new AuthenticateDAO(userID, password);
        String authenticatedID = auth.getUserID();
        if (auth.getError() != null || authenticatedID == null) {
        	auth.getError().printStackTrace();
        	
            errMsg = "ログインに失敗しました。ID、パスワードを再度確認してください。";
            request.setAttribute("ERROR", errMsg);
            RequestDispatcher rd = request.getRequestDispatcher("./signin.jsp");
            rd.forward(request, response);
            return;
        }
        var session = request.getSession();
        session.setAttribute("USER_ID", authenticatedID);
        response.sendRedirect("./DashboardServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
