<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="layout" content="main"/>
		<title>Index Page for AkkaController</title>
	</head>
	<body>
		<div>
			<h2>Info of current Akka System: ${akkaSystem}</h2><br/>

			<ul>
				<li>
					Name: ${akkaSystem.name}
				</li>
				<%--
				<li>Settings: ${akkaSystem.settings}</li>
				//--%>
			</ul>
			<br/>
		</div>
		<hr/>
		<br/>
		
		<div>
			<h4>GreetingActor: ${greetingActor}</h4><br/>
			<ul>
				<li>
					Path: ${greetingActor.path}
				</li>
			</ul>
			<br/>
		</div>
		<hr/>
		<br/>
		
	</body>
</html>
