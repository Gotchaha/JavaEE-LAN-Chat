<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  /*el表达式获取*/
  pageContext.setAttribute("path",path);
%>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-m8" />
  <title>Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
  <meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />

  <link href="css/style.css" rel='stylesheet' type='text/css' />

  <script type="text/javascript" src="js/jquery.min.js"></script>

</head>
<body>
<script>$(document).ready(function(c) {
  $('.close').on('click', function(c){
    $('.login-form').fadeOut('slow', function(c){
      $('.login-form').remove();
    });
  });
});
</script>

<h1>登录</h1>
<div class="login-form">
  <div class="close"> </div>
  <div class="head-info">
    <label class="lbl-1"> </label>
    <label class="lbl-2"> </label>
    <label class="lbl-3"> </label>
  </div>
  <div class="clear"> </div>
  <div class="avtar"><img src="images/avtar.png" height="100" width="100" /></div>
  <form action="${path}/userServlet?method=login" method="post">
    <input type="hidden" name="method" value="login">
    <input type="text" class="text" name="username" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'username';}" >
    <div class="key"><input type="password" name="password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'password';}"></div>

    <div class="signin"><input type="submit" value="Login" ></div>
  </form>
</div>


<h1>管理员登录</h1>
<div class="login-form">
  <div class="close"> </div>
  <div class="head-info">
    <label class="lbl-1"> </label>
    <label class="lbl-2"> </label>
    <label class="lbl-3"> </label>
  </div>
  <div class="clear"> </div>
  <form action="${path}/userServlet?method=root_login" method="post">
    <input type="hidden" name="method" value="root_login">
    <input type="text" class="text" name="username" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'username';}" >
    <div class="key"><input type="password" name="password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'password';}"></div>

    <div class="signin"><input type="submit" value="Login" ></div>
  </form>
</div>


</body>
</html>

