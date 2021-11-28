package com.antukbala.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextCity);
        textView = findViewById(R.id.textViewWeather);
    }

    public void getWeatherUpdate(View view) {
        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        String cityName = editText.getText().toString().trim();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=0f93a4a49fa83e9b7c05219faf55a786";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    JSONObject object2 = response.getJSONObject("sys");

                    String city = response.getString("name");
                    String country = object2.getString("country");
                    String temp = object.getString("temp");
                    String humidity = object.getString("humidity");

                    Double convert = Double.parseDouble(temp)-273.15;
                    String centigrade = convert.toString().substring(0,4);

                    String output = city+", "+country+"\n\nCurrent Temperature:\n"+centigrade+" degree centigrade.\n\n"+
                            "Humidity:\n"+humidity+" %\n";

                    textView.setText(output);
                    textView.setTextColor(Color.RED);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("");
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}