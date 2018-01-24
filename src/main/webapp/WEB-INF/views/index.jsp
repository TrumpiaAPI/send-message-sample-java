<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<!-- Bootstrap framework -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			  crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=Cp1252">
	
	<title>Send a Test Message</title>

</head>

<body>
<div class="container">
		<center>
			<h2>Send a Test Message</h2>		
			<p>Use this form to test the power of SMS!</p>
		</center>
	<form class="form-horizontal" method="post" action ="request">
	  <div class="form-group">
	    <label for="mobileNumber" class="col-sm-2 control-label">Mobile Number</label><span class="error"></span>
	    <div class="col-sm-10">
	      <input type="text" name="mobileNumber" class="form-control" id="mobileNumber" pattern="^\d{10}$" placeholder="5554443333" required>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="message" class="col-sm-2 control-label">Message</label><span class="error"><?php echo $messageErr;?></span>
	    <div class="col-sm-10">
	      <input type="text" name="message" class="form-control" id="message">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	    </div>
	  </div>
	  <div><center><b>Summary Terms & Conditions:</b> By clicking submit you agree to receive 1 text message. Message and data rates may apply. Text STOP to opt out. For help, Text HELP.</center>
	</div>
	</form>
</div>
</body>
</html>
