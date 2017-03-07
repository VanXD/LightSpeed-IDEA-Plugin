package com.vanxd.templats;

import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;
import com.intellij.openapi.ui.Messages;

/**
 * @author wyd on 2016/12/16.
 */
public class VanxdFileTemplateGroupDescriptorFactory  implements FileTemplateGroupDescriptorFactory {

    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor("Vanxd", Messages.getInformationIcon());
        return group;
    }
}
