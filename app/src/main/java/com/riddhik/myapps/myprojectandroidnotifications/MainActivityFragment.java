package com.riddhik.myapps.myprojectandroidnotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    Button simpleNotificationBtn;
    Button expandedNotificationBtn;
    Button notificationWithBtn;
    Button bigPictureStyleBtn;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        simpleNotificationBtn = (Button) rootview.findViewById(R.id.simpleNotificationBtn);
        expandedNotificationBtn = (Button) rootview.findViewById(R.id.expandedNotificationBtn);
        notificationWithBtn = (Button) rootview.findViewById(R.id.notificationWithBtn);
        bigPictureStyleBtn = (Button) rootview.findViewById(R.id.bigpicturestyleNBtn);

        simpleNotificationBtn.setOnClickListener(this);
        expandedNotificationBtn.setOnClickListener(this);
        notificationWithBtn.setOnClickListener(this);
        bigPictureStyleBtn.setOnClickListener(this);

        return rootview;
    }

    public void simpleNotification() {
        Log.d(LOG_TAG, "In simmpleNotification() method");

        //1. Create a notification ID
        int notificationID = 100;

        //2. Build the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
        mBuilder.setSmallIcon(R.drawable.ic_stat_action_stars)
                .setContentTitle(getString(R.string.NotificationTitle))
                .setContentText(getString(R.string.NotificationText));

        //3. Create the result Intent (activity to show on clicking the notification
        Intent resultIntent = new Intent(getActivity(), ResultActivity.class);

        //4. Generate the activity stack using taskstack builder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        //Bind with pending intent
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        //Set pending intent to notification builder
        mBuilder.setContentIntent(pendingIntent);

        //5. Display the notification using NotificationManager
        NotificationManager mNotificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    public void expandedNotification() {
        Log.d(LOG_TAG, "In expandedNotification() method");

        //1. Create a notification ID
        int notificationID = 200;

        //2. Build the expanded notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
        mBuilder.setSmallIcon(R.drawable.ic_stat_action_stars)
                .setContentTitle(getString(R.string.ExpNotificationTitle))
                .setContentText(getString(R.string.ExpNotificationText));
        //Set notification as Inbox style for expanded view
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(getString(R.string.ExpNotificationTitle));
        //Expanded notification content
        for (int i = 0; i < 6; i++) {
            inboxStyle.addLine(getString(R.string.ExpandedText) + " " + i);
        }
        mBuilder.setStyle(inboxStyle);

        //3. Create the result Intent (activity to show on clicking the notification
        Intent resultIntent = new Intent(getActivity(), ResultActivity.class);

        //4. Generate the activity stack using taskstack builder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        //Set pending intent to notification builder
        mBuilder.setContentIntent(pendingIntent);

        //5. Display the notification using NotificationManager
        NotificationManager mNotificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    public void notificationWithBtn() {
        Log.d(LOG_TAG, "In notificationWithBtn() method");

        //1. Create a notification ID
        int notificationID = 300;

        //2. Build the expanded notification with button
        //Create pending intent for button action
        Intent btnIntent = new Intent(getActivity(), ResultActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getActivity(), 0, btnIntent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
        mBuilder.setSmallIcon(R.drawable.ic_stat_action_stars)
                .setContentTitle(getString(R.string.NotificationWtBtnTitle))
                .setContentText(getString(R.string.NotificationWtBtnText))
                .setTicker(getString(R.string.NotificationWtBtnTicker))
                .addAction(R.drawable.mail_icon, getString(R.string.NotificationBtnLabel), pIntent);

        //Set notification as Inbox style for expanded view
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(getString(R.string.NotificationWtBtnTitle));
        //Expanded notification content
        for (int i = 0; i < 6; i++) {
            inboxStyle.addLine(getString(R.string.ExpandedText) + " " + i);
        }
        mBuilder.setStyle(inboxStyle);

        //3. Create the result Intent (activity to show on clicking the notification
        Intent resultIntent = new Intent(getActivity(), ResultActivity.class);

        //4. Generate the activity stack using taskstack builder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        //Set intent to notification builder
        mBuilder.setContentIntent(pendingIntent);

        //5. Display the notification using NotificationManager
        NotificationManager mNotificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, mBuilder.build());

    }

    public void bigPictureStyleNotification() {
        Log.d(LOG_TAG, "In bigPictureStyleNotification() method");

        //1. Create a notification ID
        int notificationID = 400;

        //2. Build the expanded notification with button
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity());
        mBuilder.setSmallIcon(R.drawable.ic_stat_action_stars)
                .setContentTitle(getString(R.string.BigPictureStyleTitle))
                .setContentText(getString(R.string.BigPictureStyleText))
                .setTicker(getString(R.string.BigPictureStyleText));
        //Create a bitmpa image
        Bitmap movie_image = BitmapFactory.decodeResource(this.getResources(), R.drawable.movie_poster_the_martian);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle().bigPicture(movie_image);
        bigPictureStyle.setSummaryText(getString(R.string.BigPictureStyleText));
        mBuilder.setStyle(bigPictureStyle);

        //3. Create the result Intent (activity to show on clicking the notification
        Intent resultIntent = new Intent(getActivity(), ResultActivity.class);

        //4. Generate the activity stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        //Set intent to notification builder
        mBuilder.setContentIntent(pendingIntent);

        //5. Display the notification using NotificationManager
        NotificationManager mNotificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simpleNotificationBtn: {
                Log.d(LOG_TAG, "Simple notification button clicked");
                simpleNotification();
                break;
            }
            case R.id.expandedNotificationBtn: {
                Log.d(LOG_TAG, "Expanded notification button clicked");
                expandedNotification();
                break;
            }
            case R.id.notificationWithBtn: {
                Log.d(LOG_TAG, "Notification with button, button clicked");
                notificationWithBtn();
                break;
            }
            case R.id.bigpicturestyleNBtn: {
                Log.d(LOG_TAG, "BigPicture style notification button clicked");
                bigPictureStyleNotification();
                break;
            }
        }
    }
}
