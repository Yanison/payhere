#[페이히어] 백엔드 엔지니어 과제

# 프로젝트 개요 및 목적
지출입 관리를 할 수 있는 가계부 웹앱 어플리케이션 구현

### 사용언어 및 개발 환경<br>
- DB : AWS RDS : Mysql5.7<br>
- FrontEnd-Language: Vanila JS<br>
- BackEnd-Language:Java<br>
- BackEnd-Framework:Spring Boot,mybatis<br>
- was:tomcat 9.0<br>
- IDE : intelliJ

# 프로젝트의 API 및 설계


## Back-End 프로젝트 구조 개요
 📦src<br>
 📂seon<br>
 ┣ 📂Controller <br>
 ┣ 📂config<br>
 ###<br>
 ┣ 📂dto  <br>
   ┣ 📂abType<br>
   ┣ 📂accountBook<br>
   ┣ 📂httpResponse<br>
   ┣ 📂security<br>
   ┗ 📂user<br>
 ┣ 📂exception<br>
 ┣ 📂jwt<br>
 ┣ 📂repository<br>
 ┣ 📂service<br>
 ┣ 📂util<br>
 ┗ 📂resources<br>
  ┣ 📂mappers<br>
 .<br>
 .<br>

<div style="display:flex">
 <div>
  ### 구현한 부분

가계부 관련 RestAPI
- 쓰기 
분류,금액,상세메모 내용을 각각 입력하여 가계부 내역을 작성할 수 있습니다. 
- 수정하기
내역,금액,상세메오 등을 각각 자유롭게 수정 가능합니다.
- 삭제하기
가계부 Row의 삭제는 일반 삭제와 영구삭제 두가지가 있습니다.<br>
일반삭제한 가계부 내역만 복구가 가능합니다.
- 불러오기
작성한 가계부 혹은 삭제한 가계부 내역을 불러올 수 있습니다.<br>
또한 가계부 내역의 분류를 직접 기입하여 저장이 가능하여 <br>
Keyup 혹은 click 이벤트시 불러올 수 있도록 하였습니다. <br>
(현재 해당 컨텐츠에 upsert 기능을 수행할 경우 sql timeout error가 생기는 issue 가 발견되었습니다.)<br>
  <div>
  <div>
### 미완성인 부분
- JWT + Security 를 적용한 로그인 인가 기능 구현
학습미숙으로 인한 개발 미완성입니다. <br>
관련 기능의 패키지 설계 단계에 머물러 있습니다. <br>
현재 로그인,회원가입 기능은 session 인증 기반으로 구현되어있습니다.
이후 충분한 학습을 한 이후에 요구스팩에 맞추어 <Strong>리펙토링할 계획입니다.</Strong>

- 가계부 상세 세부내역
참고한 가계부 스타일이 매우 심플하였기 때문에 <br>
좀더 상세한 내역을 열람 할 수 있는 내용들을 추가하지 못한것에 아쉬움이 남습니다 <br>
대신 사용자가 이용하기 편하도록 가계부의 UI 로직에 좀 더 신경썼기 떄문에 <br>
편의성 면에서 완성도를 높였습니다. <br>
   <div>
<div>

## Front-End  프로젝트 구조
📦src<br>
 .<br>
 .<br>
 .<br>
 📦src<br>
 📂seon<br>
 ┣ 📂static<br>
 ┃ ┣ 📂css<br>
 ┃ ┣ 📂img<br>
 ┃ ┗ 📂js<br>
 ┣ 📂templates<br>
 ┃ ┣ 📂fragment<br>
 ┃ ┣ 📜 HTMLs...<br>
 .<br>
 .<br>
 
 
 
 
