package com.smialko.reminderhabitstracker.presentation.notification.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.smialko.reminderhabitstracker.presentation.notification.Notification
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@HiltWorker
class RemindMeWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val reminderText = workerParameters.inputData.getString("reminderText").orEmpty()
        Notification.showNotification(context, reminderText)
        return Result.success()
    }

    @AssistedFactory
    interface Factory : ChildWorkerFactory {
         override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): RemindMeWorker
    }
}