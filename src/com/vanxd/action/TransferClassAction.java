package com.vanxd.action;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.TitlePanel;
import com.intellij.psi.PsiFile;
import com.vanxd.generator.ConfigurationHolder;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将指定class文件使用pscp传输
 *
 * @author wyd
 */
public class TransferClassAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        if (StringUtils.isEmpty(ConfigurationHolder.PSCP_CMD_FIELD)) {
            return;
        }
        PsiFile actionPsiFile = anActionEvent.getData(LangDataKeys.PSI_FILE);
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
            String msg = getResponse(exec);
            hint(anActionEvent, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResponse(Process exec) throws IOException {
        InputStream inputStream = exec.getInputStream();
        // 阻塞等待
        while (inputStream.available() < 1) {}
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        inputStream.read(bytes);
        return new String(bytes);
    }

    private void hint(AnActionEvent anActionEvent, String msg) {
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        JComponent jComponent = new TitlePanel("传输提示", msg);
        HintManager.getInstance().showInformationHint(editor, jComponent);
    }
}