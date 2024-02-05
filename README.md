
API 명세서 :

스케줄 API

| 기능 | Method | URL | request  | response |
|----|--------|-----|----------------------------------------|----------|
| 생성 | POST   |/api/schedule| ScheduleRequestDto,UserDetailsImpl     |ScheduleResponseDto|
| 조회 | GET    |/api/schedule| UserDetailsImpl                        |List<ScheduleResponseDto>|
| 수정 | PUT    |/api/schedule/{id}| long(id),ScheduleRequestDto,UserDetailsImpl |Long|
| 삭제 | DELETE |/api/schedule/{id}| long(id),UserDetailsImpl               |Long|
| ID 로 조회 | GET    |/api/schedule/search/{id}| long(id),UserDetailsImpl               |ScheduleResponseDto|
| 날자 로 조회 | GET    |/api/schedule/search<br/>/date/{date}| LocalDate,UserDetailsImpl |  List<ScheduleResponseDto>        |


유저/로그인 API

| 기능           | Method | URL  | request  |  response |
|--------------|---|---|--------------------------------------|-|
| login.html 이동 |GET|/api/user/login-page| NULL |String|
| signup.html 이동 |GET|/api/user/signup| NULL  |String|
| 유저 생성 |POST|/api/user/signup| SignupRequestDto |String |
| 로그인 |POST|/api/user/login | LoginRequestDto, HttpServletResponse |String |
| 로그아웃 컨펌 |GET|/api/logout |HttpServletRequest,HttpServletResponse |String|


홈 API

| 기능            |Method   | URL | request  |  response |
|---------------|---|-----|---|---|
| index.html 이동 | GET  | /   | Model  | String  |
| search.html 이동 | GET  | /api/user/search-page    |  Model |  String |


<img src="usecase.jpg"/>

