package modules

import com.google.inject.AbstractModule
import play.api.cache.AsyncCacheApi
import play.cache.DefaultAsyncCacheApi

class LocalGuiceModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[AsyncCacheApi]).to(classOf[AsyncCacheApi])

  }
}
