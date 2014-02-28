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
			<g:form name="actorForm">
				<span class="line">
					<label>Message for the Actor:</label>
					<g:textField name="message" value="${message}" placeholder="Write a text message for the Actor here" />
					<br/>
				</span>
				<span class="line">
					<g:actionSubmit value="Submit" action="index" />
					&nbsp;
					<input type="reset" value="Reset" />
					<br/>
				</span>
			</g:form>
			<br/>
		</div>
		<hr/>
		<br/>
		
	</body>
</html>
