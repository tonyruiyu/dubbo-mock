<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>DIV CSS遮罩层</title>
    <script type="text/javascript" src="/mock_web/jquery/jquery-1.8.2.min.js"></script>

<!--     <script type="text/javascript" src="/mock_web/js/common/json.js"></script>
 --><script language="javascript" type="text/javascript">
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("show").style.display = "block";
	}
	function hidediv() {
		document.getElementById("bg").style.display = 'none';
		document.getElementById("show").style.display = 'none';
	}
</script>
<style type="text/css">
#bg {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.7;
	opacity: .70;
	filter: alpha(opacity = 70);
}

#show {
	display: none;
	position: absolute;
	top: 25%;
	left: 22%;
	width: 53%;
	height: 49%;
	padding: 8px;
	border: 8px solid #E8E9F7;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
</head>
<body>
	<input id="btnshow" type="button" value="Show" onclick="showdiv();" />
	<div id="bg"></div>
	<div id="show">
		测试 <input id="btnclose" type="button" value="Close"
			onclick="hidediv();" />
	</div>
	<textarea id="id1" rows="10" cols="200"></textarea>
	<input type="button" value=" o k " onclick="c1();"/>

	<script>
		var formatJson = function(json, options) {
			var reg = null, formatted = '', pad = 0, PADDING = '    '; // one can also use '\t' or a different number of spaces

			// optional settings
			options = options || {};
			// remove newline where '{' or '[' follows ':'
			options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true
					: false;
			// use a space after a colon
			options.spaceAfterColon = (options.spaceAfterColon === false) ? false
					: true;

			/* // begin formatting...
			if (typeof json !== 'string') {
				// make sure we start with the JSON as a string
				json = JSON.stringify(json);
			} else {
				// is already a string, so parse and re-stringify in order to remove extra whitespace
				json = JSON.parse(json);
				json = JSON.stringify(json);
			} */

			// add newline before and after curly braces
			reg = /([\{\}])/g;
			json = json.replace(reg, '\r\n$1\r\n');

			// add newline before and after square brackets
			reg = /([\[\]])/g;
			json = json.replace(reg, '\r\n$1\r\n');

			// add newline after comma
			reg = /(\,)/g;
			json = json.replace(reg, '$1\r\n');

			// remove multiple newlines
			reg = /(\r\n\r\n)/g;
			json = json.replace(reg, '\r\n');

			// remove newlines before commas
			reg = /\r\n\,/g;
			json = json.replace(reg, ',');

			// optional formatting...
			if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
				reg = /\:\r\n\{/g;
				json = json.replace(reg, ':{');
				reg = /\:\r\n\[/g;
				json = json.replace(reg, ':[');
			}
			if (options.spaceAfterColon) {
				reg = /\:/g;
				json = json.replace(reg, ':');
			}

			$.each(json.split('\r\n'), function(index, node) {
				var i = 0, indent = 0, padding = '';

				if (node.match(/\{$/) || node.match(/\[$/)) {
					indent = 1;
				} else if (node.match(/\}/) || node.match(/\]/)) {
					if (pad !== 0) {
						pad -= 1;
					}
				} else {
					indent = 0;
				}

				for (i = 0; i < pad; i++) {
					padding += PADDING;
				}

				formatted += padding + node + '\r\n';
				pad += indent;
			});

			return formatted;
		};
		
		function c1() {
			var a = document.getElementById("id1").value;
			alert(a);
			a = formatJson(a);
			alert(a);
		}
		
	</script>

</body>
</html>