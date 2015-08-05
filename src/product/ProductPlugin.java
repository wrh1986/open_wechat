package product;

import com.tomato.base.component.AbstractComponent;
import com.tomato.base.component.Component;

/**
 * Created by wangronghua on 15/8/2.
 */
public class ProductPlugin extends AbstractComponent {
    @Override
    public String getUniqueName() {
        return "商品";
    }

    @Override
    public void init() {
        super.addConsumer(new TestTextMessageConsumer());
    }

    @Override
    public String isEnabled() {
        return null;
    }

    @Override
    public String enable() {
        return null;
    }

    @Override
    public String disable() {
        return null;
    }
}
