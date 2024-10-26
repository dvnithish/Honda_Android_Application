package com.example.prepreparation;

import androidx.activity.result.ActivityResultLauncher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.journeyapps.barcodescanner.ScanContract;

import com.journeyapps.barcodescanner.ScanOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView barcodeno1, btn_scan, btn;
    private int intLayout = 1;
    private int backbackexit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*    barcodeno1 = findViewById(R.id.barcodeno11);
        btn_scan = findViewById(R.id.btn_scan);


        barcodeno1.setOnClickListener(v -> {
            Connection connection = connectionclass();
            try {
                if (connection != null) {
                    String sqlinsert = "INSERT INTO presample2 (employeename) VALUES ('"
                            + barcodeno1.getText().toString() + "')";
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(sqlinsert);
                }
            } catch (Exception exception) {
                Log.e("Error", exception.getMessage());
            }

            Intent senderIntent = new Intent(MainActivity.this, vehiclescan.class);
            senderIntent.putExtra("KEY_SENDER", barcodeno1.getText().toString());
            startActivity(senderIntent);
        });

        btn_scan.setOnClickListener(v -> scanCode());*/
    }

  /*  @SuppressLint("NewApi")
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

    private void scanCode() {
        ScanOptions option = new ScanOptions();
        option.setPrompt("Volume up to flash on");
        option.setBeepEnabled(true);
        option.setOrientationLocked(true);
        option.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(option);
    }

    private Dialog dialogInterface;

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            TextView textView = findViewById(R.id.barcodeno11);
            textView.setText(result.getContents());
        }
    });

    public void buttonGoToMainLayout(View view) {
        intLayout = 1;
        setContentView(R.layout.activity_main);
    }

    public void buttonGoToSecondLayout(View view) {
        intLayout = 2;
        setContentView(R.layout.activity_vehiclescan);
    }

    public void buttonGoToThirdLayout(View view) {
        intLayout = 3;
        setContentView(R.layout.activity_defectscreen);
    }

    @Override
    public void onBackPressed() {
        if (intLayout == 3) {
            intLayout = 2;
            setContentView(R.layout.activity_vehiclescan);
        } else if (intLayout == 2) {
            intLayout = 1;
            setContentView(R.layout.activity_main);
        } else if (intLayout == 1) {
            intLayout = 1





            ;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle(getResources().getString(R.string.app_name));
            alertDialog.setMessage("Are you sure you want to exit??");
            alertDialog.setPositiveButton("YES", (dialog, which) -> finish());
            alertDialog.setNeutralButton("NO", (dialog, which) -> {});
            alertDialog.show();
        }*/
    public void signingotosignup(View view) {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
    }
 /*   public class MyActivity extends AppCompatActivity {

        private int currentLayout = 1; // Set the current layout to the first layout initially

        @Override
        public void onBackPressed() {
            switch (currentLayout) {
                case 4:
                    // If currently on the third layout, go to the second layout
                    setContentView(R.layout.activity_defectscreen);
                    currentLayout = 2;
                    break;
                case 3:
                    // If currently on the third layout, go to the second layout
                    setContentView(R.layout.activity_vehiclescan);
                    currentLayout = 2;
                    break;
                case 2:
                    // If currently on the second layout, go to the first layout
                    setContentView(R.layout.activity_main);
                    currentLayout = 1;
                    break;
                case 1:
                default:
                    // If currently on the first layout, perform default back button functionality
                    super.onBackPressed();
                    break;
            }
        }

    }*/

