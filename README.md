# 프레임워크 기본 모델
> 기본 모듈이 적용된 스프링 부트 예제입니다.  

🛠️️ **환경**

* Spring Boot 2.7.5 
* JDK 11 
* Gradle

⚙️️ **프로젝트 구조**
- - -
```
.
└── src/
    └── main/
        ├── java/
        │    ├── com/app/basic/                        
        │    │   ├── common/
        │    │   │    ├── api/          : Third party API 관련 파일
        │    │   │    ├── config/       : Config 관련 파일
        │    │   │    ├── entity/       : 응답값 정의
        │    │   │    ├── interceptor/  : 인터셉터 관련 파일
        │    │   │    ├── security/     : 시큐리티 관련 파일
        │    │   │    └── utils/        : 공통 함수 정의
        │    │   ├── domain/            : 도메인별 컨트롤러                                   
        │    │   └── BasicFrameworkApplication.java
        │    └── resources/
        │        ├── mapper/            : *.xml 파일 위치
        │        ├── static/            : 정적 파일 위치
        │        ├── templates/         : thymeleaf 파일 위치
        │        ├── application.properties        
        │        ├── log4jdbc.log4j2.properties
        │        └── myabatis-config.xml
        └── webapp/
            └── WEB-INF/
                └── views/              : jsp 파일 위치
```

📌 **적용 모듈**
* Spring Security
* DB Replication
* Redis(로컬 환경 : EmbeddedRedis)
* MyBatis
