package ru.mirea.shunyaev.dialog;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private TextView tvDateandTime;
    private Button btPickDate;

    private Button pickTimeBtn;

    private Button ProgressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDateandTime = findViewById(R.id.tvDate);
        btPickDate = findViewById(R.id.dateButton);
        pickTimeBtn = findViewById(R.id.timeButton);
        ProgressBtn = findViewById(R.id.progressButton);
        btPickDate.setOnClickListener(v -> {
            MyDateDialogFragment mDatePickerDialogFragment;
            mDatePickerDialogFragment = new MyDateDialogFragment();
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            Snackbar.make(v, "Выбери дату", Snackbar.LENGTH_LONG)
                    .show();
        });
        pickTimeBtn.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view, hourOfDay, minute1) -> tvDateandTime.setText(hourOfDay + ":" + minute1), hour, minute, false);
            timePickerDialog.show();
            Snackbar.make(v, "Выбери время", Snackbar.LENGTH_LONG)
                    .show();
        });
    }

    public void onClickShowDialog(View view) {
        MyDialog dialogFragment = new MyDialog();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        tvDateandTime.setText(selectedDate);
    }
    public void onClickProgress(View view) {
        MyProgressDialogFragment Progress = new MyProgressDialogFragment();
        Progress.showDialog(MainActivity.this, "Загрузка");
        Snackbar.make(view, "Выбор прогресса", Snackbar.LENGTH_LONG)
                .show();
    }
}