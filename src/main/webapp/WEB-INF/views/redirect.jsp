<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

</head>
<body>
<h1>hehey</h1>
	<script type="text/javascript"> 
		var message = '${msg}'; 
		var returnUrl = '${url}'; 
		alert(message); 
		document.location.href = returnUrl; 
</script>
</body>
</html>