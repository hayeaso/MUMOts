<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<div class="content">
	<%-- <div class="portlet light">
		<h1>Welcome to Self Assesment System</h1>
		<p>Maharishi University of Management</p>
		<div class="logo" style="text-align: center;"><img src="<c:url value="/metronic/assets/MUM_Logo.png" />"></div>
	</div> --%>

  <div id="myCarousel" class="carousel slide" data-ride="carousel">
  
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="<c:url value="/resources/images/boat_02-cropped.jpg" />" alt="Boat 2" style="width:100%; height: 480px">
      </div>

      <div class="item">
        <img src="<c:url value="/resources/images/boat_04-cropped.jpg" />" alt="Boat 4" style="width:100%; height: 480px">
      </div>
    
      <div class="item">
        <img src="<c:url value="/resources/images/camping_01-cropped.jpg" />" alt="Camping 1" style="width:100%; height: 480px">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>

</div>
