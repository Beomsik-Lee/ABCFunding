<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head></head>
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
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required="required" />
                    </div>
                    <div class="mb-3">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="pwd" required="required" pattern="[0-9a-zA-Z!@#$]{6,20}"
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
                                <input type="radio" class="form-check-input" id="male" name="gender" value="m" checked="checked" />
                                <label class="form-check-label" for="male">Male</label>
                            </div>
                        </div>
                        <div class="col-lg-6 mb-3">
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="female" name="gender" value="f" />
                                <label class="form-check-label" for="female">Female</label>
                            </div>
                        </div>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="Sign Up" />
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>