# 프로젝트 설명

- coupon-issuance : 쿠폰 발급
- coupon-issuance-request : 쿠폰 발급 요청

# docker-compose 로 redis 실행

- docker-compose 폴더에 있는 docker-compose-redis.yml 을 실행시킵니다.

```yaml
docker-compose -f ./docker-compose-redis.yml up -d
```

### 동작 확인

- redis-cli 로 접속해서 ping 명령어를 날리면 동작 확인이 가능합니다.

```shell
docker exec -it redis_main redis-cli
```

### container 종료

```yaml
docker-compose -f ./docker-compose-redis.yml down
```
