package com.example.exerciseproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowHeadlines extends Activity {
    ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
    ListView myListView;
    String urlAddress = "";
    String urlCaption = "";
    SingleItem selectedNewsItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        myListView = (ListView) this.findViewById(R.id.myListView);

        Intent callingIntent = getIntent();

        Bundle myBundle = callingIntent.getExtras();
        urlAddress = myBundle.getString("urlAddress");
        urlCaption = myBundle.getString("urlCaption");
        this.setTitle("NPR - " + urlCaption + " \t" + MainActivityChannel.niceDate());

        myListView = (ListView) this.findViewById(R.id.myListView);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int index, long id) {
                selectedNewsItem = newsList.get(index);
                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }
        });

        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
        downloader.execute(urlAddress, urlCaption);
    }

    public void showNiceDialogBox(SingleItem selectedStoryItem, Context context) {
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())) {
            description = "";
        }
        try {
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder
                    .setIcon(R.drawable.vnexpress)
                    .setTitle(Html.fromHtml(urlCaption))
                    .setMessage(title + "\n\n" + Html.fromHtml(description) + "\n")
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichOne) {
                            Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                            startActivity(browser);
                        }
                    })
                    .show();
        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage());
        }
    }
}
