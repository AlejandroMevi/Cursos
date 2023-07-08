package com.example.cursos.udemyCursos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.cursos.R
import com.example.cursos.databinding.FragmentSearchViewBinding

class SearchViewFragment : Fragment() {

    private lateinit var binding: FragmentSearchViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchViewBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun searchView(){
        val users = arrayOf("Alberto", "Alvaro", "Ana", "Amparo", "Bartolo", "Bernardo", "Carla", "Carlos", "Carolina")
        val adapterUsers : ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, users)

        binding.lvUsers.adapter = adapterUsers

        binding.svUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.svUsers.clearFocus()
                if (users.contains(query))  adapterUsers.filter.filter(query)
                return false

            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapterUsers.filter.filter(query)
                return false
            }

        })
    }
}