package com.udacity.googleindiascholarships.utils;

import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;
import com.squareup.picasso.Picasso;
import com.udacity.googleindiascholarships.R;
import com.udacity.googleindiascholarships.community.ui.entities.ExternalLinks;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by Rajat Kumar Gupta on 13/05/2018.
 */

public class CustomDialog extends Dialog implements View.OnClickListener{
    private ExternalLinks linkSelected;
    private TextView previewTitle;
    private ImageView previewImage;
    private Button openBtn;
    private Button cancelBtn;
    private Context context;
    private ProgressBar progressBar;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public CustomDialog(@NonNull Context context, ExternalLinks linkSelected) {
        super(context);
        this.context  =context;
        this.linkSelected = linkSelected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        previewTitle = findViewById(R.id.preview_title);
        previewImage = findViewById(R.id.preview_image);
        openBtn = findViewById(R.id.open_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        progressBar = findViewById(R.id.dialog_loading_progress);

        TextCrawler textCrawler = new TextCrawler();

        textCrawler.makePreview(new LinkPreviewCallback() {
            @Override
            public void onPre() {
                progressBar.setVisibility(View.VISIBLE);
                previewTitle.setVisibility(View.GONE);
                previewImage.setVisibility(View.GONE);
                openBtn.setVisibility(View.GONE);
                cancelBtn.setVisibility(View.GONE);
            }

            @Override

            public void onPos(SourceContent sourceContent, boolean b) {
                if (!sourceContent.isSuccess()) {

                } else {
                    progressBar.setVisibility(View.GONE);
                    Random random = new Random();
                    int size = sourceContent.getImages().size();
                    previewTitle.setText(sourceContent.getTitle());
                    previewTitle.setVisibility(View.VISIBLE);
                    if(size>0)
                        Picasso.with(context).load(sourceContent.getImages().get(random.nextInt(size))).into(previewImage);
                    previewImage.setVisibility(View.VISIBLE);
                    openBtn.setVisibility(View.VISIBLE);
                    cancelBtn.setVisibility(View.VISIBLE);
                }
            }
        }, linkSelected.getLinkUrl().trim());

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
