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
    <style type="text/css">

    </style>
    <script language="JavaScript">
        var tableins;
        //jquery 页面加载完成事件
        $(function(){
            layui.use('table', function(){
                //获取table模块
                var table = layui.table;
                //实例化表格
                tableins = table.render({
                        elem: '#userTable'
                        ,height: 500
                        //远程请求后台获取数据
                        ,url: '${path}/userServlet?method=userList' //数据接口
                        ,page: true //开启分页
                        ,cellMinWidth:150
                        ,response: {
                            statusName: 'status' //规定数据状态的字段名称，默认：code
                            ,statusCode: 200 //规定成功的状态码，默认：0
                            ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
                            ,countName: 'count' //规定数据总数的字段名称，默认：count
                            ,dataName: 'data' //规定数据列表的字段名称，默认：data
                        }
                        ,cols: [[ //表头
                            ,{field: 'user_name', title: '账号', width:100}
                            ,{field: 'real_name', title: '用户名', width:100}
                            ,{field: 'departName', title: '部门', width:80}
                            ,{field: 'gender', title: '性别', width: 80,templet: function(d){
                                    var sex = "女";
                                    if(d.gender ==0){
                                        sex = "男";
                                    }
                                    return sex;
                                }}
                            ,{field: 'phone', title: '电话', width: 100}
                            ,{field: 'email', title: '邮箱', width: 150}
                            ,{field: 'address', title: '地址', width: 150}
                            ,{field: 'work_phone', title: '工作电话', width: 100}
                            ,{field: 'update_time', title: '操作',width:150,templet: function(d){
                                    var html='<button type="button" class="layui-btn layui-btn-sm" onclick="updateUser('+d.id+')">修改</button>';
                                    html+='<button type="button" data-method="notice" class="layui-btn layui-btn-normal layui-btn-sm" onclick="del('+d.id+')">删除</button>';
                                    return html;
                                }}
                        ]]
                    }
                );
            });
        });


        //删除
        function del(id){
            //示范一个公告层
            layer.open({
                type: 1
                ,title: false //不显示标题栏
                ,closeBtn: false
                ,area: '300px;'
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,btn: ['确定删除', '关闭']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
                ,yes:function () {
                    alert(123);
                    //ajax调用后台删除数据

                }
            });
        }

        function closeAllLay(){
            var layer = layui.layer;
            layer.closeAll();
            search();
        }

        //修改
        function updateUser(id){
            var layer = layui.layer;
            layer.open({
                type: 2,
                area: ['1000px','600px'],
                content: "${path}/userServlet?method=addAndModPage&id="+id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            });
        }

        //增加
        function userAdd(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                area: ['1000px','600px'],
                content: "${path}/userServlet?method=addAndModPage" //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            });
        }
        function search(){
            var real_name = $("#real_name").val();
            var user_name = $("#user_name").val();
            console.log(tableins);
            tableins.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    user_name:user_name,
                    real_name:real_name
                }
            });
        }
    </script>

</head>
<body>
<div class="layui-card" style="padding: 20px;background-color: #F2F2F2;">
    <div class="layui-inline">
        <label class="layui-form-label">用户名：</label>
        <div class="layui-input-inline">
            <input type="tel" name="phone"  autocomplete="off" class="layui-input" id="real_name">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">账号：</label>
        <div class="layui-input-inline">
            <input type="tel" name="phone"  autocomplete="off" class="layui-input" id="user_name">
        </div>
    </div>
    <button type="button" class="layui-btn layui-btn-normal" onclick="search()">查询</button>
    <button type="button" class="layui-btn layui-btn-normal" onclick="userAdd()">增加</button>
</div>

<table id="userTable" ></table>

</body>
</html>
