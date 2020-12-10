package dev.marcosfarias.terpdex.ui.terpdex

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.terpdex.database.dao.TerpmonDAO
import dev.marcosfarias.terpdex.model.Terpmon
import dev.marcosfarias.terpdex.repository.TerpmonService
import kotlin.concurrent.thread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TerpdexViewModel(private val terpmonDAO: TerpmonDAO, private val terpmonService: TerpmonService) : ViewModel() {

    init {
        initNetworkRequest()
    }

    private val mTermponList: MutableList<Terpmon> = mutableListOf();

    private fun initNetworkRequest() {
        val call = terpmonService.get()
        call.enqueue(object : Callback<List<Terpmon>?> {
            override fun onResponse(
                call: Call<List<Terpmon>?>?,
                response: Response<List<Terpmon>?>?
            ) {
                response?.body()?.let { terpmons: List<Terpmon> ->
                    thread {
                        terpmonDAO.deleteAll()
                        terpmonDAO.add(terpmons)
                    }
                }
            }

            override fun onFailure(call: Call<List<Terpmon>?>?, t: Throwable?) {
                // TODO handle failure
            }
        })
    }

    fun getListTerpmon(): LiveData<List<Terpmon>> {
        return terpmonDAO.all()
    }
}
