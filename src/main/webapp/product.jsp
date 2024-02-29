<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 2024/02/27
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>配套产品介绍</title>
</head>
<script>
    function window_onload() {
        list = [
            {
                "title" : "1. 周末监督卡（单项主贝）",
                "title_tag": "会员专享",
                "services": [
                    "周末两天监督",
                    "周末卡，不支持请假延期"
                ],
                "price": "指定产品 周卡价 * 5折  * 会员折扣",
                "price_tag" : ""
            },
            {
                "title" : "2. 工作日卡（单项主贝/模拟）",
                "title_tag": "会员专享",
                "services": [
                    "工作日每天可指定时段4h（要求时段必须连续，例19:00-23:00，当周时段一经确认，不可变更）",
                    "仅支持工作日使用5天，不支持请假延期",
                    "节假日无法使用"
                ],
                "price": "指定产品 周卡价 * 6.8折  * 会员折扣",
                "price_tag" : ""
            },
            {
                "title" : "3. 圈内树洞连麦",
                "title_tag": "",
                "services": [
                    "语音沟通，由资深圈主带你答疑解惑，技巧分享、帮助提供情感、关系处理等情绪树洞"
                ],
                "price": "¥168/小时",
                "price_tag" : ""
            },
            {
                "title" : "4. 圈内树洞文字",
                "title_tag": "",
                "services": [
                    "文字交流，帮助提供情感、关系等情绪树洞"
                ],
                "price": "¥128/小时",
                "price_tag" : ""
            },
            {
                "title" : "5. 语音/连麦督惩",
                "title_tag": "",
                "services": [
                    "语音连麦，在线协助执行惩罚（可配套监督）"
                ],
                "price": "¥100/40分钟",
                "price_tag" : ""
            },{
                "title" : "6. 视频督惩",
                "title_tag": "",
                "services": [
                    "视频连麦，在线协助执行惩罚（可配套监督），在线实践"
                ],
                "price": "¥148/40分钟",
                "price_tag" : "会员低至¥85"
            },{
                "title" : "7. 语音/视频抽背",
                "title_tag": "",
                "services": [
                    "知识点发送，根据内容进行抽背，可配套监督"
                ],
                "price": "¥25/20分钟",
                "price_tag" : "会员低至¥21"
            }
        ]
        render(list)
    }
    function render(list) {
        let html_st = ""
        for(const item of list) {
            service_str = ""
            for (const service of item['services']) {
                service_str +='<li>'+service+'</li>'
            }
            style_price = ''
            if (item['price_tag']=='') {
                style_price += "style='display: none'"
            }
            style_title = ''
            if (item['title_tag'] =='') {
                style_title += "style='display: none'"
            }

            html_st += "<li><p class='li-title'>"+item['title']+"</p><span class='title-tag'"+style_title+">"+item['title_tag']+"</span><ul class='ul-detail-content'>" +
                service_str+"</ul><p class='li-price'>"+item['price']+"</p><span class='price-tag'"+style_price+" >" +item['price_tag']+"</span></li>"

        }

        document.getElementById("ul-detail").innerHTML = html_st
    }

</script>
<body onload="window_onload()">
<div class="header">
    <ul class="ul-nav">
        <li><a href="index.jsp">首页</a></li>
        <li><a href="mainproduct.jsp">监督产品</a></li>
        <li class="active"><a href="product.jsp">配套产品</a></li>
        <li><a href="recharge.jsp">会员充值</a></li>
        <li><a href="login.jsp">会员登录</a></li>
    </ul>
</div>
<div class="content">
    <ul id="ul-detail">
    </ul>
    <div class="footer">
        <p class="footer-note">
            注意事项：</br>
            1. 虚拟服务，一经售出概不退款；</br>
            2. 每周可请假一次，一次不得超过3天（一旦超期正常计费计时）；</br>
        </p>
    </div>
</div>
</body>
</html>
<style>
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

    img {
        width: 50px;
        height: 100px;
    }
    .ul-detail-content {
        padding-left: 30px;
        padding-right: 10px;
        padding-bottom: 10px;
        color: #7E7E7E;
        font-weight: lighter;
    }
    .content {
        width: 84%;
        margin-left: 8%;
    }
    #ul-detail {
        display: inline-block;
        width: 100%;
        text-decoration: none;
        margin: 0;
        padding: 0;
        /*background-color: #0A2432;*/
    }
    #ul-detail li {
        margin: 10px;
        font-size: 15px;
        background-color: white;
        border-radius: 20px;
    }
    .li-title {
        padding-top: 10px;
        padding-left: 10px;
        color: #231815;
        font-size: 18px;
        display: inline-block;
        height: 10px;
    }
    span {
        background-color: #272727;
        color: #FACE8D;
        font-size: 12px;
        padding: 5px;
        border-radius: 15px;
        margin-left: 10px;
    }
    .li-price {
        margin-top: -10px;
        padding-left: 20px;
        padding-bottom: 10px;
        color: darkred;
        display: inline-block;
    }
    .footer-note {
        margin-top: -10px;
        padding: 20px;
        font-size: 13px;
        color: #7E7E7E;
    }
</style>
