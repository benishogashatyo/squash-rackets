import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import database.CategoryDAO;
import database.CustomerDAO;
import database.dto.CustomerDTO;

@WebServlet(name = "AddCustomerServlet", value = "/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        var session = request.getSession();
        if (session.getAttribute("USER_ID") == null) {
            response.sendRedirect("./SigninServlet");
            return;
        }
        String userID = request.getParameter("userID");
        String name = request.getParameter("userName");
        String birthdate = request.getParameter("date");
        String gender = request.getParameter("gender");
        String racket_weight = request.getParameter("racket_weight");
        String racket_balance = request.getParameter("racket_balance");
        String hardness = request.getParameter("hardness");
        String manufacturer = request.getParameter("manufacturer");
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
        if (userID == null && name == null && birthdate == null && gender == null && racket_weight == null && racket_balance == null && hardness == null && manufacturer == null) {
            RequestDispatcher rd = request.getRequestDispatcher("./addCustomer.jsp");
            rd.forward(request, response);
            return;
        }
        boolean isBlank = false;
        String errMsg = null;
        if (StringUtils.isBlank(userID)) {
            errMsg = "IDを入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(name)) {
            errMsg = "名前を入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(birthdate)) {
            errMsg = "日付を入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(gender)) {
            errMsg = "性別を入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(racket_weight)) {
            errMsg = "ラケットの重さを入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(racket_balance)) {
            errMsg = "バランスポイントを入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(hardness)) {
            errMsg = "硬さを入力してください";
            isBlank = true;
        }
        if (StringUtils.isBlank(manufacturer)) {
            errMsg = "好みのメーカーを入力してください";
            isBlank = true;
        }
        if (isBlank) {
            request.setAttribute("ERROR", errMsg);
            RequestDispatcher rd = request.getRequestDispatcher("./addCustomer.jsp");
            rd.forward(request, response);
            return;
        }
        var dto = new CustomerDTO();
        dto.setUserID(userID);
        dto.setUserName(name);
        dto.setBirthdate(birthdate);
        dto.setGender(gender);
        dto.setRacket_weight(racket_weight);
        dto.setRacket_balance(racket_balance);
        dto.setHardness(hardness);
        dto.setManufacturer(manufacturer);
        errMsg = dto.getError();
        if (errMsg != null) {
            request.setAttribute("ERROR", errMsg);
            RequestDispatcher rd = request.getRequestDispatcher("./addCustomer.jsp");
            rd.forward(request, response);
            return;
        }
        List<CustomerDTO> addCustomerArray = new ArrayList<>();
        addCustomerArray.add(dto);
        var CustomerDAO = new CustomerDAO();
        CustomerDAO.AddCustomer(addCustomerArray);
        if (CustomerDAO.getError() != null) {
            request.setAttribute("ERROR", "登録に失敗しました。再度お試しください");
            RequestDispatcher rd = request.getRequestDispatcher("./addCustomer.jsp");
            rd.forward(request, response);
            return;
        }
        request.setAttribute("INFO", "登録が完了しました！");
        RequestDispatcher rd = request.getRequestDispatcher("./addCustomer.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
