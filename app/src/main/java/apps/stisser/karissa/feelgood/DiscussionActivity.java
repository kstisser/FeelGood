package apps.stisser.karissa.feelgood;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/5/2017.
 */
public class DiscussionActivity extends ActionBarActivity {
    ListView messages;
    EditText text;
    List<Message> messageHistory;
    List<String> messageList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion);
        messages = (ListView)findViewById(R.id.messageList);
        text = (EditText)findViewById(R.id.editText);
//        CountrySelection.getSelectedTopic()
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

    public void postClicked(View view){
        String username = FeelGood.getLoginName();
        String entry = text.getText().toString();
        String discussionTopic = CountrySelection.getSelectedTopic();
        Message m = new Message(1,"",username, entry, discussionTopic);
        MessagesBroker.addMessageAsync(m);
        messageList.add(m.toString());
        adapter.notifyDataSetChanged();
        text.setText("");
    }
}
