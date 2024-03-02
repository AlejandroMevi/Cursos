package com.example.cursos.udemyCursos

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Toast
import com.example.cursos.R
import com.example.cursos.databinding.FragmentWidgetsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.Calendar

class WidgetsFragment : Fragment() {
    private lateinit var activityContext: Context

    private lateinit var binding: FragmentWidgetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContext = requireContext()
        //curso()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWidgetsBinding.inflate(inflater, container, false)
        return binding.root
    }
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetJavaScriptEnabled")
    private fun curso() {

        val imageURL = "http://jotajotavm.com/img/PREMIUM-AndroidDevelopment.gif"
        Picasso.get().load(imageURL).into(binding.ivEjemplo)
        /*
        PARA USAR PICASSO AL CARGAR LA IMAGEN HAY QUE AÑADIR EN EL GRADLE LA SIGUIENTE LINEA
        implementation 'com.squareup.picasso:picasso:2.71828'


        Y DAR PERMISOS EN EL MANIFEST CON LA SIGUIENTE LINEA
        <uses-permission android:name="android.permission.INTERNET" />
         */


        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()

        binding.webView.loadUrl("http://www.google.com")

        /*
        PARA USAR EL WEBVIEW HAY QUE DAR PERMISOS EN EL MANIFEST CON LA SIGUIENTE LINEA
        <uses-permission android:name="android.permission.INTERNET" />
         */

        val mcLocal = MediaController(requireContext())
        mcLocal.setAnchorView(binding.vvLocal)
        //direccion del video guardado manera local
        //val path = "android.resource://" + requireActivity().packageName + "/" + R.raw.video
        //binding.vvLocal.setVideoURI(Uri.parse(path))
        binding.vvLocal.setMediaController(mcLocal)
        //vvLocal.start()


        val mcWeb = MediaController(requireContext())

        mcWeb.setAnchorView(binding.vvWeb)
        binding.vvWeb.setVideoPath("https://jotajotavm.com/img/video.mp4")
        binding.vvWeb.setMediaController(mcWeb)

        /*
        PARA CARGAR UN VIDEO DE UNA DIRECCION WEB HAY QUE DAR PERMISOS EN EL MANIFEST CON LA SIGUIENTE LINEA
        <uses-permission android:name="android.permission.INTERNET" />
         */


        binding.cvEjemplo.setOnDateChangeListener { _, year, month, day ->
            val date = "$day/${month + 1}/$year"
            binding.tvFecha.text = "Fecha seleccionada: $date"
        }

        val calendar = Calendar.getInstance()
        calendar.set(2026, 5 - 1, 8)
        binding.cvEjemplo.date = calendar.timeInMillis


        val d = binding.cvEjemplo.firstDayOfWeek
        binding.cvEjemplo.firstDayOfWeek = (d + 1) % 7

        /*
        binding.rbEjemplo.rating = 2.5f
        binding.rbEjemplo.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            tvRating.text = "${rating}/${ratingBar.numStars}"
        }
         */

        binding.pbDeterminado.max = 200
        binding.pbDeterminado.progress = 0
        binding.pbSecundario.progress = 0
        binding.pbSecundario.secondaryProgress = 0


        binding.sbNormal.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser)
                    Toast.makeText(activityContext, "Tú lo cambiaste", Toast.LENGTH_SHORT).show()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        /*
        PARA USAR GlobalScope Y USAR LA CORRUTINAS HAY QUE AÑADIR EN EL GRADLE LA SIGUIENTE LINEA
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
        */
        GlobalScope.launch {
            progressManager(binding.pbDeterminado)
            progressManager(binding.pbSecundario)
            seekbarManager(binding.sbNormal)
        }

        binding.npEjemplo.minValue = 0
        binding.npEjemplo.maxValue = 60
        binding.npEjemplo.value = 5
        binding.npEjemplo.wrapSelectorWheel = true
        binding.npEjemplo.setFormatter { i -> String.format("%02d", i) }

        binding.npEjemplo.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            binding.tvNumberPicker.text = "NumberPicker: Antes($oldVal) - Ahora ($newVal)"
        }

    }

    private fun seekbarManager(sb: SeekBar) {
        while (true) {
            sleep(100L)
            sb.incrementProgressBy(5)
        }
    }

    private fun progressManager(pb: ProgressBar) {
        while (pb.progress < pb.max) {
            sleep(100L)
            //pb.progress += 5
            pb.incrementProgressBy(5)
            if (pb.id == R.id.pbSecundario) pb.incrementSecondaryProgressBy(10)
        }
    }

}