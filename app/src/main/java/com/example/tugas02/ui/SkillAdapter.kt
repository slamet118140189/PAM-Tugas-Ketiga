    package com.example.tugas02.ui

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.TextView
    import androidx.navigation.findNavController
    import androidx.recyclerview.widget.RecyclerView
    import com.example.tugas02.R
    import com.example.tugas02.ui.skill.SkillFragmentDirections
    import com.example.tugas02.ui.skill.SkillItem

    class SkillAdapter(private var skillItems: List<SkillItem>) :
        RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameTextView: TextView = view.findViewById(R.id.textViewSkillName)
            val descriptionTextView: TextView = view.findViewById(R.id.textViewSkillDescription)
            val detailButton: Button = view.findViewById(R.id.buttonDetail) // Tambahkan ini
        }

        fun setFilteredList(filteredItems: List<SkillItem>) {
            skillItems = filteredItems
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_skill, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val skillItem = skillItems[position]
            holder.nameTextView.text = skillItem.name
            holder.descriptionTextView.text = skillItem.description

            // Menambahkan OnClickListener untuk tombol detail
            holder.detailButton.setOnClickListener {
                val action = SkillFragmentDirections.actionNavSkillToNavSkilldetail2(skillItem.name)
                holder.itemView.findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int {
            return skillItems.size
        }
    }
