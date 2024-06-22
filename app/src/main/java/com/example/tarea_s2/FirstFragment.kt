package com.example.tarea_s2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tarea_s2.databinding.FragmentFirstBinding
import com.google.android.material.internal.ViewUtils.dpToPx
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.setEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private fun scrollUpToMyWantedPosition() =
        with(binding.MyScrollView) {
            postDelayed({
                smoothScrollBy(0, binding.llprimero.y.toInt())
            }, 200)
        }

    override fun onResume() {
        super.onResume()
        setEventListener(requireActivity(),
            KeyboardVisibilityEventListener { isOpen ->
                if (isOpen) {
                    binding.llprimero.setPadding(0,0,0,(binding.llprimero.height/4))
//                    Toast.makeText(
//                        this@FirstFragment.context,
//                        "keyboard opened",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    scrollUpToMyWantedPosition()
                } else {
                    binding.llprimero.setPadding(0,0,0,0)
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

        binding.buttonFirst.setOnClickListener {
            val amount = "hola mundo"
            val bundle = bundleOf("test" to amount)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}