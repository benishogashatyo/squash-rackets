<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300&family=Noto+Serif+JP:wght@200;300&display=swap" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<link href="https://fonts.googleapis.com/css2?family=Material+Icons"rel="stylesheet">
<link href="css/productmanagement.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="reg.css">
</head>
<body>
<header>
    <span class="title">Racket Search</span>
</header>

<form action="./ProductRegistration" method="get">
<div id="main">
	<div class="sub">

<div class="input-field">
 <input id="product_name" type="text" name="id" class="validate">
          <label for="last_name">商品ID</label>
          </div>

	<div class="input-field">
 <input id="product_name" type="text" name="name" class="validate">
          <label for="last_name">商品名</label>
          </div>

          <div class="input-field">
 <input id="product_name" type="text" name="weight" class="validate">
          <label for="last_name">重さ</label>
          </div>
            <div class="input-field">
 <input id="product_name" type="text" name="price" class="validate">
          <label for="last_name">価格</label>
          </div>


<div class="input-field">
 <select  name="maker">
<option value="0" selected>選んでください</option>
 <option value = "1">prince</option>
 <option value = "2">HEAD</option>
 <option value = "3">TECNIFIBRE</option>
 <option value = "4">WILSON</option>
 <option value = "5">DUNLOP</option>
 </select>
 <label>メーカー</label>
</div>


<div class="input-field">
   <select  name="balance">
<option value="0" selected>選んでください</option>
 <option value = "1">トップヘビー</option>
 <option value = "2">ミドル</option>
 <option value = "3">トップライト</option>
 </select>
 <label>バランス</label>
</div>

<div class="input-field">
   <select  name="hardness">
<option value="0" selected>選んでください</option>
 <option value = "1">柔らかめ</option>
 <option value = "2">普通</option>
 <option value = "3">硬め</option>
 </select>
 <label>硬さ</label>
</div>
</div>
</div>
<input type = "submit" name = "sb" value = "送信">
<!-- <p><a class="btn  waves-effect waves-light grey darken-4">登録</a></p> -->
</form>
<script>
$(document).ready(function(){
$('select').formSelect();
});
</script>

</body>
</html>