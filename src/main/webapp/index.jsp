<%@ page language="java" import="java.util.*" contentType="text/html; charset=GB2312" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="zh">
<head>
    <meta charset="GB2312">
    <title>��Ա�б�</title>
<%--    jquery--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $.get("/manage/staff/list", {
                username : "",
                pageNum : "1",
                pageSize:"39"
            }, function (data) {
                // ��ʼ����������Ⱦ
                render(data.data.list);
                // alert(data.data.list);
            });
        });
        function render(list) {
            let html_st = ""
            for(const item of list) {
                const level = item['role'] == 0 ? "����" : (item['role'] == 1 ? "��ʦ" : "ǿ��")
                const status = item['status'] == 1 ? "�ӵ���" : "�ݲ��ӵ�"
                const imgPath = 'http://img.metyouedu.com/'+item['mainImage']

                html_st += "<div class='card'>"+
                    "<img src='"+imgPath+"'/>"+
                    " <span class='status'>"+status+"</span>"+
                "<p class='name'>"+item['username']+"</p><p>����: "+level+" ���䣺("+item['age']+") �Ա�: ("+item['gender']+")</p>" +
                "<p>"+item['detail']+"</p></div>"
                console.log(html_st)
            }

            document.getElementById("content").innerHTML = html_st
        }
    </script>
</head>
<body>
<a href="product.jsp">��Ʒ����</a>
<div id="content"></div>
</body>
</html>
<style>
    .nav {
        list-style: none;
        display: flex;
        justify-content: space-evenly;
        height: 40px;
        align-items: center;
        background-color: #F8F8F8;
        border-radius: 20px;
    }
    li.active {
        color: #4D48C0;
        border-bottom:  2px solid #4D48C0;
    }
    .title {
        line-height: 40px;
        position: absolute;
        top: 10px;
        margin-left: 15px;
    }

    .card {
        width: 90%;
        height: 100%;
        border-radius: 20px;
        padding-left: 5%;
        padding-right: 5%;
        padding-top:2%;
        padding-bottom: 2%;
        background-color: antiquewhite;
        font-size: 12px;
        margin-bottom: 15px;
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
