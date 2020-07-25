package com.hilt.room.di

import android.content.Context
import com.hilt.room.db.StudentDB
import com.hilt.room.db.StudentDao
import com.hilt.room.repository.RoomDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object DBModule {

  @Provides
   fun provideStudentDao(@ApplicationContext appContext: Context) : StudentDao {
      return StudentDB.getInstance(appContext).studentDao
  }

    @Provides
    fun provideStudentDBRepository(studentDao: StudentDao) = RoomDBRepository(studentDao)

}


//https://codelabs.developers.google.com/codelabs/android-hilt/#6
//https://github.com/google-developer-training/android-kotlin-fundamentals-apps/blob/master/RecyclerViewFundamentals/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt