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
                list = [
                    {
                        "username": "��¥��������",
                        "status": 1,
                        "detail": "6��Ȧ�䣬����������Ӣ������˶ʿС��磬Ŀǰȫ�����ǿ����Top��ȯ����ְ����У�ɼ����죬ѧ�ּ���A��������˼��������ѧ���飬Ŀ�굼���ͣ����ԣ�Ҫ���ϸ�",
                        "mainImage": "chonglou.png",
                        "gender": "��"
                    },
                    {
                        "username": "����",
                        "status": 1,
                        "detail": "ʦ��רҵ��������ʦһö�������������·������ٶȱ���գ�ۻ��죡ʵ������ḻ�����������ʦ���͸�С���ѵĻ���Ŭ����ѡ��ͬ����Ҫ??",
                        "mainImage": "jinshu.png",
                        "gender": "��"
                    },
                    {
                        "username": "����",
                        "status": 1,
                        "detail": "6��Ȧ�䣬����ѧ˶ʿ��ů�У����⿪�����������ó����й滮�ල����֤�ල���ල�����Ҹ��𣬹̶ܽ�ѧ����ḻ��ԭ����ǿ��˵һ��������Ҫ�ּۻ��ۣ�",
                        "mainImage": "jianghan.png",
                        "gender": "��"
                    },
                    {
                        "username": "�翪",
                        "status": 1,
                        "detail": "��ѧ˶ʿ�����Ƹ߷ֹ�˾���������������ڴ���������ְ���꣬�ó�����ѧϰ�̣ܽ�������ɳ������������������Ȧ�ӡ�",
                        "mainImage": "fengkai.png",
                        "gender": "��"
                    },
                    {
                        "username": "��Ҷ",
                        "status": 1,
                        "detail": "���ƻ��רҵ�����о���ḻ����Ҫ����Ϊ199���ۡ�����ϸ�����棬ƽ��Ҫ��Ƚ��������������ڼ�֣�ϣ������ȫ���ע�����������ڼල�н�����",
                        "mainImage": "ziye.png",
                        "gender": "��"
                    },
                    {
                        "username": "����",
                        "status": 1,
                        "detail": "������ְ������Ȧ��ʵ�����飬�ճ����������������ֺ��ϳͣ�С����Ҫ�Թ�����Ŷ������ͷ~���Ѿ���Ŭ���ˣ���Ҫ���Լ���ֻҪ����Ҫ�����һֱ�ڡ�",
                        "mainImage": "tuzi.png",
                        "gender": "Ů"
                    },
                    {
                        "username": "�ϴ�",
                        "status": 1,
                        "detail": "�п��о��飬����500ǿoffer����ѧ���ƻ�43������;ĿǰIT��ҵ��ְ�������θ����տ���ʦ;�Դ�����׷����Ч˫�ߣ�������ѧ����Ȥ;����������ƽʱ������ͨ�����鱳��ҵ��˯ǰ���º�˯����(���շ�);�ල����ḻ��̬�����棬�Ͽ��ã��Թذ�Ϊ�����ڴ���С���ѹ�ͬ�ɳ���",
                        "mainImage": "nanci.png",
                        "gender": "Ů"
                    },
                    {
                        "username": "ǳ��",
                        "status": 1,
                        "detail": "10��Ȧ�䣬������QSǰ10��ѧ˶ʿ��ȫ��500ǿ��ְ���ϸ�������Ͼ��ߣ��·�����������ר��������С���ѡ�׼�����˾�����������һ�Ѳ�һ���ļල��~",
                        "mainImage": "qianmeng.png",
                        "gender": "Ů"
                    },
                    {
                        "username": "����ʦ",
                        "status": 1,
                        "detail": "����ල���飬��Ʒ���Ů(������Լ)������ƿ�֤�ˣ�ר�β�������Сè�������Բ��õı����ң��������������Ҳ��������ľ���֧������ϲ���ѣ����Ҿ�׼�����ҵĶ�è����׼�����������װ���Сè",
                        "mainImage": "gulaoshi.png",
                        "gender": "Ů"
                    },
                    {
                        "username": "�մ�",
                        "status": 1,
                        "detail": "�������ǿ�������ڣ�˫һ����ѧ��ҵ��������Ҽ�Ӣ�ﾺ��һ�Ƚ�������������Ҽ���ʡ������������Ĭ��Ȥ֪�Ľ�㣬���Ͽ�쪷���䡣���������ף�����ֺܿᡣ�ͷ�����Ŀ��ֻ���ֶΣ�Ը����һ��ɳ���",
                        "mainImage": "suda.png",
                        "gender": "Ů"
                    },
                    {
                        "username": "ϫĭ",
                        "status": 1,
                        "detail": "����˵��������ץ����Ҫ�������۵ġ�����Ȧ5�꣬ƫ���̣ܽ����п�������ḻ��Ը��ͬ������",
                        "mainImage": "ximo.png",
                        "gender": "Ů"
                    },
                    {
                        "username": "ʱ��",
                        "status": 1,
                        "detail": "˧��С��㣬�������󳧲��У����ν����㷨�ŶӸ����ˣ�ת��python�㷨������ѧϰ��ѧ��ʦ��Ȧ�ھ���ḻ����Ĭ��Ȥ���������ԣ��ල�������Ϊʼ����������",
                        "mainImage": "shijian.jpg",
                        "gender": "Ů"
                    },
                    {
                        "username": "ӳѩ",
                        "status": 1,
                        "detail": "����ɰ��������������渺��Ը���ù�������ɳ�������ල���飬һ��ҽ̾��飬���С������������ʵȿ���ȫ��С��㣬�����߹�����������һ·������",
                        "mainImage": "yingxue.png",
                        "gender": "Ů"
                    }
                ]
                render(list)
                    // render(data.data.list);
                // alert(data.data.list);
            });
        });
        function render(list) {
            let html_st = ""
            for(const item of list) {
                const status = item['status'] == 1 ? "�ӵ���" : "�ݲ��ӵ�"
                const imgPath = 'http://img.metyouedu.com/'+item['mainImage']

                html_st += "<div class='card'>"+
                    "<img src='"+imgPath+"'/>"+
                    " <span class='status'>"+status+"</span>"+
                "<p class='name'>"+item['username']+"</p><p>�Ա�: ("+item['gender']+")</p>" +
                "<p>"+item['detail']+"</p></div>"
                console.log(html_st)
            }

            document.getElementById("content").innerHTML = html_st
        }
    </script>
</head>
<body>
<%--<a href="product.jsp">��Ʒ����</a>--%>
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
        color: white;
    }

    .card {
        width: 90%;
        height: 100%;
        border-radius: 20px;
        padding-left: 5%;
        padding-right: 5%;
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
