package com.example.tarea_s2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tarea_s2.databinding.FragmentFirstBinding
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.setEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private fun scrollUpToMyWantedPosition() =
        _binding?.let {
            with(it.MyScrollView) {
                postDelayed({
                    smoothScrollBy(0, _binding!!.llprimero.y.toInt())
                }, 200)
            }
        }

    override fun onResume() {
        super.onResume()
        setEventListener(requireActivity(),
            KeyboardVisibilityEventListener { isOpen ->
                if (isOpen) {
                    val altura = _binding?.llprimero?.height ?: 4
                    _binding?.llprimero?.setPadding(0, 0, 0, (altura / 4))
//                    Toast.makeText(
//                        this@FirstFragment.context,
//                        "keyboard opened",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    scrollUpToMyWantedPosition()
                } else {
                    _binding?.llprimero?.setPadding(0, 0, 0, 0)
//                    Toast.makeText(
//                        this@FirstFragment.context,
//                        "keyboard hidden",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.nombre?.setText(arguments?.getString("nombre"))
        val cal: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        arguments?.getString("fecha")?.let { s ->
            sdf.parse(s)?.let {
                cal.setTime(it)
                _binding?.fecha?.updateDate(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                )
            }
        }
        _binding?.telefono?.setText(arguments?.getString("telefono"))
        _binding?.email?.setText(arguments?.getString("email"))
        _binding?.contacto?.setText(arguments?.getString("contacto"))

        binding.buttonFirst.setOnClickListener {
            val bundle = bundleOf(
                "nombre" to binding.nombre.text.toString(),
                "fecha" to "${binding.fecha.dayOfMonth}/${binding.fecha.month}/${binding.fecha.year}",
                "telefono" to binding.telefono.text.toString(),
                "email" to binding.email.text.toString(),
                "contacto" to binding.contacto.text.toString(),
            )
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}