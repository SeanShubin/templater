package com.seanshubin.generate_from_template.core

import java.nio.file.{FileVisitor, Path}

trait FileSystemIntegration {
  def walkFileTree(start: Path, visitor: FileVisitor[_ >: Path])

  def writeBytes(path: Path, bytes: Array[Byte])

  def createDirectories(path: Path)

  def readAllBytes(path: Path): Array[Byte]

  def isDirectory(path: Path): Boolean

}
