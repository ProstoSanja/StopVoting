package com.thatguyalex;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.Objects;

@Controller
public class SiteController {

    private int votes = 0;

    @GetMapping("/")
    public String index() {
        //TODO votes
        return "index";
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        try {
            votes = Objects.requireNonNull((new File("src/test/resources/signed/")).list()).length;
        } catch (Exception ignored) {
            votes = 0;
        }
    }

}
