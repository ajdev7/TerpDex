package dev.marcosfarias.terpdex.ui.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.ui.dashboard.about.AboutFragment
import dev.marcosfarias.terpdex.ui.dashboard.evolution.EvolutionFragment
import dev.marcosfarias.terpdex.ui.dashboard.moves.MovesFragment
import dev.marcosfarias.terpdex.ui.dashboard.stats.StatsFragment

class ViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    private val terpmonId: String
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            context.getString(R.string.dashboard_tab_1),
            { AboutFragment.newInstance(terpmonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_2),
            { StatsFragment.newInstance(terpmonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_3),
            { EvolutionFragment.newInstance(terpmonId) }
        ),
        Page(
            context.getString(R.string.dashboard_tab_4),
            { MovesFragment() }
        )
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
