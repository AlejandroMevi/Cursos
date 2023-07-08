package com.example.cursos.pruebas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cursos.databinding.FragmentGraphBinding


class GraphFragment : Fragment() {

    private lateinit var binding: FragmentGraphBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }
    /* Grafica
    private fun loadGrap(
        dias: ArrayList<String>,
        faltas: ArrayList<Float>,
        descansos: ArrayList<Float>,
        asistencia: ArrayList<Float>,
        incapacidad: ArrayList<Float>,
        vacaciones: ArrayList<Float>,
        ausentismo: ArrayList<Float>,
        festivo: ArrayList<Float>
    ) {
        val year = ArrayList<String>()
        val dateFormatOrigin = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateFormatTarget = SimpleDateFormat("dd/MM", Locale.getDefault())
        for (i in 0 until dias.size) {
            val dtStart = dias[i]
            val date = dateFormatOrigin.parse(dtStart)
            date?.let { dateFormatTarget.format(it) }?.let { year.add(it) }
        }
        val xAxis: XAxis = binding.graficChard.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 7
        xAxis.position = XAxisPosition.TOP
        xAxis.valueFormatter = IndexAxisValueFormatter(year)

        val yVals1 = ArrayList<BarEntry>()

        for (i in 0 until 7) {
            val val1 = (faltas[i])
            val val2 = (descansos[i])
            val val3 = (asistencia[i])
            val val4 = (incapacidad[i])
            val val5 = (vacaciones[i])
            val val6 = (ausentismo[i])
            val val7 = (festivo[i])
            yVals1.add(
                BarEntry(
                    i.toFloat(), floatArrayOf(val1, val2, val3, val4, val5, val6, val7),
                    year
                )
            )
        }

        val set1: BarDataSet

        if (binding.graficChard.data != null &&
            binding.graficChard.data.dataSetCount > 0
        ) {
            set1 = binding.graficChard.data.getDataSetByIndex(0) as BarDataSet
            set1.values = yVals1
            binding.graficChard.data.notifyDataChanged()
            binding.graficChard.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")
            set1.setDrawIcons(false)
            set1.colors = colorsGraph
            set1.stackLabels = arrayOf(
                "Ausentismos",
                "Incapacidades",
                "Vacaciones",
                "Asistencias",
                "Faltas",
                "Festivos",
                "Descansos"
            )
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(5f)
            data.setValueTextColor(Color.BLACK)
            binding.graficChard.data = data
        }
        binding.graficChard.legend.isWordWrapEnabled = true
        binding.graficChard.legend.form = Legend.LegendForm.SQUARE
        binding.graficChard.legend.setDrawInside(false)
        binding.graficChard.description.text = ""
        binding.graficChard.setFitBars(true)
        binding.graficChard.invalidate()
        binding.graficChard.data.notifyDataChanged()
        binding.graficChard.notifyDataSetChanged()
        binding.graficChard.setOnChartValueSelectedListener(this)
    }

    private val colorsGraph = mutableListOf(
        ColorTemplate.rgb("#AAAAAA"),
        ColorTemplate.rgb("#F44336"),
        ColorTemplate.rgb("#1973E8"),
        ColorTemplate.rgb("#009CA6"),
        ColorTemplate.rgb("#941100"),
        ColorTemplate.rgb("#E36F1E"),
        ColorTemplate.rgb("#753E7B"),
    )

        override fun onValueSelected(e: Entry?, h: Highlight?) {
        var label = ""
        if (e != null){
            Log.d("h", h!!.stackIndex.toString())
            Log.i("e",((e as BarEntry).yVals as FloatArray)[h.stackIndex].toString())
            when (h.stackIndex) {
                0 -> label = "Empleados con Ausentismos ${(e.yVals as FloatArray)[h.stackIndex]}%"
                1 -> label = "Empleados con Incapacidades ${(e.yVals as FloatArray)[h.stackIndex]}%"
                2 -> label = "Empleados con Vacaciones ${(e.yVals as FloatArray)[h.stackIndex]}%"
                3 -> label = "Asistencias de empleados ${(e.yVals as FloatArray)[h.stackIndex]}%"
                4 -> label = "Empleados con Faltas ${(e.yVals as FloatArray)[h.stackIndex]}%"
                5 -> label = "Empleados con dias Festivos ${(e.yVals as FloatArray)[h.stackIndex]}%"
                6 -> label = "Empleados con Descansos ${(e.yVals as FloatArray)[h.stackIndex]}%"
            }
        }
        Utilities.loadMessageError(label, requireActivity())
    }

    override fun onNothingSelected() {
        Log.i("Activity", "Nothing selected.")
    }
     */
}