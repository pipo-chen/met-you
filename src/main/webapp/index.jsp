<%@ page language="java" import="java.util.*" contentType="text/html; charset=GB2312" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="zh">
<head>
    <meta charset="GB2312">
    <title>人员列表</title>
<%--    jquery--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $.get("/manage/staff/list", {
                username : "",
                pageNum : "1",
                pageSize:"38"
            }, function (data) {
                // 开始进行数据渲染
                render(data.data.list);
                // alert(data.data.list);
            });
        });
        function render(list) {
            let html_st = ""
            for(const item of list) {
                const level = item['role'] == 0 ? "店主" : (item['role'] == 1 ? "大师" : "强力")
                const status = item['status'] == 1 ? "接单中" : "暂不接单"
                const imgPath = 'http://img.metyouedu.com/'+item['mainImage']

                html_st += "<div class='card'>"+
                    "<img src='"+imgPath+"'/>"+
                    " <span class='status'>"+status+"</span>"+
                "<p class='name'>"+item['username']+"</p><p>("+item['age']+")岁  级别: "+level+"</p>"+
                "<p>"+item['detail']+"</p></div>"
                console.log(html_st)
            }

            document.getElementById("content").innerHTML = html_st
        }
    </script>
</head>
<body>
<div id="content"></div>
</body>
</html>
<style>
    .card {
        width: 90%;
        height: 100%;
        border-radius: 20px;
        padding-left: 5%;
        padding-right: 5%;
        padding-top:2%;
        padding-bottom: 2%;
        margin-bottom: 4px;
        background-color: antiquewhite;
        font-size: 12px;
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
        background-color: #1D374B;
        padding-left: 10px;
        padding-right: 10px;
        line-height: 30px;
        margin-top: 10px;
        border-radius: 20px;
        color: white;
    }
    .detail {
        color: #1D374B;
    }
</style>


<%--<form name="form1" action="/metyou_backend_war/manager/product/upload" method="post" enctype="multipart/form-data">--%>
<%--    <input type="file" name="upload_file"/>--%>
<%--    <input type="submit" value="spring mvc submit"/>--%>
<%--</form>--%>
