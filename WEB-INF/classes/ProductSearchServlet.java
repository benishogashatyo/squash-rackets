import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import database.CategoryDAO;
import database.ProductDAO;
import database.dto.ProductDTO;

@WebServlet(name = "ProductSearchServlet", value = "/ProductSearchServlet")
public class ProductSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        var session = request.getSession();
        String userID = (String) session.getAttribute("USER_ID");
        if (userID == null) {
            response.sendRedirect("./SigninServlet");
            return;
        }
        var CategoryDAO = new CategoryDAO();
        if (CategoryDAO.getError() != null) {
            CategoryDAO.getError().printStackTrace();
            //Todo: エラーハンドリング
            return;
        }
        request.setAttribute("WEIGHT", CategoryDAO.getWeightArray());
        request.setAttribute("BALANCE", CategoryDAO.getBalanceArray());
        request.setAttribute("HARDNESS", CategoryDAO.getHardnessArray());
        request.setAttribute("MANUFACTURER", CategoryDAO.getManufacturerArray());
        var dao = new ProductDAO();
        List<ProductDTO> productArray = dao.GetProducts();
        if (dao.getError() != null || productArray == null) {
        	dao.getError().printStackTrace();
            //Todo: エラーハンドリング
            return;
        }

        String name = request.getParameter("search");
        String minPrice = request.getParameter("min_price");
        String maxPrice = request.getParameter("max_price");
        String weight = request.getParameter("racket_weight");
        String balance = request.getParameter("racket_balance");
        String hardness = request.getParameter("hardness");
        String manufacturer = request.getParameter("manufacturer");
        int intMinPrice = 0;
        int intMaxPrice = 0;
        try {
        	  intMinPrice = Integer.parseInt(minPrice);
        }catch (NumberFormatException e) {
			// TODO: handle exception
		}

        try {
        	intMaxPrice = Integer.parseInt(maxPrice);
      }catch (NumberFormatException e) {
			// TODO: handle exception
		}


        if(StringUtils.isNotBlank(name)) {
            productArray.removeIf(list -> !list.getName().equals(name));
        }
//        if(StringUtils.isNotBlank(minPrice)) {
//            productArray.removeIf(list -> list.getPrice() >= intMinPrice);
//        }
//        if(StringUtils.isNotBlank(maxPrice)) {
//            productArray.removeIf(list -> list.getPrice() >= intMaxPrice);
//        }
        if(StringUtils.isNotBlank(weight)) {
            productArray.removeIf(list -> !list.getWeight().equals(weight));
        }
        if(StringUtils.isNotBlank(balance)) {
            productArray.removeIf(list -> !list.getBalance().equals(balance));
        }
        if(StringUtils.isNotBlank(hardness)) {
            productArray.removeIf(list -> !list.getHardness().equals(hardness));
        }
        if(StringUtils.isNotBlank(manufacturer)) {
            productArray.removeIf(list -> !list.getManufacturer().equals(manufacturer));
        }


        request.setAttribute("PRODUCTS", productArray);
        RequestDispatcher rd = request.getRequestDispatcher("./products.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
