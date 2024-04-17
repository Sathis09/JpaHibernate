//package com.myapp.config;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfigutation {
//
////
////        @Bean
////        public JedisConnectionFactory jedisConnectionFactory() {
////            Set<String> sentinels = new HashSet<>();
////            sentinels.add("192.168.1.34:26379");
////            sentinels.add("192.168.1.72:26379");
////            sentinels.add("192.168.1.73:26379");
////
////            RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration("mymaster", sentinels);
////            sentinelConfig.setPassword("your_password"); // If your Redis requires authentication
////
////            return new JedisConnectionFactory(sentinelConfig);
////        }
////
////        @Bean
////        public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
////            RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
////            redisTemplate.setConnectionFactory(redisConnectionFactory);
////            redisTemplate.setKeySerializer(new StringRedisSerializer());
////            redisTemplate.setValueSerializer(new StringRedisSerializer());
////            return redisTemplate;
////        }
//	
//	@Bean
//	public RedissonClient redissonClientConfig(){
//		Config config = new Config();
//		config.useMasterSlaveServers()
//		    .setMasterAddress("redis://127.0.0.1:6379")
//		    .addSlaveAddress("redis://127.0.0.1:6380", "redis://127.0.0.1:6381");
//		RedissonClient redisson = Redisson.create(config);
//		return redisson;
//	}
//	
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//       configuration.setHostName("localhost");
//       configuration.setPort(6379);
//        return new JedisConnectionFactory(configuration);
//    }
//    
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate() {
////        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
////        redisTemplate.setConnectionFactory(jedisConnectionFactory());
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        return redisTemplate;
////    }
//
//}
