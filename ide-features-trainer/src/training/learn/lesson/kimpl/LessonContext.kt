// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package training.learn.lesson.kimpl

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.CalledInAwt
import training.commands.kotlin.TaskContext

abstract class LessonContext(val lesson: KLesson, val editor: Editor, val project: Project) {
  /**
   * Start a new task in a lesson context
   */
  @CalledInAwt
  abstract fun task(taskContent: TaskContext.() -> Unit)

  /** Describe a simple task: just one action required */
  fun actionTask(action: String, getText: TaskContext.(action: String) -> String) {
    task {
      text(getText(action))
      trigger(action)
      test { actions(action) }
    }
  }

  /**
   * Just shortcut to write action name once
   * @see task
   */
  fun task(action: String, taskContent: TaskContext.(action: String) -> Unit) {
    task {
      taskContent(action)
    }
  }

  abstract fun caret(offset: Int)

    /** NOTE:  [line] and [column] starts from 1 not from zero. So these parameters should be same as in editors. */
    abstract fun caret(line: Int, column: Int)

  abstract fun caret(text: String)

  abstract fun caret(position: LessonSamplePosition)

  /**
   * There will not be any freeze in GUI thread.
   * The continue of the script will be scheduled with the [delayMillis]
   */
  abstract fun waitBeforeContinue(delayMillis: Int)

  abstract fun prepareSample(sample: LessonSample)
}
