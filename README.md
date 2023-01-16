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

### 구현한 부분

가계부 관련 RestAPI
- 쓰기
- ┗  (내역,금액,상세메모 내용을 포함합니다)
- 수정하기 (내역,금액,상세메오 등을 각각 자유롭게 수정 가능합니다)
- 삭제하기 (가계부 Row의 삭제는 일반 삭제와 영구삭제 두가지가 있습니다. 일반삭제한 가계부 내역만 복구가 가능합니다.)
- 불러오기 (
- ┣ 
- ┣

### 미완성인 부분
- JWT + Security 를 적용한 로그인 인가 기능 구현

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
 
 
 
 
