// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Luke/polybody-macro-calc/conf/routes
// @DATE:Mon Feb 22 19:53:25 GMT 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
