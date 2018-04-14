package com.vanxd.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiFile;
import com.vanxd.generator.ConfigurationHolder;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 * 将指定class文件使用pscp传输
 *
 * @author wyd
 */
public class TransferClassAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        if (StringUtils.isEmpty(ConfigurationHolder.PSCP_CMD_FIELD)) {
            return;
        }
        PsiFile actionPsiFile = e.getData(LangDataKeys.PSI_FILE);
        String fileName = actionPsiFile.getName();
        if (!fileName.contains(".java")) {
            return;
        }
        String className = fileName.replace(".java", ".class");
        String filePath = actionPsiFile.getContainingDirectory().getVirtualFile().getPath();
        String targetDirectoryPath = filePath.replace("src/main/java", "target/classes");
        String targetClassPath = targetDirectoryPath + "/" + className;
        String targetCmd = String.format(ConfigurationHolder.PSCP_CMD_FIELD, targetClassPath);
        try {
            Process exec = Runtime.getRuntime().exec(targetCmd);
            byte[] bytes = new byte[2048];
            exec.getInputStream().read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
