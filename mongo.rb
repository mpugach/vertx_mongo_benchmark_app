require 'vertx/vertx'
require 'vertx-mongo/mongo_client'
require 'json'

MONGO_DB = ENV["MONGO_DB"]
MONGO_COLLECTION = ENV["MONGO_COLLECTION"]

MONGO_CLIENT = VertxMongo::MongoClient.create_shared($vertx, {
  connection_string: "mongodb://localhost:27017",
  db_name: MONGO_DB
})

$vertx
  .create_http_server()
  .request_handler() do |req|
    MONGO_CLIENT.find_with_options(MONGO_COLLECTION, {}, { limit: 10 }) do |_,res|
      data = JSON.generate res
      req.response().put_header("content-type", "application/json").end(data)
    end
  end
  .listen(8080)
