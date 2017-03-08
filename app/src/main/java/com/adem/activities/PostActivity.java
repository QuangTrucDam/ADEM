package com.adem.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adem.R;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class PostActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    private DiscreteSeekBar seekBar;
    private TextView tv_frequency;
    private Button tv_timefrom;
    private Button tv_timeto;
    private ImageView iv_selectImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().setTitle("Cài đặt bài viết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setEventView();
        /*
        * TODO: start code here
        * */
        selectImage();

    }

    private void selectImage() {
        iv_selectImage = (ImageView) findViewById(R.id.iv_selectImage);
        iv_selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // For multiple images
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 100);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }else if (item.getItemId() == R.id.menu_post){
            Toast.makeText(this, "Post", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) { // single pick
            iv_selectImage.setImageURI(data.getData());
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {

        }
    }

    private void setEventView() { // set su kien select cho cac view
        seekBar = (DiscreteSeekBar) findViewById(R.id.seekBarfrequency);

        tv_frequency = (TextView) findViewById(R.id.tv_frequency);

        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_frequency.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        tv_timefrom = (Button) findViewById(R.id.tv_timefrom);
        tv_timeto = (Button) findViewById(R.id.tv_timeto);

        tv_timefrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(tv_timefrom);
            }
        });
        tv_timeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(tv_timeto);
            }
        });
    }

    public void face_share(View view) {
        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        view.startAnimation(fadein);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }

    public void showTimePicker(final TextView tv) {
        TimePickerDialog dpd = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                Log.d("timeselect", "select time");
                String time = hourOfDay +" : "+ minute;
                tv.setText(time);
            }
        },12,30,20,true);
        dpd.show(getFragmentManager(),"TimePicker");
    }
}
