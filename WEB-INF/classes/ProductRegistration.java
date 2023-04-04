

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductRegistration
 */
@WebServlet("/ProductRegistration")
public class ProductRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");


		String strId = request.getParameter("id");
        String strName = request.getParameter("name");
        String strPrice = request.getParameter("price");
        String strWeight = request.getParameter("weight");
        String strMaker = request.getParameter("maker");
        String strBalance = request.getParameter("balance");
        String strHardness = request.getParameter("hardness");

        String jspName = "Registration.jsp";
        String strMeg = null;
        String sql = null;
        int intPrice;

        if (strId == null || strId.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "IDを確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }
        if (strName == null || strName.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "名前を確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }
        if (strPrice == null || strPrice.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "名前を確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }
        if (strWeight == null || strWeight.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "名前を確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }
        if (strMaker == null || strMaker.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "メーカーを確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }
        if (strBalance == null || strBalance.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "バランスを確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }
        if (strHardness == null || strHardness.equals("")){
            //nullの時
        	 jspName = "Eroor.jsp";
        	 strMeg = "硬さを確認してください";
        	 request.setAttribute("MEG", strMeg);
             RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
             rd.forward(request, response);
            return;
        }

        intPrice = Integer.parseInt(strPrice);
      //try{}の外でConnection,Statmentを宣言する
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement prst = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/hew?characterEncoding=utf-8",
						"root",
						"");
				sql = "INSERT INTO products(product_id,product_name,product_price,product_weight,product_balance,product_hardness,product_maker) VALUES (?,?,?,?,?,?,?);";
				prst = con.prepareStatement(sql);

				prst.setString(1, strId);
				prst.setString(2, strName);
				prst.setInt(3, intPrice);
				prst.setString(4, strWeight);
				prst.setString(5, strBalance);
				prst.setString(6, strHardness);
				prst.setString(7, strMaker);


				prst.executeUpdate();
				strMeg = "登録完了";
				jspName = "product.jsp";
			} catch (ClassNotFoundException e) {
				strMeg = "IDが重複しています";
				jspName = "Eroor.jsp";
				e.printStackTrace();
			} catch (SQLException e) {
				strMeg = "IDが重複しています";
				jspName = "Eroor.jsp";
				e.printStackTrace();
			} finally {
				if (prst != null) {
					try {
						prst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (prst != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
			 RequestDispatcher rd = request.getRequestDispatcher("./Registration.jsp");
		        rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
