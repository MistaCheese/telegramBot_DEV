import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.util.concurrent.TimeUnit
import okhttp3.*
fun main() {

    try {
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        botsApi.registerBot(MainLogic())
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }
}