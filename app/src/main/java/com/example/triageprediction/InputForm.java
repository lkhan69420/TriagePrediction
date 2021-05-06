package com.example.triageprediction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class InputForm extends Fragment {

    private TextInputEditText name, age, temp, pulse, respr, sbp, dbp, popct;
    private CheckBox cebvd, chf, aids;
    private SwitchMaterial arrems, nochron;
    private RadioButton male, female;
    static String[] input = new String[100];

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public InputForm() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
//    public static InputForm newInstance(String param1, String param2) {
//        InputForm fragment = new InputForm();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        name = view.findViewById(R.id.name);
        age = view.findViewById(R.id.age);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        arrems = view.findViewById(R.id.arrems);
        temp = view.findViewById(R.id.temp);
        pulse = view.findViewById(R.id.pulse);
        respr = view.findViewById(R.id.respr);
        sbp = view.findViewById(R.id.sbp);
        dbp = view.findViewById(R.id.dbp);
        popct = view.findViewById(R.id.popct);
        cebvd = view.findViewById(R.id.cebvd);
        chf = view.findViewById(R.id.chf);
        aids = view.findViewById(R.id.aids);
        nochron = view.findViewById(R.id.nochron);

        Button button = view.findViewById(R.id.submit);
//
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                input[0] = age.getText().toString()+"."; //String array "input" contains patient information
                if (male.isChecked()) input[1] = "0.";
                else if (female.isChecked()) {
                    input[1] = "1.";
                }
                if (arrems.isChecked()) input[2] = "1."; //If patient arrived by ambulance, return "1."
                else input[2] = "0."; //If not, return "0." (the model will then read these and parse them into integers)
                input[3] = ((Integer.parseInt(temp.getText().toString()) * (9 / 5)) + 32)+".";

                input[4] = pulse.getText().toString()+"."; //Numerical value, plus a decimal point at the end so the model can read it

                input[5] = respr.getText().toString()+".";

                input[6] = sbp.getText().toString()+".";

                input[7] = dbp.getText().toString()+".";

                input[8] = popct.getText().toString()+".";

                if (cebvd.isChecked()) input[9] = "1."; //Has the patient previously been diagnosed with cerebrovascular disease?
                else input[9] = "0.";

                if (chf.isChecked()) input[10] = "1."; ////Has the patient previously been diagnosed with congestive heart failure?
                else input[10] = "0.";

                if (aids.isChecked()) input[11] = "1."; //Has the patient previously been diagnosed with HIV/AIDS?
                else input[11] = "0.";

                if (nochron.isChecked()) input[12] = "0.";
                //Does the patient have any chronic illnesses? (the three listed above were the only 3 chronic illnesses included in the dataset)
                else input[12] = "1.";

                for (int i=13;i<100;i++) {
                    input[i] = "0.";
                    /*The user will be able to choose between 87 different chief complaints. Since we haven't
                    coded this feature yet, each complaint will temporarily be listed as "no".
                     */
                }
                CriticalCareClassifier predictor = new CriticalCareClassifier();
                int prediction = predictor.predict(input);
                if (prediction == 0) System.out.println("Not admitted");
                if (prediction == 1) System.out.println("Admitted");
            }
        });

        return view;
    }

}
