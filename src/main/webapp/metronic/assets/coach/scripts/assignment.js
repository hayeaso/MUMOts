function generateAccessCode(){
	
	if( $('#accessCode').val() ) {
		$("#errorMessage").empty();
		var msg ="<strong>Warning! </strong>Student has already been assigned the test. Please try in the next 24 hours. "		
		$("#errorMessage").append(msg);
		$("#errorMessage").removeClass("hidden");
		$("#errorMessage").show();
		return false;
	}
	$.ajax({
		url:'../../../onlinetest/assignment/generateTest',
		type:"GET",
		data: $("#dateTime").serialize(),
		success:function(data){			
			$("#accessCode").val(data.accessCode);
			//$("#accessLink").val(data.accessLink);			
		}
	});	
}


function assignmentDone(userId){

	if( !$('#accessCode').val() ) {
		$("#errorMessage").empty();
		var msg ="<strong>Warning!</strong> Access Code is empty, you can not save. Please Generate the code first."		
		$("#errorMessage").append(msg);
		$("#errorMessage").removeClass("hidden");
		$("#errorMessage").show();
		return false;
	}
	
	/*else if(!$("#sentEmail").val()){
		$("#errorMessage").empty();
		var msg ="<strong>Warning!</strong> You need to send test to student. Please send email "		
		$("#errorMessage").append(msg);
		$("#errorMessage").removeClass("hidden");
		$("#errorMessage").show();
		return false;
	}*/else if( !$('#dateTime').val() ) {
		$("#errorMessage").empty();
		var msg ="<strong>Warning!</strong> Please select date to schedule sending an email."		
		$("#errorMessage").append(msg);
		$("#errorMessage").removeClass("hidden");
		$("#errorMessage").show();
		return false;
	}
	
	$.ajax({ 
	   type: "POST",
	   url: '../../../onlinetest/coach/saveAssignment',
	   data:{
		   userId:userId,
		   accessCode :$('#accessCode').val(),
		   accessLink:$('#accessLink').val(),
		   dateTime:$("#dateTime").val()
	   },
	   success: function(data){        
		   
		   if(data === "success"){
			   alert("successsss");
			  // $('#myModal').modal('show');
			   window.location.href = "../../../onlinetest/coach/home";			   
		   }
		   else{
			   $("#errorMessage").empty();
				var msg ="<strong>Warning!</strong> Sending email was already scheduled when the test was created" 		
				$("#errorMessage").append(msg);
				$("#errorMessage").removeClass("hidden");
				$("#errorMessage").show();
				return false;
		   }
	   }
	});
}

/*function closeModalCoachAssignment(){
	window.location.href = "../../../onlinetest/coach/home";	
	self.close();
}*/

function assignmentCancel(){
	window.location.href = "../../../onlinetest/coach/home";
}

/* by Diana * not needed anymore, since the date is swcheduled and will run automatically
function sendEmail(userId){
	var msg;
	if( !$('#accessCode').val() ) {
		$("#errorMessage").empty();
		msg ="<strong>Warning!</strong> Access Code is empty, you can not send email, please generate code first"
		$("#successMessage").hide();
		$("#errorMessage").append(msg);
//		$("#errorMessage").removeClass("hidden");
		$("#errorMessage").show();
		return false;
	}else if( !$('#dateTime').val() ) {
		$("#errorMessage").empty();
		var msg ="<strong>Warning!</strong> Please select date and to schedule sending an email."		
		$("#errorMessage").append(msg);
		$("#errorMessage").removeClass("hidden");
		$("#errorMessage").show();
		return false;
	}
	
	$("#errorMessage").hide();
	$("#successMessage").empty();
	msg ="<strong>Success!</strong> Email has been sent successfully! "
	$("#successMessage").append(msg);
	//$("#successMessage").show();
	$("#sentEmail").val(true);
	assignmentDone(userId);
	
	$.ajax({
		url:'../../../onlinetest/coach/sendEmail',
		data:{
			 userId:userId,		
			 accessLink:$('#accessLink').val(),
			 accessCode:$('#accessCode').val(),
			 dateTime: $("#dateTime").val(),
			 email:$('#email').html(), 
		},
		type:"GET",
		success:function(data){
			if(data=="success"){
				$("#successMessage").show();
				console.log("email sent")
			}				 
		}
	});

}*/

function saveJobSearchStatusChange(userId){

$.ajax({ 
   type: "POST",
   url: '../../../onlinetest/coach/changeStudentJobSearchStatus',
   data:{
	   userId:userId,
	   jobSearchStatus :$('input[name=jobSearchStatus]:checked').val(),  
   },
   success: function(data){        	   
	   if(data === "success"){
		   window.location.href = "../../../onlinetest/coach/home";		   
	   }	   
   }
});

}