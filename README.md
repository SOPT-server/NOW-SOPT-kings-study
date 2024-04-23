# NOW-SOPT-kings-study
안녕하슈, 서버 고수들의 심화스터디에유

#### 과제 제출 가이드라인

`본인이름/weekN` 이렇게 브랜치를 생성한 후 PR로 과제를 제출해주세요! 
ex ) `sohyun/week1`

PR에 구현 사항 & 질문 요소들을 남겨주세요!
정규 스터디 시간에 함께 나눠봅시다!

과제를 완성하지 못해다면, 구현한 기능까지 설명해주세요-!

## 1차 과제 가이드 라인

# OAuth 2.0 간단 실습 - 구글 프로필 정보 가져오기

OAuth 2.0을 이용해 구글 로그인 후, 프로필 정보를 가져와 출력하는 간단한 실습을 해보겠읍니다.

저번 스터디 내용을 바탕으로 크게 두가지를 구현해주시면 됩니다.
### Spring Security 클래스
Google OAuth 2.0을 사용하도록 Spring Security 클래스를 작성해주시면 됩니다.

구글 로그인 버튼 화면은 정말 간단한 템플릿을 드리겠습니다 하하..

너무 초라하시면 변경하셔도 댑니다.
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login with Google</h1>
    <a href="/oauth2/authorization/google">Login with Google</a>
</body>
</html>

```
로그인이 완료되었다면, 구글 서버에 접근하여 프로필 정보를 가져와주시면 됩니다.
### ProfileController 구글 프로필을 가져오는 컨트롤러
컨트롤러를 통해 구글 프로필 정보를 받아온 후, 화면에 이름과 이메일을 출력해주시면 됩니다.

출력 화면은 타임리프를 이용한 뷰를 드릴테니, 컨트롤러의 리턴값을 뷰로 해주시면 됩니다!
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    <p>Name: {{ name }}</p>
    <p>Email: {{ email }}</p>
    <img src="{{ picture }}" alt="User Profile Picture">
</body>
</html>
```
### 이론적으로 배운점 정리
여기까지 구현이 완료되었다면 너무 간단할 수 있으니...

지금까지 사용한 Spring Security와 OAuth2.0의 내부 구조를 이해하신만큼 작성해주시면 될 것 같습니다.
