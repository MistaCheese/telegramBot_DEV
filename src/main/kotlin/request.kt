import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import java.util.concurrent.TimeUnit
import okhttp3.*

class request {

    fun sendMessage(message: String, userID: String) {
        Unirest.setTimeouts(0, 0)
        val response: HttpResponse<String> =
            Unirest.get("https://api.telegram.org/bot5056719530:AAHbB8J5p2Bd1lIjavUTfKM02pN2Ej-BjPw/sendMessage")
//                .field("chat_id", userID)
//                .field("text", message)
                .asString()


    }

}