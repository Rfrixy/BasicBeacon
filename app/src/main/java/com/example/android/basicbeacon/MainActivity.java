package com.example.android.basicbeacon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseSettings;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.uriio.beacons.Beacons;
import com.uriio.beacons.ble.gatt.EddystoneGattServer;
import com.uriio.beacons.model.EddystoneUID;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    private         EddystoneGattServer mGattServer;
/*
*    List of supported devices are available
*    Devices need to have at least lollipop running.
*
*   Nearby API key : AIzaSyAiCbYsaDjhgbhglohdJmwAmEIn5OrM65k
 */
    private BluetoothAdapter bluetooth;

    private TextView Tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetooth = BluetoothAdapter.getDefaultAdapter();
        Tv = (TextView) findViewById(R.id.only_tv);
        // used to initialize the uriio beacon library

        Beacons.initialize(this);
//ALL checks:
        StartingChecks();
//                new EddystoneUID("alotofdataAlotofdata".getBytes( Charset.forName( "UTF-8" ) ),AdvertiseSettings.ADVERTISE_MODE_BALANCED, AdvertiseSettings.ADVERTISE_TX_POWER_LOW).start();
        new EddystoneUID("alotofdataAlotofdata".getBytes( Charset.forName( "UTF-8" ) ), AdvertiseSettings.ADVERTISE_MODE_BALANCED, AdvertiseSettings.ADVERTISE_TX_POWER_LOW).start();

        // ^ creating a new eddstone uid
        //UID namespace shoudl be 20 characters long
        //getbytes charset to send bytes as data


//        Useful beacon commands:
//        beacon.start();    // active beacon, it runs when possible
//        beacon.pause();    // sets a beacon to paused state, e.g. active but not running
//        beacon.stop();     // stops advertising and removes a beacon from active list

        Toast.makeText(this, "Creation end reached", Toast.LENGTH_SHORT).show();

//        mGattServer = new EddystoneGattServer(new EddystoneGattServer.Listener() {
//            @Override
//            public void onGattFinished(EddystoneBase configuredBeacon) {
//                if (null != configuredBeacon) {
//                    // take action - configured beacon is started at this point
//                    // the final beacon's saved state depends on the provided configurable beacon saved state
//                    // if you provided a non-saved beacon (or none at all), save here if desired
//                    configuredBeacon.save(true);
//                }
//
//                // mark object as disposable
//                mGattServer = null;   // close() not needed here
//            }
//        });
//
//        boolean success = mGattServer.start(sampleEUid);
//        //creates a general attribute eddystone beacon that can be readily configured using a beacon-tools app
//        //this was a text EddyUID that has already been registered.
//        if (success) {
//            // Unlock key to connect to the beacon if needed
//            String hexUnlockKey = Util.binToHex(mGattServer.getBeacon().getLockKey(), ' ');

//            Tv.setText(hexUnlockKey);
//        }
//

    }


    private void StartingChecks(){
        if( bluetooth.isMultipleAdvertisementSupported() )
            Tv.setText("Multiple advertisement is supported");
        else
            Tv.setText("Multiple advertisement is not supported");
        String status = "Bluetooth";

        if(bluetooth != null) {
            if (bluetooth.isEnabled()) {
                String mydeviceaddress = bluetooth.getAddress();
                String mydevicename = bluetooth.getName();
                status = ("Address "+ mydeviceaddress + " Name" + mydevicename);
                Tv.append("\n Bluetooth is enabled ");
                Toast.makeText(getApplicationContext(), "" + status + "", Toast.LENGTH_LONG).show();
            } else {
                status = ("Bluetooth not enabled, enabling");
                Toast.makeText(getApplicationContext(), "" + status + "", Toast.LENGTH_LONG).show();
                Tv.append("\n Bluetooth is not enabled, enabling ");
                bluetooth.enable();

            }
        } else {
            Toast.makeText(getApplicationContext(), "" + status + "", Toast.LENGTH_LONG).show();
            Tv.append(status);

        }

        Tv.append("\n List of Supported Devices:\n" +
                "Phones and tablets\n" +
                "Google Pixel [XL], Pixel C, Nexus 6P, 6, 5X, 9, patched Nexus 5\n" +
                "Alcatel One Touch Idol 3 [Dual SIM], Fierce XL\n" +
                "Asus Zenfone 2 [Laser], Zenpad 8\n" +
                "Blackberry Priv\n" +
                "HTC 10, One M9, Desire (530/626s)\n" +
                "Huawei Honor 5X, Union\n" +
                "Lenovo K3 Note, Vibe P1m, Vibe K4 Note\n" +
                "LG G3, G4 [Stylus], G5, G Flex2, V10, K10, Leon, Magna, Optimus Zone 3, Spirit, Tribute 5\n" +
                "Moto X Play, X Style, X2, G2, G3, G4, Z Droid, Droid Turbo 2\n" +
                "Nextbit Robin\n" +
                "OnePlus 2, 3\n" +
                "OPPO A33f\n" +
                "Samsung Galaxy:\n" +
                "S7 [Edge] - up to 8 concurrent running BLE advertisers\n" +
                "S5 [Active/Neo], S6 [Active/Edge/Edge Plus]\n" +
                "Note 4, Note Edge, Note 5, Note Pro, unexploded Note 7 (RIP...)\n" +
                "Tab S (8.4/10.5), Tab S2 (8.0/9.7), Tab A 9.7, Tab E\n" +
                "A3, A5 [Duos]\n" +
                "J3 Duos, J5\n" +
                "Alpha, Core Prime, Grand Prime, On7\n" +
                "Sony Xperia Z5 [Compact/Premium], C5 Ultra, C3, M4 Aqua [Dual]\n" +
                "Xiaomi Redmi 3, Note 2, Note 3, Mi 4, Mi 4i, Mi 5, Mi Max\n" +
                "ZTE Maven, ZMAX 2, Zmax Pro, Warp Elite\n" +
                "Android TVs\n" +
                "Sony Bravia 2015");

    }
}
