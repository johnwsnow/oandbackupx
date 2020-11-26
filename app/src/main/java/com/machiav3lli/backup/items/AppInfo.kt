/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.machiav3lli.backup.items

import android.app.usage.StorageStats
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.machiav3lli.backup.Constants
import com.machiav3lli.backup.handler.BackendController
import com.machiav3lli.backup.utils.DocumentUtils
import com.machiav3lli.backup.utils.FileUtils
import com.machiav3lli.backup.utils.LogUtils
import com.machiav3lli.backup.utils.LogUtils.Companion.logErrors
import com.machiav3lli.backup.utils.StorageLocationNotConfiguredException
import java.io.File
import java.io.FileNotFoundException

class AppInfo {
    private val TAG = Constants.classTag(".AppInfo")

    var packageName: String = ""

    lateinit var appMetaInfo: AppMetaInfo

    var backupDirUri: Uri = Uri.EMPTY

    var storageStats: StorageStats? = null

    var packageInfo: com.machiav3lli.backup.items.PackageInfo? = null

    private var backupHistoryCache: Pair<MutableList<BackupItem>?, Context>? = null

    val backupHistory: MutableList<BackupItem>
        get() {
            if (backupHistoryCache?.first == null) {
                if (historyCollectorThread != null) {
                    historyCollectorThread?.join()
                    Log.i(TAG, "thread $historyCollectorThread / ${Thread.activeCount()} joined")
                }
                if (backupHistoryCache?.first == null) {
                    Log.i(TAG, "refreshBackupHistory")
                    backupHistoryCache?.second?.let { refreshBackupHistory(it) }
                    historyCollectorThread?.join()
                    Log.i(TAG, "thread $historyCollectorThread / ${Thread.activeCount()} joined")
                }
            }
            return backupHistoryCache?.first ?: mutableListOf()
        }

    private var historyCollectorThread: Thread? = null

    internal constructor(context: Context, metaInfo: AppMetaInfo) {
        packageName = metaInfo.packageName.toString()
        appMetaInfo = metaInfo
        val backupDoc = DocumentUtils.getBackupRoot(context).findFile(packageName)
        backupDirUri = backupDoc?.uri ?: Uri.EMPTY
        refreshBackupHistory(context)
    }

    constructor(context: Context, packageInfo: PackageInfo) {
        packageName = packageInfo.packageName
        this.packageInfo = PackageInfo(packageInfo)
        this.appMetaInfo = AppMetaInfo(context, packageInfo)
        val backupDoc = DocumentUtils.getBackupRoot(context).findFile(packageName)
        backupDirUri = backupDoc?.uri ?: Uri.EMPTY
        refreshBackupHistory(context)
        refreshStorageStats(context)
    }

    constructor(context: Context, backupRoot: Uri, packageName: String?) {
        this.backupDirUri = backupRoot
        this.packageName = packageName ?: StorageFile.fromUri(context, backupRoot).name!!
        refreshBackupHistory(context)
        try {
            val pi = context.packageManager.getPackageInfo(this.packageName, 0)
            this.packageInfo = PackageInfo(pi)
            this.appMetaInfo = AppMetaInfo(context, pi)
            refreshStorageStats(context)
        } catch (e: PackageManager.NameNotFoundException) {
            try {
                this.packageInfo = null
                this.appMetaInfo = SpecialAppMetaInfo.getSpecialPackages(context)
                        .find { it.packageName == this.packageName }!!
                        .appMetaInfo
            } catch(e: Throwable) {
                Log.i(TAG, "$packageName is not installed")
                if (this.backupHistory.isNullOrEmpty()) {
                    throw AssertionError("Backup History is empty and package is not installed. The package is completely unknown?", e)
                }
                this.appMetaInfo = latestBackup!!.backupProperties
            }
        }
    }

    constructor(context: Context, packageInfo: PackageInfo, backupRoot: Uri) {
        this.packageName = packageInfo.packageName
        this.appMetaInfo = AppMetaInfo(context, packageInfo)
        this.packageInfo = PackageInfo(packageInfo)
        val appBackupRoot = StorageFile.fromUri(context, backupRoot).findFile(packageName)
        refreshStorageStats(context)
        this.backupDirUri = appBackupRoot?.uri ?: Uri.EMPTY
        refreshBackupHistory(context)
    }

    val latestBackup: BackupItem?
        get() = if (backupHistory.isNotEmpty()) {
            backupHistory.last()
        } else null

