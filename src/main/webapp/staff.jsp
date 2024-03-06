<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 2024/3/4
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#submitBtn').click(function (e){
                e.preventDefault();
                var formData = {
                    username : $('#username').val(),
                    password : $('#password').val()
                };
                $.ajax({
                    url:'/user/login',
                    type:'POST',
                    data : formData,
                    dataType : 'json',
                    success : function (response) {
                        if (response['status'] === 0) {
                            alert("登录成功")
                            const html_st =  "<input class='btn' type='button' id='history' onclick='opeOrder()' value='1. 操作订单'>\n" +
                                "<input class='btn' type='button' onclick='payedOrder()' value='2. 查看已结算订单'>\n" +
                                "<input class='btn' type='button' onclick='unpayedOrder()' value='3. 查看待结算订单'>"
                            document.getElementById("login-container").innerHTML = html_st
                        } else {
                            alert(response['msg'])
                        }
                    },
                    error : function (xhr, status, error) {
                        alert(error)
                        document.getElementById("loginInfo").hidden = true;
                    }
                })
            })
        })

        function payedOrder() {
            $.get("/manage/sorder/search", {
                status : 4
            }, function (data) {
                // 开始进行数据渲染
                if (data.status !== 0) {
                    alert(data.msg)
                    document.getElementById('content').innerHTML = ""
                } else {
                    $.get("/manage/commission_record/total_payed", {
                    }, function (total) {
                        //对于获得的数据进行渲染
                        var str = "已结算佣金："+total.data
                        console.log(total.data)
                        read_render(data.data, str);
                    });

                }
            });
        }
        function unpayedOrder() {
            $.get("/manage/sorder/search", {
                status : 3
            }, function (data) {
                // 开始进行数据渲染
                if (data.status !== 0) {
                    alert(data.msg)
                    document.getElementById('content').innerHTML = ""
                } else {
                    $.get("/manage/commission_record/total_unpayed", {
                    }, function (total) {
                        //对于获得的数据进行渲染
                        var str = "未结算佣金："+ total.data
                        console.log(total.data)
                        read_render(data.data, str);
                    });
                }
            });
        }

        function date_change(dateNum) {
            var time = new Date(dateNum)
            var day =("0" + time.getDate()).slice(-2);
            var month = ("0" + (time.getMonth() + 1)).slice(-2);
            var final_str = time.getFullYear()+"-"+(month)+"-"+(day) ;
            return final_str
        }

        function opeOrder() {
            $.get("/manage/sorder/search", {
            }, function (data) {
                // 开始进行数据渲染
                if (data.status !== 0) {
                    alert(data.msg)
                    document.getElementById('content').innerHTML = ""
                } else {
                    //对于获得的数据进行渲染
                    render(data.data);
                }
            });
        }

        function read_render(data, str) {
            let html_str = "<p style='color: white'>单据总数: "+data.length+"   "+str+"</p>"
            for (const item of data) {
                let imgPath = "http://img.metyouedu.com/"+item['staffImg']
                st_str = date_change(item['beginTime'])
                ed_str = date_change(item['endTime'])
                const status = item['status'] === 1 ? "未开始" : (item['status'] === 2 ? "进行中" : (item['status'] === 3 ? "已结束" : "关闭"))

                html_str += "<div class='card'>\n" +
                    "  <img src="+imgPath+">\n" +
                    "  <span>订单号："+item['id']+"</span>\n" +
                    "  <span class='date'>"+status+"</span>\n" +
                    "  <p>商品名称："+item['commodityName']+"(数量："+item['commodityNum']+")</p>\n" +
                    "  <p>监督员："+item['supervisName']+"</p>\n" +
                    "  <p>学员："+item['username']+"("+item['wechat']+")</p>\n" +
                    "  <p>本单提成：¥"+item['commission']+"</p>\n" +
                    "  <p>开始时间："+st_str+" 结束时间："+ed_str+"</p>\n" +
                    "</div>"
            }
            document.getElementById('content').innerHTML = html_str
        }
        function render(data) {
            let html = "<p style='color: white'>单据总数: "+data.length+"</p>"
            for (const item of data) {
                let imgPath = "http://img.metyouedu.com/"+item['staffImg']
                st_str = date_change(item['beginTime'])
                ed_str = date_change(item['endTime'])
                let time = item['createTime']
                var sel_str = ""
                switch (item['status']) {
                    case 1:
                        sel_str = "      <option value='1' selected = 'selected'>未开始</option>\n" +
                            "      <option value='2'>进行中</option>\n" +
                            "      <option value='3'>已结束</option>\n" +
                            "      <option value='4'>已结算</option>"
                        break;
                    case 2:
                        sel_str = "      <option value='1'>未开始</option>\n" +
                            "      <option value='2' selected = 'selected'>进行中</option>\n" +
                            "      <option value='3'>已结束</option>\n" +
                            "      <option value='4'>已结算</option>"
                        break
                    case 3:
                        sel_str = "      <option value='1'>未开始</option>\n" +
                            "      <option value='2'>进行中</option>\n" +
                            "      <option value='3' selected = 'selected'>已结束</option>\n" +
                            "      <option value='4'>已结算</option>"
                        break
                    case 4:
                        sel_str = "      <option value='1'>未开始</option>\n" +
                            "      <option value='2'>进行中</option>\n" +
                            "      <option value='3'>已结束</option>\n" +
                            "      <option value='4' selected = 'selected'>已结算</option>"
                        break
                }
                html += "<div class='card'>\n" +
                    //如果单据状态是已结算保存的话，则不能再显示下面的select？
                    "    <img src="+imgPath+">\n" +
                    "    <span>订单号："+item['id']+"</span>\n" +
                    "    <select class ='status-sel'>"+sel_str+"</select>\n" +
                    "    <p>商品名称："+item['commodityName']+"(数量："+item['commodityNum']+")</p>\n" +
                    "    <p>监督员："+item['supervisName']+"</p>\n" +
                    "    <p>学员："+item['username']+"("+item['wechat']+")</p>\n" +
                    "    <p>本单提成：¥"+item['commission']+"</p>\n" +
                    "    <p>开始时间：<input class='starts' type='date' id='start' value="+st_str+">结束时间：<input class='ends' type='date' id='end' value="+ed_str+"></p>\n" +
                    "    <input class='save_btn' id='savebtn' type='button' value='保存'>"+
                    "  </div>"
            }
            document.getElementById('content').innerHTML = html
            var btns = document.getElementsByClassName("save_btn")
            for (var i = 0; i < btns.length; i++) {
                btns[i].index = i;
                btns[i].onclick = function () {
                    var select = document.getElementsByClassName('status-sel')
                    var starts_time = document.getElementsByClassName('starts')
                    var ends_time = document.getElementsByClassName('ends')
                    var sel = select[this.index]
                    var index=sel.selectedIndex
                    var order_id = data[this.index]['id']
                    var status = sel.options[index].value

                    var start_date = starts_time[this.index].value
                    var end_date = ends_time[this.index].value
                    var beginTime = start_date.replaceAll('-','/')
                    var endTime = end_date.replaceAll('-','/')
                    //保存接口
                    $.get("/manage/sorder/change_status", {
                        status : status,
                        order_id : order_id,
                        beginTime : beginTime,
                        endTime : endTime
                    }, function (data) {
                        // 开始进行数据渲染
                        if (data.status !== 0) {
                            alert(data.msg)
                        } else {
                            alert("信息更改成功")
                        }
                    });
                }
            }
        }
    </script>
