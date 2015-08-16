import com.tomato.base.account.WechatAccountManager;
import com.tomato.base.loader.WechatAccountLoader;
import com.tomato.wechat.account.WechatAccount;
import com.tomato.wechat.menu.MenuUtils;
import com.tomato.wechat.menu.WechatButton;
import com.tomato.wechat.menu.WechatMenu;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 15/8/16.
 */
public class TestSendMenu {

    public static void main(String[] args) throws UnsupportedEncodingException {
        WechatAccountLoader loader = new WechatAccountLoader();
        loader.load();

        List<WechatAccount> wechatAccounts = WechatAccountManager.get().getAllAccounts();

        WechatMenu menu = new WechatMenu();
        List<WechatButton> buttons = new ArrayList<>();

        WechatButton button1 = new WechatButton();
        button1.setName("测试1");
        button1.setType("view");

        buttons.add(button1);
        menu.setButton(buttons);

        for(WechatAccount account : wechatAccounts) {
            button1.setUrl(MenuUtils.getOAuthUrl(account, "/wechatweb/test"));
            account.createMenu(menu);
        }

    }
}
