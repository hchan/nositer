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
                                    	window.location.reload();
                                        retval = true;
                                    }
                                }
            }  );
   
}
$(document).ready(
 function() {
	$('#loader').hide();
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
<DIV class="loginDescription">Nos Iter<BR />
translate: Journey with us<BR />
Helping you find others with common "I want to do ..."<BR/>


<DIV class="loginDescriptionExamples">
<DIV class="loginDescriptionExampleItem">Mar 01/2011: I want to </DIV>go rockclimbing.  Anyone want to join?<BR/>
<DIV class="loginDescriptionExampleItem">Mar 04/2011: I want to </DIV>go running.  Tempo run @ 4min/km for 10k anyone?<BR/>
<DIV class="loginDescriptionExampleItem">Mar 07/2011: I am trying to figure out</DIV>how to use JSF.  Hints/tips/comments?<BR/>
<DIV class="loginDescriptionExampleItem">Mar 11/2011: I am</DIV> driving to Seattle from Vancouver.  If anyone can wants to carpool, let me know<BR/>
<DIV class="loginDescriptionExampleItem">Mar 11/2011: I am looking for </DIV>a study group for the Science 11 final exam<BR/>
</DIV>
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
<TD>
<INPUT TYPE="button" VALUE="Submit" onclick="ajaxSubmit()" />
</TD>
<TD>
<DIV ID="loader">
<IMG SRC="/public/gxt/images/gxt/icons/loading.gif"/>
</DIV>
<TD>
</TR>
</TABLE>
</fieldset>
</form>
</BODY>
</HTML>