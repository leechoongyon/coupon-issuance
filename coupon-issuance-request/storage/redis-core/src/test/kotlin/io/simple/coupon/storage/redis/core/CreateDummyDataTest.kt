package io.simple.coupon.storage.redis.core

import redis.clients.jedis.Jedis

/**
 * available-coupon set 자료구조에 dummy 데이터 만드는 프로그램
 * redis docker 떠있어야 함.
 *
 * 아래 main 실행시키고,
 *
 * redis-cli 로 들어가서
 * SCARD available-coupon 명령어 치면 dummy data 생성된 것 확인 가능
 */

fun main(args: Array<String>) {
    CreateDummyDataTest().createDummyData()
}

class CreateDummyDataTest {
    fun createDummyData() {
        // Redis 접속 정보
        val host = "localhost"
        val port = 6379

        // Jedis 클라이언트 생성
        val jedis = Jedis(host, port)

        // 샘플 데이터 추가
        for (i in 1..1000) {
            jedis.sadd("available-coupon", "coupon$i")
        }

        // 종료
        jedis.close()
    }
}
