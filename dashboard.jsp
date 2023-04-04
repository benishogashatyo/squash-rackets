
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String) request.getAttribute("NAME");
    String birthdate = (String) request.getAttribute("BIRTHDATE");
    String address = (String) request.getAttribute("ADDRESS");
    String tel = (String) request.getAttribute("TEL");
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
        <h1 style="margin: 50px">ダッシュボード</h1>
        <div class="card" onclick="location.href = './ProductSearchServlet'">
            <h2><i class="material-icons">category</i> 商品リスト</h2>
        </div>
        <div class="card" onclick="location.href = './CustomerListServlet'">
            <h2><i class="material-icons">people</i> 顧客リスト</h2>
        </div>
        <div class="card" onclick="location.href = './AddCustomerServlet'">
            <h2><i class="material-icons">add</i> 顧客の追加</h2>
        </div>
        <div class="card" onclick="location.href = './ProductRegistration'">
            <h2><i class="material-icons">add</i> 商品の追加</h2>
        </div>
    </div>
   <%--  <div class="flex col center" style="margin-top: 50px">
        <h2>登録情報</h2>
        <p>名前：<%=name%>
        </p>
        <p>生年月日：<%=birthdate%>
        </p>
        <p>住所：<%=address%>
        </p>
        <p>電話番号：<%=tel%>
        </p>
    </div> --%>
</div>
</body>
</html>