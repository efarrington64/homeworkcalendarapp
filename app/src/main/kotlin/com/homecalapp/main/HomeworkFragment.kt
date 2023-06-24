package com.homecalapp.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.homecalapp.app.R
import com.homecalapp.db.Homework
import com.homecalapp.defaultDatabase
import com.homecalapp.homework.ViewHomeworkFragment

class HomeworkFragment : Fragment() {
    lateinit var refreshLayout: SwipeRefreshLayout
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main_homework, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshLayout = view.findViewById(R.id.refreshLayout)
        recycler = view.findViewById(R.id.recycler)

        refreshLayout.setOnRefreshListener(::refresh)
        recycler.adapter = RecyclerAdapter(
            requireContext().defaultDatabase.homeworks().getAll().toMutableList(),
            childFragmentManager
        )
    }

    fun refresh() {
        refreshLayout.isRefreshing = false
        (recycler.adapter as RecyclerAdapter).run {
            list.clear()
            list.addAll(requireContext().defaultDatabase.homeworks().getAll())
            notifyDataSetChanged()
        }
    }

    class RecyclerAdapter(val list: MutableList<Homework>, val manager: FragmentManager) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
        override fun getItemCount(): Int = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_homework, parent, false)
            )

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val homework = list[position]
            holder.titleText.text = "${homework.className} – ${homework.assignmentName}"
            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(ViewHomeworkFragment.KEY_ASSIGNMENT, homework.assignmentName)
                bundle.putString(ViewHomeworkFragment.KEY_CLASS, homework.className)
                ViewHomeworkFragment()
                    .apply { arguments = bundle }
                    .show(manager, "TAG")
            }
            holder.itemView.setOnLongClickListener {
                AlertDialog.Builder(it.context)
                    .setTitle("Deleting ${holder.titleText.text}")
                    .setMessage("Are you sure?")
                    .setNegativeButton(android.R.string.no) { _, _ -> }
                    .setPositiveButton(android.R.string.yes) { _, _ ->
                        it.context.defaultDatabase.homeworks().delete(homework)
                        list.remove(homework)
                        notifyDataSetChanged()
                    }
                    .show()
                false
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val titleText: TextView = view.findViewById(R.id.titleText)
            val subtitleText: TextView = view.findViewById(R.id.subtitleText)
        }
    }
}
