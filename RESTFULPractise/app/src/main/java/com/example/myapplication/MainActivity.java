package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.WeatherData;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class MainActivity extends AppCompatActivity {

    String url = "http://api.openweathermap.org/data/2.5";
    TextView city, status, humidity, pressure,temperature;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        city = (TextView) findViewById(R.id.txt_city);
        status = (TextView) findViewById(R.id.txt_status);
        humidity = (TextView) findViewById(R.id.txt_humidity);
        pressure = (TextView) findViewById(R.id.txt_press);
        temperature = (TextView)findViewById(R.id.txt_temperature);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.change_city:
                AlertDialog alertDialog = null;
                final EditText input = new EditText(MainActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Change City")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String cityName = input.getText().toString();
                                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
                                RestInterface restInterface = adapter.create(RestInterface.class);
                                getWeatherData(cityName,restInterface);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog = builder.create();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.show();
                break;
        }
        return handled;
    }
    private void getWeatherData(String query,RestInterface restInterface){
        restInterface.getWheatherReport(query,"f1a940fdccac25738777209d271adb36","metric",new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, retrofit.client.Response response) {
                city.setText("city :"+weatherData.getName());
                status.setText("Status :"+weatherData.getWeather()[0].getDescription());
                humidity.setText("humidity :"+weatherData.getMain().getHumidity().toString()+"%");
                pressure.setText("pressure :"+weatherData.getMain().getPressure().toString());
                temperature.setText("Temperature :"+weatherData.getMain().getTemp()+" celsius");
            }
            @Override
            public void failure(RetrofitError error) {
                String mError = error.getMessage();
                Toast.makeText(MainActivity.this,mError,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
