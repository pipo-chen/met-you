<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 2024/2/23
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>会员充值</title>
</head>
<body>
<div class="header">
  <ul class="ul-nav">
    <li><a href="index.jsp">首页</a></li>
    <li ><a href="mainproduct.jsp">监督产品</a></li>
    <li><a href="product.jsp">配套产品</a></li>
    <li class="active"><a href="recharge.jsp">会员充值</a></li>
  </ul>
</div>
<img src="http://img.metyouedu.com/member.png">
</body>
</html>
<style>
  img {
    width: 100%;
    height: 100%;
  }
  body {
    margin: 0;
    padding: 0;
    background-image: linear-gradient(#0A2432, #080808);
  }
  .active {
    background-color: darkred;
  }
  .header {
    padding: 10px;
    height: 50px;
    line-height: 50px;
    background-image: linear-gradient(#050505, #0A2432);
  }
  ul {
    text-decoration: none;
    margin: 0;
    padding: 0;
  }
  .ul-nav li {
    list-style: none;
    display: inline-block;
    border-radius: 20px;
    padding-left:20px;
    padding-right: 20px;
  }
  a {
    width: 200px;
    height: 200px;
    text-decoration: none;
    color:white;
  }
</style>