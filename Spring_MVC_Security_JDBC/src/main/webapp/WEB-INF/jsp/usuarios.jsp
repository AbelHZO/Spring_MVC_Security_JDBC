<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE>
<html ng-app="usersapp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js" ></script>
<script type="text/javascript" src=""></script>
<link href="<c:url value="../resources/css/style.css" />" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body ng-controller="controlusers" ng-init="listUsers()">

	<section>
		<form ng-submit="saveUser()" method="post">
			<input type="text" ng-model="usuario.usuario" placeholder="Usuario">
			<input type="password" ng-model="usuario.password" placeholder="Password">
			<input type="email" ng-model="usuario.email" placeholder="Email">
			<input type="submit" value="Guardar">
		</form>
	</section>
	<br>
	<section>
		<div class="table">
			<div ng-repeat="usuario in listausurio" ng-clock>
				<div ng-click="getUser(usuario)">{{usuario.idUser}}</div>
				<div>{{usuario.usuario}}</div>
				<div>{{usuario.password}}</div>
				<div>{{usuario.email}}</div>
			</div>
		</div>
	</section>
	
	<div class="modalForm">
		<span>Cerrar x</span>	
		<div>
			<form ng-submit="updateUser()" method="post">
				<input type="hidden" ng-model="usuario.idUser">
				<input type="text" ng-model="usuario.usuario" placeholder="Usuario">
				<input type="password" ng-model="usuario.password" placeholder="Password">
				<input type="email" ng-model="usuario.email" placeholder="Email">
				<input type="submit" value="Modificar">
			</form>
		</div>
	</div>
	
	<div id="error"></div>
<script type="text/javascript">
"use strict"

var visible = false;
var modal = document.getElementsByClassName("modalForm")[0];
modal.addEventListener("click", function(evt) {
	if(visible) {
		$(".modalForm > span").css("display", "block");
	} else {
		this.style.display = 'none';
	}
	visible = !visible;
});

angular.module("usersapp", []).service("serviceusers", [ "$http", function(h) {

	var headers = {};

	headers["X-CSRF-TOKEN"] = '${_csrf.token}';
	headers["${_csrf.parameterName}"] = '${_csrf.token}';
	headers["Content-Type"] = 'application/json; charset=utf-8';
	
	this.saveUser = function(user) {
		var json = {
			method: 'POST',
			url: '${pageContext.request.contextPath}/abelhzo/saveuser',
			headers : headers,
			data: user
		};
		
		return h(json);
	}
	
	this.updateUser = function(user) {
		var json = {
			meyhod: 'POST',
			url: '${pageContext.request.contextPath}/abelhzo/updateuser',
			headers: headers,
			data: user
		};
		
		return h(json);
	}
	
	this.getUser = function(id) {
		var json = {
			method: 'POST',	
			url: '${pageContext.request.contextPath}/abelhzo/getuser/' + id,
			headers: headers
		};
		
		return h(json);
	}
	
	this.listUsers = function() {
		var json = {
			method : 'GET',
			url : '${pageContext.request.contextPath}/abelhzo/listuser',
			headers : headers
		};
		
		return h(json);
	}	

} ]).controller("controlusers", [ "$scope", "serviceusers", function(sco, ser) {
	
	sco.saveUser = function() {
		ser.saveUser(sco.usuario).
		success(function(data) {
			sco.listUsers();
			sco.usuario = {};
		}).
		error(function(error) {
			document.getElementById("error").innerHTML = error;
		});
	}
	
	sco.listUsers = function() {
		ser.listUsers().success(function(data) {
			sco.listausurio = data;
		}).error(function(error) {
			document.getElementById("error").innerHTML = error;
		});
	}
	
	sco.getUser = function(user) {
		ser.getUser(user.idUser).
		success(function(data) {
			modal.style.display = "block";
			sco.usuario = data;
		}).
		error(function(error) {
			document.getElementById("error").innerHTML = error;
		});
	}
	
	sco.updateUser = function() {
		ser.updateUser(sco.usuario).
		success(function(data) {
			sco.listUsers();
			sco.usuario = {};
		}).
		error(function(error) {
			document.getElementById("error").innerHTML = error;
		});
	}
	
} ]);
</script>
</body>
</html>