package com.funin.imagefinder.data.security

import android.util.Log
import java.security.SecureRandom
import javax.crypto.KeyGenerator

object CipherHelper {

    private const val TAG = "CipherHelper"

    fun generateRandomKey(keySize: Int): ByteArray {
        val aesRandomKey = kotlin.runCatching {
            KeyGenerator.getInstance("AES").run {
                init(keySize, SecureRandom())
                generateKey().encoded
            }
        }
        return aesRandomKey.getOrElse { e ->
            Log.e(TAG, "cannot generateRandomKey so, generate AES random key for BC", e)
            KeyGenerator.getInstance("AES", "BC").run {
                init(keySize, SecureRandom())
                generateKey().encoded
            }
        }
    }
}