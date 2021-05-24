package com.example.triageprediction;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;


import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

//import static com.example.triageprediction.WaitingList.addPatient;

class sort implements Comparator<Patient>{
    @Override
    public int compare(Patient o1, Patient o2) {
        if (o1.prediction < o2.prediction) return 1;
        else if (o1.prediction > o2.prediction) return -1;
        return 0;
    }
}

public class InputForm extends Fragment {

    private TextInputEditText name, age, temp, pulse, respr, sbp, dbp, popct;
    private CheckBox cebvd, chf, aids;
    private SwitchMaterial arrems, nochron;
    private RadioButton male, female;
    private Spinner spinner;
    static String[] input = new String[100];
    private ArrayList<String> complaints = new ArrayList<>();
    private ArrayList<String> chronic = new ArrayList<>();
    static PriorityQueue<Patient> queue = new PriorityQueue<>(5, new sort());
    String[] RFVs = {"Jaundice", "Gastrointestinal bleeding", "Disorders of speech, speech disturbance", "Slurring", "Overdose, intentional", "Symptoms of onset of labor", "For other findings of blood tests", "Coughing up blood", "Noncompliance with medication therapy", "Cerebrovascular disease", "Vomiting blood (hematemesis)", "For other and unspecified test results", "Excessive sweating, perspiration", "Weakness (neurologic)", "State of consciousness not specified", "Other respiratory diseases", "Other heart disease", "Breathing problems, NEC", "Other endocrine, nutritional, and met...", "Hostile behavior", "Other disease of circulatory system", "Anal-rectal bleeding", "Blood in stool (melena)", "Decreased appetite", "Adverse effect of drug abuse", "Abnormal involuntary movements", "Symptoms of fluid abnormalities", "For results of blood glucose tests", "Alcohol-related problems", "Unconscious on arrival", "General ill feeling", "Tiredness, exhaustion", "Abnormal pulsations and palpitations", "Other symptoms referable to the nervo...", "Disorders of motor functions", "Violence, NOS", "Swelling of leg", "Adverse effect of alcohol", "Wheezing", "Constipation", "Chills", "Asthma", "Surgical Complications due to any imp...", "Edema", "Chest discomfort, pressure, tightness", "Depression", "Upper abdominal pain, cramps, spasms", "Medical counseling, NOS", "Hip pain, ache, soreness, discomfort", "Convulsions", "Loss of feeling (anesthesia)", "Carbuncle, furuncle, boil, cellulitis...", "Anxiety and nervousness", "Fainting (syncope)", "Hypertension", "Lower abdominal pain, cramps, spasms,", "Arm pain, ache, soreness, discomfort", "Labored or difficult breathing (dyspnea)", "Laceration/cut of upper extremity", "Earache, pain", "Medication, other and unspecified kinds", "Knee pain, ache, soreness, discomfort", "Motor vehicle accident, type of injur...", "Skin rash", "Nasal congestion", "Injury, other and unspecified of head...", "Shoulder pain, ache, soreness, discom...", "General weakness", "Pain, unspecified", "Low back pain, ache, soreness, discom...", "Neck pain, ache, soreness, discomfort", "Side pain, flank pain", "Oth symptoms/problems relat to psycho...", "Diarrhea", "Leg pain, ache, soreness, discomfort", "Throat soreness", "Vertigo - dizziness", "Fever", "Accident, NOS", "Back pain, ache, soreness, discomfort", "Shortness of breath", "Nausea", "Headache, pain in head", "Cough", "Vomiting", "Chest pain", "Abdominal pain, cramps, spasms, NOS"};

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

