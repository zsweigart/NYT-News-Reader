package com.zacharysweigart.nytnewsreader.newsdetailactivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zacharysweigart.newscard.Category;
import com.zacharysweigart.nytnewsreader.R;
import com.zacharysweigart.nytnewsreader.util.ColorUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsDetailView extends LinearLayout {
    private Context context;
    private NewsDetailController newsDetailController;

    @Bind(R.id.news_detail_image)
    ImageView detailImage;
    @Bind(R.id.news_detail_image_copyright)
    TextView copyright;
    @Bind(R.id.news_detail_headline)
    TextView headline;
    @Bind(R.id.news_detail_abstract)
    TextView abstractView;
    @Bind(R.id.news_detail_read_more)
    TextView readMore;
    @Bind(R.id.news_detail_byline)
    TextView byline;

    public NewsDetailView(Context context) {
        super(context);
        this.context = context;

        inflate(context, R.layout.news_detail_view, this);

        ButterKnife.bind(this);
    }

    public void setDetailImage(String imageUrl) {
        if(!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(context).load(imageUrl).into(detailImage);
        } else {
            detailImage.setVisibility(View.GONE);
        }
    }

    public void setCopyright(String copyrightString) {
        copyright.setText(copyrightString);
        copyright.setVisibility(TextUtils.isEmpty(copyrightString) ? GONE : VISIBLE);
    }

    public void setHeadline(String headlineString) {
        headline.setText(headlineString);
        headline.setVisibility(TextUtils.isEmpty(headlineString) ? GONE : VISIBLE);
    }

    public void setAbstract(String abstractString) {
        abstractView.setText(abstractString);
        abstractView.setVisibility(TextUtils.isEmpty(abstractString) ? GONE : VISIBLE);
    }

    public void setByline(String bylineString) {
        byline.setText(bylineString);
        byline.setVisibility(TextUtils.isEmpty(bylineString) ? GONE : VISIBLE);
    }

    public void setArticleUrl(final String url) {
        readMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    public void setActionBar(String name) {
        Category category = Category.getCategoryFromName(name);
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(category.getColorResId())));
            actionBar.setTitle(category.getCategoryTitle());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity)context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ColorUtil.darkenColor(getResources().getColor(category.getColorResId())));
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setNewsDetailController(NewsDetailController newsDetailController) {
        this.newsDetailController = newsDetailController;
    }
}
