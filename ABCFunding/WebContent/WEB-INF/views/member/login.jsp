<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- RSA scripts -->
<script src="js/jsbn.js"></script>
<script src="js/prng4.js"></script>
<script src="js/rng.js"></script>
<script src="js/rsa.js"></script>
<script src="js/base64.js"></script>
<script>
// Encrypt email and password by RSA
function encrypt() {
	// Get email and password
	var email = document.getElementById("plainEmail").value;
	var pwd = document.getElementById("plainPwd").value;
	
	// Create RSA insatance
	var rsa = new RSAKey();
	
	// Set public key by modulus and exponent
	rsa.setPublic("","");
	
	// Set encrypted value
	document.forms[0].email.value = hex2b64(rsa.encrypt(email));
	document.forms[0].pwd.value = hex2b64(rsa.encrypt(pwd));
}       
</script>
</head>
<body>
    <!-- login Section -->
    <section class="container text-center">
      <h1 class="pt-5">Sign in</h1>
      <div class="row">
          <form class="col-lg-5 my-5 mx-auto text-left" action="loginAf.do" method="post">
            <div class="form-group">
                <label for="plainEmail">Email address</label>
                <input type="email" class="form-control" id="plainEmail" name="plainEmail">
            </div>
            <div class="form-group">
                <label for="plainPwd">Password</label>
                <input type="password" class="form-control" id="plainPwd" name="plainPwd">
            </div>
            <div class="form-group text-center">
                <c:if test="${isFail}">
                   <p id="isFail"><strong>It's invalid. Check your email or password.</strong></p>
                </c:if>
                <input id="login-button" class="btn btn-primary" type="submit" onclick="encrypt()" name="login" value="Sign in">
                <a href="regi.do" class="btn btn-outline-primary">Sign up</a>
            </div>
            <input type="hidden" name="email" value="" />
            <input type="hidden" name="pwd" value="" />
          </form>
      </div>
    </section>
</body>
</html>