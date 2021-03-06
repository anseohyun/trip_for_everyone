package com.example.trip_for_everyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


//회원가입
public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.check).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.check:
                    signUp();
                    break;
            }
        }
    };


    private  void signUp(){
        String id = ((EditText)findViewById(R.id.user_id)).getText().toString();
        String password = ((EditText)findViewById(R.id.user_password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.user_password_check)).getText().toString();

        if(id.length()>0&& password.length()>0&& passwordCheck.length()>0){
            if(password.equals(passwordCheck)){
                mAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "회원가입 성공했습니다", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(task.getException().toString() !=null){
                                Toast.makeText(Register.this, "회원가입 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
            else{
                Toast.makeText(Register.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Register.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
        }
    }
}