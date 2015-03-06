package com.alberto.tareaweb;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alberto.tareaweb.data.NombreArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MainActivity extends Activity {

    public static NombreArray mNamesArray;
    public NombreListAdapter mNameListAdapter;
    public ListView listView;
    public Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoad = (Button) findViewById(R.id.btn_load);
        listView = (ListView) findViewById(R.id.list_view);


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webServices.getNames(mNamesHandler);
                btnLoad.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });
    }

    protected Handler mNamesHandler;

    {
        mNamesHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                //get response
                String response = bundle != null ? bundle.getString("response") : "";

                //Check response
                if ((response.equals("")) || response.equals("no connection")) {
                    //No internet access
                    Toast.makeText(getBaseContext(),
                            getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        //get json response into object
                        mNamesArray = new Gson().fromJson(response, NombreArray.class);
                        mNameListAdapter = new NombreListAdapter(getBaseContext(), mNamesArray.getNombre());
                        listView.setAdapter(mNameListAdapter);
                        btnLoad.setBackgroundColor(getResources().getColor(R.color.green));

                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        //invalid json response from the server
                        Toast.makeText(getBaseContext(), getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                    }
                }
            }

        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}