package com.tomato.base.loader;

import com.tomato.base.account.WechatAccountManager;
import com.tomato.wechat.account.WechatAccount;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by wangronghua on 15/8/5.
 */
public class WechatAccountLoader {

    public void load() {

        Accounts accounts = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Accounts.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            accounts = (Accounts) unmarshaller.unmarshal(this.getClass().getResourceAsStream("/accounts.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if(null != accounts) {
            for(WechatAccount account : accounts.getWechatAccountList()) {
                WechatAccountManager.get().addAccount(account);
            }
        }
    }
}

@XmlRootElement
class Accounts {

    @XmlElement(name="account")
    public List<WechatAccount> getWechatAccountList() {
        return wechatAccountList;
    }

    public void setWechatAccountList(List<WechatAccount> wechatAccountList) {
        this.wechatAccountList = wechatAccountList;
    }

    private List<WechatAccount> wechatAccountList;


}
