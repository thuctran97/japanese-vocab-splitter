<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link href="css/account.css" rel="stylesheet" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<script
	src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.tab a').on('click', function(e) {
			e.preventDefault();

			$(this).parent().addClass('active');
			$(this).parent().siblings().removeClass('active');

			var href = $(this).attr('href');
			$('.forms > form').hide();
			$(href).fadeIn(500);
		});
	});
</script>

<body>
	<div class="forms">
		<ul class="tab-group">
			<li class="tab active"><a href="#login">Log In</a></li>
			<li class="tab"><a href="#signup">Sign Up</a></li>
		</ul>
		<form action="#" id="login">
			<h1>Login to JPReader</h1>
			<div class="input-field">
				<label for="email">Email</label> <input type="email" name="email"
					required="email" /> <label for="password">Password</label> <input
					type="password" name="password" required /> <input type="submit"
					value="Login" class="button" />
				<p class="text-p">
					<a href="#">Forgot password?</a>
				</p>
			</div>
		</form>
		<form action="#" id="signup">
			<h1>Sign Up to JPReader</h1>
			<div class="input-field">
				<label for="email">Email</label> <input type="email" name="email"
					required="email" /> <label for="password">Password</label> <input
					type="password" name="password" required /> <label for="password">Confirm
					Password</label> <input type="password" name="password" required /> <input
					type="submit" value="Sign up" class="button" />
			</div>
		</form>
	</div>


</body>
</html>

