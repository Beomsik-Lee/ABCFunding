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
<script>
// Encrypt email and password by RSA
function encrypt() {
	// Get email and password
	var email = document.getElementById("email");
	var pwd = document.getElementById("password");
	
	// Create RSA insatance
	var rsa = new RSAKey();
	
	// Set public key by exponent and modulus
	rsa.setPublic("10001", "a5261939975948bb7a58dffe5ff54e65f0498f9175f5a09288810b8975871e99af3b5dd94057b0fc07535f5f97444504fa35169d461d0d30cf0192e307727c065168c788771c561a9400fb49175e9e6aa4e23fe11af69e9412dd23b0cb6684c4c2429bce139e848ab26d0829073351f4acd36074eafd036a5eb83359d2a698d3");
	
	// Set encrypted value
	email.value = rsa.encrypt(email.value);
	pwd.value = rsa.encrypt(pwd.value);
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
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="pwd">
            </div>
            <div class="form-group text-center">
                <c:if test="${isFail}">
                   <p id="isFail"><strong>It's invalid. Check your email or password.</strong></p>
                </c:if>
                <input id="login-button" class="btn btn-primary" type="submit" onclick="encrypt()" name="login" value="Sign in">
                <a href="regi.do" class="btn btn-outline-primary">Sign up</a>
            </div>
          </form>
      </div>
    </section>
</body>
</html>