    public int predictHospitalization(String[] args) throws IOException {

        // Features:
        double[] features = new double[args.length];
        for (int i = 0, l = args.length; i < l; i++) {
            features[i] = Double.parseDouble(args[i]);
        }

        // Parameters:
        double[][] bias = {{-0.13433688533290336, -0.06391433781166876, -0.09226534301410608, -0.16906957237964476, 0.06008696885392195, 0.11365645484369544, 0.060169899077380504, 0.03132974144938288, 0.030945846138575532, -0.08615163202557151, 0.05631199900448221, -0.022325929445326166, -0.09259866988903696, -0.049689901440682395, 0.1246672391595419, -0.011370703296522447, 0.09233271855378059, 0.009317318912349683, 0.06460860824965935, 0.1233028607639446, -0.06154584584388135, -0.006567199837396629, -0.035684720322122145, -0.057395730884339455, -0.15411585566951105, 0.04670845034727928, 0.011621312112283715, 0.1360495750154746, -0.1364111142399486, -0.02208326841442502, 0.13461375703972422, 0.16298912193008766, -0.0028895421562890353, 0.10350093163156417, 0.1307056800459663, -0.08135152926031777, 0.13461082412302453, -0.03418335726139658, -0.10050318652064202, 0.11003481941012117, -0.07156716621620737, -0.09843590055847293, 0.05027171057959326, 0.1713725339453922, -0.08894590065476814, 0.03863074909640327, -0.06480231970200087, -0.0807687009274686, -0.15855303080419217, 0.08314277247072711, -0.13859925103739157, 0.1049183022734695, -0.1623869964302711, -0.026651542832560954, 0.1072209014202269, 0.13496272431512726, 0.014065570614872102, 0.12512373867783316, -0.17905154258708078, -0.18359262663793716, 0.2303522894251794, 0.07986405054440956, -0.09226484065451088, -0.0996468806738012, 0.0748967669466871, -0.03331712693495549, -0.14710967811918255, -0.06465229137350492, -0.017100257712671707, -0.13188869475437312, 0.07515560354626764, 0.06791940589281234, 0.09364348784290684, -0.18212399553763137, 0.04171260743539666, -0.08207607342982859, -0.0317822794452608, -0.09440524246485962, -0.09972873519768065, -0.06986115706979414, -0.0995882171277777, -0.13310393225248396, 0.07667191526503998, 0.06344589558878105, -0.15591265334352566, -0.03946189052816376, 0.08989566464216307, 0.11375571804578713, 0.1356161315030524, -0.02489246999886885, 0.16606083425888868, 0.15288572430265152, 0.014810346056477868, 0.046095296553713846, 0.07508625701435064, 0.1288260625759157, 0.10002912891582309, -0.1723360189721895, -0.1754031773968732, -0.0591424157795982}, {0.15545896530033546}};

        int[] layers = {100, 1};

        AssetManager am = getContext().getAssets();
        InputStream is = am.open("test.txt"); //Access txt file containing 10000 (100x100) neural network weights
        BufferedReader reader = new BufferedReader(new InputStreamReader(is)); //Input reader

        double[][] master = new double[100][100]; //Initialize 100x100 floating point number array for the weights
        String s; //Placeholder for each 100-number array
//      (weights are a 3D array)

        for (int i=0;i<100;i++) {
            s = reader.readLine(); //Read each batch (array) of weights; this looks something like "0.00001, -1.290424, 0.069420"
            String[] arr = s.split(","); //Convert to string array so we can iterate over all the numbers
            double[] temp = new double[100];
            for (int j = 0; j < 100; j++) {
                temp[j] = Double.parseDouble(arr[j]); //Parse every number to floating point number
            }
            master[i] = temp; //Add 100-number array to 100x100 array
        }

        double[][][] weights = {master,{{-0.055532079846978354}, {0.04140430144761294}, {-0.041931628365175756}, {0.127037327177903}, {0.01652543697726464}, {0.21158114019099086}, {-0.06894725163011126}, {0.01555515242290984}, {-0.22790610302277076}, {0.1482363508281896}, {0.12436375694545383}, {0.02591269642384988}, {-0.24492401871523933}, {-0.023315185854582934}, {0.049772793746059966}, {-0.18396323879094842}, {0.020895268181644003}, {-0.052276096233430845}, {-0.12537291696209038}, {0.2203386850428166}, {0.11454402092669573}, {0.03800608720180053}, {0.05283577273387423}, {-0.07034453744676936}, {2.6474096950872786e-70}, {-0.1792171545326756}, {0.047229304333228334}, {-0.10122353151631794}, {-0.0002555035879801195}, {0.07298377672425209}, {-0.07160891164897985}, {-0.12312067161228803}, {0.15035877532668057}, {0.004476859584214231}, {0.0011759624064669402}, {-0.029401270814628108}, {0.2014319080552615}, {-0.1986129076563739}, {-0.000247780484516601}, {-0.016267378499424476}, {0.17341900398414492}, {0.10538754078139788}, {-8.476290752506137e-05}, {0.03693376866888879}, {-0.11134559601740825}, {-0.023692566807052073}, {5.561614807816602e-82}, {-0.15395639171671888}, {0.13675785943805788}, {0.16845866370425816}, {-0.003065744076096428}, {0.057572485950105425}, {1.7442161858287493e-40}, {-0.08963221062885975}, {-0.23679834186506393}, {0.009189304717432394}, {0.06640484526685742}, {0.07609084919220942}, {-0.013240611086070398}, {-0.007287824476214784}, {-0.19829454786181905}, {0.040709187893690324}, {0.2308412750174069}, {0.1507761261553253}, {0.2291138638362552}, {0.04070232764848452}, {-0.19876021442070851}, {0.1817557928038818}, {-3.245260455484109e-39}, {0.09302081636895285}, {-0.13401274560690477}, {-0.16870151635617303}, {-0.007127521456391339}, {0.21685531688296722}, {0.04440048753760221}, {0.1262121428163014}, {-5.35135409366681e-60}, {0.27782307420029617}, {-0.13445963602352454}, {0.08608827839511803}, {0.18499073934374602}, {0.13646625360898104}, {0.01718398498287711}, {0.18457630120854845}, {0.1882010101871509}, {1.2136586973429607e-65}, {0.10864092509129233}, {0.016973370862454564}, {0.08738248005495418}, {1.855847745912632e-57}, {-0.15295512132113706}, {5.130001843039279e-05}, {0.08239780507550673}, {-0.15836414264915585}, {-0.04361831664864687}, {0.20326908241229116}, {0.14920261090484985}, {-0.1830105894525296}, {0.17546872750186027}, {0.1016160897871749}}};
//        Add the weights from the txt-file to the 400-ish that can fit in this file

        // Prediction:
        HospitalizationClassifier clf = new HospitalizationClassifier("relu", "logistic", layers, weights, bias); //Initialize classifier
//        given weights, bias and layer parameters
        return clf.predict(features); //Make prediction

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

        spinner = view.findViewById(R.id.spinner); //Access spinner element

        spinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,RFVs)); //Add dropdown list

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //On user click, show conditions
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                input[position+13] = "1."; /* Patient data is stored in a 100-element string array.
                Indices 0-12 are basic information (age, blood pressure etc), while the rest are binary (0/1; yes/no) categorical
                variables/buckets for 87 different conditions. If a condition is selected, it will be marked "1."
                */
                complaints.add(RFVs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                /*This method may seem useless, but whenever we remove it, the entire file turns into one big error message.
                So here it will stay. (although it randomly spawned when we added the onclicklistener part so it's probably a required method)
                 */
            }
        });

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

        for (int i=0;i<100;i++) {
            input[i] = "0.";
                    /*The user will be able to choose between 87 different chief complaints. At this point,
                    the user hasn't inputted them yet, so each complaint will temporarily be listed as "no".
                     */
        }

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
                input[3] = Double.toString((Double.parseDouble(temp.getText().toString()) * (9 / 5)) + 32)+".";

                input[3] = temp.getText().toString()+".";

                input[4] = pulse.getText().toString()+"."; //Numerical value, plus a decimal point at the end so the model can read it

                input[5] = respr.getText().toString()+".";

                input[6] = sbp.getText().toString()+".";

                input[7] = dbp.getText().toString()+".";

                input[8] = popct.getText().toString()+".";

                if (cebvd.isChecked()) input[9] = "1.";
                else input[9] = "0.";
                chronic.add("Cerebrovascular disease");

                if (chf.isChecked()) input[10] = "1.";
                else input[10] = "0.";
                chronic.add("Congestive heart failure");

                if (aids.isChecked()) input[11] = "1.";
                else input[11] = "0.";
                chronic.add("HIV/AIDS");

                if (nochron.isChecked()) input[12] = "0.";
                else input[12] = "1.";

                CriticalCareClassifier predictor = new CriticalCareClassifier();
                int prediction = predictor.predictICU(input);

                int hprediction = 0;
                try {
                    hprediction = predictHospitalization(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (prediction == 0) System.out.println("ICU: Not admitted");
                if (prediction == 1) System.out.println("ICU: Admitted");
                if (hprediction == 0) System.out.println("Other hospitalization: Not admitted");
                if (hprediction == 1) System.out.println("Other hospitalization: Admitted");
                queue.add(new Patient(Name, input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7], input[8], input[12], chronic, complaints, prediction, hprediction));
            }
        });

        return view;
    }

}
