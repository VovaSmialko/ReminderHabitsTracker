package com.smialko.reminderhabitstracker.presentation.notification.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class RemindWorkerFactory @Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            RemindMeWorker::class.qualifiedName -> {
                val childWorkerFactory = workerProviders[RemindMeWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext, workerParameters)
            }
            else -> null
        }
    }
}