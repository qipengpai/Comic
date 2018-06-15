$(function(){
	register();
});
function register(){
	$.ajax({
		   url: '/Comic/qpp/comic/login/admin.do' ,  /*这是处理文件上传的servlet*/  
	       type: 'POST',    
	       data:{
	    	   "adminName": $("#username").val(),
	    	   "adminPwd":$("#password").val()
	       },
	       success:function(data){
	    	   if(data.error == 200){
	    		   window.location.href == "index.html";
	    	   }
	       }
	      
		});
}