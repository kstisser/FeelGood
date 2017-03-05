package apps.stisser.karissa.feelgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by User on 3/4/2017.
 */
public class CountrySelection extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    public static String selectedCountry = "Lebanon";
    public static String selectedTopic;
    public static Spinner spinner;
    public static Button discussionBtn;
    public static RadioGroup rgroup;
    String[] countryOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries);
        countryOptions = getResources().getStringArray(R.array.country_arrays);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        discussionBtn = (Button)findViewById(R.id.discussionBtn);
        rgroup = (RadioGroup)findViewById(R.id.RadioGroup1);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        selectedCountry = countryOptions[pos];

    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    public static String getSelectedCountry(){
        return selectedCountry;
    }

    public static String getSelectedTopic(){

        return selectedTopic;//selectedTopic;
    }

    public void discussionClicked(View view){
        selectedTopic = ((RadioButton)findViewById(rgroup.getCheckedRadioButtonId())).getText().toString();
        Intent intent = new Intent(this, DiscussionActivity.class);
        startActivity(intent);
    }

    public void askExpertClicked(View view){
        Intent intent = new Intent(this, AskExpert.class);
        startActivity(intent);
    }
}
