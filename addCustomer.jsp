<%@ page import="database.dto.WeightCategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="database.dto.BalanceCategoryDTO" %>
<%@ page import="database.dto.HardnessCategoryDTO" %>
<%@ page import="database.dto.ManufacturerCategoryDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<WeightCategoryDTO> weight = (List<WeightCategoryDTO>) request.getAttribute("WEIGHT");
    List<BalanceCategoryDTO> balance = (List<BalanceCategoryDTO>) request.getAttribute("BALANCE");
    List<HardnessCategoryDTO> hardness = (List<HardnessCategoryDTO>) request.getAttribute("HARDNESS");
    List<ManufacturerCategoryDTO> manufacturer = (List<ManufacturerCategoryDTO>) request.getAttribute("MANUFACTURER");
    String error = (String) request.getAttribute("ERROR");
    String info = (String) request.getAttribute("INFO");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    <span class="title">Racket Search</span>
</header>
<div class="container">
    <div class="flex center center-text col">
        <h1 style="margin: 50px">顧客の追加</h1>
        <%
            if (info != null) {
                out.println("<h2 style='margin: 15px'><i class='material-icons'>info</i>" + info + "</h2>");
            }
        %>
        <form action="./AddCustomerServlet" method="post" class="flex col">
            <%
                if (error != null) {
                    out.println("<span style=\"color: #ff6347\"><i class=\"material-icons\" style=\"color: #ff6347\">error</i> " + error + "</span>");
                }
            %>
            <label><span>ユーザーID</span>
                <input type="text" name="userID">
            </label>
            <label><span>名前</span>
                <input type="text" name="userName">
            </label>
            <label><span>誕生日</span>
                <input type="date" name="date">
            </label>
            <label><span>性別</span>
                <select name="gender">
                    <option value="1">男性</option>
                    <option value="2">女性</option>
                    <option value="3">未回答</option>
                </select>
            </label>
            <label><span>ラケットの重さ</span>
                <select name="racket_weight">
                    <%
                        for (WeightCategoryDTO dto : weight) {
                            out.println("<option value='" + dto.getId() + "'>" + dto.getWeight() + "</option>");
                        }
                    %>
                </select>
            </label>
            <label><span>バランスポイント</span>
                <select name="racket_balance">
                    <%
                        for (BalanceCategoryDTO dto : balance) {
                            out.println("<option value='" + dto.getId() + "'>" + dto.getBalance() + "</option>");
                        }
                    %>
                </select>
            </label>
            <label><span>硬さ</span>
                <select name="hardness">
                    <%
                        for (HardnessCategoryDTO dto : hardness) {
                            out.println("<option value='" + dto.getId() + "'>" + dto.getHardness() + "</option>");
                        }
                    %>
                </select>
            </label>
            <label><span>好みのメーカー</span>
                <select name="manufacturer">
                    <%
                        for (ManufacturerCategoryDTO dto : manufacturer) {
                            out.println("<option value='" + dto.getId() + "'>" + dto.getName() + "</option>");
                        }
                    %>
                </select>
            </label>
            <button style="margin-top: 25px" class="center">登録</button>
        </form>
    </div>
</div>
</body>
</html>