# [페이히어] 백엔드 엔지니어 과제

<img width="800px" alt="home" src="https://user-images.githubusercontent.com/88885019/212714790-61314465-8f34-4fe1-8f02-a72fd96bbfc8.png">




<br>
<br>
<br>
<br>
<br>
<br>

# 프로젝트 개요 및 목적
- 지출입 관리를 할 수 있는 가계부 웹앱 어플리케이션 구현 <br>
- 기존 레거시에 사용했던 스킬들을 최대한 배제하고 구현 <br>

#### 프로젝트 소감 및 후기
이번 프로젝트를 통해서 스프링부트를 처음 접하게 되었습니다. <br>
기존 레거시 방식을 버리고 스프링 부트를 학습하면서 프로젝트를 설계하는 방식, <br>
가령 DTO 설계에 있어 Entitiy와의 분리, Request와 Response 의 분리하여 <br>
각자의 설계 목적에 명확하게 역할을 수행하도록 하는 설계방식은 <br>
기존에 배웠던것들에 대한 의문들을 해소 시켜주었습니다.<br>
그리고 아직 공부해야할게 많다는 것을 새삼 느끼게 되었습니다. <br>
프론트부터,백엔드 개발환경이 새로웠던 만큼 프로젝트 자체에 몰입도가 높았고 즐거운 경험이였습니다. <br>

<br>
<br>

# 프로젝트의 설계

### 사용언어 및 개발 환경<br>
- DB : AWS RDS : Mysql5.7<br>
- FrontEnd-Language: Vanila JS<br>
- BackEnd-Language:Java<br>
- BackEnd-Framework:Spring Boot,mybatis<br>
- was:tomcat 9.0<br>
- IDE : intelliJ
- Library Managing : gradle,yml

<br>
<br>


## Back-End 프로젝트 구조 개요


### 구현한 부분
가계부 관련 RestAPI
- 쓰기 
분류,금액,상세메모 내용을 각각 입력하여 가계부 내역을 작성할 수 있습니다. <br>
- 수정하기
내역,금액,상세메오 등을 각각 자유롭게 수정 가능합니다.<br>
- 삭제하기
가계부 Row의 삭제는 일반 삭제와 영구삭제 두가지가 있습니다.<br>
일반삭제한 가계부 내역만 복구가 가능합니다.<br>
- 불러오기
작성한 가계부 혹은 삭제한 가계부 내역을 불러올 수 있습니다.<br>
또한 가계부 내역의 분류를 직접 기입하여 저장이 가능하여 <br>
Keyup 혹은 click 이벤트시 불러올 수 있도록 하였습니다. <br>
(현재 해당 컨텐츠에 upsert 기능을 수행할 경우 sql timeout error가 생기는 issue 가 발견되었습니다.)<br>


### 미완성인 부분
- JWT + Security 를 적용한 로그인 인가 기능 구현<br>
학습미숙으로 인한 개발 미완성입니다. <br>
관련 기능의 패키지 설계 단계에 머물러 있습니다. <br>
현재 로그인,회원가입 기능은 session 인증 기반으로 구현되어있습니다.
이후 충분한 학습을 한 이후에 요구스팩에 맞추어 <Strong>리펙토링할 계획입니다.</Strong>

- 가계부 상세 세부내역
참고한 가계부 스타일이 매우 심플하였기 때문에 <br>
좀더 상세한 내역을 열람 할 수 있는 기능들을 추가하지 못한것에 아쉬움이 남습니다 <br>
대신 사용자가 이용하기 편하도록 가계부의 UI 로직에 좀 더 신경썼기 떄문에 <br>
편의성 면에서 완성도를 높였습니다. <br>
 
 
 ## Back-End 프로젝트 구조
  📦src<br>
 📂seon<br>
 - Api Controller
 ┣ 📂Controller <br>
 - DB 및 Security 관련 configuration
 ┣ 📂config<br>
 ###<br>
 - Data Transfer Object 관리 Package
 ┣ 📂dto  <br>
   ┣ 📂abType<br>
   ┣ 📂accountBook<br>
   ┣ 📂httpResponse<br>
   ┣ 📂security<br>
   ┗ 📂user<br>
 - 예외처리 담당 패키지
 ┣ 📂exception<br>
 - 토큰 인증관련 패키지
 ┣ 📂jwt<br>
 - repository
 ┣ 📂repository<br>
 - serivceLogic
 ┣ 📂service<br>
 ┣ 📂util<br>
 ┗ 📂resources<br>
 - database sql mapper (mybatis)
  ┣ 📂mappers<br>
 .<br>
 .<br>

#### Controller
API 설계는 요청의 목적에 맞게 다음과 같이 분리하였습니다. 
- 📜AuthController.java
유저의 로그인 인증 및 인가를 담당하는 API 컨트롤러 입니다. <br>
인증/인가와 관련한 서비스 로직은 미완성이 상태입니다.
- 📜UserApiController.java
유저의 회원가입 및 로그인 로그아웃 요청을 담당하는 API컨트롤러 입니다.<br>
- 📜AccountBookApiController.java
가계부의 기능(CRUD)을 수행하기 위한 API Controller 입니다.
- 📜LocationController.java
페이징을 담당하는 컨트롤러 입니다.

이번 프로젝트에서 API Controller를 설계하면서 HttpRequest,HttpResponse 를 좀 더 섬세하게 다루는 방법, 그리고 <br>
예외처리를 방법에 대한 방향성을 얻는 계기가 되었습니다. <br>
이전 API response를 다루던 방식들은 클라이언트에 불친절하고 명확하지 않은(에러코드가 없다거나..) 방식이었습니다. <br>
이번 개발을 통하여 백엔드 개발자로서의 역할에 대해 좀 더 명확하게 깨닫는 계기가 된 것 같습니다. <br>

  
#### Service Logic

##### DTO
기존에 스프링 레거시에서 개발을

#### DB


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
 
 
 
 
