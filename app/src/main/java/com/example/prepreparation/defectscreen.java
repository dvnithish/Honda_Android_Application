package com.example.prepreparation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class defectscreen extends AppCompatActivity {
    Connection connect;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defectscreen);
        TextView submit = (TextView)findViewById(R.id.submit);
        TextView logout = (TextView)findViewById(R.id.logout);
        TextView fetchdata = (TextView) findViewById(R.id.fetchdata);
        ListView list = (ListView) findViewById(R.id.gridview1);
        TextView vehicleid = (TextView)findViewById(R.id.vehicleid);
        Intent receiverIntent = getIntent();
        String receiverValue = receiverIntent.getStringExtra("KEY_SENDER");
        vehicleid.setText(receiverValue);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(defectscreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insertDatabase(vehicleid.getText().toString());
                Toast.makeText(getApplicationContext(), "Data Save", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        fetchdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Boolean> checkedList = new ArrayList<>();
                List<Map<String, String>> data = new ArrayList<Map<String, String>

                        >();
                try {
                    connect = connectionclass();
                    if (connect != null) {
                        String query = "SELECT * FROM ref_PrepProperties";
                        Statement st = connect.createStatement();
                        ResultSet resultSet = st.executeQuery(query);
                        while (resultSet.next()) {
                            Map<String, String> tab = new HashMap<String, String>();
                            tab.put("Defect", resultSet.getString("Defect"));
                            data.add(tab);
                            checkedList.add(false);
                        }
                        adapter = new CustomAdapter(defectscreen.this, data, checkedList);
                        list.setAdapter(adapter);
                    }
                } catch (Exception ex) {
                    Log.e("set Error", ex.getMessage());
                }
            }
        });

    }
    @SuppressLint("NewApi")
    public Connection connectionclass() {
        Connection con = null;
        String ip = "192.168.1.116", port = "1433", username = "hmsi", password = "hmsi12#", databasename = "honda_barcode";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";user=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(connectionURL);
            Log.w("Connection", "open");
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
        return con;
    }

}