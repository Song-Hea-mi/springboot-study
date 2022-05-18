# 스프링부트 시작하기 책 스터디
https://github.com/insightbook/Spring-Boot

# 1장 개발환경 설정하기
1) JDK 1.8 설치
2) eclipse 설치 
- jdk 별 설치 가능한 eclipse 버전 (참고: https://wiki.eclipse.org/Eclipse/Installation)
3) STS 플러그인 / maven 설치
4) eclipse 내 설정 (java 숏컷 구성, web 숏컷 구성, 인코딩 uft-8)

# 2장 스프링프로젝트 만들어보기
![image](https://user-images.githubusercontent.com/46243850/168424946-05763df7-ba91-4609-ab4f-4fc05ce918ee.png)

# 3장 스프링 이해하기 
1) 스프링 MVC 구조
![image](https://user-images.githubusercontent.com/46243850/168425593-c45820b8-6b65-4a31-a690-b5daf429b774.png)
- 책에서는 서비스 영역에 대하여 서비스인터페이스와 서비스임플로 구현하고 있으나, 현재 프로젝트의 경우 인터페이스와 구현체 클래스 사이의 관계가 1:1이라 두 영역을 나눈 것이 큰 이점이 없을 것이라 판단하여 Service 클래스 하나로 관리
2) 데이터베이스 연결하기
- 데이터 소스 설정 (application.properties, DatabaseConfiguration.java 작성)

# 4장 간단한 게시판 구현
1) board 테이블 생성
2) myBatis 카멜 표기법 변환 (applicaion.properties, DataConfiguration.java 수정)

# 5장 스프링의 다양한 기능
1) Logback 로그
- logback-spring.xml 작성 
2) Log4JDBC 쿼리 로그
- log4j2 의존성 추가
- log4jdbc.log4j2.properties 작성
- application.properties, logack-spring.xml 수정
3) 인터셉터 - LoggerInterceptor.java 작성
4) AOP - LoggerAspect.java 작성 
5) 트랜잭션 - TransactionAspect.java 작성
6) 예외처리 - @ExceptionHandle.java 작성

# 6장 파일 첨부를 위한 기본 설정
1) file 테이블 생성
2) commons-io, commons-fileupload 의존성 추가
3) 파일 업로드 및 다운로드 - FileUtils 작성
