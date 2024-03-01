<%@ page language="java" import="java.util.*" contentType="text/html; charset=GB2312" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="zh">
<head>
    <meta charset="GB2312">
    <title>紫云阁人员列表</title>
<%--    jquery--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>

        $(function () {
            $.get("/manage/staff/list", {
                username : "",
                pageNum : "1",
                pageSize:"39",
                belong:'2',
            }, function (data) {
                // 开始进行数据渲染
                render(data.data.list);
            });
        });
        function render(list) {
            let html_st = ""
            for(const item of list) {
                const status = item['status'] == 1 ? "接单中" : "暂不接单"
                const imgPath = 'http://img.metyouedu.com/'+item['mainImage']

                html_st += "<div class='card'>"+
                    "<img src='"+imgPath+"'/>"+
                    " <span class='status'>"+status+"</span>"+
                "<p class='name'>"+item['username']+"</p><p>性别: ("+item['gender']+")</p>" +
                "<p>"+item['detail']+"</p></div>"
                console.log(html_st)
            }

            document.getElementById("content").innerHTML = html_st
        }
    </script>
</head>
<body">
<div class="header">
    <ul class="ul-nav">
        <li class="active"><a href="index.jsp">首页</a></li>
        <li><a href="mainproduct.jsp">监督产品</a></li>
        <li><a href="product.jsp">配套产品</a></li>
        <li><a href="recharge.jsp">会员充值</a></li>
        <li><a href="login.jsp">会员登录</a></li>
    </ul>
</div>
<div id="content"></div>
</body>
</html>
<style>
    body {
        margin: 0;
        padding: 0;
        background-color: #080808;
        /*background-image: linear-gradient(#0A2432, #080808);*/
    }
    .active {
        background-color: darkred;
    }
    #content {
        width: 84%;
        margin-left: 8%;
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
    .title {
        line-height: 40px;
        position: absolute;
        top: 10px;
        margin-left: 15px;
        color: white;
    }

    .card {
        width: 80%;
        height: 100%;
        border-radius: 20px;
        padding-left: 10%;
        padding-right: 10%;
        padding-top:2%;
        padding-bottom: 2%;
        background-color: #373636;
        font-size: 12px;
        margin-bottom: 15px;
        color: white;
    }
    .card img{
        height: 80px;
        width: 80px;
        border-radius: 40px;
        margin-right: 2%;
        float: left;
    }
    .name {
        font: 14px bold;
    }
    .status {
        float: right;
        background-color: beige;
        padding-left: 10px;
        padding-right: 10px;
        line-height: 30px;
        margin-top: 10px;
        border-radius: 20px;
        color: #373636;
    }
    .detail {
        color: #1D374B;
    }

</style>


<%--<form name="form1" action="/metyou_backend_war/manager/product/upload" method="post" enctype="multipart/form-data">--%>
<%--    <input type="file" name="upload_file"/>--%>
<%--    <input type="submit" value="spring mvc submit"/>--%>
<%--</form>--%>
