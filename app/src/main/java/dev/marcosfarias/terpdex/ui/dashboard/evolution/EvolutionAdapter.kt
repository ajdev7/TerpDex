package dev.marcosfarias.terpdex.ui.dashboard.evolution

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.model.Terpmon
import dev.marcosfarias.terpdex.utils.TerpmonColorUtil
import kotlinx.android.synthetic.main.item_terpmon.view.*

class EvolutionAdapter(
    private val context: Context
) : RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private val list = arrayListOf<Terpmon>()

    fun setList(list: List<Terpmon>) {
        this.list.clear()
        this.list.addAll(list)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Terpmon) {
            itemView.textViewName.text = item.name
            itemView.textViewID.text = item.id

            val color = TerpmonColorUtil(itemView.context).getTerpmonColor(item.typeofterpmon)
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.typeofterpmon?.getOrNull(0).let { firstType ->
                itemView.textViewType3.text = firstType
                itemView.textViewType3.isVisible = firstType != null
            }

            item.typeofterpmon?.getOrNull(1).let { secondType ->
                itemView.textViewType2.text = secondType
                itemView.textViewType2.isVisible = secondType != null
            }

            item.typeofterpmon?.getOrNull(2).let { thirdType ->
                itemView.textViewType1.text = thirdType
                itemView.textViewType1.isVisible = thirdType != null
            }

            Glide.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_terpmon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
