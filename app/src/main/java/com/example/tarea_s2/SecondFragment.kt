package com.example.tarea_s2

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.tarea_s2.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val html = "${arguments?.getString("nombre")}<br>" +
               "Fecha de nacimiento: ${arguments?.getString("fecha")}<br>" +
               "Tel: ${arguments?.getString("telefono")}<br>" +
               "Email: ${arguments?.getString("email")}<br>" +
               "Descripcion: ${arguments?.getString("contacto")}"

        binding.textviewSecond.text =
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        binding.buttonSecond.setOnClickListener {
            val bundle = bundleOf(
                "nombre" to arguments?.getString("nombre"),
                "fecha" to arguments?.getString("fecha"),
                "telefono" to arguments?.getString("telefono"),
                "email" to arguments?.getString("email"),
                "contacto" to arguments?.getString("contacto"),
            )
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}