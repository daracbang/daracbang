# 1. 세팅 환경

- Java 17
- Docker 24.0.5
- Node Js 18.17.0
- mysql 8.0.5
- IntelliJ
- {프로젝트 디렉토리}\backend\apiserver\docker-compose\localdb 디렉토리로 이동해
  - docker compose up - d 실행

# 2. 포트 설정

- react: 3000
- springboot: 8080
- mysql: 3307

# 3. 환경 변수

### 백엔드

- clova API 
  - id 값
  - secret 키
- jwt secret
- s3
  - bucket 이름
  - aws_url
  - access 키
  - secret 키

### 프론트

- 통신할 api 서버 url
  - REACT_APP_APPLICATION_SERVER_URL

# 4. DB 접속

- DB 계정 및 데이터베이스 정보

  - {프로젝트 디렉토리}\backend\apiserver\docker-compose\localdb\docker-compose.yml

```
    darac-mysql:
      container_name: darac-mysql
      image: mysql:8
      ports:
        - "3307:3306"
      command:
        - --character-set-server=utf8mb4
        - --collation-server=utf8mb4_unicode_ci
      environment:
        - TZ=Asia/Seoul
        - MYSQL_ROOT_PASSWORD=localpwd
        - MYSQL_DATABASE=daracdb
      restart: always
      volumes:
        - ./db/mysql:/var/lib/mysql
```
  
# 5. DB 덤프파일 최신본

/exec/dump 디렉토리 확인


# 6. 시연 시나리오

### 초기 화면

![splash](/uploads/88b0b6a9311788e7b193524bc9625d6d/splash.JPG)

### 회원 가입

![register](/uploads/25d106c27772700e4ad501d139c807cd/register.PNG)

### 로그인

 ![login](/uploads/2a2be31437c6834b1b4eeecaaa6c83cf/login.PNG)

### 메인

![main](/uploads/df4b80c0a3725f7365217b26cf58c54a/main.PNG)

### 다이어리 작성 (긍정적)

![diary](/uploads/e44e30181ca4472d4fb47ac2017c2a7c/diary.PNG)

### 감정분석 결과

![analyze](/uploads/c8b6f5c4d212d1b44a7093dc5a0b54f1/analyze.PNG)

### 다이어리 댓글

![comment](/uploads/27c92264c5f7171427f1e6472300d241/comment.PNG)

### 방명록 작성

![guestbook](/uploads/aaa02022c995010f1048dfd7a0049ed1/guestbook.JPG)
