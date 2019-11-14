package com.vanxd.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

import static com.vanxd.generator.ConfigurationHolder.PSCP_CMD_FIELD;

/**
 * @author wyd on 2017/3/4.
 */
@State(name = "LightSpeedSettings", storages = @Storage(value = "other", file = "$APP_CONFIG$/LightSpeed.xml"))
public class LightSpeedSetting implements PersistentStateComponent<Element> {

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("LightSpeedSettings");
        element.setAttribute("PSCP_CMD_FIELD", PSCP_CMD_FIELD);
        return element;
    }

    @Override
    public void loadState(Element element) {
        PSCP_CMD_FIELD = null == element.getAttributeValue("PSCP_CMD_FIELD") ? "" : element.getAttributeValue("PSCP_CMD_FIELD");
    }
}
