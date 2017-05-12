package com.vanxd.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

import java.io.File;
import java.io.IOException;

/**
 * 打开java文件编译后所在的文件夹，目前只支持windows
 *
 * @author wyd on 2017/5/12.
 */
public class OpenClassDirectoryAction extends AnAction {
    private final static String classesPath = "/target/classes";

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiFile actionPsiFile = e.getData(LangDataKeys.PSI_FILE);
        String fileName = actionPsiFile.getName();
        if (fileName.indexOf(".java") == -1) {
            return;
        }
        Project project = e.getProject();
        String projectPath = project.getBaseDir().getPath();
        String filePath = actionPsiFile.getContainingDirectory().getVirtualFile().getPath();
        String clazzPath = projectPath + this.classesPath;
        String packagePath = filePath.replace(projectPath + "/src/main/java", "");
        String targetDirectoryPath = clazzPath + packagePath;
        try {
            targetDirectoryPath = targetDirectoryPath.replace("/", File.separator);
            Runtime.getRuntime().exec("explorer " + targetDirectoryPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}