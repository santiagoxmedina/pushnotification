package com.sanmed.pushnotification.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.sanmed.pushnotification.R
import com.sanmed.pushnotification.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding:MainFragmentBinding  = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false)
        initViewModel();
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscribers()
    }

    private fun initSubscribers() {
        viewModel.goToNotification.observe(viewLifecycleOwner,this::onGoToNotification)
        viewModel.message.observe(viewLifecycleOwner,this::showMessage)
    }

    private fun showMessage(message:String) {
        if(message.isNotEmpty()){
            Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun onGoToNotification( goToNotification:Boolean) {
        if(goToNotification){
            goToNotification(viewModel.email.value)
            viewModel.goToNotificationCompleted()
        }
    }

    private fun goToNotification( email:String?) {
        if(email!=null){
            Navigation.findNavController(requireView()).navigate(MainFragmentDirections.actionMainFragmentToNotificationFragment(email))
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}