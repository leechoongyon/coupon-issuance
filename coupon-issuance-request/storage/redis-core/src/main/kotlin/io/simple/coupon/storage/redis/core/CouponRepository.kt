package io.simple.coupon.storage.redis.core

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class CouponRepository(private val redisTemplate: RedisTemplate<String, Any>) {

    /**
     * 설계
     * 1. 쿠폰 발급 요청을 함.
     *  - redis sorted set 에 쌓임.
     * 2. 쿠폰 발급 서버에서 하나씩 꺼낸 후, DB 에 저장을 해야할거 같은데?
     *  - 예상되는 문제점은 redis 코드 트랜잭션이 DB 트랜잭션이랑 묶이지 않을거 같은데.
     * 3.
     */
//    fun requestCoupon(): Boolean {
//        val requestSetKey = "couponRequests"
//        val request = UUID.randomUUID().toString()
//
//        // Redis 트랜잭션을 시작합니다.
//        val tx = redisTemplate.getConnectionFactory()?.transaction
//
//        try {
//            // Redis Sorted Set에 요청을 추가합니다.
//            tx?.zAdd(requestSetKey, System.currentTimeMillis().toDouble(), request)
//
//            // Redis Sorted Set에서 스코어가 0에서 현재 시각 사이인 요청 수를 가져옵니다.
//            val requestCount = tx?.zCount(requestSetKey, 0.0, System.currentTimeMillis().toDouble())
//
//            if (requestCount != null && requestCount <= maxRequests) {
//                // Redis 트랜잭션을 실행합니다.
//                tx.exec()
//                return true
//            } else {
//                // Redis 트랜잭션을 롤백합니다.
//                tx?.discard()
//                return false
//            }
//        } catch (e: Exception) {
//            // Redis 트랜잭션을 롤백합니다.
//            tx?.discard()
//            return false
//        }
//    }

}