    private fun refreshStorageStats(context: Context): Boolean {
        return try {
            storageStats = BackendController.getPackageStorageStats(context, packageName)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Could not refresh StorageStats. Package was not found: " + e.message)
            false
        }
    }

    fun refreshFromPackageManager(context: Context): Boolean {
        Log.d(TAG, "Trying to refresh package information for $packageName from PackageManager")
        try {
            val pi = context.packageManager.getPackageInfo(packageName, 0)
            packageInfo = PackageInfo(pi)
            appMetaInfo = AppMetaInfo(context, pi)
            refreshStorageStats(context)
        } catch (e: PackageManager.NameNotFoundException) {
            Log.i(TAG, "$packageName is not installed. Refresh failed")
            return false
        }
        return true
    }

    fun refreshBackupHistory(context: Context) {
        backupDirUri.let { backupDir ->
            historyCollectorThread?.interrupt()
            historyCollectorThread = Thread {
                val appBackupDir = StorageFile.fromUri(context, backupDir)
                val backups: MutableList<BackupItem> = mutableListOf()
                try {
                    for (file in appBackupDir.listFiles()) {
                        if (file.isPropertyFile)
                            try {
                                backups.add(BackupItem(context, file))
                            } catch (e: BackupItem.BrokenBackupException) {
                                val message = "Incomplete backup or wrong structure found in ${backupDir.encodedPath}."
                                Log.w(TAG, message)
                                logErrors(context, message)
                            } catch (e: NullPointerException) {
                                val message = "(Null) Incomplete backup or wrong structure found in ${backupDir.encodedPath}."
                                Log.w(TAG, message)
                                logErrors(context, message)
                            } catch (e: Throwable) {
                                val message = "(catchall) Incomplete backup or wrong structure found in ${backupDir.encodedPath}."
                                LogUtils.unhandledException(e, file.uri)
                                logErrors(context, message)
                            }
                    }
                } catch (e: FileNotFoundException) {
                    Log.w(TAG, "Failed getting backup history: $e")
                } catch (e: InterruptedException) {
                    return@Thread
                } catch (e: Throwable) {
                    LogUtils.unhandledException(e, backupDir.encodedPath)
                }
                backupHistoryCache = Pair(backups, context)
                //Log.i(TAG, "thread ${Thread.currentThread()} / ${Thread.activeCount()} end")
                historyCollectorThread = null
            }
            historyCollectorThread?.start()
        }
    }

    @Throws(FileUtils.BackupLocationIsAccessibleException::class, StorageLocationNotConfiguredException::class)
    fun getAppUri(context: Context, create: Boolean): Uri? {
        if (create && backupDirUri == Uri.EMPTY) {
            backupDirUri = DocumentUtils.ensureDirectory(DocumentUtils.getBackupRoot(context), packageName)!!.uri
        }
        return backupDirUri
    }

    fun deleteAllBackups(context: Context) {
        Log.i(TAG, "Deleting ${backupHistory.size} backups of $this")
        StorageFile.fromUri(context, backupDirUri).delete()
        backupHistory.clear()
        backupDirUri = Uri.EMPTY
    }

    fun delete(context: Context, backupItem: BackupItem, directBoolean: Boolean = true) {
        if (backupItem.backupProperties.packageName != packageName) {
            throw RuntimeException("Asked to delete a backup of ${backupItem.backupProperties.packageName} but this object is for $packageName")
        }
        Log.d(TAG, "[$packageName] Deleting backup revision $backupItem")
        val propertiesFileName = String.format(BackupProperties.BACKUP_INSTANCE_PROPERTIES,
                Constants.BACKUP_DATE_TIME_FORMATTER.format(backupItem.backupProperties.backupDate), backupItem.backupProperties.profileId)
        try {
            DocumentUtils.deleteRecursive(context, backupItem.backupInstanceDirUri)
            StorageFile.fromUri(context, backupDirUri).findFile(propertiesFileName)!!.delete()
        } catch (e: Throwable) {
            LogUtils.unhandledException(e, backupItem.backupProperties.packageName)
        }
        if (directBoolean) backupHistory.remove(backupItem)
    }

    val isInstalled: Boolean
        get() = packageInfo != null || appMetaInfo.isSpecial

    val isDisabled: Boolean
        get() = !appMetaInfo.isSpecial && packageInfo != null && !packageInfo!!.enabled

