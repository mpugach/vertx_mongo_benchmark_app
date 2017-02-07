#Vert.x + MongoDB benchmark app

```bash
export MONGO_DB="my_collection" MONGO_COLLECTION="my_collection"

vertx run mongo.rb
vertx run mongo.js
vertx run mongo.groovy

vertx run static.rb
vertx run static.js
vertx run static.groovy

ab -n 10000 -c 4 http://127.0.0.1:8000
wrk -t20 -c100 -d15s http://127.0.0.1:8000
```
