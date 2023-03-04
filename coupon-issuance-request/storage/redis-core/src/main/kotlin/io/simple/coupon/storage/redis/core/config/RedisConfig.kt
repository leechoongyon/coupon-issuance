package io.simple.coupon.storage.redis.core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

// 동작하는 RedisConfig

//@Configuration
//@EnableRedisRepositories
//@EnableCaching
//class RedisConfig : CachingConfigurerSupport() {
//    @Value("\${spring.redis.host}")
//    private val redisHost: String? = null
//
//    @Value("\${spring.redis.port}")
//    private val redisPort: Int = 0
//
//    @Value("\${spring.redis.password}")
//    private val redisPassword: String? = null
//
//    @Bean()
//    fun jedisConnectionFactory(): JedisConnectionFactory {
//        val redisStandaloneConfiguration = RedisStandaloneConfiguration(redisHost!!, redisPort)
//        redisStandaloneConfiguration.setPassword(redisPassword)
//        return JedisConnectionFactory(redisStandaloneConfiguration)
//    }
//
//    @Bean("redisTemplateWithJedis")
//    fun redisTemplateWithJedis(): RedisTemplate<*, *> {
//
//        val template = RedisTemplate<String, Any>()
//        template.valueSerializer = StringRedisSerializer()
//
//        template.setConnectionFactory(jedisConnectionFactory())
//        template.setEnableTransactionSupport(true)
//
//        return template
//    }
//}


@Configuration
@EnableCaching
class RedisCacheConfig : CachingConfigurer {

    @Value("\${spring.redis.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    private var redisPort: Int = 6379

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisConfig = RedisStandaloneConfiguration(redisHost, redisPort)
        return LettuceConnectionFactory(redisConfig)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.setDefaultSerializer(StringRedisSerializer())
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }

    override fun cacheManager(): CacheManager {
        val cacheManager = RedisCacheManager
            .builder(redisConnectionFactory())
            .cacheDefaults(redisCacheConfiguration())
            .build()
        return cacheManager
    }

    @Bean
    fun redisCacheConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(5))
            .disableCachingNullValues()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer()
                )
            )
    }
}


