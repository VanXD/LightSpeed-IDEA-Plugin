package com.vanxd.action;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.TitlePanel;
import com.intellij.psi.PsiFile;
import com.intellij.ui.awt.RelativePoint;
import com.vanxd.generator.ConfigurationHolder;
import com.vanxd.setting.LightSpeedSetting;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将指定class文件使用pscp传输
 *
 * @author wyd
 */
public class TransferClassAction extends AnAction {
    // 哎,这东西是真的坑,项目启动的时候为啥不自动加载已有配置
    LightSpeedSetting service = ServiceManager.getService(LightSpeedSetting.class);

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        if (StringUtils.isEmpty(ConfigurationHolder.PSCP_CMD_FIELD)) {
            return;
        }
        PsiFile actionPsiFile = anActionEvent.getData(LangDataKeys.PSI_FILE);
        String fileName = actionPsiFile.getName();
        String className = fileName.replace(".java", ".class");
        String filePath = actionPsiFile.getContainingDirectory().getVirtualFile().getPath();
        if (fileName.contains(".java")) {
            filePath = filePath.replace("src/main/java", "target/classes");
        }
        String finalFilePath = filePath + "/" + className;
        String targetCmd = String.format(ConfigurationHolder.PSCP_CMD_FIELD, finalFilePath);
        try {
            Process exec = Runtime.getRuntime().exec(targetCmd);
            String msg = getResponse(exec);
            hint(msg);
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

    private void hint(String msg) {
        RelativePoint relativePoint = new RelativePoint(new Point(9999, 9999));
        JComponent jComponent = new TitlePanel("传输提示", msg);
        HintManager.getInstance().showHint(jComponent, relativePoint, 0, 2000);
    }
}