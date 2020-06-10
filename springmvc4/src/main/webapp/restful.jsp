<%--
  Created by IntelliJ IDEA.
  User: Xiaofei
  Date: 2020/6/10
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/webjars/jquery/3.5.1/jquery.js"></script>
    <script type="text/javascript">
        var search = function () {
            var id = $("#number").val();
            $.ajax({
                url: "/customer/"+id,
                type: "GET",
                dataType: "json",//定义回调函数相应数据格式为json字符串,该属性可以省略
                success: function (data) {
                    console.log(data)
                    if (data.data.loginname != null) {
                        alert("您查询的客户登录名为:" + data.data.loginname);
                    } else {
                        alert("没有找到id为"+id+"的客户");
                    }
                },
                error: function (result) {
                    alert(result.msg)
                }
            })
        }
    </script>
</head>
<body>
<form>
    客户编号:<input type="text" name="number" id="number"/>
    <input type="button" value="查询" onclick="search()">
</form>
</body>
</html>
