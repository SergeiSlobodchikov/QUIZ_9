package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quiz.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        binding.buttonDateBirth.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker() //    binding.buttonDateBirth вызывается MaterialDatePicker, который позволяет пользователю выбрать дату.
                .setTitleText(resources.getString(R.string.date_dialoge_text))
                .build()
            dateDialog.addOnPositiveButtonClickListener { time -> // Когда пользователь выбирает дату, вызывается слушатель addOnPositiveButtonClickListener,
                calendar.timeInMillis = time
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)
                val text = "$day/$month/$year" // который сохраняет выбранное время в calendar и преобразует его в строку формата "день/месяц/год".
                Snackbar.make(binding.buttonDateBirth, text, Snackbar.LENGTH_SHORT).show() //Затем вызывается Snackbar, который отображает выбранную дату.
            }
            dateDialog.show(parentFragmentManager, "Date")
        }

        binding.button.setOnClickListener { //  При нажатии на binding.button вызывается findNavController().navigate(R.id.action_welcomeFragment_to_quizFragment),
            findNavController().navigate(R.id.action_welcomeFragment_to_quizFragment) // что приводит к переходу на фрагмент QuizFragment.
        }
    }

}