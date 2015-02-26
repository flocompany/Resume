<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<meta name="viewport" content="width=device-width"/>
		<meta name="description" content="admin"/>
        <meta charset="utf-8" />
		<style type="text/css">
				html, body {background: #181818; font-family: 'Lato', helvetica, arial, sans-serif; font-size: 16px; color: #222;}
				.container {
					overflow: auto;
				}

				.sidebar--left {
					width: 400px;
					float: left;
				}

				.sidebar--right {
					width: 400px;
					float: right;
				}

				.content {
					width: auto;
					overflow: hidden;
				}
				.error {
					font-family: 'Lato', helvetica, arial, sans-serif; 
					font-size: 16px; 
					color:red;
				}
		</style>
        <title>admin</title>
    </head>
	<body id="top">
	
	<div class="container">
		<form action="/florent_courtiade_admin" method="post">
			<input type="password" id="pwd" name="pwd">
			<input type="submit" value="Envoyer" >
			<span class="error">${error}</span>
			<br><br>
			<div class="sidebar--left">
			<textarea rows="40" cols="40" id="resume" name="resume">resume</textarea>
			</div>
			<div class="sidebar--right">
			<textarea rows="40" cols="40" id="css" name="css">css</textarea>
			</div>
			<div class="content">	
			<textarea rows="40" cols="40" id="cv" name="cv">cv</textarea>
			</div>
		</form>
	</div>
	
	</body>
</html>