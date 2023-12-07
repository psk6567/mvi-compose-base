package kr.co.psk.common

import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AES256Cipher {
    private var ivBytes = byteArrayOf(0x02, 0x05, 0x08, 0x01, 0x22, 0x33, 0x14, 0x26, 0x41, 0x23, 0x49, 0x50, 0x19, 0x02, 0x05, 0x04)
    private var secretKey = BuildConfig.SECRET_KEY

    //AES256 암호화
    fun encode(input: String): String {
        if (input.isEmpty()) return input
        val textBytes = input.toByteArray(StandardCharsets.UTF_8)
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(secretKey.toByteArray(StandardCharsets.UTF_8), "AES")
        var cipher: Cipher? = null
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
        return Base64.encodeToString(cipher.doFinal(textBytes), 0)
    }

    //AES256 복호화
    fun decode(input: String): String {
        if (input.isEmpty()) return input
        val textBytes = Base64.decode(input, 0)
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(secretKey.toByteArray(StandardCharsets.UTF_8), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
        return String(cipher.doFinal(textBytes), StandardCharsets.UTF_8)
    }
}