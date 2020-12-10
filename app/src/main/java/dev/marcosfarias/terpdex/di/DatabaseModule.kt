package dev.marcosfarias.terpdex.di

import androidx.room.Room
import dev.marcosfarias.terpdex.R
import dev.marcosfarias.terpdex.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<AppDatabase>().terpmonDAO()
    }
}
