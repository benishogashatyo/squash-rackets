
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = (String) request.getAttribute("ERROR");
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
        <h1 style="margin: 50px">サインイン</h1>
        <form action="./SigninServlet" method="post" class="flex col">
            <%
                if (error != null) {
                    out.println("<span style=\"color: #ff6347\"><i class=\"material-icons\" style=\"color: #ff6347\">error</i> " + error + "</span>");
                }
            %>
            <label><span>ユーザーID</span>
                <input type="text" name="userID">
            </label>
            <label><span>パスワード</span>
                <input type="password" name="passwd">
            </label>
            <button style="margin-top: 25px" class="center">サインイン</button>
        </form>
    </div>
</div>
</body>
</html>