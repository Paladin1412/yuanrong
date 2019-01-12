<%--
  Created by IntelliJ IDEA.
  User: zhonghang
  Date: 2018/6/30
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="containerQiniuCardPhoto">
<input type="button" id="uploadasCardPhoto" value="上传文件">
    <span id="qiniuProgressCardPhoto"></span>
    <input type="text" id="imgshidden">
</div>

</body>
<script src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/plupload/i18n/zh_CN.js"></script>
<script src="/js/qiniu/qiniu.js"></script>
<script type="application/javascript">
    $(function () {
        initQiniu();
    });
    function initQiniu(){
        <!--上传合同 -->
        var Q2 = new QiniuJsSDK();
        var uploader2 = Q2.uploader({
            runtimes: 'html5,flash,html4',
            browse_button: 'uploadasCardPhoto',
            container: 'containerQiniuCardPhoto',
            drop_element: 'containerQiniuCardPhoto',
            max_file_size: '100mb',
            flash_swf_url: '/js/plupload/Moxie.swf',
            dragdrop: true,
            chunk_size: '4mb',
            uptoken_url: '/upload/uploadToken',
            domain: 'http://files.yuanrongbank.com',
            unique_names: true,
            filters: [{
                title: 'ImageInfo',
                extensions: 'png,jpg,tif,jpeg'
            }],
            auto_start: true,
            init: {
                'FilesAdded': function (up, files) {
                    plupload.each(files, function (file) {
                        // 文件添加进队列后,处理相关的事情
                    });
                },
                'BeforeUpload': function (up, file) {
                    // 每个文件上传前,处理相关的事情
                    $("#uploadasCardPhoto").hide();
                },
                'UploadProgress': function (up, file) {
                    // 每个文件上传时,处理相关的事情
                    var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                    $("#qiniuProgressCardPhoto").text("上传进度：" + file.percent + "%；上传速度：" + formetFileSize(file.speed) + "/s ");
                },
                'FileUploaded': function (up, file, info) {
                    var domain = up.getOption('domain');
                    var res = $.parseJSON(info);
                    var sourceLink =domain + res.key;
                    if($("#imgshidden").val()){
                        $("#imgshidden").val($("#imgshidden").val()+","+sourceLink);
                    }else{
                        $("#imgshidden").val(sourceLink);
                    }

                    if($("#cardPhoto").val()){
                        $("#cardPhoto").val($("#cardPhoto").val()+"," + sourceLink);
                    }else{
                        $("#cardPhoto").val($("#imgshidden").val());
                    }

                    $("#uploadasCardPhoto").show();
                    var d=$("#cardPhoto").val();
                    var s=d.split(',');

                    var htmls="<tr id='imgsshow'><td  colspan='4'>";
                    for(var i=0;i<s.length;i++){
                        htmls+="<a href='"+s[i]+"'  target='_blank'><img src='"+s[i]+"?imageView2/1/w/100/h/100"+"' /></a>";
                    }
                    htmls+="</td></tr>";
                    $("#imgsshow").remove();
                    $("#containerQiniuCardPhoto").append(htmls);
                },
                'Error': function (up, err, errTip) {
                    //上传出错时,处理相关的事情
                    $("#uploadasCardPhoto").show();
                    $("#qiniuProgressCardPhoto").text("错误：" + errTip);
                },
                'UploadComplete': function () {
                    //队列文件处理完毕后,处理相关的事情
                },
                'Key': function (up, file) {
                    // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                    // 该配置必须要在 unique_names: false , save_key: false 时才生效

                    var key = "";
                    // do something with key here
                    return key
                }
            }
        });
    }
</script>
</html>
