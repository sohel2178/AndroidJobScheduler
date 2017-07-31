package com.linearbd.androidjobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private JobScheduler mJobScheduler;
    private Button mScheduleJobButton;
    private Button mCancelAllJobsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        mScheduleJobButton = (Button) findViewById( R.id.schedule_job );
        mCancelAllJobsButton = (Button) findViewById( R.id.cancel_all );

        mScheduleJobButton.setOnClickListener(this);
        mCancelAllJobsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.schedule_job:
                scheduleJob();
                break;

            case R.id.cancel_all:
                cancelAllJob();
                break;
        }
    }

    private void scheduleJob() {
        Log.d("HJJHHJ","Start in the Method");
        ComponentName componentName = new ComponentName(getPackageName(),MyJobSchedulerService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(1,componentName)
                .setMinimumLatency(1000)
                .setOverrideDeadline(10000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);


        JobInfo jobInfo =builder.build();

        mJobScheduler.schedule(jobInfo);

        /*if(mJobScheduler.schedule(jobInfo)==JobScheduler.RESULT_FAILURE){
            Toast.makeText(this, "Some Problem Happen", Toast.LENGTH_SHORT).show();

        }*/
    }

    private void cancelAllJob() {
        mJobScheduler.cancelAll();
    }
}
