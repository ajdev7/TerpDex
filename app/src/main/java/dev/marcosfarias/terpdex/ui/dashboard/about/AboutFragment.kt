package dev.marcosfarias.terpdex.ui.dashboard.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = AboutFragment().apply {
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
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getTerpmonById(id).observe(viewLifecycleOwner, Observer { terpmonValue ->
            terpmonValue?.let { terpmon ->
                textViewDescription.text = terpmon.xdescription
                textViewHeight.text = terpmon.height
                textViewWeight.text = terpmon.weight
                textViewEggCycle.text = terpmon.cycles
                textViewEggGroups.text = terpmon.egg_groups
                textViewBaseEXP.text = terpmon.base_exp
            }
        })
    }
}
