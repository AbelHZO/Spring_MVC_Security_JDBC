<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>	
<!DOCTYPE>
<html ng-app="usersapp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<title>Insert title here</title>
</head>
<body ng-controller="controlusers">

	<section>
		<form ng-submit="saveUser()" method="post">
			<input type="text" ng-model="usuario.usuario" placeholder="Usuario">
			<input type="text" ng-model="usuario.password" placeholder="Password">
			<input type="text" ng-model="usuario.email" placeholder="Email">
			<input type="submit" value="Guardar">
		</form>
	</section>

	<section>
		<div class="table">
			<div ng-repeat="usuario in listausurio" ng-clock>
				<div>{{usuario.idUser}}</div>
				<div>{{usuario.usuario}}</div>
				<div>{{usuario.password}}</div>
				<div>{{usuario.email}}</div>
				<div>
					 <select name="rol">
						<option value="">--SELECCIONE ROL--</option>
					 </select>
				</div>
			</div>
		</div>
	</section>
<script type="text/javascript">
"use strict"

angular.module("usersapp", []).service("serviceusers", [ "$http", function(h) {

	var headers = {};

	headers["X-CSRF-TOKEN"] = '${_csrf.token}';
	headers["${_csrf.parameterName}"] = '${_csrf.token}';
	headers["Content-Type"] = 'application/json; charset=utf-8';

	this.listUsers = function() {
		var json = {
			method : 'GET',
			url : "${pageContext.request.contextPath}/abelhzo/listuser",
			headers : headers
		}
		return h(json);
	}
	
	this.saveUser = function(user) {
		var json = {
			method: 'POST',
			url : "${pageContext.request.contextPath}/abelhzo/saveuser",
			headers : headers,
			data: user
		}
		
		return h(json);
	}

} ]).controller("controlusers", [ "$scope", "serviceusers", function(sco, ser) {
	
	ser.listUsers().success(function(data) {
		sco.listausurio = data;
	}).error(function(error) {

	});
	
	sco.saveUser = function() {

		ser.saveUser(sco.usuario).
		success(function(data) {
			alert(data)
		}).
		error(function(error) {
			
		});
	}
	
} ]);
</script>
</body>
</html>