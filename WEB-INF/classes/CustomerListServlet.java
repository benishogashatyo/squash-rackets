import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CustomerDAO;
import database.dto.CustomerDTO;

@WebServlet(name = "CustomerListServlet", value = "/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        var dao = new CustomerDAO();
        List<CustomerDTO> customerArray = dao.GetCustomer();
        if (customerArray == null || dao.getError() != null) {
        	dao.getError().printStackTrace();
            //Todo: エラーハンドリング
            return;
        }
        request.setAttribute("CUSTOMERS", customerArray);
        RequestDispatcher rd = request.getRequestDispatcher("./customerList.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
