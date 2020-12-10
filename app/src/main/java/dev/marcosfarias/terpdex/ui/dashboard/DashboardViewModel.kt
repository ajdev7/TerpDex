package dev.marcosfarias.terpdex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.terpdex.database.dao.TerpmonDAO
import dev.marcosfarias.terpdex.model.Terpmon

class DashboardViewModel(private val terpmonDAO: TerpmonDAO) : ViewModel() {

    fun getTerpmonById(id: String?): LiveData<Terpmon> {
        return terpmonDAO.getById(id)
    }

    fun getTerpmonEvolutionsByIds(ids: List<String>): LiveData<List<Terpmon>> {
        return terpmonDAO.getEvolutionsByIds(ids)
    }
}
