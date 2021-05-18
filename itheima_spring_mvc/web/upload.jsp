<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>单文件上传：</p>
    <form action="${pageContext.request.contextPath}/user/quick20" method="post" enctype="multipart/form-data">
        名称：<input type="text" name="username"><br/>
        文件1：<input type="file" name="uploadFile"><br/>
        <input type="submit" value="提交">
    </form>
    <br/>
    <br/>
    <p>多文件上传：</p>
    <form action="${pageContext.request.contextPath}/user/quick21" method="post" enctype="multipart/form-data">
        名称：<input type="text" name="username"><br/>
        文件1：<input type="file" name="uploadFile"><br/>
        文件2：<input type="file" name="uploadFile"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
