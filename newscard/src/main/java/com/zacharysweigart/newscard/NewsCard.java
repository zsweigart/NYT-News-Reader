package com.zacharysweigart.newscard;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class NewsCard extends CardView {
    private Context context;

    private TextView newsCategory;
    private ImageView newsImage;
    private TextView newsHeadline;
    private TextView newsByline;

    public NewsCard(Context context) {
        super(context);
        inflateLayout(context);
    }

    public NewsCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public NewsCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.news_card_layout, this, true);

        newsCategory = (TextView) view.findViewById(R.id.news_card_category);
        newsImage = (ImageView) view.findViewById(R.id.news_card_image);
        newsHeadline = (TextView) view.findViewById(R.id.news_card_headline);
        newsByline = (TextView) view.findViewById(R.id.news_card_byline);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = (int) Util.convertDpToPixel(8, context);
        params.setMargins(margin, margin, margin, margin);
        this.setLayoutParams(params);
    }

    public void setNewsCategory(String categoryName) {
        Category category = Category.getCategoryFromName(categoryName);
        this.newsCategory.setText(category.getCategoryTitle());
        this.newsCategory.setBackgroundColor(context.getResources().getColor(category.getColorResId()));
    }

    public void setNewsImage(String newsImageUrl) {
        if(!TextUtils.isEmpty(newsImageUrl)) {
            newsImage.setVisibility(View.VISIBLE);
            Picasso.with(context).load(newsImageUrl).into(newsImage);
        } else {
            newsImage.setImageBitmap(null);
            newsImage.setVisibility(View.GONE);
        }
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline.setText(newsHeadline);
    }

    public void setNewsByline(String newsByline) {
        this.newsByline.setText(newsByline);
    }

    public class Builder {
        private NewsCard newsCard;
        private Context context;

        public Builder(Context context) {
            this.newsCard = new NewsCard(context);
            this.context = context;
        }

        public Builder setCategory(String categoryName) {
            newsCard.setNewsCategory(categoryName);
            return this;
        }

        public Builder setImage(String imageUrl) {
            newsCard.setNewsImage(imageUrl);
            return this;
        }

        public Builder setHeadline(String headline) {
            newsCard.setNewsHeadline(headline);
            return this;
        }

        public Builder setByline(String byline) {
            newsCard.setNewsByline(byline);
            return this;
        }

        public NewsCard build() {
            return newsCard;
        }
    }
}
