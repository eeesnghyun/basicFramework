<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/styles/common.css">
</head>
<body>
    <main class="form-signin w-100 m-auto">
        <form method="post" action="/user/save">
            <h1 class="h3 mb-3 fw-normal text-center">회원가입</h1>

            <div class="form-floating">
                <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디">
                <label for="userId">아이디</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="userPw" name="userPw" placeholder="패스워드">
                <label for="userPw">패스워드</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="이름">
                <label for="userName">이름</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit">가입하기</button>
        </form>
    </main>
</body>
</html>