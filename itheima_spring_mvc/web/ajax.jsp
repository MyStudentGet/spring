<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
</head>
<body>

</body>
<script>
    var tbl_userList = new Array();
    tbl_userList.push({username:"zhangsan",passwold:"12345"});
    tbl_userList.push({username:"lisi",passwold:"67890"});


    $.ajax({
        type:"POST",
        url:"${pageContext.request.contextPath}/user/quick14",
        data:JSON.stringify(tbl_userList),
        contentType:"application/json;charset=utf-8"

    });
    console.log("2");

</script>
</html>
