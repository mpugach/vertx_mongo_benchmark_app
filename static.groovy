import io.vertx.groovy.core.Vertx

vertx
  .createHttpServer()
  .requestHandler({ req ->
    req
      .response()
      .putHeader("content-type", "application/json")
      .end("{}")
  })
  .listen(8080)
