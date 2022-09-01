import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.jsoup.Jsoup;

public class TelegramBot_02day_07_GIFs {
    public static void main(String[] args) throws InterruptedException {
        // Create your bot passing the token received from @BotFather
        TelegramBot bot = new TelegramBot("5530824224:AAFLOO4X2cjinAZa57r1ty8MkJnSzzlUbHo");

        // Register for updates
        bot.setUpdatesListener(updates -> {

            updates.forEach(upd -> {
                try {
                    //System.out.println(upd);
                    long chatId = upd.message().chat().id();

                    //Строковая переменная incomeMessage = обновляемое сообщение пользователя как текст
                    String incomeMessage = upd.message().text();

                    //Сообщение пользователя может отличаться от просто слова-тэга, нужна та или иная валидация
                    //но, мы просто объявляем массив строк tagFromIncomeMessage[] и делим сообщение пользователя на пробелы " "
                    String tagFromIncomeMessage[] = incomeMessage.split(" ", 0);

                    //получаем строковую переменную tag как первый, то есть нулевой элемент массива
                    String tag =(String) tagFromIncomeMessage[0];

                    //удобно поставить breakpoint - точку останова для дебаггера, чтобы проверить,
                    // что же у нас в итгое в строковой переменной tag
                    //System.out.println(tag);

                    // for tag 'reach'
                    // https://api.giphy.com/v1/gifs/random?api_key=RAtkuUn1DXs7MIEbJWR2iGfAgo2On66q&tag=rich&rating=g
                    // for tag 'broke'
                    // https://api.giphy.com/v1/gifs/random?api_key=RAtkuUn1DXs7MIEbJWR2iGfAgo2On66q&tag=broke&rating=g

                    //делаем запрос согласно документации к библиотеки org.jsoup
                    String jsonString = Jsoup.connect("https://api.giphy.com/v1/gifs/random?api_key=RAtkuUn1DXs7MIEbJWR2iGfAgo2On66q&tag=" + tag + "&rating=g")
                            .ignoreContentType(true)
                            .execute()
                            .body();

                    //Для парсинга полученного ответа сервера в виде JSON используем
                    // библиотеку jackson.databind

                    //Ещё одна точка останова для дебаггера. Хорошая.
                    // Что сделаем? Оставим на вечер. А! Нет, это же шутка про
                    // вражеский пармезан...
                    // Тогда... Тогда - зелёный чай, с обычным сыром, у-у-у, вкусно.
                    // Как раз можно будет перекопировать этот JSON
                    // во что-то типа "JSON beautify"
                    //System.out.println(jsonString);

                    //Создаём экземпляр (нужного для парсинга) класса
                    ObjectMapper objectMapper = new ObjectMapper();

                    //Считываем JSON
                    var jsonNode = objectMapper.readTree(jsonString);

                    //Нужна ссылка на .gif data-images-original-url
                    //удобнее выискивать в debugger-е
                    //пишем примерную строку и ставим точку останова
                    //подбираем запросы нужным образом
                    String resultUrl = jsonNode.get("data").get("images").get("original").get("url").asText();

                    //выводим строку в консоль, для удобства
                    //System.out.println(resultUrl);

                    //Конкатенация строк, создаём сообщение
                    String resultUrlWithTag = "Tag that was used is: " + tag + "\n" +
                            "The URL with GIF is: " + resultUrl;

                    //Инициализируем класс, то есть создаём экземпляр класса
                    SendMessage answerMessage1 = new SendMessage(chatId, resultUrlWithTag);

                    //команда выполнить экземпляр класса
                    bot.execute(answerMessage1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
}

// women money beach cars

//Tag that was used is: women
//The URL with GIF is: https://media1.giphy.com/media/3oKIPrpqff5q4RZrnq/giphy.gif?cid=6c992ab173f85b5dd5f87986dd1505bc5474f1b4261f155d&rid=giphy.gif&ct=g
