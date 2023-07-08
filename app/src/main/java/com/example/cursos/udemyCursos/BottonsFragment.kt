package com.example.cursos.udemyCursos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cursos.R
import com.example.cursos.databinding.FragmentBottonsBinding
import com.google.android.material.chip.Chip

class BottonsFragment : Fragment() {

    private lateinit var binding: FragmentBottonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btBoton.setOnClickListener {
            //Código que queramos ejecutar al pulsar el botón
            Toast.makeText(requireActivity(), "btBoton Pulsado", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton.setOnClickListener {
            //Código que queramos ejecutar al pulsar el botón
            Toast.makeText(requireActivity(), "imageButton Pulsado", Toast.LENGTH_SHORT).show()
        }

        var chip: Chip
        for (i in 0 until binding.cgCondiciones.childCount) {
            chip = binding.cgCondiciones.getChildAt(i) as Chip
            chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

            chip.setOnCloseIconClickListener {
                binding.cgCondiciones.removeView(it)
            }
            chip.setOnClickListener {
                val aux = it as Chip
                Toast.makeText(requireActivity(), "${aux.text} pulsado", Toast.LENGTH_SHORT).show()
            }

        }

        val chipNew = Chip(requireContext())
        chipNew.text = "Opción"
        chipNew.chipIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_mail)
        chipNew.isChipIconVisible = false
        chipNew.isCloseIconVisible = true
        // necessary to get single selection working
        chipNew.isClickable = true
        chipNew.isCheckable = false

        binding.cgCondiciones.addView(chipNew as View)
        chipNew.setOnCloseIconClickListener { binding.cgCondiciones.removeView(chipNew as View) }


        val rb = binding.rgVacaciones.getChildAt(1) as RadioButton
        binding.rgVacaciones.check(rb.id)

        binding.cbSeguro.isEnabled = true
        binding.cbSeguro.isChecked = true

        binding.tgNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) Toast.makeText(requireActivity(), "Toggle Activado", Toast.LENGTH_SHORT)
                .show()
            else Toast.makeText(requireActivity(), "Toggle Desactivado", Toast.LENGTH_SHORT).show()

        }

        binding.swNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) Toast.makeText(requireActivity(), "Switch Activado", Toast.LENGTH_SHORT)
                .show()
            else Toast.makeText(requireActivity(), "Switch Desactivado", Toast.LENGTH_SHORT).show()

        }

        binding.fabButton.setOnClickListener {
            Toast.makeText(requireActivity(), "fabButton Pulsado", Toast.LENGTH_SHORT).show()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.id) {
                R.id.rbPlaya -> {
                    if (checked) Toast.makeText(
                        requireActivity(),
                        "Vamos a la playa", Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.rbMontaña -> {
                    if (checked) Toast.makeText(
                        requireActivity(),
                        "Vamos a la montaña", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun onCheckBoxClicked(view: View) {
        if (view is CheckBox) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.cbSeguro -> {
                    if (checked) Toast.makeText(
                        requireActivity(),
                        "Seguro Contratado",
                        Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(requireActivity(), "Seguro Anulado", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.cbCancelable -> {
                    if (checked) Toast.makeText(
                        requireActivity(),
                        "La reserva podrá ser cancelada", Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(
                        requireActivity(),
                        "La reserva NO podrá ser cancelada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottonsBinding.inflate(inflater, container, false)
        return binding.root
    }

}