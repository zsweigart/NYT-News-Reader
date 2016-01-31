package com.zacharysweigart.newscard;

public enum Category {
    MISCELLANEOUS(R.color.category_miscellaneous_blue, "News", "all"),
    SPORTS(R.color.category_sports_orange, "Sports", "sports"),
    ARTS(R.color.category_arts_yellow, "Arts", "arts"),
    FINANCIAL(R.color.category_financial_green, "Financial", "business"),
    HEALTH(R.color.category_health_purple, "Health", "health"),
    TRAVEL(R.color.category_travel_red, "Travel", "travel"),
    SCIENCE(R.color.category_science_indigo, "Science and Technology", "science;technology");

    private int colorResId;
    private String categoryTitle;
    private String urlString;

    Category(int colorResId, String categoryTitle, String urlString) {
        this.colorResId = colorResId;
        this.categoryTitle = categoryTitle;
        this.urlString = urlString;
    }

    public int getColorResId() {
        return colorResId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getUrlString() {
        return urlString;
    }

    public static Category getCategoryFromName(String name) {
        name = name.toLowerCase();

        if(name.contains("sport")) {
            return SPORTS;
        }

        if(name.contains("art") || name.contains("theater")) {
            return ARTS;
        }

        if(name.contains("financial") || name.contains("business")) {
            return FINANCIAL;
        }

        if(name.contains("health")) {
            return HEALTH;
        }

        if(name.contains("travel")) {
            return TRAVEL;
        }

        if(name.contains("science") || name.contains("tech")) {
            return SCIENCE;
        }

        return MISCELLANEOUS;
    }
}
