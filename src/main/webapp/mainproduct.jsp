<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 2023/12/21
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>监督产品介绍</title>
</head>
<script>
    function window_onload() {
        list = [
            {
                "title" : "1. 作息/手机监督",
                "title_tag": "",
                "services": [
                    "监督早睡早起、起床打卡",
                    "通过睡前设备使用情况查寝",
                    "根据执行情况奖惩",
                    "根据要求系统随机匹配监督"
                ],
                "price": "¥89/周     ¥320/月",
                "price_tag" : ""
            },
            {
                "title" : "2. 基础常规监督(学习/减肥/运动)择一",
                "title_tag": "",
                "services": [
                    "纯打卡监督，无陪伴闲聊，学习打卡",
                    "根据完成度奖惩训诫",
                    "根据要求系统随机匹配监督"
                ],
                "price": "¥119/周     ¥428/月",
                "price_tag" : ""
            },
            {
                "title" : "3. 单项主贝监督🔥 （可指定）",
                "title_tag": "",
                "services": [
                    "学习/减肥/运动任选其一（30m-60m提醒）",
                    "开放在线自习室，每日计划执行落地",
                    "计划过程督促提醒、完成度、质量检查",
                    "复盘奖惩，选赠1次语音训诫/督惩(30min)"
                ],
                "price": "¥190/周     ¥625/月",
                "price_tag" : "会员低至¥581/月"
            },
            {
                "title" : "4. 模拟哥哥/姐姐（可指定）",
                "title_tag": "",
                "services": [
                    "高质管教，学习或生活三项左右(管教性质)",
                    "好友列表星标优先，定制性家规/家法、控单",
                    "结果奖惩，含1次视频/连麦督惩（30min）",
                    "严格督促的同时，也提供暖心陪伴"
                ],
                "price": "¥288/周     ¥1036/月",
                "price_tag" : "会员低至¥881/月"
            },
            {
                "title" : "5. 店主监督",
                "title_tag": "",
                "services": [
                    "单项主贝监督（¥219/周 ¥710/月）",
                    "模拟哥哥"
                ],
                "price": "¥1520/月",
                "price_tag" : ""
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
        <li class="active"><a href="mainproduct.jsp">监督产品</a></li>
        <li><a href="product.jsp">配套产品</a></li>
        <li><a href="recharge.jsp">会员充值</a></li>
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
    .content {
        width: 84%;
        margin-left: 8%;
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
