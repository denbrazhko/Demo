package com.rexbot.bitrtix.bot;

import androidx.appcompat.app.AppCompatActivity;

public class Flow extends AppCompatActivity {


//    private Bittrexv3 bittrexv3 = new Bittrexv3(3,3);
//    private JSONArray newJson;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flow);
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//
//        StrictMode.setThreadPolicy(policy);
//
//        String currencies[] =
//                {"ADA","ARK","BTC","BTXCRD","DASH","ETH","GRS","MET",
//                        "NEO","OCEAN","OMG","QTUM","STRAX","USDT","VTC","XEM","XMR","ZEC"};
//        Log.i("PUBLIC KEY :", Key.key().getPublicKey());
//
//        Log.i("PRIVATE KEY :", Key.key().getPrivateKey());
//
//        Log.i("===? LOS ?=== ",bittrexv3.getBalances());
//        ArrayList<String> arrayList = new ArrayList<>();
//
//        try {
//            newJson = new JSONArray(bittrexv3.getBalances());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        for(int i = 0; i < newJson.length(); i++)
//        {
//            try {
//                arrayList.add(currencies[i] + ": " + newJson.getJSONObject(i).getString("total"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Buffer.buffer().setArrayList(arrayList);
//
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Not used now", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//    @Override
//    public void onBackPressed() {
//
//        return;
//    }
}