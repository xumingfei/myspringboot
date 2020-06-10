<%--
  Created by IntelliJ IDEA.
  User: Xiaofei
  Date: 2020/6/10
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试json交互</title>
    <script src="/webjars/jquery/3.5.1/jquery.js"></script>
    <script type="text/javascript">
        function testJson() {
            var loginname = $("#loginname").val();
            var password = $("#password").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/testJson",
                type: "post",
                data: JSON.stringify({loginname:loginname, password: password}),
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        alert("您的登录名为:"+data.loginname+"密码为:"+data.password)
                    }
                }
            })
            <%--$.post("${pageContext.request.contextPath}/testJson",{loginname:loginname, password: password},function (data) {--%>
            <%--    if (data != null) {--%>
            <%--        alert("您的登录名为:"+data.loginname+"密码为:"+data.password)--%>
            <%--    }--%>
            <%--})--%>
        }
        function aaa() {

            $.ajax({
                url: "${pageContext.request.contextPath}/form",
                type: "post",
                data: $('#form').serialize(),
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        alert("您的登录名为:"+data.loginname+"密码为:"+data.password)
                    }
                },
                error : function () {
                    alert("异常")
                }
            })
        }
    </script>
</head>
<body>
<form id="form">
    登录名<input type="text" id="loginname" name="loginname"/><br>
    密码<input type="text" id="password" name="password"/><br>
    <input type="button" value="测试json交互" onclick="testJson()"/>
    <input type="button" value="form提交" onclick="aaa()"/>
</form>
</body>
</html>
