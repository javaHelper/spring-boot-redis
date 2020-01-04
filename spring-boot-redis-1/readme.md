If yoo dont pass the Id,  then 

@Indexed - Mark properties value to be included in a secondary index.

redis 127.0.0.1:6379> KEYS *
 1) "item"
 2) "item:id:-2092428813"
 3) "item:id:811049787"
 4) "item:1596044799"
 5) "item:-2092428813:idx"
 6) "item:811049787"
 7) "item:1596044799:idx"
 8) "item:-2092428813"
 9) "item:id:1596044799"
10) "item:811049787:idx"
redis 127.0.0.1:6379>


redis 127.0.0.1:6379> HSCAN item:-2092428813 0 COUNT 10000
1) "0"
2) 1) "_class"
   2) "com.example.model.Item"
   3) "id"
   4) "-2092428813"
   5) "name"
   6) "Sambar Vadi"
   7) "category"
   8) "Junk Food"
   
redis 127.0.0.1:6379> SSCAN item 0 COUNT 10000
1) "0"
2) 1) "-2092428813"
   2) "811049787"
   3) "1596044799"