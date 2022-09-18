package dev.spotifynutzer.supportbot.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.File
import java.io.IOException

/**
 * @author SpotifyNutzer
 * Discord: Paul Weber#1234
 * GitHub: https://github.com/SpotifyNutzeer
 */
object ConfigProvider {

  private val objectMapper: ObjectMapper = ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)

  @Throws(ConfigurationException::class, IOException::class)
  fun saveConfig(file: File, configuration: MutableMap<String, Any>) {
    objectMapper.writeValue(file, configuration)
  }

  @Throws(IOException::class)
  fun getConfigFromFile(file: File): MutableMap<String, Any>? {

    val map = objectMapper.readValue(file,
      MutableMap::class.java)

    map.tryCast<MutableMap<String, Any>> {
      return this
    }

    return null

  }

  private inline fun <reified T> Any?.tryCast(block: T.() -> Unit) {
    if (this is T) {
      block()
    }
  }
}