require 'vertx/vertx'

$vertx
  .create_http_server()
  .request_handler() { |req| req.response().put_header("content-type", "application/json").end("{}") }
  .listen(8080)
