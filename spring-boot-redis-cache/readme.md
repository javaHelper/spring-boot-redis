# Swagger

```
http://localhost:8080/management/v1/swagger-ui.html#
```

#@Ref: https://www.concretepage.com/spring-boot/spring-boot-redis-cache

# Details

This page will walk through Spring Boot Redis cache example. RedisCacheManager is the CacheManager backed by Redis. If Redis is available and configured in our Spring Boot application, RedisCacheManager will be auto-configured. Redis connections are obtained from Lettuce or Jedis Java Redis clients. Redis dependencies are resolved by spring-boot-starter-data-redis starter. In Spring Boot 2.0 Lettuce are resolved by default instead of Jedis. To work with Jedis, we need to include jedis dependency in our build file. 
Spring @EnableCaching enables Spring cache management capability in our application. It is annotated with @SpringBootApplication annotation. @Cacheable indicates that the result of invoking method can be cached and once result is cached, next call to method execution is skipped and only cached result is served. @CachePut adds or updates cache but does not skip method execution. @CacheEvict evicts cache but does not skip method execution. @Caching is used to group multiple cache annotations.


# Technologies Used
Find the technologies being used in our example. 
1. Java 9 
2. Spring 5.0.8.RELEASE 
3. Spring Data 2.0.9.RELEASE 
4. Spring Boot 2.0.4.RELEASE 
5. Maven 3.5.2 
6. MySQL 5.5 
7. Eclipse Oxygen

# Maven File
Spring provides spring-boot-starter-data-redis to resolve Redis dependencies. It provides basic auto configurations for Lettuce and Jedis client libraries. By default Spring Boot 2.0 uses Lettuce. To get pooled connection factory we need to provide commons-pool2 dependency. Find the Maven file. 


Using Lettuce Configurations
Spring Boot 2.0 starter spring-boot-starter-data-redis resolves Lettuce by default. Spring provides LettuceConnectionFactory to get connections. To get pooled connection factory we need to provide commons-pool2 on the classpath. To work with Lettuce we need following Maven dependencies.

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>		
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-pool2</artifactId>
</dependency> 
```

To configure Lettuce pool we need to use spring.redis.* prefix with Lettuce pool connection properties. Find the Lettuce pool sample configurations. 

```
application.properties
spring.redis.host=localhost 
spring.redis.port=6379
spring.redis.password= 

spring.redis.lettuce.pool.max-active=7 
spring.redis.lettuce.pool.max-idle=7
spring.redis.lettuce.pool.min-idle=2
spring.redis.lettuce.pool.max-wait=-1ms  
spring.redis.lettuce.shutdown-timeout=200ms 
```

We can override default Redis host, port and password configurations. Use max-wait a negative value if we want to block indefinitely.


# RedisCacheManager
In Spring Boot, RedisCacheManager is auto-configured. Here we will discuss how to configure Spring Boot Redis cache properties to change its default value for auto-configured RedisCacheManager and then we will create a sample own RedisCacheManager to get full control on configurations. 

# 1. Auto-configured RedisCacheManager
If Redis is available and configured in our Spring Boot application, RedisCacheManager will be auto-configured. We can control Spring cache configurations using spring.cache.* property. 
spring.cache.type: Defines cache type. If we do not configure this property, It will be auto-detected to the environment. For Redis cache its value is redis . 
spring.cache.cache-names: Creates additional caches on startup. 

Redis cache defaults can be configured by spring.cache.redis.* . 
spring.cache.redis.cache-null-values: It accepts Boolean value. When the value is true, it will allow caching null values otherwise not. 
spring.cache.redis.time-to-live: Cache expiration time. 
spring.cache.redis.use-key-prefix: It accepts Boolean value. If true then key prefix will be used while writing to Redis. Default value is true 





# Using @Cacheable

@Cacheable indicates that the result of invoking method can be cached and once result is cached, next call to method execution is skipped and only cached result is served. Find some of its elements. 
cacheNames: Name of the caches in which method result are stored. 
value: Alias for cacheNames. 
condition: Spring SpEL expression to make conditional caching. 
key: SpEL to compute key dynamically. 
keyGenerator: Bean name for custom KeyGenerator. 
unless: SpEL to veto method caching. 
sync: It is uses to synchronize method invocation when several threads are attempting to load a value for same key. 

To compute key, condition or unless, we can use following meta-data in SpEL. 
#result: Reference to the result of method. 
#root.method: Reference to the method. 
#root.target: Reference to the target object. 
#root.caches: Reference to the affected caches. 
#root.methodName: Shortcut to the method name. 
#root.targetClass: Shortcut to target class. 
#root.args[1], #p1 or #a1: They give second argument of method. Changing numerical value, we can get other arguments. We can also access arguments by their name. 

Now find the sample code snippet to use @Cacheable annotation.

```
@Cacheable(value= "articleCache", key= "#articleId")		
public Article getArticleById(long articleId) {
  ------
}
```

In the above code, method result will be cached with articleCache cache name using key as passed article id. It means for different article id, result will be cached with different key but with same cache name. Once the method result is cached for a key, then for the same key, method will not execute and the cached result will be served. 
Find one more example.

```
@Cacheable(value= "allArticlesCache", unless= "#result.size() == 0")	
public List<Article> getAllArticles(){
  ------
} 
```

In the above code, method result will not be cached if size of the result will be 0. If we do not provide key, by default it will be ("") or method parameters are used to compute the key if available.

# Using @CachePut

@CachePut triggers a cache put operation. It does not skip method execution and result is cached in associated cache for every execution. @CachePut has elements same like @Cacheable such as cacheNames, value, condition, key, unless, keyGenerator etc. Find the sample code snippet to use @CachePut.

```
@CachePut(value= "articleCache", key= "#article.articleId")
public Article addArticle(Article article){
   ------
}
```

The above method will execute for every call and method result will be added or updated in cache corresponding to key for given cache name.

# Using @CacheEvict

@CacheEvict triggers a cache evict operation. It does not skip method execution and evicts cache for every execution. It has elements such as cacheNames, value, condition, key, keyGenerator, allEntries etc. If allEntries= true, all entries inside the caches are removed. Find the code snippet to use @CacheEvict.


```
@CacheEvict(value= "allArticlesCache", allEntries= true)	
public void deleteArticle(long articleId) {
  ------
}
```

The above method will execute every call and all the entries of caches will be removed.

# Using @Caching

@Caching is the group annotation for multiple cache annotations. It has cacheable, put and evict elements. 
Find the code snippet to use @CachePut and @CacheEvict in group using @Caching.

```
@Caching(
   put= { @CachePut(value= "articleCache", key= "#article.articleId") },
   evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
)
public Article updateArticle(Article article) {
   ------
} 
```

Find the code snippet to use multiple @CacheEvict in group using @Caching.
	
```
@Caching(
   evict= { 
	@CacheEvict(value= "articleCache", key= "#articleId"),
	@CacheEvict(value= "allArticlesCache", allEntries= true)
   }
)
public void deleteArticle(long articleId) {
   ------
} 
```

spring.cache.redis.key-prefix: Defines key prefix. By default a key prefix is added to avoid overlapping keys when two separate caches uses same key. 
