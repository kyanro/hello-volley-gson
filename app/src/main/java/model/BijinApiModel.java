package model;

import java.util.List;

/**
 * Created by ppp on 2014/08/28.
 */
public class BijinApiModel {

    public static class BijinModel {
        public int id;
        public String link;
        public Category category;
        public String thumb;
        public String pubDate;
    }

    public static class Category {
        public String category;
    }
}