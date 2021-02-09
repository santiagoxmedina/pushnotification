package com.sanmed.pushnotification.ui.notification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sanmed.pushnotification.R
import com.sanmed.pushnotification.databinding.NotificationFragmentBinding

class NotificationFragment : Fragment() {

    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NotificationFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.notification_fragment, container, false)
        initViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onUserEmail(NotificationFragmentArgs.fromBundle(requireArguments()).email)
        initSubscribers()
    }

    private fun initSubscribers() {
        viewModel.message.observe(viewLifecycleOwner, this::showMessage)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
    }

    private fun showMessage(message: String) {
        if (message.isNotEmpty()) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}