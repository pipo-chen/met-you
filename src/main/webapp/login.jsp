<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 2024/2/28
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>会员登录</title>
</head>
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
          const html_st = "<input class='btn' type='button' id='history' onclick='searchOrder()' value='1. 查询消费历史'>\n" +
                  "<input class='btn'  type='button' id='cards' onclick='searchCard()' value='2. 查询会员卡余额'>"
          alert("登录成功！")
          document.getElementById("login-container").innerHTML = html_st
        },
        error : function (xhr, status, error) {
          alert(error)
          document.getElementById("loginInfo").hidden = true;
        }
      })
    })
  })

  function searchCard() {
    $.get("/card/search", {
      status : "1",
    }, function (data) {
      // 开始进行数据渲染
      console.log(data.data);
      //获得会员卡相关信息 进行展示
      cards(data.data);
    });

  }

  function searchOrder() {
    $.get("/supervisor/order/search", {
      // payway : "4",
    }, function (data) {
      // 开始进行数据渲染
      console.log(data.data);
      //获得会员卡相关信息 进行展示
      render(data.data);
    });

  }
  function cards(list) {
    console.log(list)
    let html_st = ""
    // 0. 初始卡 1. 青铜卡； 2. 白银卡； 3. 钻石卡 4. 紫金卡
    for (const item of list) {
      const date = new Date(item['createTime'])
      let level = "其他"
      let color = ""
      switch (item['level']) {
        case 1:
          level = "青铜卡";
          color = "style='background-color:#E68787'"

          break
        case 2:
          level = "白银卡";
          color =  "style='background-color:#EFF0B5'"
          break
        case 3:
          level = "钻石卡";
          color =  "style='background-color:#B5C5F0'"
          break
        case 4:
          level = "紫金卡";
          color =  "style='background-color:#896EF2'"
          break;
      }
      html_st += "<div class='member_card' style='background-color: "+color+"'><span>"+date.getFullYear()+'-'+date.getMonth()+'-'+date.getDay()+'-'+item['id']+"</span>\n" +
              "    <h2>"+level+" ¥"+item['balance']+"元</h2>\n" +
              "  </div>"
    }
    document.getElementById("content").innerHTML = html_st

  }
  function render(list) {
    let html_st = ""
    for (const item of list) {
      // 1. 未开始；2.进行中；3.已结束 4. 关闭
      const status = item['status'] === 1 ? "未开始" : (item['status'] === 2 ? "进行中" : (item['status'] === 3 ? "已结束" : "关闭"))
      const payway = item['payway'] === 4 ? "会员卡" : (item['payway'] === 3 ? "微信" : (item['payway'] === 2 ? "淘宝" : "支付宝"))
      const date = new Date(item['createTime'])
      const imgPath = 'http://img.metyouedu.com/'+item['staffImg']
      let style_title = ""
      if (item['payway'] !== 4) {
        style_title += "style='display: none'"
      }
      html_st += "<div class=\"card\">\n" +
              "      <div>\n" +
              "        <img src="+imgPath+">\n" +
              "        <span>订单号："+date.getFullYear()+'-'+date.getMonth()+'-'+date.getDay()+'-'+item['id']+"</span>\n" +
              "        <span class=\"date\">"+status+"</span>\n" +
              "        <p>商品名称："+item['commodityName']+"("+item['originPrice']+")</p>\n" +
              "        <p>监督员："+item['supervisName']+"</p>\n" +
              "        <div>\n" +
              "          <p>支付方式："+payway+"  实付金额：¥"+item['salePrice']+"元</p>\n" +
              "          <p "+style_title+">会员卡号："+item['cardId']+"  会员卡余额：¥"+item['balance']+"元</p>\n" +
              "        </div>\n" +
              "      </div>\n" +
              "    </div>"
    }
    document.getElementById("content").innerHTML = html_st
  }

</script>
<body>
<div class="header">
  <ul class="ul-nav">
    <li class="active"><a href="index.jsp">首页</a></li>
    <li><a href="mainproduct.jsp">监督产品</a></li>
    <li><a href="product.jsp">配套产品</a></li>
    <li><a href="recharge.jsp">会员充值</a></li>
    <li><a href="login.jsp">会员登录</a></li>
  </ul>
</div>
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
  .member_card {
    background-color: #0A2432;
    border-radius: 20px;
    color: #f0f0f0;
    padding: 10px;
    margin-top: 10px;
  }
  .member_card p {
    text-align: center;
    padding: 20px;
  }

</style>