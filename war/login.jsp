<%@page import="java.util.ArrayList"%>
<HTML>
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
                            	   var xml;
                            	  
                            	   if (typeof data == "string") {
                            		   if (navigator.appName == "Microsoft Internet Explorer") {
                            	   		   xml = new ActiveXObject("Microsoft.XMLDOM");
                            	    	   xml.async = false;
                            	    	   xml.loadXML(data);
                            		   } else {
                            			   xml = data;
                            		   }
                            	   } else {
                            	       xml = data;
                            	   }
                                    var numErrors = 0;
                                    $(xml).find("ERRORS").each(function() {                                    	
                                    	numErrors++;
                                    });
                                  
                                    if (numErrors > 0) {
                                    	//$('#loginErrors').show();
                                    	$('#loginErrors').hide();
                                    	$('#loginErrors').fadeIn("slow");
                                          //$("#errors").html(errors);
                                          //$("#errors").focus();
                                           //$('html, body').animate({scrollTop:
											//$("#errors").offset().top}, 'slow');
                                        retval = false;
                                    } else {
                                    	$('#loader').show();
                                    	$('#loginSuccessful').show();
                                    	window.location.reload();
                                        retval = true;
                                    }
                                }
            }  );
   
}
$(document).ready(
 function() {
	$('#loader').hide();
	$('#loginSuccessful').hide();
	$.ajaxSetup({
		  beforeSend: function() {
		     $('#loader').show();
		  },
		  complete: function(){
		     $('#loader').hide();
		  },
		  success: function() {}
		});
	 $('#loginErrors').hide();
 }
);
</script>
</head>
<BODY>
<DIV class="loginDescription">
<H1>Nos Iter</H1>
<DIV style="font-style: italic; display: inline">translation (from Latin)</DIV>: Journey with us<BR />
Nos iter is about connecting you to others by reaching into common groups and telling them want you want to do<BR/>
So you may have answered the question "What I am doing" ... at Nos Iter we go one step further
by inviting others to journey with us by asking:<BR/> 
"I want to ..."<BR/>
Post what you want to do in social groups and see how others connect with you.
<BR/>
<BR/>
<DIV class="loginDescriptionExamples">
<DIV class="loginDescriptionExampleItem">Mar 01/2011: I want to </DIV>go rockclimbing.  Anyone want to join?<BR/>
<DIV class="loginDescriptionExampleItem">Mar 04/2011: I want to </DIV>go running.  Tempo run @ 4min/km for 10k anyone?<BR/>
<DIV class="loginDescriptionExampleItem">Mar 07/2011: I want to </DIV>how to use JSF.  Hints/tips/comments?<BR/>
<DIV class="loginDescriptionExampleItem">Mar 11/2011: I want to </DIV>drive to Seattle from Vancouver.  If anyone can wants to carpool, let me know<BR/>
<DIV class="loginDescriptionExampleItem">Mar 11/2011: I want to </DIV>find a study group for the Science 11 final exam<BR/>
<BR/>
</DIV>
</DIV>

<form METHOD="POST" ACTION="/login" id="ajax-form">
<fieldset>

<DIV class="loginErrors" id="loginErrors">
Invalid Login/Password
</DIV>

<TABLE class="centerTable">
<TR>
<TD>Login:</TD>
<TD><INPUT TYPE="TEXT" NAME="login" VALUE="q8e192"/></TD>
</TR>

<TR>
<TD>Password:</TD>
<TD><INPUT TYPE="PASSWORD" NAME="password" VALUE="password"/></TD> 
</TR>

<TR>
<TD>
<INPUT TYPE="button" VALUE="Submit" onclick="ajaxSubmit()" />
</TD>
<TD>
<DIV ID="loader">
<DIV ID="loginSuccessful">Login Successful</DIV>
<IMG SRC="/public/gxt/images/gxt/icons/loading.gif"/>
</DIV>
<TD>
</TR>
</TABLE>
</fieldset>
</form>
<TABLE class="centerTable">
<TR>
<TD align="center"><A HREF="/Register.html">Create an Account</A>
</TR>
</TABLE>
</BODY>
</HTML>