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
        en.put("error_no_implementation", "Your browser does not support Estonian ID card.");
        en.put("error_no_certificates", "Failed to find ID card certificates, if you have plugged in your card now, please refresh the page.");
        en.put("error_unknown", "An unknown error has been encountered: ");
        en.put("error_auth_failed", "Authentication has failed.");
        en.put("error_failed_creation", "Failed to initiate signing process.");
        en.put("error_failed_signature", "Failed to sign the document.");
        en.put("error_delete_failed", "Failed to delete the document.");


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
        et.put("error_no_implementation", "Teie weebilugeja ei toeta Eesti ID kaarti.");
        et.put("error_no_certificates", "ID kaarti leidmine ebaõnnestus, kui te sisestasite seda praegu, palun laadige see veebileht ümber.");
        et.put("error_unknown", "Ilmus tundmatu viga: ");
        et.put("error_auth_failed", "Sisselogimine ebaõnnestus.");
        et.put("error_failed_creation", "Allkirjastamise algatamine ebaõnnestus.");
        et.put("error_failed_signature", "Allkirjastamine ebaõnnestus.");
        et.put("error_delete_failed", "Allkirja kustutamine ebaõnnestus.");


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
        ru.put("error_no_implementation", "Выш браузер не поддерживает Эстонскую ИД карту.");
        ru.put("error_no_certificates", "Не удалось найти ИД карту, если вы её теперь вставили, пожалуйста перезагрузите эту страницу.");
        ru.put("error_unknown", "Произошла неизвестная ошибка: ");
        ru.put("error_auth_failed", "не удалост подтвердить личность.");
        ru.put("error_failed_creation", "Не удалось начать процесс подписи.");
        ru.put("error_failed_signature", "Не удалось подписать документ.");
        ru.put("error_delete_failed", "Не удалось удалить подпись.");
    }
}
