import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TelegramBot_02day_01_currencies_rates {
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

                    //тип лонг - очень большое число, это номер id для сообщения
                    long chatId = upd.message().chat().id();
                    //Строка Имя
                    String senderFirstName = upd.message().from().firstName();
                    //Строка Фамилия
                    String senderSecondName = upd.message().from().lastName();
                    //Строка Имяпользователя - но пользователи не всегда его назначают и может быть null
                    //с более сложным кодом можно проверить что-то вроде isNotNull() и не выводить
                    String senderUsername = upd.message().from().username();
                    //Текст сообщения - такой, каким его прислал пользователь
                    String incomeMessage = upd.message().text();

                    //Зацензурированный вариант (что на самом деле сложно и делается большим словарём)
                    String censoredIncomeMessage = incomeMessage
                            .replaceAll("[Ff]uck", "***")
                            .replaceAll("arse", "***")
                            .replaceAll("dick", "***")
                            .replaceAll("ass", "***")
                            .replaceAll("трах", "***")
                            .replaceAll("секс", "***")
                            .replaceAll("ху[ий]", "***")
                            .replaceAll("пиписька", "***");

                    //Это из ДЗ, ненужная в реальном мире замена некоторых букв сочетанием букв
                    String replacedIncomeMessage = censoredIncomeMessage
                            .replaceAll("о", "обро")
                            .replaceAll("o", "o, bro, ");

                    //Строковая переменная, строится конкатенацией
                    // (плюсиками строк и переменных).
                    // Это ответ бота на написанное пользователем.
                    String message = "Dear " +
                            senderFirstName + " " + senderSecondName + ",\n" +
                            "Your username (if it is setted up) is: " + senderUsername + " .\n" +
                            "As a matter of fact, you have written: \n\n" +
                            "\"" + censoredIncomeMessage + "\"" + "\n\n" +
                            "But with the power that is given to me by a Programmer, " +
                            "Me, as an empowered bot, change all " +
                            "your 'o'-es to 'obro'-es and 'о' на 'обро': \n\n" +
                            "\"" + replacedIncomeMessage + "\"";

                    //Pattern pattern = Pattern.compile("(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})");
//                    Matcher matcher = pattern.matcher(incomeMessage);
//                    String dataFromIncomeMessage = "";
//                    String result = "";
//                    if (matcher.find()) {
//                        dataFromIncomeMessage = matcher.group(1);
//                        System.out.println(dataFromIncomeMessage);

                    Document doc = Jsoup.connect("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + incomeMessage).get();
                    Elements valutes = doc.select("Valute");
                    String resultAsPhrase = "";
                    for (Element valute : valutes) {
                        if (valute.attr("ID").equals("R01235")) {
                            String result = valute.select("Value").text();
                            resultAsPhrase = "RUB to USD ratio is: " + result;
                            System.out.println(resultAsPhrase);
                        }
                    }
//                    }

                    //Инициируется класс, который посылает сообщение
                    SendMessage request = new SendMessage(chatId, message);
                    SendMessage request02 = new SendMessage(chatId, resultAsPhrase);

                    //обращение к боту чтобы выполнить экземпляр класса,
                    // который посылает сообщение в чат
                    // (ответ бота в виде строки, которую мы строили одним
                    // из простых способов - конкатенацией)
                    bot.execute(request);
                    bot.execute(request02);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //Методы без слова void при объявлении должны возвращать
            // функции или значения, или что-то
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }
}

//8/12/2022

//Dear ilya galkin,
//Your username (if it is setted up) is: null .
//As a matter of fact, you have written:
//
//"08/08/2022"
//
//But with the power that is given to me by a Programmer, Me, as an empowered bot, change all your 'o'-es to 'obro'-es and 'о' на 'обро':
//
//"08/08/2022"
//

//RUB to USD ratio is: 60,3696