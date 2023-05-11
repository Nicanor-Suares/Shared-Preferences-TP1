package com.example.sharedpreferencestp1;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sharedpreferencestp1.models.Persona;
import com.example.sharedpreferencestp1.requests.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mutableError;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mutableError = new MutableLiveData<>();
    }

    public MutableLiveData<String> getMutableError() {
        return mutableError;
    }

    public void login(String mail, String pass){
        Persona user = ApiClient.login(context,mail,pass);
        if(user == null){
            mutableError.setValue("Corrobore usuario y/0 contraseña!");
        }
        else{
            Intent intent = new Intent(context,RegistroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("logueado",true);
            context.startActivity(intent);
        }

    }
}