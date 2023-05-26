package com.example.docapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FormFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;

    private TextView docnameTxt, dateTxt;
    EditText fnameInput, numberInput, emailInput;
    String username, docname = "", fname = "", number = "", email = "", date = "", time = "", type = "";
    Button confirmBtn, calendarBtn;

    Database myDB;

    ArrayAdapter<String> spinnerArrayAdapter;
    String h[] = {"8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00"};
    ArrayList<String> hours;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_form, container, false);

        hours = new ArrayList<String>();

        myDB = new Database(getActivity());

        Bundle bundle = this.getArguments();

        docname = bundle.getString("doc");
        docnameTxt = (TextView) view.findViewById(R.id.docnameTxt);
        docnameTxt.setText("Appointment at: " + docname);




        fnameInput = view.findViewById(R.id.fnameTxt);
        numberInput = view.findViewById(R.id.numberTxt);
        emailInput = view.findViewById(R.id.emailTxt);

        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);

        fnameInput.setText(sharedPreferences.getString("fname", null));
        numberInput.setText(sharedPreferences.getString("number", null));
        emailInput.setText(sharedPreferences.getString("email", null));

        dateTxt = (TextView) view.findViewById(R.id.dateTxt);
        calendarBtn = view.findViewById(R.id.btnCalendar);

        if(bundle.containsKey("date")){
            date = bundle.getString("date");
            dateTxt.setText(date);
            for(int i = 0; i < h.length; i++){
                if(!myDB.checkHour(docname,date,h[i])){
                    hours.add(h[i]);
                }
            }
        }

        Spinner spinner = view.findViewById(R.id.spinner);
        spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, hours);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);


        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CalendarFragment calendarFragment = new CalendarFragment();
                bundle.putString("date", date);
                bundle.putString("doc", docname);
                calendarFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, calendarFragment).commit();
            }
        });



        confirmBtn = view.findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = sharedPreferences.getString("username", null);
                fname = fnameInput.getText().toString();
                number = numberInput.getText().toString();
                email = emailInput.getText().toString();
                time = spinner.getSelectedItem().toString();
                type = spinner2.getSelectedItem().toString();

                if(!fname.isEmpty() && !number.isEmpty() && !email.isEmpty() && !date.isEmpty()){
                    if(myDB.checkDate(docname, date, time)){
                        Toast.makeText(getActivity(), "The date selected is already TAKEN!", Toast.LENGTH_SHORT).show();
                    }else{
                        myDB.makeAppointment(username, docname, date, time, type);
                        AppointmentsFragment docFragment = new AppointmentsFragment();
                        getFragmentManager().beginTransaction().replace(R.id.frame_layout, docFragment).commit();
                        Toast.makeText(getActivity(), "Appointment registered!", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Fill in all spaces!", Toast.LENGTH_SHORT).show();
                }

            }
        });



        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();

        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}