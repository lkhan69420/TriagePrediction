package com.example.triageprediction;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
////        ViewPager viewPager = findViewById(R.id.view_pager);
////        viewPager.setAdapter(sectionsPagerAdapter);
////        TabLayout tabs = findViewById(R.id.tabs);
////        tabs.setupWithViewPager(viewPager);
////        FloatingActionButton fab = findViewById(R.id.fab);
////
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
////    }

    public static void main(String[] args) {
        ICUClassifier predictor = new ICUClassifier();
        String[] st = new String[581];
        st[0]="22.";
        st[1]="1.";
        st[2]="1.";
        st[3]="98.";
        st[4]="90.";
        st[5]="16.";
        st[6]="102.";
        st[7]="67.";
        st[8]="99.";
        for (int i=9; i<581; i++) {
            st[i]=("0.");
        }
        int prediction = predictor.predict(st);
        System.out.println(prediction);
    }

}