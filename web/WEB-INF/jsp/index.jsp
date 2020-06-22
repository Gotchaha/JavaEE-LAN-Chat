<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    /*el表达式获取*/
    pageContext.setAttribute("path",path);
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${path}/layui/css/layui.css"/>
    <script src="${path}/js/jquery.min.js" language="JavaScript"></script>
    <script src="${path}/layui/layui.all.js"></script>
    <style type="text/css">
        .top{
            width: 100%;
            height: 80px;
            background-color: #2E2D3C;
            font-size: 25px;
            line-height: 80px;
            text-indent: 20px;
            color: white;
            display: flex;
            justify-content: space-between;
        }
        .content{width: 100%;display: flex}
        .menu{width: 14%;height: 660px;background-color:#2E2D3C }
        .con{width: 86%;height: 660px;}
    </style>
    <script language="JavaScript">
        $(function(){
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
            //监听导航点击
            element.on('nav(demo)', function(elem){
            });
            element.render();
        });

    </script>
</head>
<body>
<div class="top">
    <div class="left">局域网聊天成员管理系统</div>
    <div class="right">欢迎你 ! 管理员 </div>
</div>


<div class="con">
    <iframe src="${path}/userServlet?method=gotoUser" name="myFrame" width="100%" height="660px" frameborder="0" >
    </iframe>
</div>
</div>
</body>
</html>
