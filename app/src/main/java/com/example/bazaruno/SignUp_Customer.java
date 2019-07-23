package com.example.bazaruno;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class SignUp_Customer extends Fragment {

    View view;
    public SignUp_Customer() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.signup_customer,container,false);

        Button signup=(Button) view.findViewById(R.id.signup);
        final EditText email=(EditText) view.findViewById(R.id.email);
        final EditText name=(EditText) view.findViewById(R.id.name);
        final EditText pass=(EditText) view.findViewById(R.id.pass);
        final EditText again_pass=(EditText) view.findViewById(R.id.again_pass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n=name.getText().toString();
                String em=email.getText().toString();
                String p=pass.getText().toString();
                String again_p=again_pass.getText().toString();

                        email.getText().clear();
                        pass.getText().clear();
                        name.getText().clear();
                        again_pass.getText().clear();


                if (n!="" && em != "" && p!="" && again_p !="")
                {
                    Snackbar.make(view, n+"\n"+em+"\n"+p+"\n"+again_p, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    Snackbar.make(view,"Fill the field correctly",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }
            }
        });
        return view;
    }
}
