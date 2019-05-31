package com.thatguyalex.model;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NewsFeed {

    public static List<News> getNews() throws Exception {
        List<News> news = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader( "src/test/resources/news.txt"));
        String line = reader.readLine();
        while (!StringUtils.isBlank(line)) {
            news.add(new News(line.trim()));
            line = reader.readLine();
        }
        reader.close();
        return news;
    }

    public static class News {
        public String name, url;
        public News(String name, String url) {
            this.name = name;
            this.url = url;
        }
        public News(String unparsed) {
            int location = unparsed.indexOf("|");
            this.name = unparsed.substring(0, location);
            this.url = unparsed.substring(location+1);
        }
    }

}
