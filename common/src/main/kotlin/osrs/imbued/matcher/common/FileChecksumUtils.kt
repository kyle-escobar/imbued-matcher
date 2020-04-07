package osrs.imbued.matcher.common

import java.io.File
import java.math.BigInteger
import java.nio.file.Files
import java.security.MessageDigest

/**
 * A static utility class for calculating a file's MD5 checksum.
 */
fun File.getChecksum(): String {
    val md = MessageDigest.getInstance("MD5")
    Files.newInputStream(this.toPath()).use { reader ->
        return BigInteger(1, md.digest(reader.readAllBytes())).toString(16).padStart(32, '0')
    }
}