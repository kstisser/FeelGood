package apps.stisser.karissa.feelgood;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/5/2017.
 */
public class AskExpert extends ActionBarActivity {
    public static ListView messages;
    public static EditText text;
    List<Message> messageHistory;
    List<String> messageList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_expert);

        messages = (ListView)findViewById(R.id.expertResponses);
        text = (EditText)findViewById(R.id.questionTxt);
        messageHistory = MessagesBroker.getMessages(CountrySelection.getSelectedTopic(), new MessagesBroker.MessageCallBack() {
            @Override
            public void onMessage(Message message) {
                if (!message.from.equals(FeelGood.getLoginName())) {
                    messageList.add(message.toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        messageList = new ArrayList<String>();
        int count = 0;
        for(Message m: messageHistory){
            messageList.add(m.toString());
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, messageList);
        messages.setAdapter(adapter);
    }

    public void submitQuestionClicked(){
        String question = text.getText().toString();
        String user = FeelGood.getLoginName();
        String topic = "Ask the Expert";
        Message m = new Message(user, question, topic, "");
        MessagesBroker.submitQuestion(m);
        adapter.notifyDataSetChanged();
        text.setText("");
    }
}
