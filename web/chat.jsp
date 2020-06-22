<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>聊天</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
    <meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/reset.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="style/css.css" />
    <script type="text/javascript" src="script/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="script/dateFormate.js"></script>

    <script type="text/javascript">

        $(function(){

            //用于保存当前本地最新的聊天记录ID值，初始值为0，目的是第一次加载时获取全部聊天记录
            var finalMessageId = 0;

            askForNew();

            //声明函数：询问服务器端是否存在新的聊天记录
            function askForNew() {

                $.post("ServletAsk",{"finalMessageId":finalMessageId},function(hasNew){

                    //注意：这里hasNew是字符串，即使服务器返回的是"false"，在if中判断也为true
                    if(hasNew == "true") {

                        //给服务器端发送请求，获取最新的聊天记录
                        getNew();

                    }

                },"text");

                //注意：一定要使用函数的引用，不能加()
                setTimeout(askForNew, 1000);

            }

            //声明函数：获取新的聊天记录内容
            function getNew() {

                var $showMessage = $("#showMessage");

                $.post("ServletGetNew",{"finalMessageId":finalMessageId},function(newMessage){

                    /* private Integer messageId;
                    private String message;
                    private Date messageTime; */

                    for(var i = 0; i < newMessage.length; i++) {

                        var messageId = newMessage[i].messageId;
                        var message = newMessage[i].message;
                        var messageTime = newMessage[i].messageTime;

                        messageTime = new Date(messageTime).Format("yyyy年MM月dd日 hh:mm:ss");

                        var htmlStr = "<div class=\"bubble you\">" + messageTime + " " + message + "</div>";

                        //console.log(htmlStr);
                        $showMessage.append(htmlStr);

                        finalMessageId = messageId;

                    }

                    //获取#showMessage对应的DOM对象，通过scrollTop属性设置滚动条的显示位置
                    $showMessage[0].scrollTop = 10000000;

                },"json");

            }

            //给多行文本框绑定键盘按下事件
            $("#sendMessage").keypress(function(event){

                //在用户按下回车键时，发送聊天消息
                //通过事件对象的keyCode属性获取当前按下的键对应的ASCII码
                if(event.keyCode == 13) {

                    //获取聊天消息的内容
                    var message = $.trim(this.value);

                    //使用Ajax技术将聊天消息发送到服务器端
                    $.post("ServletSay",{"message":message});

                    //清空多行文本框
                    this.value = "";

                }

            });

        });

    </script>
</head>
<body>
    <div class="wrapper">
        <div class="container">
            <div class="left">
                <div class="top">
                    <input type="text" placeholder="搜索" />
                    <a href="javascript:;" class="search"></a>
                </div>
                <ul class="people">
                    <li class="person" data-chat="person1">
                        <img src="imgs/icon.jpg" alt="" />
                        <span class="name">全体成员</span>
                        <%
                            Date time = new Date();
                            SimpleDateFormat formatter =new SimpleDateFormat("MM-dd");
                            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
                            String NowTime = formatter.format(time);
                            String PTime = formatter2.format(time);
                        %>
                        <span class="time"> <%=NowTime %> </span>
                        <span class="preview">说点啥...</span>
                    </li>
                </ul>
            </div>

            <div class="right">
                <div class="top"><span>To: <span class="name">ALL</span></span></div>
                <div class="conversation-start">
                    <span>今天, <%=PTime%> </span>
                </div>
                <div id="showMessage"></div>
                <div class="chat" data-chat="person1"></div>
                <textarea id="sendMessage"></textarea>
            </div>
        </div>
    </div>


    <script src="js/index.js"></script>


</body>
</html>
