package com.example.tugas02.ui.skill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas02.databinding.FragmentSkillBinding
import androidx.appcompat.widget.SearchView
import com.example.tugas02.ui.SkillAdapter
import java.util.Locale

data class SkillItem(
    val name: String,
    val description: String
)

class SkillFragment : Fragment() {

    private var _binding: FragmentSkillBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SkillAdapter
    private val skillItems = listOf(
        SkillItem(
            "Python",
            "Bahasa pemrograman serbaguna dengan sintaksis yang mudah dibaca dan kuat."
        ),
        SkillItem(
            "JavaScript",
            "Bahasa pemrograman berorientasi objek untuk pengembangan web dan aplikasi berbasis browser"
        ),
        SkillItem(
            "Java",
            "Bahasa pemrograman yang kuat dan umum digunakan untuk pengembangan aplikasi berbasis Android"
        ),
        SkillItem(
            "C++",
            "Bahasa pemrograman yang efisien dan digunakan dalam pengembangan perangkat lunak, game, dan sistem"
        ),
        SkillItem(
            "C#",
            "Bahasa pemrograman yang kuat dan digunakan untuk pengembangan aplikasi berbasis Windows dan game"
        ),
        SkillItem(
            "Ruby",
            "Bahasa pemrograman dinamis yang mudah dipahami, sering digunakan untuk pengembangan web"
        ),
        SkillItem(
            "Swift",
            "Bahasa pemrograman eksklusif Apple untuk pengembangan aplikasi iOS, macOS, watchOS, dan tvOS"
        ),
        SkillItem("PHP", "Bahasa pemrograman server-side untuk pengembangan web dinamis"),
        SkillItem(
            "Rust",
            "Bahasa pemrograman dengan fokus pada keamanan dan kinerja tinggi, sering digunakan dalam sistem"
        ),
        SkillItem(
            "Golang",
            "Bahasa pemrograman yang dirancang oleh Google dengan fokus pada efisiensi dan pengembangan perangkat lunak berskala besar"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val searchView = binding.searchView

        adapter = SkillAdapter(skillItems)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        return rootView
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<SkillItem>()

            if (query.isBlank()) {
                // If the query is empty, display the original list
                filteredList.addAll(skillItems)
            } else {
                for (i in skillItems) {
                    if (i.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                        filteredList.add(i)
                    }
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            }

            adapter.setFilteredList(filteredList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
