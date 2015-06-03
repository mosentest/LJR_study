<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 文章标题： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="title" class="col-xs-8"/><div id="name-tip"></div>
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 文章内容： </label> 
        <div class="col-sm-9"> 
         <textarea rows="15" cols="29" id="content"></textarea>
        </div> 
       </div> 
       <div class="form-group"> 
        <label class="col-sm-3 control-label no-padding-right font" for="state">类型： </label> 
        <div class="col-sm-9"> 
          <select class="chosen-select" data-placeholder="请选择" id="type"></select>
        </div> 
       </div> 
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="typeId" value="${bean.tbSubjectType.id }">
      </form> 
     </div> 
     <div class="modal-footer"> 
      <button type="button" class="btn btn-primary btn-sm"  id="ok" autocomplete="off"  data-loading-text="正在处理中..." ><i class="icon-ok bigger-110" ></i>确定</button> 
      <button type="button" class="btn btn-success btn-sm" name="backid" id="backid">返回列表</button>
     </div> 
    </div> 
   </div> 
  </div>
</body>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="jsp/classrooms/index.jsp";
		 });
		
    	$("#type").empty();
    	$typeId=$("#typeId").val();
    	$.ajax({
            type: "get",
            url: "subjecttype/list.html",
            cache:false,
            success: function(data) {
		   		var html = "";
    	    	$.each(data.list, function(i, item) {
    	    		if(parseInt($typeId) == item.id){
        	    		html +="<option value='&typeId="+item.id+"' selected='selected'>"+item.name+"</option>";
            	    }else{
    	    			html +="<option value='&typeId="+item.id+"' >"+item.name+"</option>";
                	}
    		    });
    	    	$("#type").append(html);
    	    	$("#type").chosen();
    			$("#type_chosen").css("width","130px");
            }
        });
    	
		$("#ok").on('click',function() { //提交事件
	        $.ajax({
	            type: "POST",
	            url: "article/add",
	            data: "id="+$("#id").val()+"&name="+$("#name").val()+"&state="+$("#state").val(),
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/classrooms/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/article/index.jsp");
			        }
			        else{
					    $("#warning-block").html('<div class="alert alert-block alert-danger">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="danger bold-center">'+data.msg+'</div></div>');
				    }
	            },
	            error: function(XMLHttpRequest, textStatus, errorThrown) {
	                alert(XMLHttpRequest.status + "-" + XMLHttpRequest.readyState + "-" + textStatus);
	            }
	        });//ajax
 	   });//提交事件
    });
</script>
</html>