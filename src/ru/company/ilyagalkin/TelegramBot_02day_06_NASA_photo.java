import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVideo;
import org.jsoup.Jsoup;

public class TelegramBot_02day_06_NASA_photo {
    public static void main(String[] args) throws InterruptedException {
        // Create your bot passing the token received from @BotFather
        TelegramBot bot = new TelegramBot("5530824224:AAFLOO4X2cjinAZa57r1ty8MkJnSzzlUbHo");
        // Register for updates
        bot.setUpdatesListener(updates -> {

            updates.forEach(upd -> {
                try {
                    long chatId = upd.message().chat().id();
                    String incomeMessage = upd.message().text();

                    //String datePhoto = "2010-10-10";
                    String datePhoto = incomeMessage;
                    String jsonString = Jsoup.connect("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + incomeMessage)
                            .ignoreContentType(true)
                            .execute()
                            .body();

                    ObjectMapper objectMapper06 = new ObjectMapper();
                    var jsonNode = objectMapper06.readTree(jsonString);

                    String imageUrl = jsonNode.get("url").asText();
                    String imageExplanation = jsonNode.get("explanation").asText();
                    System.out.println(imageUrl);
                    System.out.println(imageExplanation);
                    String result = imageUrl + "\n" + imageExplanation;

                    SendMessage request01 = new SendMessage(chatId, result);
                    //SendPhoto request02 = new SendPhoto(chatId, imageUrl);

                    bot.execute(request01);
                    //bot.execute(request02);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
}
