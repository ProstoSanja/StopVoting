package com.thatguyalex.model;

import java.util.HashMap;
import java.util.Map;

public class Lang {

    public Map<String, String> et;
    public Map<String, String> ru;
    public Map<String, String> en;

    public Lang() {
        en = new HashMap<>();
        en.put("lang", "en");
        en.put("areyousure", "Are you sure?");
        en.put("thankyou", "Thank you, ");
        en.put("welcome", "Welcome, ");
        en.put("iwantstop", "I want this to STOP");
        en.put("takeaction", "Take Action");
        en.put("cancelvote", "Cancel my vote");
        en.put("download", "Download signed document");
        en.put("peoplevoted", "People who have voted: ");
        en.put("gov1", "New government entered into power ");
        en.put("gov2", " days ago. This happened since:");
        en.put("shareon", "Share on: ");

        et = new HashMap<>();
        et.put("lang", "et");
        et.put("areyousure", "Kas te olete kindel?");
        et.put("thankyou", "Aitäh, ");
        et.put("welcome", "Tere tulemast, ");
        et.put("iwantstop", "Ma tahan seda PEATADA");
        et.put("takeaction", "Tegutsema");
        et.put("cancelvote", "Tühista minu hääl");
        et.put("download", "Laadi alla dokument");
        et.put("peoplevoted", "Inimesi hääletas: ");
        et.put("gov1", "Uus valitsus astus sisse ");
        et.put("gov2", " päeva tagasi. See juhtus selle aja jooksul:");
        et.put("shareon", "Jaga teistega: ");


        ru = new HashMap<>();
        ru.put("lang", "ru");
        ru.put("areyousure", "Вы уверены?");
        ru.put("thankyou", "Спасибо, ");
        ru.put("welcome", "Добро пожаловать, ");
        ru.put("iwantstop", "Я хочу это ОСТАНОВИТЬ");
        ru.put("takeaction", "Действовать");
        ru.put("cancelvote", "Отменить мой голос");
        ru.put("download", "Скачать подписанный документ");
        ru.put("peoplevoted", "Людей проголосовало: ");
        ru.put("gov1", "Новое правительство вступило в должность ");
        ru.put("gov2", " дней назад. С тех пор произошло следующее:");
        ru.put("shareon", "Поделиться: ");
    }
}
