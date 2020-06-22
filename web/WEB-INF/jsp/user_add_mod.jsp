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
    </style>
    <script language="JavaScript">
        $(function(){
            var form = layui.form;
            form.render('radio');
            form.render('select');
            form.verify({
                username: function(value, item){ //value：表单的值、item：表单的DOM对象
                    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                        return '用户名不能有特殊字符';
                    }
                    if(/(^\_)|(\__)|(\_+$)/.test(value)){
                        return '用户名首尾不能出现下划线\'_\'';
                    }
                    if(/^\d+\d+\d$/.test(value)){
                        return '用户名不能全为数字';
                    }
                }

                //我们既支持上述函数式的方式，也支持下述数组的形式
                //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
                ,pass: [
                    /^[\S]{6,12}$/
                    ,'密码必须6到12位，且不能出现空格'
                ],memo:[
                    /^[\S]{5,10}$/
                    ,'描述必须超过5个字符不能大于10个字符'
                ],email:[
                    /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
                    ,'邮箱格式不对'
                ]
            });
            //给form赋值
            form.val("myform", { //myform 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                department_id:'${userBean.department_id}',
                gender:'${userBean.gender}'
            });
            /**/

        });

    </script>

</head>
<body>


<blockquote class="layui-elem-quote layui-text">
    用户增加
</blockquote>

<div style="padding: 10px; background-color: #F2F2F2;">
    <div class="layui-card" style="padding: 5px;">
        <form class="layui-form" action="${path}/userServlet" method="post" lay-filter="myform">
            <input type="hidden" name="method" value="addAndModUser"/>
            <input type="hidden" name="id" value="${userBean.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="user_name"  autocomplete="off" placeholder="请输入账号" class="layui-input" value="${userBean.user_name}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password"   autocomplete="off" placeholder="请输入密码" class="layui-input" value="${userBean.password}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">姓名 </label>
                <div class="layui-input-block">
                    <input type="text" name="real_name"   autocomplete="off" placeholder="请输入姓名    " class="layui-input" value="${userBean.real_name}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">部门</label>
                <div class="layui-input-block">
                    <select name="department_id" lay-filter="aihao">
                        <option value="0">---请选择---</option>
                        <c:forEach items="${departs}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">职位</label>
                <div class="layui-input-block">
                    <input type="text" name="position"  autocomplete="off" placeholder="请输入职位    " class="layui-input" value="${userBean.position}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="gender" value="0" title="男" checked="checked">
                    <input type="radio" name="gender" value="1" title="女" >
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" autocomplete="off" placeholder="请输入电话    " class="layui-input" value="${userBean.phone}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email"   autocomplete="off" placeholder="请输入邮箱    " class="layui-input" value="${userBean.email}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address"  autocomplete="off" placeholder="请输入地址    " class="layui-input" value="${userBean.address}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">工作电话</label>
                <div class="layui-input-block">
                    <input type="text" name="work_phone"  autocomplete="off" placeholder="请输入工作电话    " class="layui-input" value="${userBean.work_phone}">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入描述" class="layui-textarea" name="memo">${userBean.memo}</textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>


    </div>

</div>




</body>
</html>
