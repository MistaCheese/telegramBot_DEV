import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.util.concurrent.TimeUnit
import okhttp3.*
import org.telegram.telegrambots.meta.api.objects.Update

import java.io.File
import java.time.LocalDate


open class MainLogic : TelegramLongPollingBot() {
    @Override
    override fun onUpdateReceived(update: Update) {

        val pathToTestFolder = "C:\\Jenkins\\workspace\\trading-system\\checkTime_DEV\\"
        if (update.hasMessage() && update.message.hasText()) {

            val chatId = update.message.chatId
            val message = SendMessage()
            val sendFile = SendDocument()
            sendFile.chatId = chatId.toString()
            message.chatId = chatId.toString()

            when (update.message.text) {
                "/start" -> {
                    message.text =
                        "Здравствуйте! Это бот для помощи в разработке Trading System \nДля подписки на рассылку введите /go\nДля отображения статистики за день введите /stat"
                }
                "/go" -> {
                    val usersList = File(pathToTestFolder + "users_id.txt") // Файл в папке с тестом

                    if (usersList.readText().split(",")
                            .contains(message.chatId)
                    ) { // Если уже есть в списке на рассылку
                        message.text = "Вы уже есть в списке на рассылку"
                    } else {
                        usersList.appendText(message.chatId + ",") // Если нет в списке на рассылку
                        message.text = "Вы добавлены в рассылку"
                    }
                }
                "/stat" -> {
                    val logStat =
                        File(pathToTestFolder + LocalDate.now().toString() + " logStat.txt") // Файл со статистикой
                    val inputFile = InputFile(logStat)
                    sendFile.document = inputFile
                    execute(sendFile)


                }
                "help" -> {
                    message.text =
                        "Для подписки на рассылку введите /go\n Для отображения статистики за день введите /stat"
                }
                else -> {
                    message.text =
                        "Извините, комманда не распознана \nДля подписки на рассылку введите /go\nДля отображения статистики за день введите /stat"
                }
            }
            execute(message)
        }
    }

    override fun getBotUsername(): String {
        return "TecmanTraiderByBit_bot"
    }

    override fun getBotToken(): String {
        return "5056719530:AAHbB8J5p2Bd1lIjavUTfKM02pN2Ej-BjPw"
    }

}