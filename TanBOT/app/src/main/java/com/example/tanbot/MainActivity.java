package com.example.tanbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ENABLE_BT = 0;
    public static final int REQUEST_DISCOVER_BT = 1;

    TextView mStatusBlueTv, mPairedTv;
    ImageView mBlueIv;
    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStatusBlueTv = findViewById(R.id.statusBluetoothTv);
        mPairedTv = findViewById(R.id.pairedTv);
        mBlueIv = findViewById(R.id.BluetoothIv);
        mOnBtn = findViewById(R.id.onBtn);
        mOffBtn = findViewById(R.id.offBtn);
        mDiscoverBtn = findViewById(R.id.discoverableBtn);
        mPairedBtn = findViewById(R.id.pairedBtn);

        //Адаптер
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        //проверка доступности блютус адаптера
        if (mBlueAdapter == null) {
            mStatusBlueTv.setText("Bluetooth не доступен");
        } else {
            mStatusBlueTv.setText("Bluetooth доступен");
        }

        //Смена картинки при смене статуса блютус (вкл/выкл)
        if (!mBlueAdapter.isEnabled()) {
            mBlueIv.setImageResource(R.drawable.ic_action_off);
            showToast("Disabled");
        } else {
            mBlueIv.setImageResource(R.drawable.ic_action_on);
            showToast("Enabled");
        }
    /*
        //когда кнопка включена
        mOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mBlueAdapter.isEnabled()) {
                    showToast("Включение блютус...");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    //startActivityForResult(intent, REQUEST_ENABLE_BT);

                } else {
                    showToast("Bluetooth уже включен");
                }
            }
        });

        //кнопка Обнаружить
        mDiscoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mBlueAdapter.isDiscovering()) {
                    showToast("сделать ваше устройство доступным для обнаружения");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);

                }
            }
        });

        //кнопка выключить блютус
        mOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBlueAdapter.isEnabled()) {
                    mBlueAdapter.disable();
                    showToast("Выключение Bluetooth");
                    mBlueIv.setImageResource(R.drawable.ic_action_off);
                } else {
                    showToast("Bluetooth уже выключен");
                }
            }
        });

        //кнопка подключенные устройства
        mPairedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBlueAdapter.isEnabled()) {
                    mPairedTv.setText("подключенные устройства");

                    Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                    for (BluetoothDevice device: devices){
                        mPairedTv.append("\nУстройство: " + device.getName() + ", " + device);
                    }
                } else{
                    showToast("Включите блютус для отображения списка подключенных устройств");
                }
            }
        });

         */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK){
                    //блютус включен
                    mBlueIv.setImageResource(R.drawable.ic_action_on);
                    showToast("Bluetooth включен");
                } else {// пользователь не разрешил включить бютус
                    showToast("не получается включить Bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    // Receiver


    //функция всплывающего сообщения
    private void showToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}