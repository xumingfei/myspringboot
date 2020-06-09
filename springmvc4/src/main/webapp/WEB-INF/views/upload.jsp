<%--
  Created by IntelliJ IDEA.
  User: Xiaofei
  Date: 2020/4/4
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <div class="upload" >
        <form action="/uploadFile" enctype="multipart/form-data" method="post">
            <input type="file" name="file" /><br/>
            <input type="submit" value="上传"/>
        </form>
    </div>
</body>
</html>
