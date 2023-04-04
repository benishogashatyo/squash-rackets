<%@ page import="database.dto.CustomerDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CustomerDTO> dto = (List<CustomerDTO>) request.getAttribute("CUSTOMERS");
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
        <h1 style="margin: 50px">顧客リスト</h1>
        <%
            for (CustomerDTO record : dto) {
        %>
        <div class="card">
            <h2><%=record.getUserName()%>
            </h2>
            <p>生年月日：<%=record.getBirthdate()%>
            </p>
            <p>性別：<%=record.getGender()%>
            </p>
            <p>ラケットの重さ：<%=record.getRacket_weight()%>
            </p>
            <p>バランスポイント：<%=record.getRacket_balance()%>
            </p>
            <p>硬さ：<%=record.getHardness()%>
            </p>
            <p>好みのメーカー：<%=record.getManufacturer()%>
            </p>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
