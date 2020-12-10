package dev.marcosfarias.terpdex.ui.dashboard.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.model.Terpmon
import dev.marcosfarias.terpdex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_evolution.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EvolutionFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))
        val recyclerView = recyclerViewEvolvingTerpmon
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val adapter = EvolutionAdapter(view.context)
        recyclerView.adapter = adapter

        dashboardViewModel.getTerpmonById(id).observe(viewLifecycleOwner, Observer { terpmonValue ->
            terpmonValue?.let { terpmon ->
                val evolutions = terpmon.evolutions ?: emptyList()
                dashboardViewModel.getTerpmonEvolutionsByIds(evolutions).observe(viewLifecycleOwner, Observer {
                    val terpmons: List<Terpmon> = it
                    adapter.setList(terpmons)
                    adapter.notifyDataSetChanged()

                    if (terpmons.isEmpty())
                        textNonEvolving.visibility = View.VISIBLE
                })
            }
        })
    }
}
