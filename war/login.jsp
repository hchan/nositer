
<%@page import="java.util.ArrayList"%><HTML>
<head>
<link rel="stylesheet" type="text/css" href="/public/css/main.css" />
<script type="text/javascript" src="/public/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
function ajaxSubmit () {
	
            $.ajax ( {
                               url: "/login",
                               data: $("#ajax-form").serialize(),
                               //contentType: "text/html; charset=utf-8",
                               type: 'POST',
                               success: function(data){
                            	   
                                    var numErrors = $(data).find("ERRORS").size();
                                    
                                    if (numErrors > 0) {
                                    	$('#loginErrors').show();
                                          //$("#errors").html(errors);
                                          //$("#errors").focus();
                                           //$('html, body').animate({scrollTop:
											//$("#errors").offset().top}, 'slow');
                                         retval = false;
                                    } else {
                                    		window.location.reload();
                                           retval = true;
                                    }
                                }
            }  );
   
}
$(document).ready(function() {
	 $('#loginErrors').hide();
	});
</script>
</head>
<BODY><DIV class="loginDescription">Nos Iter<BR />
Journey with us<BR />
</DIV>

<form METHOD="POST" ACTION="/login" id="ajax-form">
<fieldset>

<DIV class="loginErrors" id="loginErrors">
Invalid Login/Password
</DIV>

<TABLE class="centerTable">
<TR>
<TD>Login (email address):</TD>
<TD><INPUT TYPE="TEXT" NAME="login" /></TD>
</TR>

<TR>
<TD>Password:</TD>
<TD><INPUT TYPE="PASSWORD" NAME="password" /></TD> 
</TR>

<TR>
<TD colspan="2">
<INPUT TYPE="button" VALUE="Submit" onclick="ajaxSubmit()" />
</TD>
</TR>
</TABLE>
</fieldset>
</form>
</BODY>
</HTML>