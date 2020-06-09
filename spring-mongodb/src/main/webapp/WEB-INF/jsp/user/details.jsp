<%--
  Created by IntelliJ IDEA.
  User: Xiaofei
  Date: 2020/6/7
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户详情</title>
</head>
<body>
    <center>
        <table border="1">
            <tr>
                <td>标签</td>
                <td>值</td>
            </tr>
            <tr>
                <td>用户编号</td>
                <td><c:out value="${user.id}"></c:out></td>
            </tr> <tr>
                <td>用户名称</td>
                <td><c:out value="${user.userName}"></c:out></td>
            </tr> <tr>
                <td>备注</td>
                <td><c:out value="${user.note}"></c:out></td>
            </tr>
        </table>
    </center>
${user}
</body>
</html>
