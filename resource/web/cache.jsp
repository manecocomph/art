<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="com.maneco.art.cache.jcs.JcsTester" %>

<html>
 <head>
  <title>we are here</title>
  <style type="text/css">
    <!--
    BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;font-size:12px;}
    H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;}
    PRE, TT {border: 1px dotted #525D76}
    A {color : black;}A.name {color : black;}
    -->
  </style>
  <script
  		function addQuestion() {
		alert('here');
		if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				alert("cool" + xmlhttp.responseText);
			}
		}
		var url = "../action/QuestionAction.php?type=add";
		var question = document.getElementById('question');
		url = url + '&question=' + question.value;
		alert(url);
		xmlhttp.open("GET", url,true);
		xmlhttp.send();
	} 
  srirpt/>
 </head>
 <body>
 you are here
 <%
 	JcsTester.testMaxObjects();
 %>
 
 <%= JcsTester.result %>
 </body>
 </html>