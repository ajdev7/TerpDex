package dev.marcosfarias.terpdex.ui.dashboard.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
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
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getTerpmonById(id).observe(viewLifecycleOwner, Observer { terpmonValue ->
            terpmonValue?.let { terpmon ->
                textViewTypeDefenses.text = terpmon.ydescription

                textViewHP.text = terpmon.hp.toString()
                textViewAttack.text = terpmon.attack.toString()
                textViewDefense.text = terpmon.defense.toString()
                textViewSpAtk.text = terpmon.special_attack.toString()
                textViewSpDef.text = terpmon.special_defense.toString()
                textViewSpeed.text = terpmon.speed.toString()
                textViewTotal.text = terpmon.total.toString()

                progressBarHP.progress = terpmon.hp ?: 0
                progressBarAttack.progress = terpmon.attack ?: 0
                progressBarDefense.progress = terpmon.defense ?: 0
                progressBarSpAtk.progress = terpmon.special_attack ?: 0
                progressBarSpDef.progress = terpmon.special_defense ?: 0
                progressBarSpeed.progress = terpmon.speed ?: 0
                progressBarTotal.progress = terpmon.total ?: 0
            }
        })
    }
}
