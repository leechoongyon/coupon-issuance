package io.simple.coupon.storage.redis.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    @Value("\${spring.redis.host}")
    private val redisHost: String? = null

    @Value("\${spring.redis.port}")
    private val redisPort: Int = 0

    @Value("\${spring.redis.password}")
    private val redisPassword: String? = null

    @Bean()
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(redisHost!!, redisPort)
        redisStandaloneConfiguration.setPassword(redisPassword)
        return JedisConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean("redisTemplateWithJedis")
    fun redisTemplateWithJedis(): RedisTemplate<*, *> {

        val template = RedisTemplate<String, Any>()
        template.valueSerializer = StringRedisSerializer()

        template.setConnectionFactory(jedisConnectionFactory())
        template.setEnableTransactionSupport(true)

        return template
    }
}


