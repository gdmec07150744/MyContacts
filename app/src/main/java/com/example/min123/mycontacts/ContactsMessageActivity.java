package com.example.min123.mycontacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



/**
 * Created by min123 on 2016/11/18.
 */
public class ContactsMessageActivity extends AppCompatActivity{
    private TextView nameEditText;
    private TextView mobileEditText;
    private TextView qqEditText;
    private TextView danweiEditText;
    private TextView addressEditText;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_message);
        nameEditText= (TextView) findViewById(R.id.name);
        mobileEditText= (TextView) findViewById(R.id.phone);
        danweiEditText= (TextView) findViewById(R.id.job);
        qqEditText= (TextView) findViewById(R.id.qq);
        addressEditText= (TextView) findViewById(R.id.address);
        Bundle localbundle=getIntent().getExtras();
        int id=localbundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user=ct.getUserByID(id);
        nameEditText.setText("姓名:"+user.getName());
        mobileEditText.setText("电话："+user.getPhone());
        qqEditText.setText("QQ："+user.getQq());
        addressEditText.setText("地址："+user.getAddress());
        danweiEditText.setText("单位："+user.getDanwei());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
