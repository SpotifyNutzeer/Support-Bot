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

  private var configuration: Configuration = Configuration()
  private val objectMapper: ObjectMapper = ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)

  @Throws(ConfigurationException::class, IOException::class)
  fun saveConfig(file: File, configuration: Configuration) {
    println(objectMapper.writer().writeValueAsString(configuration.config))
    objectMapper.writeValue(file, configuration.config)
  }

  @Throws(IOException::class)
  fun getConfigFromFile(file: File): Configuration? {

    val map = objectMapper.readValue(file,
      MutableMap::class.java)

    map.tryCast<MutableMap<String, Any>> {
      return Configuration(this)
    }

    return null

  }

  private inline fun <reified T> Any?.tryCast(block: T.() -> Unit) {
    if (this is T) {
      block()
    }
  }
}