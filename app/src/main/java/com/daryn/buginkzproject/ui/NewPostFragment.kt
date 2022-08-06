package com.daryn.buginkzproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.daryn.buginkzproject.MainActivity
import com.daryn.buginkzproject.R
import com.daryn.buginkzproject.databinding.FragmentHomeBinding
import com.daryn.buginkzproject.databinding.NewPostFragmentBinding
import com.daryn.buginkzproject.models.HomeViewModel
import com.daryn.buginkzproject.models.NewPostViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class NewPostFragment : Fragment() {
    private lateinit var viewModel: NewPostViewModel
    private var _binding: NewPostFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NewPostViewModel::class.java)
        _binding = NewPostFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        closeFragment()


        return root

    }
    private fun closeFragment(){
    val imageButton:ImageButton=binding.closeButton
        imageButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}