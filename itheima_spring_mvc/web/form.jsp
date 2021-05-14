<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/5/13
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/quick13" method="post">
        <%--表明是第几个Tbl_User对象的username--%>
        第一个对象名称：<input type="text" name="tbl_userList[0].username"><br/>
        第一个对象密码：<input type="text" name="tbl_userList[0].password"><br/>
        第二个对象名称：<input type="text" name="tbl_userList[1].username"><br/>
        第二个对象密码：<input type="text" name="tbl_userList[1].password"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
