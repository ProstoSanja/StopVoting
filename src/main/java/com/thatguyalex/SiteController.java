package com.thatguyalex;

import com.thatguyalex.model.Lang;
import com.thatguyalex.model.NewsFeed;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Controller
public class SiteController {

    private int votes = 0;
    private long days = 0;
    private List<NewsFeed.News> news = new ArrayList<>();
    private Lang lang = new Lang();

    @GetMapping({"/", "/et"})
    public String index(Model model) {
        setmodel(model);
        model.addAttribute("lang", lang.et);
        return "index";
    }
    @GetMapping("/ru")
    public String indexru(Model model) {
        setmodel(model);
        model.addAttribute("lang", lang.ru);
        return "index";
    }
    @GetMapping("/en")
    public String indexen(Model model) {
        setmodel(model);
        model.addAttribute("lang", lang.en);
        return "index";
    }
    private void setmodel(Model model) {
        model.addAttribute("votes", votes);
        model.addAttribute("days", days);
        model.addAttribute("news", news);
    }

    @Scheduled(fixedRate = 1000*60*5)
    public void reportCurrentTime() {
        try {
            long diffInMillies = (new Date()).getTime() - (new SimpleDateFormat("ddMMyyyy").parse("05032019")).getTime();
            days =  TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        } catch (Exception ignored) {
            days = 0;
        }
        try {
            votes = Objects.requireNonNull((new File("src/test/resources/signed/")).list()).length;
        } catch (Exception ignored) {
            votes = 0;
        }
        try {
            news = NewsFeed.getNews();
        } catch (Exception ignored) {

        }
    }

}
