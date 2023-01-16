#[페이히어] 백엔드 엔지니어 과제

# 프로젝트 개요 및 목적
지출입 관리를 할 수 있는 가계부 웹앱 어플리케이션 구현

### 사용언어 및 개발 환경<br>
- DB : AWS RDS : Mysql5.7<br>
- FrontEnd-Language: Vanila JS<br>
- BackEnd-Language:Java<br>
- BackEnd-Framework:Spring Boot,mybatis<br>
- was:tomcat 9.0<br>



# 프로젝트의 API 및 설계

## Back-End


## Front-End

📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂itewon
 ┃ ┃ ┃ ┗ 📂seon
 ┃ ┃ ┃ ┃ ┣ 📂Controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountBookApiController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜LocationController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserApiController.java
 ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┗ 📜CommonVo.java
 ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DatabaseConfig.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┣ 📂abType
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AbTypeListDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜InsertTypeDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SelectTypeListDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂accountBook
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateAccountBookDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteAccountBookDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SelectAccountBookDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateAccountBookDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂httpResponse
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorCode.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜HttpResponseMessage.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthorityDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenDto.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateAccountDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetails.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SelectUserDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignInRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDto.java
 ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DuplicateMemberException.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜NotFoundMemberException.java
 ┃ ┃ ┃ ┃ ┣ 📂jwt
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAccessDeniedHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationEntryPoint.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtSecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenProvider.java
 ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AbTypeRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountBookRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountBookService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailsService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MailService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┣ 📂util
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityUtil.java
 ┃ ┃ ┃ ┃ ┗ 📜SeonApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂mappers
 ┃ ┃ ┃ ┣ 📜AbTypeMapper.xml
 ┃ ┃ ┃ ┣ 📜AccountBookMapper.xml
 ┃ ┃ ┃ ┗ 📜UserMapper.xml
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┣ 📜accountBook.css
 ┃ ┃ ┃ ┃ ┣ 📜common.css
 ┃ ┃ ┃ ┃ ┣ 📜createAccount.css
 ┃ ┃ ┃ ┃ ┣ 📜footer.css
 ┃ ┃ ┃ ┃ ┣ 📜header.css
 ┃ ┃ ┃ ┃ ┣ 📜home.css
 ┃ ┃ ┃ ┃ ┗ 📜login.css
 ┃ ┃ ┃ ┣ 📂img
 ┃ ┃ ┃ ┃ ┣ 📜img_hero_4.png
 ┃ ┃ ┃ ┃ ┣ 📜logo.png
 ┃ ┃ ┃ ┃ ┗ 📜yanison.png
 ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┣ 📜accountBook.js
 ┃ ┃ ┃ ┃ ┣ 📜addAccountBook.js
 ┃ ┃ ┃ ┃ ┣ 📜createAccount.js
 ┃ ┃ ┃ ┃ ┣ 📜header.js
 ┃ ┃ ┃ ┃ ┣ 📜home.js
 ┃ ┃ ┃ ┃ ┗ 📜login.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂fragment
 ┃ ┃ ┃ ┃ ┣ 📜commonResources.html
 ┃ ┃ ┃ ┃ ┣ 📜footer.html
 ┃ ┃ ┃ ┃ ┗ 📜header.html
 ┃ ┃ ┃ ┣ 📜accountBook.html
 ┃ ┃ ┃ ┣ 📜createAccount.html
 ┃ ┃ ┃ ┣ 📜home.html
 ┃ ┃ ┃ ┗ 📜login.html
 ┃ ┃ ┗ 📜application.yml
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂itewon
 ┃ ┃ ┃ ┗ 📂seon
 ┃ ┃ ┃ ┃ ┗ 📜SeonApplicationTests.java
