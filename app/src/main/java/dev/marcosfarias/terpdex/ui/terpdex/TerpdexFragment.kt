package dev.marcosfarias.terpdex.ui.terpdex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.leinardi.android.speeddial.SpeedDialView
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.model.Terpmon
import dev.marcosfarias.terpdex.ui.generation.GenerationFragment
import dev.marcosfarias.terpdex.ui.search.SearchFragment
import dev.marcosfarias.terpdex.utils.TerpmonColorUtil
import kotlinx.android.synthetic.main.fragment_terpdex.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TerpdexFragment : Fragment() {

    private val terpdexViewModel: TerpdexViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_terpdex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            TerpmonColorUtil(view.context).convertColor(R.color.background)

        val progressBar = progressBar
        val recyclerView = recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        terpdexViewModel.getListTerpmon().observe(viewLifecycleOwner, Observer {
            val terpmons: List<Terpmon> = it
            recyclerView.adapter = TerpmonAdapter(terpmons, view.context)
            if (terpmons.isNotEmpty())
                progressBar.visibility = View.GONE
        })

        val speedDialView = speedDial
        speedDialView.inflate(R.menu.menu_terpdex)
        speedDialView.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.menuAllGen -> {
                    showAllGen()
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.menuSearch -> {
                    showSearch()
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                else -> {
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
            }
        })
    }

    private fun showAllGen() {
        val dialog = GenerationFragment()
        dialog.show(requireFragmentManager(), "")
    }

    private fun showSearch() {
        val dialog = SearchFragment()
        dialog.show(requireFragmentManager(), "")
    }
}
