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
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
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
<input type="hidden" id="userKeyBuffer" />
<input type="hidden" id="userIdBuffer" />
    <div id="userLogin">
        <form class="layui-form" action="${pageContext.request.contextPath}/showPhoto.action" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">您的姓名！</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required  lay-verify="required" placeholder="您的姓名" autocomplete="off" class="layui-input">
                    <input type="hidden" name="userKey" id="userKey" />
                    <input type="hidden" name="userId" id="userId" />
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

    <script src="${pageContext.request.contextPath}/js/layui/layui.all.js"></script>
    <script>
        //表单提交
        layui.use(['form','jquery'], function(){
            var form = layui.form;
            //监听提交
            form.on('submit(formDemo)', function(data){
                var isSubmit = false;
                //layer.msg(JSON.stringify(data.field));
                //imageUpload();
                layer.msg("玩命检索中...");
                $.ajax({
                    url: "${pageContext.request.contextPath}/checkNameAutoority.action",
                    method: "POST",
                    //contentType:"application/json",//默认键值对，上传
                    dataType: "json",
                    async:false,
                    data: "loginName=" + data.field.title,//键值对
                    success: function (resultData) {
                        if (resultData.imageExists == false && resultData.userExists == true) {
                            layer.msg("系统查询没有您的相册请上传...");
                            console.log("权限ok");
                            //缓存,上传图片的权限
                            $("#userKeyBuffer").val(resultData.userKey);
                            $("#userIdBuffer").val(resultData.userId);
                            //有用户权限，但是没有图片，显示上传
                            imageUpload();
                        } else if (resultData.imageExists == true && resultData.userExists == true) {
                            //缓存
                            $("#userKeyBuffer").val(resultData.userKey);
                            $("#userIdBuffer").val(resultData.userId);
                            //设置用于验证
                            $("#userKey").val(resultData.userKey);
                            $("#userId").val(resultData.userId);
                            console.log("权限和图片都ok");
                            isSubmit = true;
                            //form.render();
                        }
                        else{
                            layer.msg("对不起！您无权进入查看...");
                        }
                    }
                    , error: function (err) {
                        layer.msg("检索失败请重试...");
                    }
                });
                return isSubmit;
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
                $("#uploadUserKey").val($("#userKeyBuffer").val());
                //执行实例
                var uploadInst = upload.render({
                    elem: '#test1' //绑定元素
                    ,url: '${pageContext.request.contextPath}/addImages.action' //上传接口
                    ,multiple: true//多选
                    ,data:{
                        "userKey":$("#userKeyBuffer").val(),
                        "userId":$("#userIdBuffer").val()
                    }
                    ,number:10
                    // ,before:function () {
                    //     var addHtml = "<input type='hidden' value='"+$("#userKeyBuffer").val()+"' name='userKey' id='uploadUserKey' />";
                    //     $("#test1").parent().append(addHtml);
                    // }
                    ,done: function(res){
                        //上传完毕回调
                        if(res.result=="ok"){
                            console.log("路径："+res.lj);
                            console.log(res);
                            //上传ok给出提示
                            msg("上传成功！手机端多张图片请分次上传！");
                            if($("#imgtz").val()==undefined){
                                $("#userLogin").append("<button id='imgtz' onclick='showHome()' class='layui-btn'>上传完成，跳转主页</button>");
                            }
                        }else if(res.result="exceed"){
                            msg("上传图片超过限制，最大20张！");
                            if($("#imgtz").val()==undefined){
                                $("#userLogin").append("<button id='imgtz' onclick='showHome()' class='layui-btn'>上传完成，跳转主页</button>");
                            }
                        }
                        else
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
        function showHome(){
            console.log("进入");
            //动态创建form，并且提交
            var decForm = $("<form></form>");
            decForm.prop("action","${pageContext.request.contextPath}/showPhoto.action");
            decForm.prop("method","post");
            var parVal = $("<input type='text' name='userKey' />");
            var parVal2 = $("<input type='text' name='userId' />");
            parVal.prop("value",$("#userKeyBuffer").val());
            parVal2.prop("value",$("#userIdBuffer").val());
            decForm.append(parVal);
            decForm.append(parVal2);
            decForm.appendTo("body");
            decForm.css("display","none");
            decForm.submit();
            return false;
        }
    </script>
</body>
</html>
