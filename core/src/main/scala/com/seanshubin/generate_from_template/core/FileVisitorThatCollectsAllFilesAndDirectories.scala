package com.seanshubin.generate_from_template.core

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, FileVisitor, Path}

import scala.collection.mutable.ArrayBuffer

class FileVisitorThatCollectsAllFilesAndDirectories(ignoreDirectoryNames: Seq[Path], ignoreFileNamePatterns: Seq[String]) extends FileVisitor[Path] {
  private val filesVisitedBuffer = new ArrayBuffer[Path]

  def filesVisited: Seq[Path] = filesVisitedBuffer

  override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
    if (ignoreDirectoryNames.contains(dir.getFileName)) {
      FileVisitResult.SKIP_SUBTREE
    } else {
      filesVisitedBuffer.append(dir)
      FileVisitResult.CONTINUE
    }
  }

  override def visitFileFailed(file: Path, exc: IOException): FileVisitResult = ???

  override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
    if (ignoreFileNamePatterns.exists(file.getFileName.toString.matches)) {
      FileVisitResult.CONTINUE
    } else {
      filesVisitedBuffer.append(file)
      FileVisitResult.CONTINUE
    }
  }

  override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
    FileVisitResult.CONTINUE
  }
}
