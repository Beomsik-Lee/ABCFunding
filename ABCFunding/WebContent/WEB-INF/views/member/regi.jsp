<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    
    // Set modulus and exponent of public key
    rsa.setPublic("${RSAModulus}","${RSAExponent}");
    
    // Set encrypted value
    document.forms[0].email.value = hex2b64(rsa.encrypt(email));
    document.forms[0].pwd.value = hex2b64(rsa.encrypt(pwd));
}
</script>
</head>
<body>
    <!-- Regi Section -->
    <section class="container" style="max-width: 800px;">
        <h1 class="pt-5 mb-5 text-center">Sign Up</h1>
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="addMember.do" method="post">
                    <div class="mb-3">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="plainEmail">Email</label>
                        <input type="email" class="form-control" id="plainEmail" name="plainEmail" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="plainPwd">Password</label>
                        <input type="password" class="form-control" id="plainPwd" name="plainPwd" required="required" pattern="[0-9a-zA-Z!@#$]{6,20}"
                        placeholder="Number / Upper and lower case / !@#$ / 6~20 " />
                    </div>
                    <div class="mb-3">
                        <label for="birthDate">Birth Date</label>
                        <input type="date" name="birth" class="form-control" id="birthDate" required="required" />
                    </div>
                    <div class="mb-2">
                        <span>Gender</span>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 mb-3">
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="male" name="gender" value="0" checked="checked" />
                                <label class="form-check-label" for="male">Male</label>
                            </div>
                        </div>
                        <div class="col-lg-6 mb-3">
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="female" name="gender" value="1" />
                                <label class="form-check-label" for="female">Female</label>
                            </div>
                        </div>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="Sign Up" onclick="encrypt()" />
                    </div>
                    <input type="hidden" name="email" value="" />
                    <input type="hidden" name="pwd" value="" />
                </form>
            </div>
        </div>
    </section>
</body>
</html>