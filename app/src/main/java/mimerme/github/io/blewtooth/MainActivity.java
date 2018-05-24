package mimerme.github.io.blewtooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import mimerme.github.io.blewtooth.temp.MyRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
    private RecyclerView mRecycleView;
    private BlutoothListAdapter mBTList;
    private MyRecyclerViewAdapter adapter;

    ArrayList<mimerme.github.io.blewtooth.BluetoothDevice> blueToothDevices
            = new ArrayList<mimerme.github.io.blewtooth.BluetoothDevice>();



    private void addDevice(String name, short rssi){
        for(mimerme.github.io.blewtooth.BluetoothDevice device : blueToothDevices){
            if(device.getName().equals(name)){
                device.setSignalStrength(rssi);
                return;
            }
        }
        blueToothDevices.add(new mimerme.github.io.blewtooth.BluetoothDevice(name, rssi));
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mRecycleView = (RecyclerView) findViewById(R.id.bluetoothList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mBTList = new BlutoothListAdapter(this, blueToothDevices);
        mRecycleView.setAdapter(mBTList);

        //mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new MyRecyclerViewAdapter(this, animalNames);
        //mRecycleView.setAdapter(adapter);

        BroadcastReceiver receiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                    short rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                    String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                    addDevice(name, rssi);
                    mBTList.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"  RSSI: " + rssi + "dBm", Toast.LENGTH_SHORT).show();
                } else if(BTAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                    Toast.makeText(context, "Starting another discovery...", Toast.LENGTH_SHORT).show();
                    BTAdapter.startDiscovery();
                }
            }
        };
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(receiver, new IntentFilter(BTAdapter.ACTION_DISCOVERY_FINISHED));

        BTAdapter.startDiscovery();
    }

}
