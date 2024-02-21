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
                pageSize:"39"
            }, function (data) {
                // 开始进行数据渲染
                list = [
                    {
                        "username": "重楼（店主）",
                        "status": 1,
                        "detail": "6年圈龄，高冷男主，英国金融硕士小哥哥，目前全球五百强国内Top级券商在职，在校成绩优异，学分绩点A级，有雅思备考及留学经验，目标导向型，理性，要求严格。",
                        "mainImage": "chonglou.png",
                        "gender": "男"
                    },
                    {
                        "username": "锦书",
                        "status": 1,
                        "detail": "师范专业，曾是老师一枚，高冷严厉，下罚单的速度比你眨眼还快！实践经验丰富，你的人生导师，送给小朋友的话：努力和选择同样重要??",
                        "mainImage": "jinshu.png",
                        "gender": "男"
                    },
                    {
                        "username": "江寒",
                        "status": 1,
                        "detail": "6年圈龄，经济学硕士，暖男，阳光开朗三观正，擅长考研规划监督，考证监督，监督严厉且负责，管教督学经验丰富，原则性强，说一不二，不要讨价还价！",
                        "mainImage": "jianghan.png",
                        "gender": "男"
                    },
                    {
                        "username": "风开",
                        "status": 1,
                        "detail": "法学硕士，本科高分过司考、四六级，国内大型律所在职多年，擅长考研学习管教，督促你成长，带你玩真正纯粹的圈子。",
                        "mainImage": "fengkai.png",
                        "gender": "男"
                    },
                    {
                        "username": "子叶",
                        "status": 1,
                        "detail": "本科会计专业，考研经验丰富，主要方向为199管综。做事细致认真，平常要求比较严厉。凡事重在坚持，希望你能全神贯注、心无旁骛，在监督中进步。",
                        "mainImage": "ziye.png",
                        "gender": "男"
                    },
                    {
                        "username": "兔子",
                        "status": 1,
                        "detail": "央企在职，五年圈内实践经验，日常温柔体贴，犯错手黑严惩，小朋友要乖乖听话哦，摸摸头~你已经很努力了，不要否定自己。只要你需要，姐姐一直在。",
                        "mainImage": "tuzi.png",
                        "gender": "女"
                    },
                    {
                        "username": "南辞",
                        "status": 1,
                        "detail": "有考研经验，世界500强offer，大学共计获43项荣誉;目前IT行业在职，曾兼任高三艺考教师;对待任务追求质效双高，对心理学感兴趣;声音好听，平时会连麦沟通问题或抽背作业，睡前故事哄睡达人(另收费);监督经验丰富，态度认真，严宽并济，以关爱为名，期待与小朋友共同成长。",
                        "mainImage": "nanci.png",
                        "gender": "女"
                    },
                    {
                        "username": "浅梦",
                        "status": 1,
                        "detail": "10年圈龄，严主，QS前10商学硕士，全球500强在职，严格管理，果断决策，下罚单绝不手软专收听话的小朋友。准备好了就来和我体验一把不一样的监督吧~",
                        "mainImage": "qianmeng.png",
                        "gender": "女"
                    },
                    {
                        "username": "顾老师",
                        "status": 1,
                        "detail": "三年监督经验，理科疯批女(答疑另约)，疯狂会计考证人，专治不听话的小猫，服从性不好的别找我，可以是你的严主也可以是你的精神支柱，不喜背叛，找我就准备好我的逗猫棒。准备好了吗，我亲爱的小猫",
                        "mainImage": "gulaoshi.png",
                        "gender": "女"
                    },
                    {
                        "username": "苏打",
                        "status": 1,
                        "detail": "世界五百强国企管理岗，双一流大学毕业，曾获国家级英语竞赛一等奖，论文曾获国家级、省部级荣誉。幽默风趣知心姐姐，可严可飒风格多变。放弃很容易，但坚持很酷。惩罚不是目的只是手段，愿和你一起成长。",
                        "mainImage": "suda.png",
                        "gender": "女"
                    },
                    {
                        "username": "汐沫",
                        "status": 1,
                        "detail": "“我说过犯错误被抓到是要付出代价的。”入圈5年，偏爱管教，考研考公经验丰富，愿共同进步。",
                        "mainImage": "ximo.png",
                        "gender": "女"
                    },
                    {
                        "username": "时涧",
                        "status": 1,
                        "detail": "帅气小姐姐，互联网大厂产研，曾任金融算法团队负责人，转型python算法及机器学习教学老师，圈内经验丰富，幽默风趣。处事理性，监督风格以终为始，严厉负责。",
                        "mainImage": "shijian.jpg",
                        "gender": "女"
                    },
                    {
                        "username": "映雪",
                        "status": 1,
                        "detail": "温柔可爱善良，严厉认真负责，愿意用棍棒助你成长。三年监督经验，一年家教经验，考研、四六级、教资等考试全能小姐姐，陪你走过荆棘坎坷，一路生花。",
                        "mainImage": "yingxue.png",
                        "gender": "女"
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
<body>
<%--<a href="product.jsp">产品介绍</a>--%>
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
