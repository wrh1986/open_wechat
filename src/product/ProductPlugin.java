package product;

import com.tomato.base.component.AbstractComponent;

/**
 * Created by wangronghua on 15/8/2.
 */
public class ProductPlugin extends AbstractComponent {

    @Override
    public String getID() {
        return "TEST000001";
    }

    @Override
    public String getUniqueName() {
        return "商品";
    }

    @Override
    public void init() {
        super.addConsumer(new TestTextMessageConsumer().setComponent(this));
    }

}
