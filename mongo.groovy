import io.vertx.groovy.core.Vertx
import io.vertx.groovy.ext.mongo.MongoClient
import groovy.json.JsonOutput

String mongoDb = System.getenv()["MONGO_DB"]
String mongoCollection = System.getenv()["MONGO_COLLECTION"]

def mongoClient = MongoClient.createShared(vertx, [
  connection_string: "mongodb://localhost:27017",
  db_name: mongoDb
])

vertx
  .createHttpServer()
  .requestHandler({ req ->
    mongoClient.findWithOptions(mongoCollection, [:], [limit: 10], { res ->
      String data = res.result().collect { JsonOutput.toJson it }

      req
        .response()
        .putHeader("content-type", "application/json")
        .end(data)
    })
  })
  .listen(8080)
