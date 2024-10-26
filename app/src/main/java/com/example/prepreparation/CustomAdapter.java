package com.example.prepreparation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class CustomAdapter extends ArrayAdapter<Map<String, String>> {
    private Context mContext;
    private List<Map<String, String>> mValues;
    private ArrayList<Boolean> mCheckedList;
public CustomAdapter(Context context, List<Map<String, String>> values, ArrayList<Boolean> checkedList) {
        super(context, R.layout.activity_listview, values);
        mContext = context;
        mValues = values;
        mCheckedList = checkedList;
    }
@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.activity_listview, parent, false);
        CheckBox checkBox = itemView.findViewById(R.id.Defect);
        checkBox.setChecked(mCheckedList.get(position));
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckedList.set(position, isChecked);
        });
        TextView defectNameView = itemView.findViewById(R.id.Defect);
        defectNameView.setText(mValues.get(position).get("Defect"));
        return itemView;
      }
    public void insertDatabase(String vehicleId)
        {
        try {
            Connection connect = connectionclass();
            if (connect != null) {
                List<Map<String, String>> selectedItems = getSelectedItems();
                for (Map<String, String> item : selectedItems) {
                    String defectName = item.get("Defect");
                    String sqlInsert = "INSERT INTO presample2 (vehicleid, Defect) VALUES ('" + vehicleId + "', '" + defectName + "')";

                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sqlInsert);
                }
                connect.close();
            }
        } catch (Exception ex) {
            Log.e("CustomAdapter", "Failed to insert data into database", ex);
        }

    }
        private List<Map<String, String>> getSelectedItems() {
        List<Map<String, String>> selectedItems = new ArrayList<>();
        for (int i = 0; i < mValues.size(); i++) {
            if (mCheckedList.get(i)) {
                selectedItems.add(mValues.get(i));
            }
        }
        return selectedItems;
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





