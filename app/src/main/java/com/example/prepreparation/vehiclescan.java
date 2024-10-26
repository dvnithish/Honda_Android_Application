package com.example.prepreparation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class vehiclescan extends AppCompatActivity {
    private TextView employeename,vehicleid,btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiclescan);

        employeename = (TextView) findViewById(R.id.employeename);
        vehicleid = (TextView) findViewById(R.id.vehicleid);
    //    vehicleid.setText("Scan Next Vehicle");
        btn = (TextView) findViewById(R.id.scanvehicleid);

        vehicleid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionclass();
                try {
                    if (connection != null) {
                        //  String rememberMeValue = rememberMeCheckbox.isChecked() ? ;
                        String sqlinsert = "INSERT INTO presample1 (vehicleid) VALUES ('"
                                + vehicleid.getText().toString() + "')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                } catch (Exception exception) {
                    Log.e("Error", exception.getMessage());
                }

                Intent senderIntent = new Intent(vehiclescan.this,defectscreen.class);
                senderIntent.putExtra("KEY_SENDER",vehicleid.getText().toString());
                startActivity(senderIntent);

                btn.setText("Scan Next Id");

            }
        });

        Intent receiverIntent = getIntent();
        String receiverValue = receiverIntent.getStringExtra("KEY_SENDER");
        employeename.setText(receiverValue);

        btn.setOnClickListener(v ->
        {
            scanCode();
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
    private void scanCode()
    {
        ScanOptions option = new ScanOptions();
        option.setPrompt("Volume up to flash on");
        option.setBeepEnabled(true);
        option.setOrientationLocked(true);
        option.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(option);
    }
    private Dialog dialogInterface;

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result ->
    {
        if (result.getContents() !=null){
            if (result.getContents()==null){
                //   Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();

            }else{
                TextView textView=findViewById(R.id.vehicleid);
                textView.setText(result.getContents());
            }
        }
    });


}
