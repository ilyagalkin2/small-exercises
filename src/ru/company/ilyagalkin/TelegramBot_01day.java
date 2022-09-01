import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

public class TelegramBot_01day {
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

                    //Текчт сообщения такой, каким его прислал пользователь
                    String incomeMessage = upd.message().text();

                    //Зацензурированный вариант (что на самом деле сложно и делается большим словарём)
                    String censoredIncomeMessage = incomeMessage
                            .replaceAll("[Ff]uck", "***")
                            .replaceAll("arse", "***")
                            .replaceAll("dick", "***")
                            .replaceAll("ass", "***")
                            .replaceAll("трах", "***")
                            .replaceAll("плохой", "***");

                    //Это из ДЗ, ненужная в реальном мире замена некоторых букв сочетанием букв
                    String replacedIncomeMessage = censoredIncomeMessage
                            .replaceAll("о", "обро")
                            .replaceAll("o", ", bro, ");

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

                    //Инициируется класс, который посылает сообщение
                    SendMessage request = new SendMessage(chatId, message);

                    //обращение к боту чтобы выполнить экземпляр класса,
                    // который посылает сообщение в чат
                    // (ответ бота в виде строки, которую мы строили одним
                    // из простых способов - конкатенацией)
                    bot.execute(request);
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

//User:

//Fuck, man! It's awesome. You're smart ass! No dickhead arse could make such a great work to change offensive words like плохой to censored ones!
//What a great трах with computer! Good work!
//Работа - изучать новое.

//Bot:

//Dear ilya galkin,
//Your username (if it is setted up) is: null .
//As a matter of fact, you have written:
//
//"***, man! It's awesome. You're smart ***! No ***head *** could make such a great work to change offensive words like *** to censored ones!
//What a great *** with computer! Good work!
//Работа - изучать новое."
//
//But with the power that is given to me by a Programmer, Me, as an empowered bot, change all your 'o'-es to 'obro'-es and 'о' на 'обро':
//
//"***, man! It's awes, bro, me. Y, bro, u're smart ***! N, bro,  ***head *** c, bro, uld make such a great w, bro, rk t, bro,  change , bro, ffensive w, bro, rds like *** t, bro,  cens, bro, red , bro, nes!
//What a great *** with c, bro, mputer! G, bro, , bro, d w, bro, rk!
//Рабоброта - изучать нобровоброе."