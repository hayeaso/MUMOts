<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<style>
.page-header{
background-color: #2d3f50;
}
.page-logo{
margin-left: 60px;}
</style>
<!-- FOR ANIMATION -->
<link href="<c:url value="/resources/css/jquerysctipttop.css"/>" rel="stylesheet" type="text/css"  />
<script type="text/javascript" src="<c:url value="/resources/js/jquery.fireworks.js"/>"> </script>

<div class="content" style="height:50%; width:100%;">
	<div class="portlet light" id="imgs" style="background-color: #2d3f50">
		<div  class="logo" style="text-align: center; height:450px; color:#33ba9c">
		<h1 id="animationSandbox" class="animated bold tada">You have Successfully Completed the Test. </h1>
		<h3 class="animated bold infinite bounceIn">Maharishi University of Mangagement</h3>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
    setTimeout(function() {
    	console.log("Fireworks should start");
        $('#imgs').fireworks();
    });
    
});
</script>