</head>

<body>
<div id="response">
    <div id="login-container">
        <h2>登录</h2>
        <form id="loginInfo">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username"><br>
            </div>
            <div class="form-group">
                <label for="password">密码:</label>
                <input type="password" id="password" name="password"><br>
            </div>
            <button class="loginbtn" type="submit" id="submitBtn">登录</button>
        </form>
    </div>

    <div id="content">
    </div>
</div>

</body>
</html>
<style>
    #savebtn {
        background-color: cornflowerblue;
        padding-left: 20px;
        padding-right: 20px;
        margin-top: 5px;
    }
    .form-group input {
        height: 30px;
        border-radius: 30px;
    }
    .loginbtn {
        border-radius: 30px;
        width: 100%;
        height: 45px;
        background-color: black;
        color: white;
    }

    .btn {
        background-color: darkgreen;
        border-radius: 20px;
        border: none;
        color: white;
        height: 45px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        margin-top: 10px;

    }
    body {
        font-family: Arial, sans-serif;
        background-color: black;
        color: #333;
    }
    #login-container {
        max-width: 100%;
        padding: 20px;
        margin: 0 auto;
        background-color: #fff;
        box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        border-radius: 5px;
        overflow: hidden;
    }
    h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 15px;
    }
    label, input {
        display: block;
        width: 100%;
    }
    select {
        padding-top: 5px;
        padding-bottom: 5px;
        padding-left: 10px;
        padding-right: 10px;
    }
    input{
        padding-top: 5px;
        padding-bottom: 5px;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: black;
        color: #333;
    }
    .btn {
        background-color: darkgreen;
        border-radius: 20px;
        border: none;
        color: white;
        height: 45px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        margin-top: 10px;

    }
    img {
        width: 55px;
        height: 55px;
        border-radius: 25px;
    }
    .card {
        padding: 20px;
        border-radius: 20px;
        margin-top: 20px;
        background-color: #FACE8D;
    }
    .date {
        float: right;
        background-color: darkgreen;
        padding: 5px;
        color: white;
        border-radius: 20px;
    }

</style>
