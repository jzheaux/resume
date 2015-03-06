<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${model.profile.firstName} ${model.profile.lastName}</title>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/resume.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/rotated.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/resume.css"/>
</head>
<body data-resume-id="${model.id}" data-page-context="${pageContext.request.contextPath}">
	<div id="content">
		<header class="profile">
			<h3><a href="http://FreeHTMLtoPDF.com/?convert">Download as PDF</a></h3>
			<h1>${model.profile.firstName} ${model.profile.lastName}</h1>
			<h2>${model.profile.profession}</h2>
			<ul>
				<li><span class="email"><a data-src="${model.profile.emailRotated}" href="mailto:" class="rotated"></a></span></li>
				<li><span class="phone"><a data-src="${model.profile.phoneRotated}" href="tel:" class="rotated"></a></span></li>
				<li><span class="location"><a href="http://maps.google.com/maps/${model.profile.city},+${model.profile.state}">${model.profile.city}, ${model.profile.state}</a></span></li>
				<li><span class="facebook"><a href="http://facebook.com/${model.profile.facebookHandle}">Facebook</a></span></li>
				<li><span class="linkedin"><a href="http://linkedin.com/in/${model.profile.linkedinHandle}/en">LinkedIn</a></span></li>
				<li><span class="twitter"><a href="http://twitter.com/jzheaux">Twitter</a></span></li>
			</ul>
		</header>
		<section>
			<h1>Objective</h1>
			<p>${model.objective}</p>
		</section>
		<section>
			<h1>Work History</h1>
			<div id="jobs">
				<img src="${pageContext.request.contextPath}/resources/images/726.GIF">
			</div>
		</section>
		<section>
			<h1>Skills</h1>
			<div id="skills">
				<img src="${pageContext.request.contextPath}/resources/images/726.GIF">
			</div>
		</section>
		<section>
			<h1>Education</h1>
			<div id="degrees">
				<img src="${pageContext.request.contextPath}/resources/images/726.GIF">
			</div>
		</section>
	</div>
</body>
</html>