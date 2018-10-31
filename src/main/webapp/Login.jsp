<%--
  Created by IntelliJ IDEA.
  User: trytheworld
  Date: 2018/10/25
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>登陆</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <%-- bootstrap中的文件上传模块不知道为什么无法使用，每次打开页面会把页面变为一片空白
        这里改用layui的文件上传模块
     --%>
    <link rel="stylesheet" href="js/layui/css/layui.css">
    <style>
        *{
            padding: 0px;
            margin: 0px;
        }
        #userLogin{
            width:50%;
            height:40%;
            margin:10% auto;
        }
    </style>
</head>
<body>

    <div id="userLogin">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">您的姓名！</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required  lay-verify="required" placeholder="您的姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
 <%--           <div class="layui-form-item">
                <label class="layui-form-label">密码框</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">辅助文字</div>
            </div>--%>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>

    <script src="js/layui/layui.all.js"></script>
    <script>
        //表单提交
        layui.use(['form','jquery'], function(){
            var form = layui.form;
            //监听提交
            form.on('submit(formDemo)', function(data){
                //layer.msg(JSON.stringify(data.field));
                //imageUpload();
                layer.msg("玩命检索中...");
                $.ajax({
                    url: "/checkNameAutoority.action",
                    method: "POST",
                    //contentType:"application/json",//默认键值对，上传
                    dataType: "json",
                    data: "loginName=" + data.field.title,//键值对
                    success: function (data) {
                        console.log("--->" + data.imageExists);
                        if (data.imageExists == "false" && data.userExists == "true") {
                            //有用户权限，但是没有图片，显示上传
                            imageUpload();
                        } else if (data.imageExists == "true" && data.userExists == "true") {
                            console.log("提交");
                        }
                    }
                    , error: function (err) {
                        layer.msg("检索失败请重试...");
                    }
                });
                //console.log(data.field);
                return false;
            });
        });
        //判断是否存在图片，如果不存在则显示上传框
        function imageUpload(){
            //添加控件到页面中
            var contentShow = "<button type='button' class='layui-btn' id='test1'>\n" +
                "<i class='layui-icon'>&#xe67c;</i>上传图片\n" +
                "</button>";
            //清空原始内容
            $("#userLogin").empty();
            //添加新内容
            $("#userLogin").append(contentShow);
            //图片上传
            layui.use('upload', function(){
                var upload = layui.upload;

                //执行实例
                var uploadInst = upload.render({
                    elem: '#test1' //绑定元素
                    ,url: '/addImages.action/' //上传接口
                    ,multiple: true//多选
                    ,done: function(res){
                        layer.msg('玩命提示中');
                        //上传完毕回调
                        if(res.result=="ok"){
                            //上传ok给出提示
                            msg("上传成功！");
                        }else
                        {
                            msg("上传失败！");
                        }

                    }
                    ,error: function(){
                        //请求异常回调
                        console.log("异常");
                    }
                });
            });
        }

        //提示弹窗
        function msg(msg){
            layer.open({
                content: msg,
                yes: function(index, layero){
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                    //console.log("====>");
                }
            });
        }
    </script>
</body>
</html>
