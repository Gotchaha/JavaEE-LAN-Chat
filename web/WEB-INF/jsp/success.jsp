<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script language="JavaScript">
        $(function(){
            var layer = layui.layer;
            layer.alert('增加成功', function(index){
                parent.closeAllLay();
            });
        });

    </script>
</head>
<body >

</body>
</html>
