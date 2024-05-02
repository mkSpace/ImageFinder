package com.funin.imagefinder.data.datasource.local

import android.content.Context
import android.util.Base64
import androidx.core.content.edit
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.funin.imagefinder.data.datasource.local.dao.DocumentDao
import com.funin.imagefinder.data.datasource.local.entity.Document
import com.funin.imagefinder.data.security.CipherHelper
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    version = 1,
    entities = [
        Document::class,
    ]
)
abstract class ImageFinderDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: ImageFinderDatabase? = null
        private const val DB_NAME = "image-finder-database"

        fun getInstance(context: Context): ImageFinderDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
        }

        private fun buildDatabase(context: Context): ImageFinderDatabase =
            buildEncryptedDatabase(context)

        private fun buildEncryptedDatabase(context: Context): ImageFinderDatabase {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            val sharedPreferences = EncryptedSharedPreferences.create(
                context,
                "app_preferences",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

            var savedDbPass = sharedPreferences.getString("db_passphrase", "")
            if (savedDbPass.isNullOrBlank()) {
                val secretKey = CipherHelper.generateRandomKey(256)
                savedDbPass = String(Base64.encode(secretKey, Base64.DEFAULT))
                sharedPreferences.edit { putString("db_passphrase", savedDbPass) }
                secretKey.fill(0, 0, secretKey.size - 1)
            }
            val dbPassBytes = Base64.decode(savedDbPass, Base64.DEFAULT)
            val dbPass = CharArray(dbPassBytes.size) { i -> dbPassBytes[i].toInt().toChar() }
            val database = Room.databaseBuilder(context, ImageFinderDatabase::class.java, DB_NAME)
                .openHelperFactory(
                    SupportFactory(SQLiteDatabase.getBytes(dbPass))
                )
                .build()
            dbPass.fill('0', 0, dbPass.size - 1)
            return database
        }

        @Suppress("unused")
        private fun buildNonEncryptedDatabase(context: Context): ImageFinderDatabase =
            Room.databaseBuilder(context, ImageFinderDatabase::class.java, DB_NAME)
                .build()
    }

    abstract fun documentDao(): DocumentDao
}