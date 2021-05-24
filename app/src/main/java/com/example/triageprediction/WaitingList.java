package com.example.triageprediction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.triageprediction.InputForm.queue;

public class WaitingList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    static View view;

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public WaitingList() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
//    public static WaitingList newInstance(String param1, String param2) {
//        WaitingList fragment = new WaitingList();
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

//    static void addPatient() {
//        TextView patientName = view.findViewById(R.id.patientname);
//        TextView designation = view.findViewById(R.id.designation);
//        Patient next = queue.poll();
//        patientName.setText(next.name);
//        int p = next.prediction;
//        System.out.println("Prediction");
//        System.out.println(p);
//        if (p == 1) designation.setText("Critical care unit – ESI 1/2");
//        else if (p == 2) designation.setText("Other hospitalization – ESI 2/3");
//        else if (p == 3) designation.setText("Discharge – ESI 4/5");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.waitinglist, container, false);
        final TextView patientName = view.findViewById(R.id.patientname); //Access text box containing patient name
        final TextView designation = view.findViewById(R.id.designation); //Access text box containing patient designation

        Button button = view.findViewById(R.id.nextpatient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!queue.isEmpty()) {
                    Patient next = queue.poll(); //If the queue is not empty, get the patient at the head of the queue
                    patientName.setText(next.name); //Put patient name on screen
                    int p = next.prediction;
                    if (p == 1) designation.setText("Critical care unit – ESI 1/2"); //Put patient designation on screen
                    else if (p == 2) designation.setText("Other hospitalization – ESI 2/3");
                    else if (p == 3) designation.setText("Discharge – ESI 4/5");
                } else {
                    System.out.println("Queue is empty");
                }
            }
        });

        return view;
    }
}
