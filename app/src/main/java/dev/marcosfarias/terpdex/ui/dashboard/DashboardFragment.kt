package dev.marcosfarias.terpdex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.utils.TerpmonColorUtil
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getTerpmonById(id).observe(viewLifecycleOwner, Observer { terpmonValue ->
            terpmonValue?.let { terpmon ->
                textViewID.text = terpmon.id
                textViewName.text = terpmon.name

                val color =
                    TerpmonColorUtil(view.context).getTerpmonColor(terpmon.typeofpokemon)
                app_bar.background.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                toolbar_layout.contentScrim?.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                activity?.window?.statusBarColor =
                    TerpmonColorUtil(view.context).getTerpmonColor(terpmon.typeofpokemon)

                terpmon.typeofpokemon?.getOrNull(0).let { firstType ->
                    textViewType3.text = firstType
                    textViewType3.isVisible = firstType != null
                }

                terpmon.typeofpokemon?.getOrNull(1).let { secondType ->
                    textViewType2.text = secondType
                    textViewType2.isVisible = secondType != null
                }

                terpmon.typeofpokemon?.getOrNull(2).let { thirdType ->
                    textViewType1.text = thirdType
                    textViewType1.isVisible = thirdType != null
                }

                Glide.with(view.context)
                    .load(terpmon.imageurl)
                    .placeholder(android.R.color.transparent)
                    .into(imageView)

                val pager = viewPager
                val tabs = tabs
                pager.adapter =
                    ViewPagerAdapter(requireFragmentManager(), requireContext(), terpmon.id!!)
                tabs.setupWithViewPager(pager)
            }
        })
    }
}
