
API 명세서 :

스케줄 API

| 기능 | Method | URL | request                                 | response |
|----|--------|-----|-----------------------------------------|----------|
| 생성 | POST   |/api/schedule| ScheduleRequestDto,UserDetailsImpl      |ScheduleResponseDto|
| 조회 | GET    |/api/schedule| UserDetailsImpl                         |List<ScheduleResponseDto>|
| 수정 | PUT    |/api/schedule/{id}| long(id),ScheduleRequestDto,UserDetailsImpl |Long|
| 삭제 | DELETE |/api/schedule/{id}| long(id),UserDetailsImpl                |Long|
| ID 로 조회 | GET    |/api/schedule/search/{id}| long(id),UserDetailsImpl                |ScheduleResponseDto|
| 날자 로 조회 | GET    |/api/schedule/search<br/>/date/{date}| LocalDate,UserDetailsImpl |  List<ScheduleResponseDto>        |
