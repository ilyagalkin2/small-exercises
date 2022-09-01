import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TelegramBot_02day_02_lenta_ru {
    public static void main(String[] args) {
        // Create your bot passing the token received from @BotFather
        TelegramBot bot = new TelegramBot("5530824224:AAFLOO4X2cjinAZa57r1ty8MkJnSzzlUbHo");

        // Register for updates
        bot.setUpdatesListener(updates -> {

            // ... process updates
            // return id of last processed update or confirm them all
            updates.forEach(upd -> {
                //if our bot is withing other people's channels and we see it is
                //working but the messages are null
                try {
                    //чтобы отобразить в консоли
                    //System.out.println(upd);
                    long chatId = upd.message().chat().id();
                    String incomeMessage = upd.message().text();

//                    int number = 1;
                    int number = Integer.parseInt(incomeMessage);
                    Document doc02 = Jsoup.connect("https://lenta.ru/rss").get();
                    int index = number - 1;
                    Element news = doc02.select("item").get(index);
                    String category = news.select("category").text();
                    String title = news.select("title").text();
                    String link = news.select("link").text();
                    String description = news.select("description").text();
                    String resultLenta = category + "\n" + title + "\n" + description + "\n" + link;

                    SendMessage request = new SendMessage(chatId, resultLenta);

                    bot.execute(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
}
