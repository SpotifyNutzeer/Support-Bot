package dev.spotifynutzer.supportbot.configuration

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * @author SpotifyNutzer
 * Discord: Paul Weber#1234
 * GitHub: https://github.com/SpotifyNutzeer
 */
class Configuration {

  var config: MutableMap<String, Any>

  constructor() {
    config = HashMap()
  }

  constructor(configuration: MutableMap<String, Any>) {
    config = configuration
  }

  fun containsKey(key: String): Boolean = config.containsKey(key)

  fun size(): Int = config.size

  fun containsValue(value: Any) = config.containsValue(value)

  operator fun get(key: String): Any? = config[key]

  operator fun set(key: String, value: Any) {
    config[key] = value
  }

  fun getOrDefault(key: String, defaultValue: Any): Any = config.getOrDefault(key, defaultValue)

  @JsonIgnore
  fun isEmpty(): Boolean = config.isEmpty()

}

