<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="/jsp/header.jsp" %>
<div role="dialog" aria-labelledby="myModalLabel"> 
   <div class="modal-dialog"> 
    <div class="modal-content"> 
     <div class="modal-body">
      <form id="add-form-dialog" class="form-horizontal" role="form">
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问卷名称： </label> 
        <div class="col-sm-9"> 
         <input type="text" id="name" class="col-xs-8" value="${bean.name}"/><div id="name-tip"></div>
        </div> 
       </div>
       <div class="form-group"> 
        <label class="col-sm-3 control-label no-padding-right font" for="state">类型： </label> 
        <div class="col-sm-9"> 
          <c:choose>
		   <c:when test="${update eq 'update'}">
		   ${bean.tbSubjectType.name}
<!-- 		     <input type="text" id="name" class="col-xs-8" value="" readonly="readonly"/> -->
		   </c:when>
		   <c:otherwise><select class="chosen-select" data-placeholder="请选择" id="typeId"></select></c:otherwise>
		  </c:choose>
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问题1： </label> 
        <div class="col-sm-9"> 
          <c:choose>
		   <c:when test="${update eq 'update'}"> ${bean1.tbDiscipline.question}</c:when>
		   <c:otherwise><select  id="question1"></select></c:otherwise>
		  </c:choose>         
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问题2： </label> 
        <div class="col-sm-9"> 
          <c:choose>
		   <c:when test="${update eq 'update'}">${bean2.tbDiscipline.question}</c:when>
		   <c:otherwise><select  id="question2"></select></c:otherwise>
		  </c:choose>            
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问题3： </label> 
        <div class="col-sm-9"> 
          <c:choose>
		   <c:when test="${update eq 'update'}">${bean3.tbDiscipline.question}</c:when>
		   <c:otherwise><select  id="question3"></select></c:otherwise>
		  </c:choose>             
        </div> 
       </div> 
        <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问题4： </label> 
        <div class="col-sm-9"> 
          <c:choose>
		   <c:when test="${update eq 'update'}">${bean4.tbDiscipline.question}</c:when>
		   <c:otherwise><select  id="question4"></select></c:otherwise>
		  </c:choose>            
        </div> 
       </div>
       <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right font" for="name"> 问题5： </label> 
        <div class="col-sm-9"> 
          <c:choose>
		   <c:when test="${update eq 'update'}">${bean5.tbDiscipline.question}</c:when>
		   <c:otherwise><select  id="question5"></select></c:otherwise>
		  </c:choose>         
        </div> 
       </div>
       <!-- 警告框 -->
       <div id="warning-block"></div>
       <input type="hidden" id="typeId_update" value="${bean.tbSubjectType.id }">
       <input type="hidden" id="update" value="${update }">
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
				window.location.href="jsp/questionnaire/index.jsp";
		 });
		
    	$("#typeId").empty();
    	$typeId=$("#typeId_update").val();
    	//选择科目
    	$.ajax({
            type: "get",
            url: "subjecttype/list.html",
            cache:false,
            success: function(data) {
		   		var html = "<option value='-1' >请选择</option>";
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

    	$("#typeId").change(function(){
    		$("#question1").empty();
    		$("#question2").empty();
    		$("#question3").empty();
    		$("#question4").empty();
    		$("#question5").empty();
   			//获取题目
	    	$.ajax({
	            type: "get",
	            url: "discipline/list.html?id=&question=&typeId="+$("#typeId").val()+"&typeName=",
	            success: function(data) {
	        		$("#question1").empty();
	        		$("#question2").empty();
	        		$("#question3").empty();
	        		$("#question4").empty();
	        		$("#question5").empty();
// 		            console.log(data);
			   		var html = "";
	    	    	$.each(data.page.content, function(i, item) {
    	    			html +="<option value='"+item.id+"' >"+item.question+"</option>";
	    		    });
	    	    	$("#question1").append(html);
// 	    	    	$("#question1").chosen();
// 	    			$("#question1_chosen").css("width","330px");
	    	    	$("#question2").append(html);
// 	    	    	$("#question2").chosen();
// 	    			$("#question2_chosen").css("width","330px");
	    	    	$("#question3").append(html);
// 	    	    	$("#question3").chosen();
// 	    			$("#question3_chosen").css("width","330px");
	    	    	$("#question4").append(html);
// 	    	    	$("#question4").chosen();
// 	    			$("#question4_chosen").css("width","330px");
	    	    	$("#question5").append(html);
// 	    	    	$("#question5").chosen();
// 	    			$("#question5_chosen").css("width","330px");
	            }
	        });
        });
        
    	
		$("#ok").on('click',function() { //提交事件
			console.log($("#typeId").val());
			var surl ="questionnaire/add";
			var result = JSON.stringify({"name":$("#name").val(),
														"typeId":$("#typeId").val(),
														"question1":$("#question1").val(),
														"question2":$("#question2").val(),
														"question3":$("#question3").val(),
														"question4":$("#question4").val(),
														"question5": $("#question5").val()
								});
			//但是修改的时候
			if("update" == $("#update").val()){
// 				surl="questionnaire/edit";
// 				result= JSON.stringify({"question":$("#question").val(),
// 													"answers":$("#answers").val(),
// 													"id":$("#id").val(),
// 													"optionId1":$("#optionId1").val(),
// 													"optionId2":$("#optionId2").val(),
// 													"optionId3":$("#optionId3").val(),
// 													"optionId4":$("#optionId4").val(),
// 													"name1":$("#name1").val(),
// 													"name2":$("#name2").val(),
// 													"name3":$("#name3").val(),
// 													"name4":$("#name4").val(),
// 													"tpyeId": $("#typeId").val()
// 							});
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
			                    '<div class="success bold-center">'+data.msg+',<a href="jsp/questionnaire/index.jsp" class="green">'+
			                    '<span id="mysecond" class="green">'+5+
			                    '</span>秒自动跳转</a><div></div>');
		            	 countDown(5, "jsp/questionnaire/index.jsp");
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