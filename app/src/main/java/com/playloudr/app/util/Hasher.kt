package com.playloudr.app.util

import org.mindrot.jbcrypt.BCrypt

object Hasher {

  fun hash(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
  }

  fun compare(password: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(password, hashedPassword)
  }
}