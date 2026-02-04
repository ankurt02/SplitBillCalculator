package com.example.splitbill.di

import android.app.Application
import androidx.room.Room
import com.example.splitbill.data.local.AppDatabase
import com.example.splitbill.data.repository.SplitBillRepositoryImpl
import com.example.splitbill.domain.repository.SplitBillRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "split_bill_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePersonDao(db: AppDatabase) = db.personDao()

    @Provides
    @Singleton
    fun provideExpenseDao(db: AppDatabase) = db.expenseDao()

    @Provides
    @Singleton
    fun provideSplitBillRepository(repository: SplitBillRepositoryImpl): SplitBillRepository {
        return repository
    }
}
