<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!-- FOR ANIMATION -->
<link href="<c:url value="/resources/css/jquerysctipttop.css"/>" rel="stylesheet" type="text/css"  />
<script type="text/javascript" src="<c:url value="/resources/js/jquery.fireworks.js"/>"> </script>

<div class="content">
	<div class="portlet light">
		<h1>You have successfully completed the test!</h1>
		<p>Maharishi University of Management</p>
		<div id="imgs" class="logo" style="text-align: center;"><img src="<c:url value="/metronic/assets/MUM_Logo.png" />"></div>
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
