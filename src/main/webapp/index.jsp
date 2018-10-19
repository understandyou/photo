<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相册</title>
</head>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <script src="js/xuehua.js"></script>
   <style>
        *{
            margin: 0;
            padding: 0;
            list-style: none;
        }
        html,body{
            width: 100%;
            height: 100%;
            background-color: black;
        }
         body{
            /*perspective: 1000px;
            -webkit-perspective: 1000px;  Safari 和 Chrome */
            overflow:hidden;
         } 
    /* 小屏幕（平板，大于等于 768px） */
    @media (min-width: 768px) { 
        .mycontent{
            width:580px;
            height: 800px;
            margin: 15% auto;
        }
       ul li{
			background-size: 620px 830px;
        }
     }

    /* 中等屏幕（桌面显示器，大于等于 992px） */
/*     @media (min-width: 992px) { 
        .mycontent{
            width: 800px;
            height: 80%;
            margin: 10% auto;
        }
     } */

    /* 大屏幕（大桌面显示器，大于等于 1200px） */
    @media (min-width: 1200px) { 
        .mycontent{
            width: 500px;
            height:600px;
            margin: 10px auto;
        }
       ul li{
			background-size: 500px 600px;
        }
     }
        .mycontent{
/*             width: 800px;
            height: 80%; */
            transition: all 1s;
            -moz-transition: all 1s; /* Firefox 4 */
            -webkit-transition: all 1s; /* Safari 和 Chrome */
            perspective: 1000px;
            -webkit-perspective: 1000px; /* Safari 和 Chrome */
        }
        ul,li{
            width: 100%;
            height: 100%;
            transform-origin: left bottom;
        }
        ul{
            position: relative;
            transform-style: preserve-3d;/* 标准 */
            -webkit-transform-style: preserve-3d;	/* Safari 和 Chrome */
            transition:all 1s;
            -moz-transition: all 1s; /* Firefox 4 */
            -webkit-transition: all 1s; /* Safari 和 Chrome */
        }
        
        /* ul:hover{
            transform: rotateX(70deg);
        } */
        ul li{
            background-color:black;
            position:absolute;
            top: 0;
            left:0;
            transform-origin: left bottom;
            transition:all 1s;
            background-repeat:no-repeat;
        }
		ul li:nth-child(5){
			background-image: url("imgs/psb1.jpg");
		}
		ul li:nth-child(4){
			background-image: url("imgs/psb2.jpg");
		}
		ul li:nth-child(3){
			background-image: url("imgs/psb3.jpg");
		}
		ul li:nth-child(2){
			background-image: url("imgs/psb4.jpg");
		}
		ul li:nth-child(1){
			background-image: url("imgs/psb5.jpg");
		}
		
    </style>
    <script>
    fn.snow({
        minSize: 5,
        maxSize: 30,
        newOn: 100,
        flakeSpeed: 5
    });
        var clickSum = 0;
        var isok = false;
        $(function(){
            $(".mycontent:nth-child(1)").click(function(){
                if(!isok){
                    $("ul").css({
                        //"-webkit-perspective": "1000px",
                        "transform":"rotateX(45deg)"//标准
                        ,"-ms-transfrom":"rotateX(45deg)"//ie
                        ,"-moz-transfrom":"rotateX(45deg)"/* Firefox */
                    });
                }
                else
                    return false;
                isok = true;
            });
            $("ul li").click(function(){
                if(!isok)return;
                var ds = $(this).attr("dushu");
                console.log("--"+ds);
                if(ds == 80){
                    $("ul,ul li").css({"transform":"none"});
                    isok = false;
                    return false;
                }
                $(this).css({
                    "transform": "rotateY(-"+ds+"deg)"//标准
                    ,"-ms-transfrom":"rotateX(-"+ds+"deg)"//ie
                    ,"-moz-transfrom":"rotateX(-"+ds+"deg)"/* Firefox */
                });
            });
            if(support_css3('transform')){
            	console.log("支持")
            }else{
            	$("body").html("<font color='red'>此浏览器不支持！请切换浏览器！</font>");

            }
            /* 记录 */
            $.ajax({
                url:"/addLog.action",
                type:"POST",
                data:"{ name: ${requestScope.username}},first:yes",
                success:function(result){

                }
            });
        });
        setInterval(function(){
            /* 记录5s */
            $.ajax({
                url:"/addLog.action",
                type:"POST",
                async:false,//关闭异步，方便调试
                data:"{ name: ${requestScope.username}},first:no",
                success:function(result){
                },
                error:function(err){
                    console.log(err);
                }
            });

        },5000);
        /* 检查是否支持某些属性 */
        var support_css3 = (function() {
		 var div = document.createElement('div'),
		  vendors = 'Ms O Moz Webkit'.split(' '),
		  len = vendors.length;
		 
		 return function(prop) {
		  if ( prop in div.style ) return true;
		 
		  prop = prop.replace(/^[a-z]/, function(val) {
		   return val.toUpperCase();
		  });
		 
		  while(len--) {
		   if ( vendors[len] + prop in div.style ) {
		   return true;
		   } 
		  }
		  return false;
		 };
		})();
    </script>
<body>
<div id="snowFlow" >
	 <div class="mycontent">
        <ul>
            <li dushu="80"></li>
            <li dushu="100"></li>
            <li dushu="120"></li>
            <li dushu="130"></li>
            <li dushu="150"></li>
        </ul>
    </div>
</div> 
</body>
</html>