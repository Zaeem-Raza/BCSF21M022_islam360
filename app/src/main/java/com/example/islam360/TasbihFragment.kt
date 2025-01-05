package com.example.islam360

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.islam360.databinding.FragmentTasbihBinding

class TasbihFragment : Fragment() {

    private var _binding: FragmentTasbihBinding? = null
    private val binding get() = _binding!!

    private var count = 0
    private var currentPhraseIndex = 0
    private val tasbihPhrases = listOf("الله أكبر", "سبحان الله", "الحمد لله")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasbihBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Tasbih phrase
        binding.allahuAkbarText.text = tasbihPhrases[currentPhraseIndex]

        // Handle Plus Button Click
        binding.plusIcon.setOnClickListener {
            count++
            binding.tasbihCount.text = count.toString()
        }

        // Handle Reset Button Click
        binding.resetIcon.setOnClickListener {
            count = 0
            binding.tasbihCount.text = count.toString()
        }

        // Handle Changing Tasbih Phrase on Text Click
        binding.allahuAkbarText.setOnClickListener {
            currentPhraseIndex = (currentPhraseIndex + 1) % tasbihPhrases.size
            binding.allahuAkbarText.text = tasbihPhrases[currentPhraseIndex]
            Toast.makeText(
                requireContext(),
                "Changed to ${binding.allahuAkbarText.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
