package apps.stisser.karissa.feelgood;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FeelGood extends ActionBarActivity {

    public static TextView usernameLbl;
    public static EditText usernameTxt;
    public static Button loginBtn;
    public static String loginName = "";
    public static EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel_good);
        usernameLbl = (TextView)findViewById(R.id.username);
        usernameTxt = (EditText)findViewById(R.id.userTxt);
        loginBtn = (Button)findViewById(R.id.loginButton);
        passwordText = (EditText)findViewById(R.id.pswdEditTxt);
        passwordText.setTransformationMethod(new PasswordTransformationMethod());
        MessagesBroker.pollMessages();
    }

    public void loginClicked(View view){
        loginName = usernameTxt.getText().toString();
        if(loginName.contains("doctor".toLowerCase())){
            Intent intent = new Intent(this, ExpertResponse.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, CountrySelection.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feel_good, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getLoginName(){
        return loginName;
    }
}

