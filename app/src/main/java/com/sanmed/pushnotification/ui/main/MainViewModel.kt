package com.sanmed.pushnotification.ui.main

import android.app.Application
import android.content.Context
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.pushnotification.R
import java.util.regex.Pattern

class MainViewModel(application:Application) : AndroidViewModel(application) {

    private val _email = MutableLiveData<String>()
    val  email :MutableLiveData<String>
    get() = _email

    private val _goToNotification = MutableLiveData<Boolean>()
    val goToNotification : LiveData<Boolean>
    get() = _goToNotification

    private val _message = MutableLiveData<String>()
    val  message :LiveData<String>
        get() = _message

    fun onJoinClick(){
        if(_email.value!=null && Patterns.EMAIL_ADDRESS.matcher(_email.value!!).matches()){
            _goToNotification.value = true
        }else{
            _message.value = getApplication<Application>().getString(R.string.invalid_email)
        }
    }

    fun goToNotificationCompleted() {
        _goToNotification.value = false
    }
}