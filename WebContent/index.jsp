<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en-AU">

<head>
<title>Encoding</title>
<meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<div id="content">
		<div id="header">
			<div id="title">
				<h1>AVENIR | Encoding</h1>
			</div>
			<!-- <img src="images/bg/header_left.jpg" style="margin-left: 10%;" /> -->
		</div>

		<div id="mainMenu">
			<ul class="floatLeft">
				<li><a href="" class="here">Home</a></li>
				<li><a href="">Rules</a></li>
				<li><a href="">Contact</a></li>
				<li><a href="" class="last">Logout</a></li>
			</ul>
		</div>

		<div id="page">

			<div class="width25 floatRight leftColumn">

				<ul class="sideMenu">
					<div id="timer">
						<p align="center" id="timerText">Timer</p>
						<h1 align="center" id="quiz-time-left"></h1>
					</div>
				</ul>
			</div>

			<div class="width75 floatLeft">
				<div class="gradient">
					<div style="margin-top:30px">Write a program to check whether a number is even or odd.</div>
					<form id="fileform" action="Upload" method="post" enctype="multipart/form-data" align="center">
						<input type="file" name="program" id="file" />
						<input type="button" value="Upload" id="uploadbtn" />
					</form>
				</div>

				<div>
					<div id="success">
						<pre>
							<span id="result1"></span>
						</pre>
					</div>
					<div id="failure">
						<pre>
							<span id="result2"></span>
						</pre>
					</div>
				</div>
			</div>

		</div>

	</div>
	<div id="footer">

		<div id="width">
			<span class="floatLeft"> Copyright 2016-2017 </span> <span class="floatRight"> <a href="">home</a> <span class="grey">|</span> <a href="">about us</a> <span class="grey">|</span> <a href="">news</a>
				<span class="grey">|</span> <a href="">contact</a> <span class="grey">|</span> <a href="">links</a>
			</span>
		</div>
	</div>
	<script src="js/ajaxcompile.js" type="text/javascript"></script>
	<script src="js/timer.js" type="text/javascript"></script>
</body>
</html>