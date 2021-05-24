package com.example.triageprediction;

import android.widget.CheckBox;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Patient {

    public ArrayList<String> chronic;
    public ArrayList<String> rfvs;
    public String sex;
    public int prediction;
    public String name, age, temp, pulse, respr, sbp, dbp, popct, arrems, nochron;

    Patient(String name, String age, String sex, String arrems, String temp, String pulse, String respr, String sbp, String dbp, String popct, String nochron, ArrayList<String> chronic, ArrayList<String> rfvs, int icu, int hospitalized) {
        this.name = name;
        this.age = age;
        if (sex == "0.") this.sex = "Male";
        else this.sex = "Female";
        if (arrems == "0.") this.arrems = "No";
        else this.arrems = "Yes";
        this.temp = temp;
        this.pulse = pulse;
        this.respr = respr;
        this.sbp = sbp;
        this.dbp = dbp;
        this.popct = popct;
        if (nochron == "0.") this.nochron = "The patient has chronic illnesses.";
        else this.nochron = "The patient has no chronic illnesses.";
        this.chronic = chronic;
        this.rfvs = rfvs;
        if (icu == 1) this.prediction = 1; //ICU patient = 1, other hospitalization = 2, discharge = 3
        //We assigned numbers so the priority queue can sort patients (minimum value = 1 – for ICU patients, who are at the head of the queue)
        else if (icu == 0 && hospitalized == 1) this.prediction = 2;
        else if (icu == 0 && hospitalized == 0) this.prediction = 3;

    }

}
