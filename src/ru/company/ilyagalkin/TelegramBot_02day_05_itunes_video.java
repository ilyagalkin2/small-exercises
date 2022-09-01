import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMediaGroup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVideo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TelegramBot_02day_05_itunes_video {
    public static void main(String[] args) throws InterruptedException {
        // Create your bot passing the token received from @BotFather
        TelegramBot bot = new TelegramBot("5530824224:AAFLOO4X2cjinAZa57r1ty8MkJnSzzlUbHo");
        // Register for updates
        bot.setUpdatesListener(updates -> {
            updates.forEach(upd -> {
                try {
                    long chatId = upd.message().chat().id();
                    String incomeMessage = upd.message().text();

                    //String movieName = "Terminator";
                    String jsonString = Jsoup.connect("https://itunes.apple.com/search?media=movie&term=" + incomeMessage)
                            .ignoreContentType(true)
                            .execute()
                            .body();
                    ObjectMapper objectMapper = new ObjectMapper();
                    var jsonNode = objectMapper.readTree(jsonString);
                    String resultUrlPhoto = jsonNode.get("results").get(0).get("artworkUrl100").asText();
                    String resultUrlVideo = jsonNode.get("results").get(0).get("previewUrl").asText();
                    System.out.println(resultUrlPhoto);
                    System.out.println(resultUrlVideo);

                    String messageToUserWithPhoto = "The URL for picture is: \n" + resultUrlPhoto + "\n";
                    String messageToUserWithVideo = "The URL for video is: " + resultUrlVideo + "\n" +
                            "On some systems videos will not be shown.";

                    SendMessage messageWithPhoto = new SendMessage(chatId, messageToUserWithPhoto);
                    SendMessage messageWithVideo = new SendMessage(chatId, messageToUserWithVideo);

                    SendPhoto requestForPhoto = new SendPhoto(chatId, resultUrlPhoto);
                    SendVideo requestForVideo = new SendVideo(chatId, resultUrlVideo);

                    bot.execute(messageWithPhoto);
                    bot.execute(messageWithVideo);

                    bot.execute(requestForPhoto);
                    bot.execute(requestForVideo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
}
