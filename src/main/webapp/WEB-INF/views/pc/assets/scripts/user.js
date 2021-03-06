/**
 *
 * Created by wynfrith on 15-6-6.
 */


$(".UserDel").on("click",function(){
    var id = this.getAttribute("data-id");
    var type = this.getAttribute("data-type");
    var option = this.getAttribute("data-option");
    var $this = $(this);
        if(option == ''){ //我的发布
            if(confirm("确定删除吗?")){
                $.ajax({
                    type:"POST",
                    url:"/user/"+type+"/del/"+id,
                    success:function(data){
                        if(data.result){
                            alert("删除成功");
                            window.location.href = '/user/'+type+'/mypost';
                        }
                    }
                });
            }

        }else if(option =='fav'){ //删除收藏
            if(confirm("确定取消收藏吗?")){
                $.ajax({
                    type:"POST",
                    url:"/user/"+type+"/fav/"+id,
                    success:function(data){
                        if(data.result){
                            alert("取消收藏成功");
                            window.location.href = '/user/'+type+'/myfav';
                        }
                    }
                });
            }

        }


});

//多选删除
$(".del_all").on("click",function() {
    var ids = '';
    var type = this.getAttribute("data-type");
    var option = this.getAttribute("data-option");
    console.log(option);
    $(".col_del_check").each(function () {
        if (this.checked == true) {
            ids += this.getAttribute("data-id") + ";";
        }
    });

    if (ids != '') {
        if(option == ''){
            if (confirm("确定要删除选择的帖子吗?")) {
                $.ajax({
                    type: "POST",
                    url: "/user/" + type + "/del/" + "0",
                    data: {ids: ids},
                    success: function (data) {
                        if (data.result) {
                            alert("删除成功");
                                window.location.href = '/user/'+type+'/mypost';
                        }
                    }
                });
            }
        }else if(option == 'fav'){
            if (confirm("确定要取消收藏这些帖子吗?")) {
                $.ajax({
                    type: "POST",
                    url: "/user/" + type + "/fav/" + "0",
                    data: {ids: ids},
                    success: function (data) {
                        if (data.result) {
                            alert("取消收藏成功");
                            window.location.href = '/user/'+type+'/myfav';
                        }
                    }
                });
            }
        }

    }
});


$(document).ready(function(){
    $("#checkAll").click(function(){
        if (this.checked == true) {
            $(".col_del_check").each(function(){
                this.checked = true;
            })
        }else{
            $(".col_del_check").each(function(){
                this.checked = false;
            })
        }
    })
});
