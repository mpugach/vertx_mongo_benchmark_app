var Vertx = require("vertx-js/vertx");
var MongoClient = require("vertx-mongo-js/mongo_client");

var mongoDb = process.env.MONGO_DB;
var mongoCollection = process.env.MONGO_COLLECTION;

var mongoClient = MongoClient.createShared(vertx, {
  connection_string: "mongodb://localhost:27017",
  db_name: mongoDb,
});

vertx
  .createHttpServer()
  .requestHandler(function (req) {
    mongoClient.findWithOptions(mongoCollection, {}, { limit: 10 }, function (res, err) {
      req
        .response()
        .putHeader("content-type", "application/json")
        .end(JSON.stringify(res));
    })
  })
  .listen(8080);
