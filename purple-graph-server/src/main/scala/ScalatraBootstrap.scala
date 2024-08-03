import com.bitbucket.fllsouto.purplegraph.api._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new PurpleGraphAPI, "/purplegraph/api/*")
  }
}
