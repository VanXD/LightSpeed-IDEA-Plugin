package com.vanxd.action;

/**
 * 直接生成Service接口、ServiceImpl和控制器
 * 使用模板：fileTemplates.j2ee.service
 *
 * @author wyd on 2016/12/16.
 */
public class GenerateVanSuitAction extends GenerateAction {

    @Override
    protected String[] getTemplates() {
        return new String[]{"Vanxd ServiceInterface", "Vanxd ServiceImpl", "Vanxd Controller"};
    }
}
