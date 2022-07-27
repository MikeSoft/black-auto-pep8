package mike.learning.plugintest.actions

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ScriptRunnerUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VfsUtil
import java.nio.charset.Charset


class HelloWorldAction : AnAction() {

    fun blackStyleExecute(path: String) {
        val generalCommandLine = GeneralCommandLine("black ${path}")
        generalCommandLine.charset = Charset.forName("UTF-8")

//        val processHandler: ProcessHandler = OSProcessHandler(generalCommandLine)



    }

    override fun actionPerformed(e: AnActionEvent) {
        println("Hello World!")

        var psiFile = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE)
        val path: String? = psiFile?.canonicalPath
        if (path != null && path.contains(".py")) {
//            blackStyleExecute(path)
            var out = ScriptRunnerUtil.getProcessOutput(GeneralCommandLine("black", path))

//            val project: Project = ProjectManager.getInstance().openProjects[0]

            VfsUtil.markDirtyAndRefresh(true, true, true, psiFile);
        }

//        Messages.showInfoMessage(path, "Hello Title info!")
    }

}