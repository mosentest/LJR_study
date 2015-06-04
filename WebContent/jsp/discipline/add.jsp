<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问题： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="question" class="col-xs-8" value="${bean.question }"/><div id="name-tip"></div>
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 答案： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="answers" class="col-xs-8" value="${bean.answers }"/><div id="name-tip"></div>
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 选项1： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name1" class="col-xs-8" value="${bean1.content }"/><div id="name-tip"></div>
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 选项2： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name2" class="col-xs-8" value="${bean2.content}"/><div id="name-tip"></div>
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 选项3： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name3" class="col-xs-8" value="${bean3.content}"/><div id="name-tip"></div>
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 选项4： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name4" class="col-xs-8" value="${bean4.content}"/><div id="name-tip"></div>
        </div> 
       </div> 
       <div class="form-group"> 
        <label class="col-sm-3 control-label no-padding-right font" for="state">类型： </label> 
        <div class="col-sm-9"> 
          <select class="chosen-select" data-placeholder="请选择" id="typeId"></select>
        </div> 
       </div> 
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="typeId_update" value="${bean.tbSubjectType.id }">
       <input type="hidden" id="update" value="${update }">
       <input type="hidden" id="id" value="${bean.id }">
       <input type="hidden" id="optionId1" value="${bean1.id }">
       <input type="hidden" id="optionId2" value="${bean2.id }">
       <input type="hidden" id="optionId3" value="${bean3.id }">
       <input type="hidden" id="optionId4" value="${bean4.id }">
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
				window.location.href="jsp/discipline/index.jsp";
		 });
		
    	$("#typeId").empty();
    	$typeId=$("#typeId_update").val();
    	$.ajax({
            type: "get",
            url: "subjecttype/list.html",
            cache:false,
            success: function(data) {
		   		var html = "";
    	    	$.each(data.list, function(i, item) {
    	    		if(parseInt($typeId) == item.id){
        	    		html +="<option value='"+item.id+"' selected='selected'>"+item.name+"</option>";
            	    }else{
    	    			html +="<option value='"+item.id+"' >"+item.name+"</option>";
                	}
    		    });
    	    	$("#typeId").append(html);
    	    	$("#typeId").chosen();
    			$("#typeId_chosen").css("width","130px");
            }
        });
    	
		$("#ok").on('click',function() { //提交事件
			console.log($("#typeId").val());
			var surl ="discipline/add";
			var result = JSON.stringify({"question":$("#question").val(),
														"answers":$("#answers").val(),
														"name1":$("#name1").val(),
														"name2":$("#name2").val(),
														"name3":$("#name3").val(),
														"name4":$("#name4").val(),
														"tpyeId": $("#typeId").val()
								});
			//但是修改的时候
			if("update" == $("#update").val()){
				surl="discipline/edit";
				result= JSON.stringify({"question":$("#question").val(),
													"answers":$("#answers").val(),
													"id":$("#id").val(),
													"optionId1":$("#optionId1").val(),
													"optionId2":$("#optionId2").val(),
													"optionId3":$("#optionId3").val(),
													"optionId4":$("#optionId4").val(),
													"name1":$("#name1").val(),
													"name2":$("#name2").val(),
													"name3":$("#name3").val(),
													"name4":$("#name4").val(),
													"tpyeId": $("#typeId").val()
							});
			}
			console.log(result);
	        $.ajax({
	            type: "POST",
	            url: surl,
	            headers : {
			                'Accept' : 'application/json',
			                'Content-Type' : 'application/json;charset=utf-8'
		         } ,
	            data: result,
	            success: function(data) {
	            	if(data.success == true){
			            $("#warning-block").html('<div class="alert alert-block alert-success">'+
			                    '<button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>'+
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/discipline/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/discipline/index.jsp");
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