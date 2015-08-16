package com.tomato.web.listener;

import com.tomato.base.account.WechatAccountManager;
import com.tomato.base.loader.ComponentLoader;
import com.tomato.base.loader.WechatAccountLoader;
import com.tomato.product.ProductPlugin;
import com.tomato.rushhour.Test2Plugin;
import com.tomato.wechat.account.WechatAccount;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangronghua on 15/8/16.
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(event.getApplicationContext().getParent() == null) {
            ComponentLoader defaultComponentLoader = new ComponentLoader();
            defaultComponentLoader.load();

            WechatAccountLoader defaultAccountLoader = new WechatAccountLoader();
            defaultAccountLoader.load();

            List<WechatAccount> accountList = WechatAccountManager.get().getAllAccounts();

            for(int index = 0; index < accountList.size(); index ++ ) {
                if(index % 2 == 0) {
                    accountList.get(index).enableComponent(new ProductPlugin());
                } else {
                    accountList.get(index).enableComponent(new Test2Plugin());
                }

            }
        }

    }
}
