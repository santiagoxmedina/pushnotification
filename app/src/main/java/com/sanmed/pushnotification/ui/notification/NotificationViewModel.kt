package com.sanmed.pushnotification.ui.notification

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanmed.pushnotification.R

class NotificationViewModel(application:Application) : AndroidViewModel(application) {

    private val _welcome = MutableLiveData<String>()
    val  welcome : LiveData<String>
        get() = _welcome

    private val _message = MutableLiveData<String>()
    val  message : LiveData<String>
        get() = _message

    val toEmail = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val text = MutableLiveData<String>()

    fun onUserEmail(email: String) {
        _welcome.value = getApplication<Application>().getString(R.string.welcome,email)
    }

    fun onSendClick(){
        _message.value = String.format("To: ${toEmail.value}, Title:${title.value} , Text:${text.value}")
    }
}