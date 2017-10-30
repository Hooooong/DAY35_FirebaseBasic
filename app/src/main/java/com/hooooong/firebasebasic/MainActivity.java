package com.hooooong.firebasebasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hooooong.firebasebasic.model.Bbs;
import com.hooooong.firebasebasic.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements UserAdapter.Callback {

    private RecyclerView userRecyclerView, bbsRecyclerView ;

    private EditText editId, editName, editAge, editTitle;
    private Button btnSign, btnWrite;

    private UserAdapter userAdapter;
    private BbsAdapter bbsAdapter;

    FirebaseDatabase database;
    private DatabaseReference userRef;
    private DatabaseReference bbsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        // 아무런 Node 를 정의하지 않으면, root 를 참조한다.
        bbsRef = database.getReference("bbs");
        userRef = database.getReference("user");

        initView();
        initAdapter();
        initListener();
    }

    private void initAdapter() {
        userAdapter = new UserAdapter(this);
        userRecyclerView.setAdapter(userAdapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bbsAdapter = new BbsAdapter();
        bbsRecyclerView.setAdapter(bbsAdapter);
        bbsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        userRecyclerView = (RecyclerView)findViewById(R.id.userRecyclerView);
        bbsRecyclerView =  (RecyclerView)findViewById(R.id.bbsRecyclerView);
        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        editAge = (EditText) findViewById(R.id.editAge);
        editTitle = (EditText) findViewById(R.id.editTitle);
        btnSign= (Button) findViewById(R.id.btnSign);
        btnWrite =  (Button) findViewById(R.id.btnWrite);
    }

    private void initListener(){
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> data = new ArrayList<>();
                for( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = null;
                    if(snapshot.hasChild("bbs")){
                        Map map = (HashMap)snapshot.getValue();
                        Log.e("MainActivity",map.get("age") + "");
                        String username = (String)map.get("username");
                        long age = (long)map.get("age");
                        String email = (String)map.get("email");

                        user = new User(username, (int)age, email);

                        DataSnapshot bbsSnapshot = snapshot.child("bbs");
                        List<Bbs> bbsList = new ArrayList<>();

                        for( DataSnapshot item : bbsSnapshot.getChildren()){
                            Bbs bbs = item.getValue(Bbs.class);
                            bbsList.add(bbs);
                        }
                        user.setBbsList(bbsList);
                        data.add(user);

                    }else{

                        user = snapshot.getValue(User.class);
                        data.add(user);
                    }
                    user.setUser_id(snapshot.getKey());
                }
                userAdapter.modifyUserData(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bbsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Bbs> data = new ArrayList<>();
                for( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Bbs bbs = snapshot.getValue(Bbs.class);
                    Log.e("MainActivity", bbs.getTitle());
                    data.add(bbs);
                }
                bbsAdapter.modifyBbsData(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void signIn(View view){
        String id = editId.getText().toString();
        User user = new User(editName.getText().toString(), Integer.parseInt(editAge.getText().toString()), "none");
        userRef.child(id).setValue(user);
    }

    public String user_id = "";

    public void write(View view){
        if(user_id == null || "".equals(user_id)){

        }else{
            String title = editTitle.getText().toString();
            Bbs bbs = new Bbs(title, "내용","2017-10-30",user_id);

            // Node 생성
            // push() 를 하면 key 를 하나 생성해준다.
            String bbsKey = bbsRef.push().getKey();
            // 게시판에 데이터를 입력
            bbsRef.child(bbsKey).setValue(bbs);
            // 사용자 아이디에 게시글 추가
            userRef.child(user_id).child("bbs").child(bbsKey).setValue(bbs);
        }
    }

    @Override
    public void setUserId(String id) {
        this.user_id = id;
    }
}