    val isSystem: Boolean
        get() = appMetaInfo.isSystem || appMetaInfo.isSpecial

    val isSpecial: Boolean
        get() = appMetaInfo.isSpecial

    val packageLabel: String
        get() = if (appMetaInfo.packageLabel != null) appMetaInfo.packageLabel!! else packageName

    val versionCode: Int
        get() = appMetaInfo.versionCode

    val versionName: String?
        get() = appMetaInfo.versionName

    val hasBackups: Boolean
        get() = backupHistory.isNotEmpty()

    fun getApkPath(): String = packageInfo?.apkDir ?: ""

    fun getDataPath(): String = packageInfo?.dataDir ?: ""

    fun getDevicesProtectedDataPath(): String = packageInfo?.deDataDir ?: ""

    // Uses the context to get own external data directory
    // e.g. /storage/emulated/0/Android/data/com.machiav3lli.backup/files
    // Goes to the parent two times to the leave own directory
    // e.g. /storage/emulated/0/Android/data
    fun getExternalDataPath(context: Context): String {
        // Uses the context to get own external data directory
        // e.g. /storage/emulated/0/Android/data/com.machiav3lli.backup/files
        // Goes to the parent two times to the leave own directory
        // e.g. /storage/emulated/0/Android/data
        val externalFilesPath = context.getExternalFilesDir(null)!!.parentFile!!.parentFile!!.absolutePath
        // Add the package name to the path assuming that if the name of dataDir does not equal the
        // package name and has a prefix or a suffix to use it.
        return File(externalFilesPath, packageName).absolutePath
    }

    // Uses the context to get own obb data directory
    // e.g. /storage/emulated/0/Android/obb/com.machiav3lli.backup
    // Goes to the parent two times to the leave own directory
    // e.g. /storage/emulated/0/Android/obb
    fun getObbFilesPath(context: Context): String {
        // Uses the context to get own obb data directory
        // e.g. /storage/emulated/0/Android/obb/com.machiav3lli.backup
        // Goes to the parent two times to the leave own directory
        // e.g. /storage/emulated/0/Android/obb
        val obbFilesPath = context.obbDir.parentFile!!.absolutePath
        // Add the package name to the path assuming that if the name of dataDir does not equal the
        // package name and has a prefix or a suffix to use it.
        return File(obbFilesPath, packageName).absolutePath
    }

    /**
     * Returns the list of additional apks (excluding the main apk), if the app is installed
     *
     * @return array of with absolute filepaths pointing to one or more split apks or null if
     * the app is not splitted
     */
    val apkSplits: Array<String>
        get() = appMetaInfo.splitSourceDirs

    val isUpdated: Boolean
        get() = latestBackup?.let { backupHistory.isNotEmpty() && it.backupProperties.versionCode > versionCode }
                ?: false

    val hasApk: Boolean
        get() = backupHistory.any { it.backupProperties.hasApk }

    val hasAppData: Boolean
        get() = backupHistory.any { it.backupProperties.hasAppData }

    val hasExternalData: Boolean
        get() = backupHistory.any { it.backupProperties.hasExternalData }

    val hasDevicesProtectedData: Boolean
        get() = backupHistory.any { it.backupProperties.hasDevicesProtectedData }

    val hasObbData: Boolean
        get() = backupHistory.any { it.backupProperties.hasObbData }

    val dataBytes: Long
        get() = if (appMetaInfo.isSpecial) 0 else storageStats!!.dataBytes

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val appInfo = other as AppInfo
        return packageName == appInfo.packageName
                && appMetaInfo == appInfo.appMetaInfo
                && backupDirUri == appInfo.backupDirUri
                && storageStats == appInfo.storageStats
                && packageInfo == appInfo.packageInfo
                && backupHistory == appInfo.backupHistory
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + packageName.hashCode()
        hash = 31 * hash + appMetaInfo.hashCode()
        hash = 31 * hash + backupDirUri.hashCode()
        hash = 31 * hash + storageStats.hashCode()
        hash = 31 * hash + packageInfo.hashCode()
        hash = 31 * hash + backupHistory.hashCode()
        return hash
    }

    override fun toString(): String {
        return "Schedule{" +
                "packageName=" + packageName +
                ", appMetaInfo=" + appMetaInfo +
                ", appUri=" + backupDirUri +
                ", storageStats=" + storageStats +
                ", packageInfo=" + packageInfo +
                ", backupHistory=" + backupHistory +
                '}'
    }
}