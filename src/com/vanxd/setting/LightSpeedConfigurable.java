package com.vanxd.setting;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static com.vanxd.generator.ConfigurationHolder.PSCP_CMD_FIELD;

/**
 * @author wyd on 2017/3/4.
 */
public class LightSpeedConfigurable implements SearchableConfigurable {
    private static LightSpeedConfigForm configForm;
    @NotNull
    @Override
    public String getId() {
        return "lightspeed";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (null == configForm) {
            configForm = new LightSpeedConfigForm();
        }
        // 虽然LightSpeedSetting实现了持久化接口，也在XML中注册了，但就是要有这一行。
        ServiceManager.getService(LightSpeedSetting.class);
        return configForm.mainPanel;
    }

    @Override
    public boolean isModified() {
        return  !PSCP_CMD_FIELD.equals(configForm.pscpCmdField.getText());
    }

    @Override
    public void apply() {
        PSCP_CMD_FIELD = configForm.pscpCmdField.getText();
    }

    @Override
    public void reset() {
        configForm.pscpCmdField.setText(PSCP_CMD_FIELD);
    }

    @Override
    public void disposeUIResources() {

    }

    @Nls
    @Override
    public String getDisplayName() {
        return "LightSpeed";
    }
    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }
}
