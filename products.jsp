<%@ page import="java.util.List" %>
<%@ page import="database.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("PRODUCTS");
    List<WeightCategoryDTO> weight = (List<WeightCategoryDTO>) request.getAttribute("WEIGHT");
    List<BalanceCategoryDTO> balance = (List<BalanceCategoryDTO>) request.getAttribute("BALANCE");
    List<HardnessCategoryDTO> hardness = (List<HardnessCategoryDTO>) request.getAttribute("HARDNESS");
    List<ManufacturerCategoryDTO> manufacturer = (List<ManufacturerCategoryDTO>) request.getAttribute("MANUFACTURER");
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
        <h1 style="margin: 50px">商品検索</h1>
        <form method="get" action="./ProductSearchServlet" class="flex col">
            <label><span>商品名</span>
                <input type="text" name="search">
            </label>
            <div class="flex row">
                <label><span>最低価格</span>
                    <input type="number" name="min_price">
                </label>
                <label><span>最高価格</span>
                    <input type="number" name="max_price">
                </label>
            </div>
            <label><span>ラケットの重さ</span>
                <select name="racket_weight">
                    <option value="">-----</option>
                    <%
                        for (WeightCategoryDTO w : weight) {
                            out.println("<option value='" + w.getWeight() + "'>" + w.getWeight() + "</option>");
                        }
                    %>
                </select>
            </label>
            <label><span>バランスポイント</span>
                <select name="racket_balance">
                    <option value="">-----</option>
                    <%
                        for (BalanceCategoryDTO b : balance) {
                            out.println("<option value='" + b.getBalance() + "'>" + b.getBalance() + "</option>");
                        }
                    %>
                </select>
            </label>
            <label><span>硬さ</span>
                <select name="hardness">
                    <option value="">-----</option>
                    <%
                        for (HardnessCategoryDTO h : hardness) {
                            out.println("<option value='" + h.getHardness() + "'>" + h.getHardness() + "</option>");
                        }
                    %>
                </select>
            </label>
            <label><span>メーカー</span>
                <select name="manufacturer">
                    <option value="">-----</option>
                    <%
                        for (ManufacturerCategoryDTO m : manufacturer) {
                            out.println("<option value='" + m.getName() + "'>" + m.getName() + "</option>");
                        }
                    %>
                </select>
            </label>
            <button>検索</button>
        </form>

        <%

        %>
        <%
            for (ProductDTO record : products) {
        %>
        <div class="card">
            <h2><%=record.getName()%>
            </h2>
            <h3>価格：<%=record.getPrice()%>円
            </h3>
            <img src="<%=record.getImg()%>" height="200px" alt="<%=record.getName()%>">
            <p>ラケットの重さ：<%=record.getWeight()%>
            </p>
            <p>バランスポイント：<%=record.getBalance()%>
            </p>
            <p>硬さ：<%=record.getHardness()%>
            </p>
            <p>メーカー：<%=record.getManufacturer()%>
            </p>
            <p>商品説明：<%=record.getDescription()%>
            </p>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>