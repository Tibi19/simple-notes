package com.tam.simplenotes.di

import android.app.Application
import androidx.room.Room
import com.tam.simplenotes.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application): NoteDatabase =
        Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "database_notes"
        )
            .fallbackToDestructiveMigration()
            .build()

}