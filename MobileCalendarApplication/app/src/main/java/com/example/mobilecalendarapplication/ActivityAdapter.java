package com.example.mobilecalendarapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {

    private List<com.example.mobilecalendarapplication.Activity> activityList = new ArrayList<>();
    private static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private TextView activityName, activityDescription;
        private TextView activityStartDate, activityStartTime;
        private TextView activityEndDate, activityEndTime;
        private TextView activityAddress;
        private TextView activityRemeinderDate1, activityRemeinderTime1;
        private TextView activityRemeinderDate2, activityRemeinderTime2;

        private Button deleteActivityButton;
        private Button updateActivityButton;
        private Button shareActivityButton;

        private DatabaseHelper databaseHelper;

        private Toast toast;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            activityName = (TextView) view.findViewById(R.id.activityName);
            activityDescription = (TextView) view.findViewById(R.id.activityDescription);
            activityStartDate = (TextView) view.findViewById(R.id.activityStartDate);
            activityStartTime = (TextView) view.findViewById(R.id.activityStartTime);
            activityEndDate = (TextView) view.findViewById(R.id.activityEndDate);
            activityEndTime = (TextView) view.findViewById(R.id.activityEndTime);
            activityAddress = (TextView) view.findViewById(R.id.activityAddress);
            activityRemeinderDate1 = (TextView) view.findViewById(R.id.activityRemeinderDate1);
            activityRemeinderTime1 = (TextView) view.findViewById(R.id.activityRemeinderTime1);
            activityRemeinderDate2 = (TextView) view.findViewById(R.id.activityRemeinderDate2);
            activityRemeinderTime2 = (TextView) view.findViewById(R.id.activityRemeinderTime2);



            deleteActivityButton = view.findViewById(R.id.deleteActivityButton);
            updateActivityButton = view.findViewById(R.id.updateActivityButton);
            shareActivityButton = view.findViewById(R.id.shareActivityButton);

            deleteActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Mobile Calendar Application!");
                    builder.setMessage("Record delete?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {


                            String name, descp;
                            int position = getAdapterPosition();

                            Log.d("button => delete => ", "  " + position);
                            Log.d("button => delete => ", "  " + position + "  Name -> " + activityList.get(position).getName());

                            Activity activity = activityList.get(position);
                            int activityID;

                            // alarm iptal 1
                            activityID = activity.getActivityID() * 10;
                            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
                            Intent intent = new Intent(mContext, AlertReceiver.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, activityID, intent, 0);
                            alarmManager.cancel(pendingIntent);

                            // alarm iptal 2
                            activityID = activity.getActivityID() * 10 + 1;
                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(mContext, activityID, intent, 0);
                            alarmManager.cancel(pendingIntent1);

                            name = activityList.get(position).getName();
                            descp = activityList.get(position).getDescription();
                            databaseHelper = new DatabaseHelper(mContext);
                            databaseHelper.deleteUser(name,descp);


                            Log.d("activity 0 ", mContext.getClass().getSimpleName());

                            switch (mContext.getClass().getSimpleName()) {

                                case "listActivity":
                                    listActivity.onDelete(position);
                                    break;

                                case "calendarDayActivity":
                                    calendarDayActivity.onDelete(position);
                                    break;

                                case "calendarWeekActivity":
                                    calendarWeekActivity.onDelete(position);
                                    break;

                                case "calendarMonthActivity":
                                    calendarMonthActivity.onDelete(position);
                                    break;
                                    
                                default:

                                    break;
                            }

                            toast = Toast.makeText(mContext, "The record was deleted.", Toast.LENGTH_LONG);
                            toast.show();



                        }
                    });


                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            toast = Toast.makeText(mContext, "The delete was discarded.", Toast.LENGTH_LONG);
                            toast.show();


                        }
                    });

                    builder.show();














                }
            });


            updateActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int position = getAdapterPosition();
                    Intent intent = new Intent(mContext, updateActivity.class);
                    Bundle bundle = new Bundle();
                    Activity activity = activityList.get(position);
                    Activity activityNew = new Activity(
                            activity.getActivityID(),
                            activity.getName(),
                            activity.getDescription(),
                            activity.getStartDate(),
                            activity.getStartTime(),
                            activity.getEndDate(),
                            activity.getEndTime(),
                            activity.getAddress(),
                            activity.getRemeinderStartDate1(),
                            activity.getRemeinderStartTime1(),
                            activity.getRemeinderStartDate2(),
                            activity.getRemeinderStartTime2());

                    bundle.putSerializable("activity", (Serializable) activityNew);
                    bundle.putInt("position", position);
                    intent.putExtra("bundle",bundle);
                    mContext.startActivity(intent);

                }
            });

            shareActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int position = getAdapterPosition();


                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);


                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, Activity.toString(activityList.get(position)));
                    mContext.startActivity(Intent.createChooser(intent, "Share"));


                }
            });




        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Log.d("button => short => ", "  " + position);

        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            return false;
        }



    }



    public ActivityAdapter(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_card, parent, false);
        mContext = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Activity activity = activityList.get(position);

        holder.activityName.setText("Etkinlik Adı: "+activity.getName());
        holder.activityDescription.setText("Detay: "+activity.getDescription());
        holder.activityStartDate.setText("Başlangıç Tarihi: "+activity.getStartDate().toString());
        holder.activityStartTime.setText("Başlangıç Saati: "+activity.getStartTime().toString());
        holder.activityEndDate.setText("Bitiş Tarihi: "+activity.getEndDate().toString());
        holder.activityEndTime.setText("Bitiş Saati: "+activity.getEndTime().toString());
        holder.activityAddress.setText("Adres: "+activity.getAddress());
        holder.activityRemeinderDate1.setText("Hatırlatıcı Tarihi 1:"+activity.getRemeinderStartDate1());
        holder.activityRemeinderTime1.setText("Hatırlatıcı Saati 1:"+activity.getRemeinderStartTime1());
        holder.activityRemeinderDate2.setText("Hatırlatıcı Tarihi 2:"+activity.getRemeinderStartDate2());
        holder.activityRemeinderTime2.setText("Hatırlatıcı Saati 2:"+activity.getRemeinderStartTime2());

    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }






}
