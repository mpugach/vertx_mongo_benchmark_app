var Vertx = require("vertx-js/vertx");

vertx
  .createHttpServer()
  .requestHandler(function (req) {
    req
      .response()
      .putHeader("content-type", "application/json")
      .end("{}");
  })
  .listen(8080);
